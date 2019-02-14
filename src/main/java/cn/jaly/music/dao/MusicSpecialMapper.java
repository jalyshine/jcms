package cn.jaly.music.dao;

import cn.jaly.music.entity.MusicSpecial;
import cn.jaly.music.entity.MusicSpecialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicSpecialMapper {
    long countByExample(MusicSpecialExample example);

    int deleteByExample(MusicSpecialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MusicSpecial record);

    int insertSelective(MusicSpecial record);

    List<MusicSpecial> selectByExampleWithBLOBs(MusicSpecialExample example);

    List<MusicSpecial> selectByExample(MusicSpecialExample example);

    MusicSpecial selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MusicSpecial record, @Param("example") MusicSpecialExample example);

    int updateByExampleWithBLOBs(@Param("record") MusicSpecial record, @Param("example") MusicSpecialExample example);

    int updateByExample(@Param("record") MusicSpecial record, @Param("example") MusicSpecialExample example);

    int updateByPrimaryKeySelective(MusicSpecial record);

    int updateByPrimaryKeyWithBLOBs(MusicSpecial record);

    int updateByPrimaryKey(MusicSpecial record);

    // 自定义
    List<MusicSpecial> selectByExampleWithType(MusicSpecialExample example);
}