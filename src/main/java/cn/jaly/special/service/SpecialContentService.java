package cn.jaly.special.service;

import cn.jaly.special.dao.SpecialContentDataMapper;
import cn.jaly.special.dao.SpecialContentMapper;
import cn.jaly.special.entity.SpecialContent;
import cn.jaly.special.entity.SpecialContentData;
import cn.jaly.special.entity.SpecialContentDataExample;
import cn.jaly.special.entity.SpecialContentExample;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SpecialContentService {

	@Autowired
	private SpecialContentMapper specialContentMapper;
	
	@Autowired
	private SpecialContentDataMapper specialContentDataMapper;
	
	@Transactional(readOnly=true)
	public List<SpecialContent> queryForList(String keyword, String stm, String edm, Integer specialId, String order){
		Date startTime = DateTimeUtils.parseSimple(stm);
		Date endTime = DateTimeUtils.parseSimple(edm);
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		return specialContentMapper.queryForList(keyword, startTime, endTime, specialId, order);
	}
	
	@Transactional(readOnly=true)
	public SpecialContent getByIdWithData(Integer id){
		return specialContentMapper.selectByPrimaryKeyWithData(id);
	}
	
	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		SpecialContentExample example = new SpecialContentExample();
		SpecialContentExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<SpecialContent> specialContents = specialContentMapper.selectByExample(example);
		if(specialContents == null || specialContents.isEmpty()){
			return null;
		} else {
			return specialContents.get(0).getId();
		}
	}
	
	@Transactional
	public void save(SpecialContent specialContent){
		specialContent.setUpdateTime(new Date());
		if(specialContent.getId() == null){
			specialContentMapper.insert(specialContent);
			if(specialContent.getIsData()){
				SpecialContentData specialContentData = specialContent.getSpecialContentData();
				specialContentData.setHits(0);
				specialContentData.setSpecialContentId(specialContent.getId());
				specialContentDataMapper.insert(specialContentData);
			}
		} else {
			specialContentMapper.updateByPrimaryKey(specialContent);
			if(specialContent.getIsData()){
				SpecialContentData specialContentData = specialContent.getSpecialContentData();
				specialContentDataMapper.updateByPrimaryKeyWithBLOBs(specialContentData);
			}
		}
	}

	@Transactional
	public void delete(Integer id){
		specialContentDataMapper.deleteByPrimaryKey(id);
		specialContentMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	public void delete(Integer[] ids) {
		List<Integer> aids = Arrays.asList(ids);
		SpecialContentDataExample specialContentDataExample = new SpecialContentDataExample();
		SpecialContentDataExample.Criteria criteria1 = specialContentDataExample.createCriteria();
		criteria1.andSpecialContentIdIn(aids);
		specialContentDataMapper.deleteByExample(specialContentDataExample);

		SpecialContentExample specialContentExample = new SpecialContentExample();
		SpecialContentExample.Criteria criteria2 = specialContentExample.createCriteria();
		criteria2.andIdIn(aids);
		specialContentMapper.deleteByExample(specialContentExample);
	}
	 
}
