package cn.jaly.admin.dao;

import cn.jaly.admin.entity.Admin;
import cn.jaly.admin.entity.AdminExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    // 自定义
    List<Admin> selectByExampleWithRole(AdminExample example);

    Admin selectByPrimaryKeyWithRole(Integer id);

    Admin selectByUserNameWithRole(String userName);
}