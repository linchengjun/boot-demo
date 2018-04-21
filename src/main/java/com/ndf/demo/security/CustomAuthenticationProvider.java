package com.ndf.demo.security;

import com.ndf.demo.common.RoleEnum;
import com.ndf.demo.domain.User;
import com.ndf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 4/20/18.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginFailureChecker loginFailureChecker;

    @Autowired
    private UserService userService;

    private static final String OVER_LIMIT_MSG = "登录失败次数已超过限制，明天再来登录";

    private static final String LOGIN_FAIL_MSG = "账户名或者密码错误，请重新输入";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(loginFailureChecker.hasOverLimit(authentication.getName())){
            throw new LockedException(OVER_LIMIT_MSG);
        }
        String loginName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantedAuthList = new ArrayList<>();
        if (validateUser(loginName, password, grantedAuthList)) {
            //登录成功，删除登录失败次数记录
            loginFailureChecker.removeFailureCheck(authentication.getName());

            Authentication auth = new UsernamePasswordAuthenticationToken(loginName, password, grantedAuthList);
            return auth;
        } else {
            //登录失败，增加失败次数
            loginFailureChecker.incrFailureTimes(authentication.getName());
            throw new BadCredentialsException(LOGIN_FAIL_MSG);
        }
    }

    public boolean validateUser(String loginName, String password, List<GrantedAuthority> grantedAuthList) {
        User user = userService.queryByName(loginName);
        if (user == null || loginName == null || password == null) {
            return false;
        }
        String encryptPassword = userService.encryptPwd(loginName, password);
        if (user.getPassword().equals(encryptPassword)) {
            //查询用户的角色
            grantedAuthList.add(new SimpleGrantedAuthority(RoleEnum.DEVELOP.getName()));
            return true;
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
