package cn.jaly.comment.dao;

import cn.jaly.comment.entity.CommentData;
import cn.jaly.comment.entity.CommentDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDataMapper {
    long countByExample(CommentDataExample example);

    int deleteByExample(CommentDataExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(CommentData record);

    int insertSelective(CommentData record);

    List<CommentData> selectByExampleWithBLOBs(CommentDataExample example);

    List<CommentData> selectByExample(CommentDataExample example);

    CommentData selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") CommentData record, @Param("example") CommentDataExample example);

    int updateByExampleWithBLOBs(@Param("record") CommentData record, @Param("example") CommentDataExample example);

    int updateByExample(@Param("record") CommentData record, @Param("example") CommentDataExample example);

    int updateByPrimaryKeySelective(CommentData record);

    int updateByPrimaryKeyWithBLOBs(CommentData record);

    int updateByPrimaryKey(CommentData record);
}