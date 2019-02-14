package cn.jaly.content.service;

import cn.jaly.content.dao.CopyFromMapper;
import cn.jaly.content.entity.CopyFrom;
import cn.jaly.content.entity.CopyFromExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CopyFromService {

	@Autowired
	private CopyFromMapper copyFromMapper;
	
	@Transactional(readOnly=true)
	public List<CopyFrom> getBySiteId(Integer siteId, String kwd){
		CopyFromExample example = new CopyFromExample();
		example.setOrderByClause("list_order asc");
		CopyFromExample.Criteria criteria = example.createCriteria();
		criteria.andSiteIdEqualTo(siteId);
		if(kwd != null && !"".equals(kwd)){
			criteria.andNameLike("%" + kwd + "%");
		}
		return copyFromMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public CopyFrom getById(Integer id){
		return copyFromMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(CopyFrom copyFrom){
		if(copyFrom.getId() == null){
			copyFromMapper.insert(copyFrom);
		} else {
			copyFromMapper.updateByPrimaryKeySelective(copyFrom);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		copyFromMapper.deleteByPrimaryKey(id);
	}

}
