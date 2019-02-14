package cn.jaly.tip.dao;

import cn.jaly.tip.entity.TipType;
import cn.jaly.tip.entity.TipTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TipTypeMapper {
    long countByExample(TipTypeExample example);

    int deleteByExample(TipTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TipType record);

    int insertSelective(TipType record);

    List<TipType> selectByExample(TipTypeExample example);

    TipType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TipType record, @Param("example") TipTypeExample example);

    int updateByExample(@Param("record") TipType record, @Param("example") TipTypeExample example);

    int updateByPrimaryKeySelective(TipType record);

    int updateByPrimaryKey(TipType record);
}