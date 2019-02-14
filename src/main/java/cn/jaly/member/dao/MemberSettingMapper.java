package cn.jaly.member.dao;

import cn.jaly.member.entity.MemberSetting;
import cn.jaly.member.entity.MemberSettingExample;
import cn.jaly.member.entity.MemberSettingWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSettingMapper {
    long countByExample(MemberSettingExample example);

    int deleteByExample(MemberSettingExample example);

    int deleteByPrimaryKey(Integer siteId);

    int insert(MemberSettingWithBLOBs record);

    int insertSelective(MemberSettingWithBLOBs record);

    List<MemberSettingWithBLOBs> selectByExampleWithBLOBs(MemberSettingExample example);

    List<MemberSetting> selectByExample(MemberSettingExample example);

    MemberSettingWithBLOBs selectByPrimaryKey(Integer siteId);

    int updateByExampleSelective(@Param("record") MemberSettingWithBLOBs record, @Param("example") MemberSettingExample example);

    int updateByExampleWithBLOBs(@Param("record") MemberSettingWithBLOBs record, @Param("example") MemberSettingExample example);

    int updateByExample(@Param("record") MemberSetting record, @Param("example") MemberSettingExample example);

    int updateByPrimaryKeySelective(MemberSettingWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MemberSettingWithBLOBs record);

    int updateByPrimaryKey(MemberSetting record);
}