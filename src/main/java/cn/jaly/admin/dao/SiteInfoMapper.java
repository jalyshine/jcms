package cn.jaly.admin.dao;

import cn.jaly.admin.entity.SiteInfo;
import cn.jaly.admin.entity.SiteInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteInfoMapper {
    long countByExample(SiteInfoExample example);

    int deleteByExample(SiteInfoExample example);

    int deleteByPrimaryKey(Integer siteId);

    int insert(SiteInfo record);

    int insertSelective(SiteInfo record);

    List<SiteInfo> selectByExampleWithBLOBs(SiteInfoExample example);

    List<SiteInfo> selectByExample(SiteInfoExample example);

    SiteInfo selectByPrimaryKey(Integer siteId);

    int updateByExampleSelective(@Param("record") SiteInfo record, @Param("example") SiteInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") SiteInfo record, @Param("example") SiteInfoExample example);

    int updateByExample(@Param("record") SiteInfo record, @Param("example") SiteInfoExample example);

    int updateByPrimaryKeySelective(SiteInfo record);

    int updateByPrimaryKeyWithBLOBs(SiteInfo record);

    int updateByPrimaryKey(SiteInfo record);
}