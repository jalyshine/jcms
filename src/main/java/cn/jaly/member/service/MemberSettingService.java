package cn.jaly.member.service;

import cn.jaly.member.dao.MemberSettingMapper;
import cn.jaly.member.entity.MemberSetting;
import cn.jaly.member.entity.MemberSettingExample;
import cn.jaly.member.entity.MemberSettingWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberSettingService {

    @Autowired
    private MemberSettingMapper memberSettingMapper;

    @Transactional(readOnly = true)
    public MemberSettingWithBLOBs getBySiteId(Integer siteId){
        return memberSettingMapper.selectByPrimaryKey(siteId);
    }

    @Transactional
    public void save(MemberSettingWithBLOBs memberSetting){
        MemberSettingWithBLOBs record = memberSettingMapper.selectByPrimaryKey(memberSetting.getSiteId());
        if(record == null){
            memberSettingMapper.insert(memberSetting);
        } else {
            memberSettingMapper.updateByPrimaryKeySelective(memberSetting);
        }
    }
}
