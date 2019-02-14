package cn.jaly.comment.dao;

import cn.jaly.comment.entity.CommentSetting;
import cn.jaly.comment.entity.CommentSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentSettingMapper {
    long countByExample(CommentSettingExample example);

    int deleteByExample(CommentSettingExample example);

    int deleteByPrimaryKey(Integer siteId);

    int insert(CommentSetting record);

    int insertSelective(CommentSetting record);

    List<CommentSetting> selectByExample(CommentSettingExample example);

    CommentSetting selectByPrimaryKey(Integer siteId);

    int updateByExampleSelective(@Param("record") CommentSetting record, @Param("example") CommentSettingExample example);

    int updateByExample(@Param("record") CommentSetting record, @Param("example") CommentSettingExample example);

    int updateByPrimaryKeySelective(CommentSetting record);

    int updateByPrimaryKey(CommentSetting record);
}