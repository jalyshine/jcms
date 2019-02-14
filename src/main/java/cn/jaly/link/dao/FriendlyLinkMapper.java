package cn.jaly.link.dao;

import cn.jaly.link.entity.FriendlyLink;
import cn.jaly.link.entity.FriendlyLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendlyLinkMapper {
    long countByExample(FriendlyLinkExample example);

    int deleteByExample(FriendlyLinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FriendlyLink record);

    int insertSelective(FriendlyLink record);

    List<FriendlyLink> selectByExample(FriendlyLinkExample example);

    FriendlyLink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FriendlyLink record, @Param("example") FriendlyLinkExample example);

    int updateByExample(@Param("record") FriendlyLink record, @Param("example") FriendlyLinkExample example);

    int updateByPrimaryKeySelective(FriendlyLink record);

    int updateByPrimaryKey(FriendlyLink record);

    // 自定义
    List<FriendlyLink> queryForList(@Param("siteId") Integer siteId,
                                    @Param("keyword") String keyword,
                                    @Param("order") String order);
}