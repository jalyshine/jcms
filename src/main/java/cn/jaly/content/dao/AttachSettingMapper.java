package cn.jaly.content.dao;

import cn.jaly.content.entity.AttachSetting;
import cn.jaly.content.entity.AttachSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachSettingMapper {
    long countByExample(AttachSettingExample example);

    int deleteByExample(AttachSettingExample example);

    int deleteByPrimaryKey(Integer siteId);

    int insert(AttachSetting record);

    int insertSelective(AttachSetting record);

    List<AttachSetting> selectByExample(AttachSettingExample example);

    AttachSetting selectByPrimaryKey(Integer siteId);

    int updateByExampleSelective(@Param("record") AttachSetting record, @Param("example") AttachSettingExample example);

    int updateByExample(@Param("record") AttachSetting record, @Param("example") AttachSettingExample example);

    int updateByPrimaryKeySelective(AttachSetting record);

    int updateByPrimaryKey(AttachSetting record);
}