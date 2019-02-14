package cn.jaly.link.service;

import cn.jaly.link.dao.FriendlyLinkTypeMapper;
import cn.jaly.link.entity.FriendlyLinkType;
import cn.jaly.link.entity.FriendlyLinkTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendlyLinkTypeService {

	@Autowired
	private FriendlyLinkTypeMapper friendlyLinkTypeMapper;
	
	@Transactional(readOnly=true)
	public List<FriendlyLinkType> getBySite(Integer siteId){
		FriendlyLinkTypeExample example = new FriendlyLinkTypeExample();
		FriendlyLinkTypeExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		return friendlyLinkTypeMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public FriendlyLinkType getById(Integer id){
		return friendlyLinkTypeMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(FriendlyLinkType type){
		if(type.getId() == null){
			friendlyLinkTypeMapper.insert(type);
		} else {
			friendlyLinkTypeMapper.updateByPrimaryKeySelective(type);
		}
	}
	
	@Transactional
	public void delete(Integer id){ 
		friendlyLinkTypeMapper.deleteByPrimaryKey(id);
	}
}
