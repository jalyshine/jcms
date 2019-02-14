package cn.jaly.movie.dao;

import cn.jaly.movie.entity.MovieData;
import cn.jaly.movie.entity.MovieDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDataMapper {
    long countByExample(MovieDataExample example);

    int deleteByExample(MovieDataExample example);

    int deleteByPrimaryKey(Integer movieId);

    int insert(MovieData record);

    int insertSelective(MovieData record);

    List<MovieData> selectByExampleWithBLOBs(MovieDataExample example);

    List<MovieData> selectByExample(MovieDataExample example);

    MovieData selectByPrimaryKey(Integer movieId);

    int updateByExampleSelective(@Param("record") MovieData record, @Param("example") MovieDataExample example);

    int updateByExampleWithBLOBs(@Param("record") MovieData record, @Param("example") MovieDataExample example);

    int updateByExample(@Param("record") MovieData record, @Param("example") MovieDataExample example);

    int updateByPrimaryKeySelective(MovieData record);

    int updateByPrimaryKeyWithBLOBs(MovieData record);

    int updateByPrimaryKey(MovieData record);
}