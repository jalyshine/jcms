package cn.jaly.special.dao;

import cn.jaly.special.entity.SpecialContentData;
import cn.jaly.special.entity.SpecialContentDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialContentDataMapper {
    long countByExample(SpecialContentDataExample example);

    int deleteByExample(SpecialContentDataExample example);

    int deleteByPrimaryKey(Integer specialContentId);

    int insert(SpecialContentData record);

    int insertSelective(SpecialContentData record);

    List<SpecialContentData> selectByExampleWithBLOBs(SpecialContentDataExample example);

    List<SpecialContentData> selectByExample(SpecialContentDataExample example);

    SpecialContentData selectByPrimaryKey(Integer specialContentId);

    int updateByExampleSelective(@Param("record") SpecialContentData record, @Param("example") SpecialContentDataExample example);

    int updateByExampleWithBLOBs(@Param("record") SpecialContentData record, @Param("example") SpecialContentDataExample example);

    int updateByExample(@Param("record") SpecialContentData record, @Param("example") SpecialContentDataExample example);

    int updateByPrimaryKeySelective(SpecialContentData record);

    int updateByPrimaryKeyWithBLOBs(SpecialContentData record);

    int updateByPrimaryKey(SpecialContentData record);
}