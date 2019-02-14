package cn.jaly.special.dao;

import cn.jaly.special.entity.SpecialType;
import cn.jaly.special.entity.SpecialTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialTypeMapper {
    long countByExample(SpecialTypeExample example);

    int deleteByExample(SpecialTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpecialType record);

    int insertSelective(SpecialType record);

    List<SpecialType> selectByExample(SpecialTypeExample example);

    SpecialType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpecialType record, @Param("example") SpecialTypeExample example);

    int updateByExample(@Param("record") SpecialType record, @Param("example") SpecialTypeExample example);

    int updateByPrimaryKeySelective(SpecialType record);

    int updateByPrimaryKey(SpecialType record);
}