package cn.jaly.message.service;

import cn.jaly.message.dao.MessageGroupMapper;
import cn.jaly.message.entity.MessageGroup;
import cn.jaly.message.entity.MessageGroupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MessageGroupService {

	@Autowired
	private MessageGroupMapper messageGroupMapper;

	@Transactional(readOnly=true)
	public List<MessageGroup> queryForList(Integer siteId, String keyword, String order){
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		if(keyword != null && !keyword.isEmpty()){
			keyword = "%" + keyword + "%";
		}
		return messageGroupMapper.queryForList(siteId, keyword, order);
	}
	
	@Transactional(readOnly=true)
	public MessageGroup getById(Integer id){
		return messageGroupMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(MessageGroup messageGroup){
		messageGroup.setUpdateTime(new Date());
		if(messageGroup.getId() == null){
			messageGroupMapper.insert(messageGroup);
		} else {
			messageGroupMapper.updateByPrimaryKeySelective(messageGroup);
		}
	}
	
	@Transactional
	public void delete(Integer id){ 
		messageGroupMapper.deleteByPrimaryKey(id);
	}
}
