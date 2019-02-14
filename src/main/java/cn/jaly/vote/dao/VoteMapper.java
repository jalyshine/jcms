package cn.jaly.vote.dao;

import cn.jaly.vote.entity.Vote;
import cn.jaly.vote.entity.VoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteMapper {
    long countByExample(VoteExample example);

    int deleteByExample(VoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Vote record);

    int insertSelective(Vote record);

    List<Vote> selectByExampleWithBLOBs(VoteExample example);

    List<Vote> selectByExample(VoteExample example);

    Vote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Vote record, @Param("example") VoteExample example);

    int updateByExampleWithBLOBs(@Param("record") Vote record, @Param("example") VoteExample example);

    int updateByExample(@Param("record") Vote record, @Param("example") VoteExample example);

    int updateByPrimaryKeySelective(Vote record);

    int updateByPrimaryKeyWithBLOBs(Vote record);

    int updateByPrimaryKey(Vote record);
}