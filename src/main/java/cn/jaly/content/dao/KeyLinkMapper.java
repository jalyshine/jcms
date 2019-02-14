package cn.jaly.content.dao;

import cn.jaly.content.entity.KeyLink;
import cn.jaly.content.entity.KeyLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyLinkMapper {
    long countByExample(KeyLinkExample example);

    int deleteByExample(KeyLinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KeyLink record);

    int insertSelective(KeyLink record);

    List<KeyLink> selectByExample(KeyLinkExample example);

    KeyLink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KeyLink record, @Param("example") KeyLinkExample example);

    int updateByExample(@Param("record") KeyLink record, @Param("example") KeyLinkExample example);

    int updateByPrimaryKeySelective(KeyLink record);

    int updateByPrimaryKey(KeyLink record);
}