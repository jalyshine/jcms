package cn.jaly.content.service;

import cn.jaly.content.dao.CategoryMapper;
import cn.jaly.content.dao.DownloadDataMapper;
import cn.jaly.content.dao.DownloadMapper;
import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.entity.*;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DownloadService {

	@Autowired
	private DownloadMapper downloadMapper;

	@Autowired
	private DownloadDataMapper downloadDataMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly=true)
	public List<Download> queryForList(String keyword, String stm, String edm,
									   Integer categoryId, Byte status, String order){
		Date startTime = DateTimeUtils.parseSimple(stm);
		Date endTime = DateTimeUtils.parseSimple(edm);
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		return downloadMapper.queryForList(keyword, startTime, endTime, categoryId, status, order);
	}

    @Transactional(readOnly=true)
	public Download getByIdWithData(Integer id){
		return downloadMapper.selectByPrimaryKeyWithData(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		DownloadExample example = new DownloadExample();
		DownloadExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<Download> downloads = downloadMapper.selectByExample(example);
		if(downloads == null || downloads.isEmpty()){
			return null;
		} else {
			return downloads.get(0).getId();
		}
	}

	@Transactional
	public void save(Download download){
		Integer categoryId = download.getCategoryId();

		download.setUpdateTime(new Date());
		DownloadDataWithBLOBs downloadData = download.getDownloadData();
		if(download.getId() == null){
			downloadData.setHits(0);
			downloadMapper.insert(download);
			downloadData.setDownloadId(download.getId());
			downloadDataMapper.insert(downloadData);
		} else{
			downloadMapper.updateByPrimaryKey(download);
			downloadDataMapper.updateByPrimaryKeyWithBLOBs(downloadData);
		}

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer id){
		Download download = downloadMapper.selectByPrimaryKey(id);
		Integer categoryId = download.getCategoryId();

		downloadDataMapper.deleteByPrimaryKey(id);
		downloadMapper.deleteByPrimaryKey(id);

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer[] ids) {
		Download download = downloadMapper.selectByPrimaryKey(ids[0]);
		Integer categoryId = download.getCategoryId();

		List<Integer> aids = Arrays.asList(ids);
		DownloadDataExample downloadDataExample = new DownloadDataExample();
		DownloadDataExample.Criteria criteria = downloadDataExample.createCriteria();
		criteria.andDownloadIdIn(aids);
		downloadDataMapper.deleteByExample(downloadDataExample);

		DownloadExample downloadExample = new DownloadExample();
		DownloadExample.Criteria criteria1 = downloadExample.createCriteria();
		criteria1.andIdIn(aids);
		downloadMapper.deleteByExample(downloadExample);

		upateModelDataCount(categoryId);
	}

	/**
	 * 更新模型数据，每次内容变动的时候触发
	 * @param categoryId
	 */
	private void upateModelDataCount(Integer categoryId){
		Category category = categoryMapper.selectByPrimaryKeyWithModel(categoryId);
		Model model = category.getModel();
		modelMapper.updateDataCount(model);
	}
}
