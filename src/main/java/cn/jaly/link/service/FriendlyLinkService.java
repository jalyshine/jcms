package cn.jaly.link.service;

import cn.jaly.link.dao.FriendlyLinkMapper;
import cn.jaly.link.entity.FriendlyLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FriendlyLinkService {

	@Autowired
	private FriendlyLinkMapper friendlyLinkMapper;

	@Transactional(readOnly=true)
	public List<FriendlyLink> queryForList(Integer siteId, String keyword,  String order){
		if(order == null || "".equals(order)) {
			order = "update_time desc";
		}
		return friendlyLinkMapper.queryForList(siteId, keyword, order);
	}
	
	@Transactional(readOnly=true)
	public FriendlyLink getById(Integer id){
		return friendlyLinkMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(FriendlyLink friendlyLink){
		friendlyLink.setUpdateTime(new Date());
		if(friendlyLink.getId() == null){
			friendlyLinkMapper.insert(friendlyLink);
		} else {
			friendlyLinkMapper.updateByPrimaryKeySelective(friendlyLink);
		}
	}
	
	@Transactional
	public void delete(Integer id){ 
		friendlyLinkMapper.deleteByPrimaryKey(id);
	}
	
}
