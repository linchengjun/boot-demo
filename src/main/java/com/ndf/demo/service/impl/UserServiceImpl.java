package com.ndf.demo.service.impl;

import com.ndf.demo.dao.UserMapper;
import com.ndf.demo.domain.User;
import com.ndf.demo.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lin on 4/21/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        if(user == null
                || StringUtils.isBlank(user.getUserName())
                || StringUtils.isBlank(user.getPassword())){
            return false;
        }
        String encryptPwd = encryptPwd(user.getUserName(), user.getPassword());
        user.setPassword(encryptPwd);
        user.setCreateTime(new Date());
        user.setStatus(0);

        userMapper.insert(user);

        return true;
    }

    @Override
    public String encryptPwd(String userName, String rawPassword) {
        String encryptPassword = DigestUtils.sha256Hex(userName
                + DigestUtils.md5Hex(userName)
                + DigestUtils.sha256Hex(rawPassword)
        );
        return encryptPassword;
    }

    @Override
    public User queryByName(String userName) {
        if(StringUtils.isBlank(userName)){
            return null;
        }
        return userMapper.queryByName(userName);
    }
}
