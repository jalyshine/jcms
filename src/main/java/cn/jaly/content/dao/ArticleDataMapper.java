package cn.jaly.content.dao;

import cn.jaly.content.entity.ArticleData;
import cn.jaly.content.entity.ArticleDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDataMapper {
    long countByExample(ArticleDataExample example);

    int deleteByExample(ArticleDataExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleData record);

    int insertSelective(ArticleData record);

    List<ArticleData> selectByExampleWithBLOBs(ArticleDataExample example);

    List<ArticleData> selectByExample(ArticleDataExample example);

    ArticleData selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") ArticleData record, @Param("example") ArticleDataExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleData record, @Param("example") ArticleDataExample example);

    int updateByExample(@Param("record") ArticleData record, @Param("example") ArticleDataExample example);

    int updateByPrimaryKeySelective(ArticleData record);

    int updateByPrimaryKeyWithBLOBs(ArticleData record);

    int updateByPrimaryKey(ArticleData record);
}