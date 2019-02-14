package cn.jaly.content.service;

import cn.jaly.content.dao.CategoryMapper;
import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.dao.PictureDataMapper;
import cn.jaly.content.dao.PictureMapper;
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
public class PictureService {

	@Autowired
	private PictureMapper pictureMapper;

	@Autowired
	private PictureDataMapper pictureDataMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly=true)
	public List<Picture> queryForList(String keyword, String stm, String edm,
									  Integer categoryId, Byte status, String order){
		Date startTime = DateTimeUtils.parseSimple(stm);
		Date endTime = DateTimeUtils.parseSimple(edm);
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		return pictureMapper.queryForList(keyword, startTime, endTime, categoryId, status, order);
	}

    @Transactional(readOnly=true)
	public Picture getByIdWithData(Integer id){
		return pictureMapper.selectByPrimaryKeyWithData(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		PictureExample example = new PictureExample();
		PictureExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<Picture> pictures = pictureMapper.selectByExample(example);
		if(pictures == null || pictures.isEmpty()){
			return null;
		} else {
			return pictures.get(0).getId();
		}
	}

	@Transactional
	public void save(Picture picture){
		Integer categoryId = picture.getCategoryId();

		picture.setUpdateTime(new Date());
		PictureData pictureData = picture.getPictureData();
		if(pictureData.getCopyFromId()!=null && pictureData.getCopyFromId() == 0){
			pictureData.setCopyFromId(null);
		}
		if(picture.getId() == null){
			pictureData.setHits(0);
			pictureMapper.insert(picture);
			pictureData.setPictureId(picture.getId());
			pictureDataMapper.insert(pictureData);
		} else{
			pictureMapper.updateByPrimaryKey(picture);
			pictureDataMapper.updateByPrimaryKeyWithBLOBs(pictureData);
		}

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer id){
		Picture picture = pictureMapper.selectByPrimaryKey(id);
		Integer categoryId = picture.getCategoryId();

		pictureDataMapper.deleteByPrimaryKey(id);
		pictureMapper.deleteByPrimaryKey(id);

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer[] ids) {
		Picture picture = pictureMapper.selectByPrimaryKey(ids[0]);
		Integer categoryId = picture.getCategoryId();

		List<Integer> aids = Arrays.asList(ids);
		PictureDataExample pictureDataExample = new PictureDataExample();
		PictureDataExample.Criteria criteria = pictureDataExample.createCriteria();
		criteria.andPictureIdIn(aids);
		pictureDataMapper.deleteByExample(pictureDataExample);

		PictureExample pictureExample = new PictureExample();
		PictureExample.Criteria criteria1 = pictureExample.createCriteria();
		criteria1.andIdIn(aids);
		pictureMapper.deleteByExample(pictureExample);

		upateModelDataCount(categoryId);
	}

	/**
	 * 更新模型数据，每次内容变动的时候触发
	 * @param categoryId
	 */
	private void upateModelDataCount(Integer categoryId){
		Category category = categoryMapper.selectByPrimaryKeyWithModel(categoryId);
		modelMapper.updateDataCount(category.getModel());
	}
}