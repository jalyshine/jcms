package cn.jaly.admin.service;

import cn.jaly.admin.dao.BadWordMapper;
import cn.jaly.admin.entity.BadWord;
import cn.jaly.admin.entity.BadWordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BadWordService {

	@Autowired
	private BadWordMapper badWordMapper;
	
	@Transactional(readOnly=true)
	public List<BadWord> getAll(String keyword){
		BadWordExample example = new BadWordExample();
		example.setOrderByClause("list_order asc");
		if(keyword != null && !"".equals(keyword)){
			BadWordExample.Criteria criteria = example.createCriteria();
			criteria.andWordLike("%" + keyword + "%");
		}
		return badWordMapper.selectByExample(example);
	}

	@Transactional(readOnly=true)
	public BadWord getById(Integer id){
		return badWordMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(BadWord badWord){
		badWord.setUpdateTime(new Date());
		if(badWord.getId() == null){
			badWordMapper.insert(badWord);
		} else {
			badWordMapper.updateByPrimaryKeySelective(badWord);
		}
	}
	
	@Transactional
	public void delete(Integer id){ 
		badWordMapper.deleteByPrimaryKey(id);
	}
}
