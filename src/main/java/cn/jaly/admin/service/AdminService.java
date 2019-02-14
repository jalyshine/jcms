package cn.jaly.admin.service;

import cn.jaly.admin.dao.AdminMapper;
import cn.jaly.admin.entity.Admin;
import cn.jaly.admin.entity.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Transactional(readOnly = true)
    public List<Admin> getAllByAdminRoleId(Integer adminRoleId) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if(adminRoleId != null){
            criteria.andAdminRoleIdEqualTo(adminRoleId);
        }
        return adminMapper.selectByExampleWithRole(example);
    }

    @Transactional(readOnly = true)
    public Admin getByIdWithRole(Integer id) {
        return adminMapper.selectByPrimaryKeyWithRole(id);
    }

    @Transactional(readOnly = true)
    public Admin getById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    public Admin getByUserNameWithRole(String userName) {
        return adminMapper.selectByUserNameWithRole(userName);
    }

    @Transactional(readOnly = true)
    public Integer getIdByUserName(String userName) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<Admin> admins = adminMapper.selectByExample(example);
        if(admins == null || admins.isEmpty()){
            return null;
        }
        return admins.get(0).getId();
    }

    @Transactional(readOnly = true)
    public Admin getByUserName(String userName) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<Admin> admins = adminMapper.selectByExampleWithRole(example);
        if(admins.isEmpty()){
            return null;
        }
        return admins.get(0);
    }

    @Transactional
    public void delete(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void save(Admin admin) {
        if(admin.getId() == null){
            adminMapper.insert(admin);
        } else {
            adminMapper.updateByPrimaryKeySelective(admin);
        }
    }

}
