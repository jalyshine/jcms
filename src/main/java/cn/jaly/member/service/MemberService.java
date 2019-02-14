package cn.jaly.member.service;

import cn.jaly.member.dao.MemberDetailMapper;
import cn.jaly.member.dao.MemberGroupMapper;
import cn.jaly.member.dao.MemberMapper;
import cn.jaly.member.entity.Member;
import cn.jaly.member.entity.MemberDetail;
import cn.jaly.member.entity.MemberDetailExample;
import cn.jaly.member.entity.MemberExample;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private MemberGroupMapper memberGroupMapper;

	@Autowired
	private MemberDetailMapper memberDetailMapper;

	@Transactional(readOnly = true)
	public List<Member> queryForList(Integer siteId, Byte tts, Integer grp,
									 String stm, String edm, String kwd, String odr){
		Date startTime = DateTimeUtils.parseSimple(stm);
		Date endTime = DateTimeUtils.parseSimple(edm);
		if(kwd != null && !"".equals(kwd)){
			kwd = "%" + kwd + "%";
		}
		Boolean status = null;
		if (tts != null){
			status = (tts == (byte)1);
		}
		return memberMapper.selectWithGroup(siteId, status, grp, startTime, endTime, kwd, odr);
	}

	@Transactional(readOnly=true)
	public Member getById(Integer id){
		Member member = memberMapper.selectByPrimaryKey(id);
		if(member != null){
			MemberDetailExample example = new MemberDetailExample();
			MemberDetailExample.Criteria criteria = example.createCriteria();
			criteria.andMemberIdEqualTo(id);
			List<MemberDetail> memberDetails = memberDetailMapper.selectByExample(example);
			if(memberDetails != null && memberDetails.size() == 1){
				member.setMemberDetail(memberDetails.get(0));
			} else {
				member.setMemberDetail(null);
			}
		}
		return member;
	}

	@Transactional(readOnly=true)
	public Integer getIdByUserName(String userName){
		Member member = getByUserName(userName);
		if(member != null){
			return member.getId();
		}
		return null;
	}

	@Transactional(readOnly=true)
	public Member getByUserName(String userName){
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andUserNameEqualTo(userName);
		List<Member> members = memberMapper.selectByExample(memberExample);
		if(members != null && !members.isEmpty()){
			return members.get(0);
		}
		return null;
	}

	@Transactional(readOnly=true)
	public String getUserNameByPhone(String phoneNumber){
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andPhoneEqualTo(phoneNumber);
		List<Member> members = memberMapper.selectByExample(memberExample);
		if(members != null && !members.isEmpty()){
			return members.get(0).getUserName();
		}
		return null;
	}

	@Transactional
	public void save(Member member){
		if(member.getId() == null){
			member.setRegTime(new Date());
			memberMapper.insert(member);
			memberGroupMapper.updateMemberCountByPrimaryKey(member.getMemberGroupId());
		} else {
			memberMapper.updateByPrimaryKeySelective(member);
			MemberDetailExample example = new MemberDetailExample();
			MemberDetailExample.Criteria criteria = example.createCriteria();
			criteria.andMemberIdEqualTo(member.getId());
			List<MemberDetail> memberDetails = memberDetailMapper.selectByExample(example);
			if(memberDetails == null || memberDetails.isEmpty()){
				member.getMemberDetail().setMemberId(member.getId());
				memberDetailMapper.insertSelective(member.getMemberDetail());
			} else {
				memberDetailMapper.updateByExampleSelective(member.getMemberDetail(), example);
			}
		}
	}

	@Transactional
	public void upateBySelective(Member member){
		memberMapper.updateByPrimaryKeySelective(member);
	}
	
	@Transactional
	public void delete(Integer id){
		Integer memberGroupId = memberMapper.selectByPrimaryKey(id).getMemberGroupId();
		memberDetailMapper.deleteByPrimaryKey(id);
		memberMapper.deleteByPrimaryKey(id);
		memberGroupMapper.updateMemberCountByPrimaryKey(memberGroupId);
	}

	@Transactional
	public void delete(Integer[] mid){
		for(Integer id : mid){
			delete(id);
		}
	}

	@Transactional
	public void move(Integer groupId, Integer[] mid) {
		Member member = new Member();
		member.setMemberGroupId(groupId);

		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdIn(Arrays.asList(mid));
		memberMapper.updateByExampleSelective(member, memberExample);
		// 更新会员数量
		memberGroupMapper.updateMemberCountByPrimaryKey(groupId);
		for(Integer id : mid){
			member = memberMapper.selectByPrimaryKey(id);
			memberGroupMapper.updateMemberCountByPrimaryKey(member.getMemberGroupId());
		}
	}

	@Transactional
	public void lock(Boolean isLock, Integer[] mid) {
		Member member = new Member();
		member.setIsLock(isLock);

		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdIn(Arrays.asList(mid));
		memberMapper.updateByExampleSelective(member, memberExample);
	}

}
