package cn.jaly.music.dao;

import cn.jaly.music.entity.MusicAlbum;
import cn.jaly.music.entity.MusicAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicAlbumMapper {
    long countByExample(MusicAlbumExample example);

    int deleteByExample(MusicAlbumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MusicAlbum record);

    int insertSelective(MusicAlbum record);

    List<MusicAlbum> selectByExample(MusicAlbumExample example);

    MusicAlbum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MusicAlbum record, @Param("example") MusicAlbumExample example);

    int updateByExample(@Param("record") MusicAlbum record, @Param("example") MusicAlbumExample example);

    int updateByPrimaryKeySelective(MusicAlbum record);

    int updateByPrimaryKey(MusicAlbum record);

    // 自定义
    List<MusicAlbum> queryForList(@Param("singerId") Integer singerId,
                                  @Param("keyword") String keyword,
                                  @Param("order") String order);
}