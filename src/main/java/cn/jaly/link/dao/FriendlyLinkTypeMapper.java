package cn.jaly.link.dao;

import cn.jaly.link.entity.FriendlyLinkType;
import cn.jaly.link.entity.FriendlyLinkTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendlyLinkTypeMapper {
    long countByExample(FriendlyLinkTypeExample example);

    int deleteByExample(FriendlyLinkTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FriendlyLinkType record);

    int insertSelective(FriendlyLinkType record);

    List<FriendlyLinkType> selectByExample(FriendlyLinkTypeExample example);

    FriendlyLinkType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FriendlyLinkType record, @Param("example") FriendlyLinkTypeExample example);

    int updateByExample(@Param("record") FriendlyLinkType record, @Param("example") FriendlyLinkTypeExample example);

    int updateByPrimaryKeySelective(FriendlyLinkType record);

    int updateByPrimaryKey(FriendlyLinkType record);
}