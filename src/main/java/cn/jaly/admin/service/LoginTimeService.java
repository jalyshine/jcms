package cn.jaly.admin.service;

import cn.jaly.admin.dao.LoginTimesMapper;
import cn.jaly.admin.entity.LoginTimes;
import cn.jaly.admin.entity.LoginTimesExample;
import cn.jaly.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoginTimeService {

    @Autowired
    private LoginTimesMapper loginTimesMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @Transactional(readOnly = true)
    public LoginTimes getByUserName(String userName, Boolean isAdmin){
        LoginTimesExample example = new LoginTimesExample();
        LoginTimesExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andIsAdminEqualTo(isAdmin);
        List<LoginTimes> loginTimes = loginTimesMapper.selectByExample(example);
        if(loginTimes == null || loginTimes.isEmpty()){
            return null;
        }
        return loginTimes.get(0);
    }

    @Transactional
    public void save(LoginTimes loginTimes){
        if(loginTimes.getId() == null){
            Boolean isAdmin = loginTimes.getIsAdmin();
            String userName = loginTimes.getUserName();
            Integer uid = null;
            if(isAdmin){
                uid = adminService.getIdByUserName(userName);
            } else {
                uid = memberService.getIdByUserName(userName);
            }
            if(uid != null){
                loginTimesMapper.insert(loginTimes);
            }
        } else {
            loginTimesMapper.updateByPrimaryKeySelective(loginTimes);
        }
    }

    @Transactional
    public void delete(String userName, Boolean isAdmin){
        LoginTimesExample example = new LoginTimesExample();
        LoginTimesExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andIsAdminEqualTo(isAdmin);
        loginTimesMapper.deleteByExample(example);
    }

}
