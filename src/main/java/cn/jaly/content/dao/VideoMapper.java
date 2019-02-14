package cn.jaly.content.dao;

import cn.jaly.content.entity.Video;
import cn.jaly.content.entity.VideoExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMapper {
    long countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExample(VideoExample example);

    Video selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    // 自定义
    List<Video> queryForList(@Param("keyword") String keyword,
                             @Param("startTime") Date startTime,
                             @Param("endTime") Date endTime,
                             @Param("categoryId") Integer categoryId,
                             @Param("status") Byte status,
                             @Param("order") String order);

    Video selectByPrimaryKeyWithData(Integer id);
}