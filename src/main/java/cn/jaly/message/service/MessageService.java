package cn.jaly.message.service;

import cn.jaly.message.dao.MessageMapper;
import cn.jaly.message.entity.Message;
import cn.jaly.message.entity.MessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Transactional(readOnly=true)
	public List<Message> queryForList(Integer siteId, String keyword, String order){
		MessageExample example = new MessageExample();
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		example.setOrderByClause(order);
		MessageExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		if(keyword != null && !"".equals(keyword)){
			criteria.andTitleLike("%" + keyword + "%");
		}
		return messageMapper.selectByExample(example);
	}

	@Transactional(readOnly=true)
	public Message getById(Integer id){
		return messageMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Message message){
		message.setUpdateTime(new Date());
		if(message.getId() == null){
			messageMapper.insert(message);
		} else {
			messageMapper.updateByPrimaryKeySelective(message);
		}
	}

	@Transactional
	public void delete(Integer id){
		messageMapper.deleteByPrimaryKey(id);
	}
}
