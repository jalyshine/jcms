package cn.jaly.content.dao;

import cn.jaly.content.entity.RecommendPosition;
import cn.jaly.content.entity.RecommendPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendPositionMapper {
    long countByExample(RecommendPositionExample example);

    int deleteByExample(RecommendPositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RecommendPosition record);

    int insertSelective(RecommendPosition record);

    List<RecommendPosition> selectByExample(RecommendPositionExample example);

    RecommendPosition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RecommendPosition record, @Param("example") RecommendPositionExample example);

    int updateByExample(@Param("record") RecommendPosition record, @Param("example") RecommendPositionExample example);

    int updateByPrimaryKeySelective(RecommendPosition record);

    int updateByPrimaryKey(RecommendPosition record);

    // 自定义
    List<RecommendPosition> selectWithModel(@Param("siteId") Integer siteId, @Param("keyword") String keyword);
}