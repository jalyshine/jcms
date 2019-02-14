package cn.jaly.content.dao;

import cn.jaly.content.entity.Picture;
import cn.jaly.content.entity.PictureExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureMapper {
    long countByExample(PictureExample example);

    int deleteByExample(PictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    int insertSelective(Picture record);

    List<Picture> selectByExample(PictureExample example);

    Picture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Picture record, @Param("example") PictureExample example);

    int updateByExample(@Param("record") Picture record, @Param("example") PictureExample example);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    // 自定义
    List<Picture> queryForList(@Param("keyword") String keyword,
                               @Param("startTime") Date startTime,
                               @Param("endTime") Date endTime,
                               @Param("categoryId") Integer categoryId,
                               @Param("status") Byte status,
                               @Param("order") String order);

    Picture selectByPrimaryKeyWithData(Integer id);
}