package cn.jaly.content.dao;

import cn.jaly.content.entity.VideoData;
import cn.jaly.content.entity.VideoDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDataMapper {
    long countByExample(VideoDataExample example);

    int deleteByExample(VideoDataExample example);

    int deleteByPrimaryKey(Integer videoId);

    int insert(VideoData record);

    int insertSelective(VideoData record);

    List<VideoData> selectByExampleWithBLOBs(VideoDataExample example);

    List<VideoData> selectByExample(VideoDataExample example);

    VideoData selectByPrimaryKey(Integer videoId);

    int updateByExampleSelective(@Param("record") VideoData record, @Param("example") VideoDataExample example);

    int updateByExampleWithBLOBs(@Param("record") VideoData record, @Param("example") VideoDataExample example);

    int updateByExample(@Param("record") VideoData record, @Param("example") VideoDataExample example);

    int updateByPrimaryKeySelective(VideoData record);

    int updateByPrimaryKeyWithBLOBs(VideoData record);

    int updateByPrimaryKey(VideoData record);
}