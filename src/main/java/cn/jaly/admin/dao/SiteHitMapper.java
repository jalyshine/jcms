package cn.jaly.admin.dao;

import cn.jaly.admin.entity.SiteHit;
import cn.jaly.admin.entity.SiteHitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteHitMapper {
    long countByExample(SiteHitExample example);

    int deleteByExample(SiteHitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SiteHit record);

    int insertSelective(SiteHit record);

    List<SiteHit> selectByExample(SiteHitExample example);

    SiteHit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SiteHit record, @Param("example") SiteHitExample example);

    int updateByExample(@Param("record") SiteHit record, @Param("example") SiteHitExample example);

    int updateByPrimaryKeySelective(SiteHit record);

    int updateByPrimaryKey(SiteHit record);

    // 自定义
    List<SiteHit> selectNearestHits(@Param("siteId") Integer siteId, @Param("days") Integer days);
}