package cn.jaly.member.dao;

import cn.jaly.member.entity.MemberDetail;
import cn.jaly.member.entity.MemberDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDetailMapper {
    long countByExample(MemberDetailExample example);

    int deleteByExample(MemberDetailExample example);

    int deleteByPrimaryKey(Integer memberId);

    int insert(MemberDetail record);

    int insertSelective(MemberDetail record);

    List<MemberDetail> selectByExample(MemberDetailExample example);

    MemberDetail selectByPrimaryKey(Integer memberId);

    int updateByExampleSelective(@Param("record") MemberDetail record, @Param("example") MemberDetailExample example);

    int updateByExample(@Param("record") MemberDetail record, @Param("example") MemberDetailExample example);

    int updateByPrimaryKeySelective(MemberDetail record);

    int updateByPrimaryKey(MemberDetail record);
}