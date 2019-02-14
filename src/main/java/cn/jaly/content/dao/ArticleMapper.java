package cn.jaly.content.dao;

import cn.jaly.content.entity.Article;
import cn.jaly.content.entity.ArticleExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    //    自定义
    List<Article> queryForList(@Param("keyword") String keyword,
                               @Param("startTime") Date startTime,
                               @Param("endTime") Date endTime,
                               @Param("categoryId") Integer categoryId,
                               @Param("status") Byte status,
                               @Param("order") String order);

    Article selectByPrimaryKeyWithData(Integer id);
}