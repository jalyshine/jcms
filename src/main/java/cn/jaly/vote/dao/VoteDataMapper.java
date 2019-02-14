package cn.jaly.vote.dao;

import cn.jaly.vote.entity.VoteData;
import cn.jaly.vote.entity.VoteDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteDataMapper {
    long countByExample(VoteDataExample example);

    int deleteByExample(VoteDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoteData record);

    int insertSelective(VoteData record);

    List<VoteData> selectByExample(VoteDataExample example);

    VoteData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoteData record, @Param("example") VoteDataExample example);

    int updateByExample(@Param("record") VoteData record, @Param("example") VoteDataExample example);

    int updateByPrimaryKeySelective(VoteData record);

    int updateByPrimaryKey(VoteData record);
}