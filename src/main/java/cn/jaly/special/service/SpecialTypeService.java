package cn.jaly.special.service;

import cn.jaly.special.dao.SpecialTypeMapper;
import cn.jaly.special.entity.SpecialType;
import cn.jaly.special.entity.SpecialTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecialTypeService {

	@Autowired
	private SpecialTypeMapper specialTypeMapper;

	@Transactional(readOnly = true)
	public List<SpecialType> getBySpecialId(Integer specialId){
		SpecialTypeExample example = new SpecialTypeExample();
		SpecialTypeExample.Criteria criteria = example.createCriteria();
		criteria.andSpecialIdEqualTo(specialId);
		return specialTypeMapper.selectByExample(example);
	}
	
	@Transactional
	public void delete(Integer id){
		specialTypeMapper.deleteByPrimaryKey(id);
	}
}
