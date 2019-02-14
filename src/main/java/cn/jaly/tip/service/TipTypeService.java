package cn.jaly.tip.service;

import cn.jaly.tip.dao.TipTypeMapper;
import cn.jaly.tip.entity.TipType;
import cn.jaly.tip.entity.TipTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipTypeService {

	@Autowired
	private TipTypeMapper tipTypeMapper;
	
	@Transactional(readOnly=true)
	public List<TipType> getBySite(Integer siteId){
		TipTypeExample example = new TipTypeExample();
		TipTypeExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		return tipTypeMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public TipType getById(Integer id){
		return tipTypeMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(TipType type){
		if(type.getId() == null){
			tipTypeMapper.insert(type);
		} else {
			tipTypeMapper.updateByPrimaryKeySelective(type);
		}
	}
	
	@Transactional
	public void delete(Integer id){ 
		tipTypeMapper.deleteByPrimaryKey(id);
	}
}
