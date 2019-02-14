package cn.jaly.music.dao;

import cn.jaly.music.entity.MusicAttribute;
import cn.jaly.music.entity.MusicAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicAttributeMapper {
    long countByExample(MusicAttributeExample example);

    int deleteByExample(MusicAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MusicAttribute record);

    int insertSelective(MusicAttribute record);

    List<MusicAttribute> selectByExample(MusicAttributeExample example);

    MusicAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MusicAttribute record, @Param("example") MusicAttributeExample example);

    int updateByExample(@Param("record") MusicAttribute record, @Param("example") MusicAttributeExample example);

    int updateByPrimaryKeySelective(MusicAttribute record);

    int updateByPrimaryKey(MusicAttribute record);
}