package cn.jaly.member.service;

import cn.jaly.member.dao.MemberGroupMapper;
import cn.jaly.member.dao.MemberMapper;
import cn.jaly.member.dao.MemberVerifyMapper;
import cn.jaly.member.entity.*;
import cn.jaly.utils.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MemberVerifyService {

    @Autowired
    private MemberVerifyMapper memberVerifyMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberGroupMapper memberGroupMapper;

    @Transactional(readOnly = true)
    public List<MemberVerify> queryForList(Integer siteId, String tts, String order){
        MemberVerifyExample example = new MemberVerifyExample();
        example.setOrderByClause(order);
        MemberVerifyExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        if(tts != null && !"".equals(tts)){
            Byte status = Byte.parseByte(tts);
            criteria.andStatusEqualTo(status);
        }
        return memberVerifyMapper.selectByExample(example);
    }

    @Transactional(readOnly = true)
    public MemberVerify getById(Integer id) {
        return memberVerifyMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void delete(Integer[] ids){
        MemberVerifyExample example = new MemberVerifyExample();
        MemberVerifyExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        memberVerifyMapper.deleteByExample(example);
    }

    @Transactional
    public void delete(Integer id){
        memberVerifyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量修改状态 0未审核; 1通过; 2拒绝; 3忽略; 4无法通过; 5删除
     *
     * @param ids
     * @param status
     */
    @Transactional
    public void update(Integer[] ids, Byte status) {
        String message = "";
        switch (status){
            case Constant.MEMBER_VERIFY_REFUSE: message = "申请被拒绝"; break;
            case Constant.MEMBER_VERIFY_IGNORE: message = "申请被忽略"; break;
        }
        MemberVerifyExample example = new MemberVerifyExample();
        MemberVerifyExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        MemberVerify verify = new MemberVerify();
        verify.setMessage(message);
        verify.setStatus(status);
        memberVerifyMapper.updateByExampleSelective(verify, example);
    }

    @Transactional
    public void pass(Integer[] ids){
        for (Integer id : ids) {
            MemberVerify memberVerify = getById(id);
            if (saveMember(memberVerify)) {
                memberVerify.setMessage("审核通过");
                memberVerify.setStatus((byte) Constant.MEMBER_VERIFY_PASS);
            } else {
                memberVerify.setMessage("重名无法通过");
                memberVerify.setStatus((byte) Constant.MEMBER_VERIFY_ERROR);
            }
            memberVerifyMapper.updateByPrimaryKeySelective(memberVerify);
        }
    }

    private boolean saveMember(MemberVerify memberVerify) {
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(memberVerify.getUserName());
        List<Member> members = memberMapper.selectByExample(example);
        if(members != null && !members.isEmpty()){
            return false;
        }

        Member member = new Member();
        member.setUserName(memberVerify.getUserName());
        member.setPassword(memberVerify.getPassword());
        member.setEmail(memberVerify.getEmail());
        member.setEncrypt(memberVerify.getEncrypt());
        member.setRegIp(memberVerify.getRegIp());
        member.setRegTime(memberVerify.getRegTime());
        member.setIsLock(false);
        member.setHasMessage(false);
        if (member.getMemberDetail() == null) {
            member.setMemberDetail(new MemberDetail());
        }

        memberMapper.insert(member);
        memberGroupMapper.updateMemberCountByPrimaryKey(member.getMemberGroupId());

        return true;
    }

    @Transactional
    public void save(MemberVerify memberVerify) {
        if(memberVerify.getId() == null){
            memberVerify.setRegTime(new Date());
            memberVerifyMapper.insert(memberVerify);
        } else {
            memberVerifyMapper.updateByPrimaryKey(memberVerify);
        }
    }
}
