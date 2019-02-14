package cn.jaly.admin.dao;

import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.entity.BackMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BackMenuMapper {
    long countByExample(BackMenuExample example);

    int deleteByExample(BackMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BackMenu record);

    int insertSelective(BackMenu record);

    List<BackMenu> selectByExample(BackMenuExample example);

    BackMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BackMenu record, @Param("example") BackMenuExample example);

    int updateByExample(@Param("record") BackMenu record, @Param("example") BackMenuExample example);

    int updateByPrimaryKeySelective(BackMenu record);

    int updateByPrimaryKey(BackMenu record);

    // 自定义
    void batchUpdateListOrder(@Param("records") List<BackMenu> records);

    void batchUpdatePathAndDepth(@Param("records") List<BackMenu> records);
}