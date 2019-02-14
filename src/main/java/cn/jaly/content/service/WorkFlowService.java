package cn.jaly.content.service;

import java.util.List;

import cn.jaly.content.dao.WorkFlowMapper;
import cn.jaly.content.entity.WorkFlow;
import cn.jaly.content.entity.WorkFlowExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkFlowService {

	@Autowired
	private WorkFlowMapper workFlowMapper;
	
	@Transactional(readOnly=true)
	public List<WorkFlow> getBySiteId(Integer siteId){
		WorkFlowExample example = new WorkFlowExample();
		WorkFlowExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		return workFlowMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public WorkFlow getById(Integer id){
		return workFlowMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(WorkFlow workFlow){
		if(workFlow.getId() == null){
			workFlowMapper.insert(workFlow);
		} else {
			workFlowMapper.updateByPrimaryKeySelective(workFlow);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		workFlowMapper.deleteByPrimaryKey(id);
	}	
	
}
