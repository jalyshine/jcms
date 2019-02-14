package cn.jaly.member.dao;

import cn.jaly.member.entity.MemberGroup;
import cn.jaly.member.entity.MemberGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberGroupMapper {
    long countByExample(MemberGroupExample example);

    int deleteByExample(MemberGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberGroup record);

    int insertSelective(MemberGroup record);

    List<MemberGroup> selectByExample(MemberGroupExample example);

    MemberGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberGroup record, @Param("example") MemberGroupExample example);

    int updateByExample(@Param("record") MemberGroup record, @Param("example") MemberGroupExample example);

    int updateByPrimaryKeySelective(MemberGroup record);

    int updateByPrimaryKey(MemberGroup record);

    // 自定义
    int updateMemberCountByPrimaryKey(Integer id);
}