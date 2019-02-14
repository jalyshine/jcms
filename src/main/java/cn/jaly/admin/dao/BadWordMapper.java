package cn.jaly.admin.dao;

import cn.jaly.admin.entity.BadWord;
import cn.jaly.admin.entity.BadWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BadWordMapper {
    long countByExample(BadWordExample example);

    int deleteByExample(BadWordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BadWord record);

    int insertSelective(BadWord record);

    List<BadWord> selectByExample(BadWordExample example);

    BadWord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BadWord record, @Param("example") BadWordExample example);

    int updateByExample(@Param("record") BadWord record, @Param("example") BadWordExample example);

    int updateByPrimaryKeySelective(BadWord record);

    int updateByPrimaryKey(BadWord record);
}