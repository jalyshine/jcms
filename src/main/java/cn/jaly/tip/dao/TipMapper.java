package cn.jaly.tip.dao;

import cn.jaly.tip.entity.Tip;
import cn.jaly.tip.entity.TipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TipMapper {
    long countByExample(TipExample example);

    int deleteByExample(TipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tip record);

    int insertSelective(Tip record);

    List<Tip> selectByExample(TipExample example);

    Tip selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tip record, @Param("example") TipExample example);

    int updateByExample(@Param("record") Tip record, @Param("example") TipExample example);

    int updateByPrimaryKeySelective(Tip record);

    int updateByPrimaryKey(Tip record);

    // 自定义
    List<Tip> queryForList(@Param("siteId") Integer siteId,
                           @Param("typeId") Integer typeId,
                           @Param("keyword") String keyword,
                           @Param("order") String order);
}