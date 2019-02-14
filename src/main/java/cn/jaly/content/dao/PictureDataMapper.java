package cn.jaly.content.dao;

import cn.jaly.content.entity.PictureData;
import cn.jaly.content.entity.PictureDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureDataMapper {
    long countByExample(PictureDataExample example);

    int deleteByExample(PictureDataExample example);

    int deleteByPrimaryKey(Integer pictureId);

    int insert(PictureData record);

    int insertSelective(PictureData record);

    List<PictureData> selectByExampleWithBLOBs(PictureDataExample example);

    List<PictureData> selectByExample(PictureDataExample example);

    PictureData selectByPrimaryKey(Integer pictureId);

    int updateByExampleSelective(@Param("record") PictureData record, @Param("example") PictureDataExample example);

    int updateByExampleWithBLOBs(@Param("record") PictureData record, @Param("example") PictureDataExample example);

    int updateByExample(@Param("record") PictureData record, @Param("example") PictureDataExample example);

    int updateByPrimaryKeySelective(PictureData record);

    int updateByPrimaryKeyWithBLOBs(PictureData record);

    int updateByPrimaryKey(PictureData record);
}