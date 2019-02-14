package cn.jaly.content.dao;

import cn.jaly.content.entity.SinglePage;
import cn.jaly.content.entity.SinglePageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SinglePageMapper {
    long countByExample(SinglePageExample example);

    int deleteByExample(SinglePageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SinglePage record);

    int insertSelective(SinglePage record);

    List<SinglePage> selectByExampleWithBLOBs(SinglePageExample example);

    List<SinglePage> selectByExample(SinglePageExample example);

    SinglePage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SinglePage record, @Param("example") SinglePageExample example);

    int updateByExampleWithBLOBs(@Param("record") SinglePage record, @Param("example") SinglePageExample example);

    int updateByExample(@Param("record") SinglePage record, @Param("example") SinglePageExample example);

    int updateByPrimaryKeySelective(SinglePage record);

    int updateByPrimaryKeyWithBLOBs(SinglePage record);

    int updateByPrimaryKey(SinglePage record);
}