package cn.jaly.music.dao;

import cn.jaly.music.entity.Music;
import cn.jaly.music.entity.MusicExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicMapper {
    long countByExample(MusicExample example);

    int deleteByExample(MusicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Music record);

    int insertSelective(Music record);

    List<Music> selectByExample(MusicExample example);

    Music selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Music record, @Param("example") MusicExample example);

    int updateByExample(@Param("record") Music record, @Param("example") MusicExample example);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);

    // 自定义
    List<Music> queryForList(@Param("albumId") Integer albumId,
                             @Param("keyword") String keyword,
                             @Param("startTime") Date startTime,
                             @Param("endTime") Date endTime,
                             @Param("order") String order);

    List<Music> queryByIds(@Param("ids") List<Integer> ids);

    void batchDelete(@Param("ids") Integer[] ids);
}