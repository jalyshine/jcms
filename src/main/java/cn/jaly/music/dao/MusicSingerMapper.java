package cn.jaly.music.dao;

import cn.jaly.music.entity.MusicSinger;
import cn.jaly.music.entity.MusicSingerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicSingerMapper {
    long countByExample(MusicSingerExample example);

    int deleteByExample(MusicSingerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MusicSinger record);

    int insertSelective(MusicSinger record);

    List<MusicSinger> selectByExample(MusicSingerExample example);

    MusicSinger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MusicSinger record, @Param("example") MusicSingerExample example);

    int updateByExample(@Param("record") MusicSinger record, @Param("example") MusicSingerExample example);

    int updateByPrimaryKeySelective(MusicSinger record);

    int updateByPrimaryKey(MusicSinger record);

    // 自定义
    List<MusicSinger> selectByExampleWithArea(MusicSingerExample example);
}