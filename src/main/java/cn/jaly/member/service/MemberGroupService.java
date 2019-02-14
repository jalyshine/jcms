package cn.jaly.member.service;

import cn.jaly.member.dao.MemberGroupMapper;
import cn.jaly.member.entity.MemberGroup;
import cn.jaly.member.entity.MemberGroupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberGroupService {

    @Autowired
    private MemberGroupMapper memberGroupMapper;

    @Transactional(readOnly = true)
    public List<MemberGroup> getBySiteId(Integer siteId) {
        MemberGroupExample example = new MemberGroupExample();
        MemberGroupExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        return memberGroupMapper.selectByExample(example);
    }

    @Transactional(readOnly = true)
    public MemberGroup getById(Integer id) {
        return memberGroupMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    public Integer getIdByName(String name){
        MemberGroupExample example = new MemberGroupExample();
        MemberGroupExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<MemberGroup> memberGroups = memberGroupMapper.selectByExample(example);
        if(memberGroups == null || memberGroups.isEmpty()){
            return null;
        }
        return memberGroups.get(0).getId();
    }

    @Transactional
    public void save(MemberGroup memberGroup) {
        if (memberGroup.getId() == null) {
            memberGroup.setMemberCount(0);
            memberGroupMapper.insert(memberGroup);
        } else {
            memberGroupMapper.updateByPrimaryKeySelective(memberGroup);
        }
    }

    @Transactional
    public void delete(Integer id) {
        memberGroupMapper.deleteByPrimaryKey(id);
    }

}
