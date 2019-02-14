package cn.jaly.comment.dao;

import cn.jaly.comment.entity.Comment;
import cn.jaly.comment.entity.CommentExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    // 自定义
    List<Comment> queryForList(@Param("siteId") Integer siteId,
                               @Param("keyword") String keyword,
                               @Param("startTime") Date startTime,
                               @Param("endTime") Date endTime,
                               @Param("status") Byte status,
                               @Param("order") String order);

    Comment selectByPrimaryKeyWithData(Integer id);
}