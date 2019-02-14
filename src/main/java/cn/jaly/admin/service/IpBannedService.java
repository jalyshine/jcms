package cn.jaly.admin.service;

import cn.jaly.admin.dao.IpBannedMapper;
import cn.jaly.admin.entity.IpBanned;
import cn.jaly.admin.entity.IpBannedExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IpBannedService {

	@Autowired
	private IpBannedMapper ipBannedMapper;
	
	@Transactional(readOnly=true)
	public List<IpBanned> getAll(String keyword){
		if(keyword == null || "".equals(keyword)){
			return ipBannedMapper.selectByExample(null);
		}
		IpBannedExample example = new IpBannedExample();
		IpBannedExample.Criteria criteria = example.createCriteria();
		criteria.andIpLike("%" + keyword + "%");
		return ipBannedMapper.selectByExample(example);
	}
	
	@Transactional(readOnly=true)
	public IpBanned getById(Integer id){
		return ipBannedMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public IpBanned getByIp(String ip){
		IpBannedExample example = new IpBannedExample();
		IpBannedExample.Criteria criteria = example.createCriteria();
		criteria.andIpEqualTo(ip);
		List<IpBanned> ipBanneds = ipBannedMapper.selectByExample(example);
		if(ipBanneds == null || ipBanneds.isEmpty()){
			return null;
		}
		return ipBanneds.get(0);
	}

	@Transactional
	public void save(IpBanned ipBanned){
		if(ipBanned.getId() == null){
			ipBannedMapper.insert(ipBanned);
		} else {
			ipBannedMapper.updateByPrimaryKeySelective(ipBanned);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		ipBannedMapper.deleteByPrimaryKey(id);
	}
}
