package cn.jaly.member.dao;

import cn.jaly.member.entity.MemberVerify;
import cn.jaly.member.entity.MemberVerifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberVerifyMapper {
    long countByExample(MemberVerifyExample example);

    int deleteByExample(MemberVerifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberVerify record);

    int insertSelective(MemberVerify record);

    List<MemberVerify> selectByExample(MemberVerifyExample example);

    MemberVerify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberVerify record, @Param("example") MemberVerifyExample example);

    int updateByExample(@Param("record") MemberVerify record, @Param("example") MemberVerifyExample example);

    int updateByPrimaryKeySelective(MemberVerify record);

    int updateByPrimaryKey(MemberVerify record);
}