package cn.jaly.admin.service;


import cn.jaly.admin.dao.AdminRoleMapper;

import cn.jaly.admin.entity.AdminRole;
import cn.jaly.admin.entity.AdminRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminRoleService {

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Transactional(readOnly=true)
	public List<AdminRole> getAll(){
		return adminRoleMapper.selectByExample(null);
	}

	@Transactional(readOnly=true)
	public List<AdminRole> getAllEnabled(){
		AdminRoleExample example = new AdminRoleExample();
		AdminRoleExample.Criteria criteria = example.createCriteria();
		criteria.andDisabledEqualTo(false);
		return adminRoleMapper.selectByExample(example);
	}

	@Transactional(readOnly=true)
	public AdminRole getById(Integer id){
		return adminRoleMapper.selectByPrimaryKey(id);
	}
	
	@Transactional(readOnly=true)
	public Integer getIdByName(String name){
		AdminRoleExample example = new AdminRoleExample();
		AdminRoleExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<AdminRole> adminRoles = adminRoleMapper.selectByExample(example);
		if(adminRoles == null || adminRoles.isEmpty()){
			return null;
		}
		return adminRoles.get(0).getId();
	}

	@Transactional
	public void delete(Integer id){
		adminRoleMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	public void save(AdminRole adminRole){
		if(adminRole.getId() == null){
			adminRoleMapper.insert(adminRole);
		} else {
			adminRoleMapper.updateByPrimaryKeySelective(adminRole);
		}
	}
	
}
