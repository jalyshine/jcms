package cn.jaly.content.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.entity.Model;
import cn.jaly.content.entity.ModelExample;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModelService {

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly=true)
	public List<Model> getBySiteId(Integer siteId){
		ModelExample example = new ModelExample();
		ModelExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		return modelMapper.selectByExample(example);
	}

	/**
	 * 给定表名，按条件查找内容。用于专题导入内容
	 * @param tableName
	 * @param keyword
	 * @param stm
	 * @param edm
	 * @param categoryId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryContentForList(String tableName, String keyword,
														 String stm, String edm, Integer categoryId){
		Date startTime = DateTimeUtils.parseSimple(stm);
		Date endTime = DateTimeUtils.parseSimple(edm);
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		return modelMapper.queryContentForList(tableName, keyword, startTime, endTime, categoryId);
	}

	@Transactional(readOnly=true)
	public Model getById(Integer id){
		return modelMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Model model){
		model.setUpdateTime(new Date());
		if(model.getId() == null){
			model.setItemCount(0);
			modelMapper.insert(model);
		} else {
			modelMapper.updateByPrimaryKeySelective(model);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		modelMapper.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public Integer getContentHits(String tableName, Integer id){
		return modelMapper.queryContentHit(tableName, id);
	}

	@Transactional
	public void updateContentHits(String tableName, Integer id, Integer times){
		modelMapper.updateContentHit(tableName, id, times);
	}
	
}
