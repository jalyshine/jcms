package cn.jaly.admin.dao;

import cn.jaly.admin.entity.IpBanned;
import cn.jaly.admin.entity.IpBannedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IpBannedMapper {
    long countByExample(IpBannedExample example);

    int deleteByExample(IpBannedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IpBanned record);

    int insertSelective(IpBanned record);

    List<IpBanned> selectByExample(IpBannedExample example);

    IpBanned selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IpBanned record, @Param("example") IpBannedExample example);

    int updateByExample(@Param("record") IpBanned record, @Param("example") IpBannedExample example);

    int updateByPrimaryKeySelective(IpBanned record);

    int updateByPrimaryKey(IpBanned record);
}