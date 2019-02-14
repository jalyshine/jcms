package cn.jaly.content.dao;

import cn.jaly.content.entity.AttachIndex;
import cn.jaly.content.entity.AttachIndexExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachIndexMapper {
    long countByExample(AttachIndexExample example);

    int deleteByExample(AttachIndexExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AttachIndex record);

    int insertSelective(AttachIndex record);

    List<AttachIndex> selectByExample(AttachIndexExample example);

    AttachIndex selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AttachIndex record, @Param("example") AttachIndexExample example);

    int updateByExample(@Param("record") AttachIndex record, @Param("example") AttachIndexExample example);

    int updateByPrimaryKeySelective(AttachIndex record);

    int updateByPrimaryKey(AttachIndex record);

    // 自定义
    void batchInsert(@Param("records") List<AttachIndex> records);
}