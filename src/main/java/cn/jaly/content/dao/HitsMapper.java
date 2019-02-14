package cn.jaly.content.dao;

import cn.jaly.content.entity.Hits;
import cn.jaly.content.entity.HitsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HitsMapper {
    long countByExample(HitsExample example);

    int deleteByExample(HitsExample example);

    int deleteByPrimaryKey(String hitsId);

    int insert(Hits record);

    int insertSelective(Hits record);

    List<Hits> selectByExample(HitsExample example);

    Hits selectByPrimaryKey(String hitsId);

    int updateByExampleSelective(@Param("record") Hits record, @Param("example") HitsExample example);

    int updateByExample(@Param("record") Hits record, @Param("example") HitsExample example);

    int updateByPrimaryKeySelective(Hits record);

    int updateByPrimaryKey(Hits record);
}