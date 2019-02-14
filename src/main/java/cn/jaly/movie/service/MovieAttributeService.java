package cn.jaly.movie.service;

import cn.jaly.movie.dao.MovieAttributeMapper;
import cn.jaly.movie.entity.MovieAttribute;
import cn.jaly.movie.entity.MovieAttributeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieAttributeService {

	@Autowired
	private MovieAttributeMapper movieAttributeMapper;
	
	@Transactional(readOnly=true)
	public List<MovieAttribute> getByAttrName(String attrName){
		MovieAttributeExample example = new MovieAttributeExample();
		MovieAttributeExample.Criteria criteria = example.createCriteria();
		criteria.andAttrNameEqualTo(attrName);
		return movieAttributeMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public MovieAttribute getById(Integer id){
		return movieAttributeMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(MovieAttribute movieAttribute){
		if(movieAttribute.getId() == null){
			movieAttributeMapper.insert(movieAttribute);
		} else {
			movieAttributeMapper.updateByPrimaryKeySelective(movieAttribute);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		movieAttributeMapper.deleteByPrimaryKey(id);
	}
}
