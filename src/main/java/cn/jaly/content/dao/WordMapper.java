package cn.jaly.content.dao;

import cn.jaly.content.entity.Word;
import cn.jaly.content.entity.WordExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordMapper {
    long countByExample(WordExample example);

    int deleteByExample(WordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Word record);

    int insertSelective(Word record);

    List<Word> selectByExampleWithBLOBs(WordExample example);

    List<Word> selectByExample(WordExample example);

    Word selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Word record, @Param("example") WordExample example);

    int updateByExampleWithBLOBs(@Param("record") Word record, @Param("example") WordExample example);

    int updateByExample(@Param("record") Word record, @Param("example") WordExample example);

    int updateByPrimaryKeySelective(Word record);

    int updateByPrimaryKeyWithBLOBs(Word record);

    int updateByPrimaryKey(Word record);

    //    自定义
    List<Word> queryForList(@Param("keyword") String keyword,
                            @Param("startTime") Date startTime,
                            @Param("endTime") Date endTime,
                            @Param("categoryId") Integer categoryId,
                            @Param("order") String order);
}