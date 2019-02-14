package cn.jaly.special.dao;

import cn.jaly.special.entity.SpecialContent;
import cn.jaly.special.entity.SpecialContentExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialContentMapper {
    long countByExample(SpecialContentExample example);

    int deleteByExample(SpecialContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpecialContent record);

    int insertSelective(SpecialContent record);

    List<SpecialContent> selectByExample(SpecialContentExample example);

    SpecialContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpecialContent record, @Param("example") SpecialContentExample example);

    int updateByExample(@Param("record") SpecialContent record, @Param("example") SpecialContentExample example);

    int updateByPrimaryKeySelective(SpecialContent record);

    int updateByPrimaryKey(SpecialContent record);

    // 自定义
    List<SpecialContent> queryForList(@Param("keyword") String keyword,
                                      @Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime,
                                      @Param("specialId") Integer specialId,
                                      @Param("order") String order);

    SpecialContent selectByPrimaryKeyWithData(Integer id);
}