package cn.jaly.message.dao;

import cn.jaly.message.entity.MessageGroup;
import cn.jaly.message.entity.MessageGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageGroupMapper {
    long countByExample(MessageGroupExample example);

    int deleteByExample(MessageGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageGroup record);

    int insertSelective(MessageGroup record);

    List<MessageGroup> selectByExample(MessageGroupExample example);

    MessageGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageGroup record, @Param("example") MessageGroupExample example);

    int updateByExample(@Param("record") MessageGroup record, @Param("example") MessageGroupExample example);

    int updateByPrimaryKeySelective(MessageGroup record);

    int updateByPrimaryKey(MessageGroup record);

    // 自定义
    List<MessageGroup> queryForList(@Param("siteId") Integer siteId,
                                    @Param("keyword") String keyword,
                                    @Param("order") String order);
}