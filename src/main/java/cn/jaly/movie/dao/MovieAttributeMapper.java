package cn.jaly.movie.dao;

import cn.jaly.movie.entity.MovieAttribute;
import cn.jaly.movie.entity.MovieAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieAttributeMapper {
    long countByExample(MovieAttributeExample example);

    int deleteByExample(MovieAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MovieAttribute record);

    int insertSelective(MovieAttribute record);

    List<MovieAttribute> selectByExample(MovieAttributeExample example);

    MovieAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MovieAttribute record, @Param("example") MovieAttributeExample example);

    int updateByExample(@Param("record") MovieAttribute record, @Param("example") MovieAttributeExample example);

    int updateByPrimaryKeySelective(MovieAttribute record);

    int updateByPrimaryKey(MovieAttribute record);
}