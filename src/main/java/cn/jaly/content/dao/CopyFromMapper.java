package cn.jaly.content.dao;

import cn.jaly.content.entity.CopyFrom;
import cn.jaly.content.entity.CopyFromExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyFromMapper {
    long countByExample(CopyFromExample example);

    int deleteByExample(CopyFromExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CopyFrom record);

    int insertSelective(CopyFrom record);

    List<CopyFrom> selectByExample(CopyFromExample example);

    CopyFrom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CopyFrom record, @Param("example") CopyFromExample example);

    int updateByExample(@Param("record") CopyFrom record, @Param("example") CopyFromExample example);

    int updateByPrimaryKeySelective(CopyFrom record);

    int updateByPrimaryKey(CopyFrom record);
}