package cn.jaly.member.dao;

import cn.jaly.member.entity.MemberFavorite;
import cn.jaly.member.entity.MemberFavoriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberFavoriteMapper {
    long countByExample(MemberFavoriteExample example);

    int deleteByExample(MemberFavoriteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberFavorite record);

    int insertSelective(MemberFavorite record);

    List<MemberFavorite> selectByExample(MemberFavoriteExample example);

    MemberFavorite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberFavorite record, @Param("example") MemberFavoriteExample example);

    int updateByExample(@Param("record") MemberFavorite record, @Param("example") MemberFavoriteExample example);

    int updateByPrimaryKeySelective(MemberFavorite record);

    int updateByPrimaryKey(MemberFavorite record);
}