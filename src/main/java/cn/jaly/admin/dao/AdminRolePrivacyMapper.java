package cn.jaly.admin.dao;

import cn.jaly.admin.entity.AdminRolePrivacy;
import cn.jaly.admin.entity.AdminRolePrivacyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRolePrivacyMapper {
    long countByExample(AdminRolePrivacyExample example);

    int deleteByExample(AdminRolePrivacyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminRolePrivacy record);

    int insertSelective(AdminRolePrivacy record);

    List<AdminRolePrivacy> selectByExample(AdminRolePrivacyExample example);

    AdminRolePrivacy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminRolePrivacy record, @Param("example") AdminRolePrivacyExample example);

    int updateByExample(@Param("record") AdminRolePrivacy record, @Param("example") AdminRolePrivacyExample example);

    int updateByPrimaryKeySelective(AdminRolePrivacy record);

    int updateByPrimaryKey(AdminRolePrivacy record);

    // 自定义
    void batchInsert(@Param("records") List<AdminRolePrivacy> records);

    List<AdminRolePrivacy> selectByExampleWithRole(AdminRolePrivacyExample example);

    List<AdminRolePrivacy> selectAsMenu(@Param("module") String module,
                                        @Param("entity") String entity,
                                        @Param("action") String action,
                                        @Param("siteId") Integer siteId,
                                        @Param("roleId") Integer roleId);

    /**
     * 或取某个角色拥有权限的站点ID
     *
     * @param roleId
     * @return
     */
    List<Integer> selectOwnSiteIdsByRoleId(Integer roleId);
}