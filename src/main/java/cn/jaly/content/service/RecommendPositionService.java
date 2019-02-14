package cn.jaly.content.service;

import java.util.List;

import cn.jaly.content.dao.RecommendPositionMapper;
import cn.jaly.content.entity.RecommendPosition;
import cn.jaly.content.entity.RecommendPositionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecommendPositionService {

	@Autowired
	private RecommendPositionMapper recommendPositionMapper;
	
	@Transactional(readOnly=true)
	public List<RecommendPosition> getBySiteId(Integer siteId, String keyword){
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		return recommendPositionMapper.selectWithModel(siteId, keyword);
	}

	@Transactional(readOnly = true)
	public List<RecommendPosition> getByCategoryId(Integer categoryId){
		RecommendPositionExample example = new RecommendPositionExample();
		example.or().andCategoryIdEqualTo(categoryId);
		example.or().andCategoryIdIsNull();
		List<RecommendPosition> recommendPositions = recommendPositionMapper.selectByExample(example);
		return recommendPositions;
	}

	@Transactional(readOnly=true)
	public RecommendPosition getById(Integer id){
		return recommendPositionMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(RecommendPosition recommendPosition){
		if(recommendPosition.getId() == null){
			recommendPositionMapper.insert(recommendPosition);
		} else {
			recommendPositionMapper.updateByPrimaryKey(recommendPosition);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		recommendPositionMapper.deleteByPrimaryKey(id);
	}
}
