package cn.jaly.content.dao;

import cn.jaly.content.entity.WorkFlow;
import cn.jaly.content.entity.WorkFlowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFlowMapper {
    long countByExample(WorkFlowExample example);

    int deleteByExample(WorkFlowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkFlow record);

    int insertSelective(WorkFlow record);

    List<WorkFlow> selectByExample(WorkFlowExample example);

    WorkFlow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkFlow record, @Param("example") WorkFlowExample example);

    int updateByExample(@Param("record") WorkFlow record, @Param("example") WorkFlowExample example);

    int updateByPrimaryKeySelective(WorkFlow record);

    int updateByPrimaryKey(WorkFlow record);
}