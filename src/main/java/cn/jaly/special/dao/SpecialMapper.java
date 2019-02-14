package cn.jaly.special.dao;

import cn.jaly.special.entity.Special;
import cn.jaly.special.entity.SpecialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialMapper {
    long countByExample(SpecialExample example);

    int deleteByExample(SpecialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Special record);

    int insertSelective(Special record);

    List<Special> selectByExample(SpecialExample example);

    Special selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Special record, @Param("example") SpecialExample example);

    int updateByExample(@Param("record") Special record, @Param("example") SpecialExample example);

    int updateByPrimaryKeySelective(Special record);

    int updateByPrimaryKey(Special record);
}