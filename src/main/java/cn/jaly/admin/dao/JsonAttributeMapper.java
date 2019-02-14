package cn.jaly.admin.dao;

import cn.jaly.admin.entity.JsonAttribute;
import cn.jaly.admin.entity.JsonAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonAttributeMapper {
    long countByExample(JsonAttributeExample example);

    int deleteByExample(JsonAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JsonAttribute record);

    int insertSelective(JsonAttribute record);

    List<JsonAttribute> selectByExample(JsonAttributeExample example);

    JsonAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JsonAttribute record, @Param("example") JsonAttributeExample example);

    int updateByExample(@Param("record") JsonAttribute record, @Param("example") JsonAttributeExample example);

    int updateByPrimaryKeySelective(JsonAttribute record);

    int updateByPrimaryKey(JsonAttribute record);

    // 自定义
    void batchInsert(@Param("records") List<JsonAttribute> records);
}