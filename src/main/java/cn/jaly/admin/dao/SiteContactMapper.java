package cn.jaly.admin.dao;

import cn.jaly.admin.entity.SiteContact;
import cn.jaly.admin.entity.SiteContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteContactMapper {
    long countByExample(SiteContactExample example);

    int deleteByExample(SiteContactExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SiteContact record);

    int insertSelective(SiteContact record);

    List<SiteContact> selectByExample(SiteContactExample example);

    SiteContact selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SiteContact record, @Param("example") SiteContactExample example);

    int updateByExample(@Param("record") SiteContact record, @Param("example") SiteContactExample example);

    int updateByPrimaryKeySelective(SiteContact record);

    int updateByPrimaryKey(SiteContact record);
}