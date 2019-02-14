package cn.jaly.vote.service;

import cn.jaly.vote.dao.VoteMapper;
import cn.jaly.vote.entity.Vote;
import cn.jaly.vote.entity.VoteExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class VoteService {

	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional(readOnly=true)
	public List<Vote> queryForList(Integer siteId, String keyword, String order){
		VoteExample example = new VoteExample();
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		example.setOrderByClause(order);
		VoteExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		if(keyword != null && !"".equals(keyword)){
			criteria.andTitleLike("%" + keyword + "%");
		}
		return voteMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public Vote getById(Integer id){
		return voteMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(Vote vote){
		vote.setUpdateTime(new Date());
		if(vote.getId() == null){
			voteMapper.insert(vote);
		} else {
			voteMapper.updateByPrimaryKeySelective(vote);
		}
	}
	
	@Transactional
	public void delete(Integer id){ 
		voteMapper.deleteByPrimaryKey(id);
	}
}
