package cn.jaly.member.dao;

import cn.jaly.member.entity.Member;
import cn.jaly.member.entity.MemberExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    long countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    // 自定义
    List<Member> selectWithGroup(@Param("site") Integer siteId, @Param("isLock") Boolean status,
                                 @Param("group") Integer groupId, @Param("start") Date startTime,
                                 @Param("end") Date endTime, @Param("keyword") String keyword,
                                 @Param("order") String orderBy);
}