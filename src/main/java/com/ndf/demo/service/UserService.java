package com.ndf.demo.service;

import com.ndf.demo.domain.User;

/**
 * Created by lin on 4/20/18.
 */
public interface UserService {

    boolean register(User user);

    String encryptPwd(String userName, String rawPassword);

    User queryByName(String userName);

}
