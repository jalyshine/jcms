package cn.jaly.music.dao;

import cn.jaly.music.entity.MusicVideo;
import cn.jaly.music.entity.MusicVideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicVideoMapper {
    long countByExample(MusicVideoExample example);

    int deleteByExample(MusicVideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MusicVideo record);

    int insertSelective(MusicVideo record);

    List<MusicVideo> selectByExample(MusicVideoExample example);

    MusicVideo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MusicVideo record, @Param("example") MusicVideoExample example);

    int updateByExample(@Param("record") MusicVideo record, @Param("example") MusicVideoExample example);

    int updateByPrimaryKeySelective(MusicVideo record);

    int updateByPrimaryKey(MusicVideo record);

    // 自定义
    List<MusicVideo> queryForList(@Param("singerId") Integer singerId,
                                  @Param("keyword") String keyword,
                                  @Param("order") String order);
}