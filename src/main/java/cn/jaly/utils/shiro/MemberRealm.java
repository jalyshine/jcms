package cn.jaly.utils.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jaly.member.dao.MemberMapper;
import cn.jaly.member.entity.Member;
import cn.jaly.member.entity.MemberExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRealm extends AuthorizingRealm {

    @Autowired
    private MemberMapper memberMapper;

    private Member member;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Object username = principals.getPrimaryPrincipal();

        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andUserNameEqualTo(username.toString());
        List<Member> members = memberMapper.selectByExample(memberExample);

        if (members == null || members.isEmpty()) {
            return null;
        }
        member = members.get(0);

        Set<String> roles = new HashSet<>();
        //所有会员均有[Member]角色
        roles.add("Member");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        // 把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken token2 = (UsernamePasswordToken) token;

        // 从UsernamePasswordToken中获取Username
        String username = token2.getUsername();

        // 从数据库中查询username所对应的用户信息
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        List<Member> members = memberMapper.selectByExample(memberExample);

        // 根据表单提交的用户名和数据库查询的结果，比对后抛出对应的异常。
        if (members == null || members.isEmpty()) {
            throw new UnknownAccountException("用户不存在！");
        }

        member = members.get(0);
        if (member.getIsLock()) {
            throw new LockedAccountException("用户被锁定！");
        }

        // 根据用户情况构建 AuthenticationInfo 对象并返回，通常使用的实现类为: SimpleAuthenticationInfo
        // 以下信息是从数据库中获取的
        // principal：认证的实体信息，可以是 userName, 也可以是数据表对应的用户的实体类对象。
        Object principal = username;
        // credentials：密码。
        Object credentials = member.getPassword();
        // realmName：当前 realm 对象的 name，调用父类的 getName() 方法即可
        String realmName = this.getName();
        // 将用户名作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(member.getEncrypt());

        // 作为 ShiroRealm 的 doGetAuthenticationInfo 方法的返回值
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,
                credentials, credentialsSalt, realmName);

        return info;

    }

}
