package com.ndf.demo;

import com.ndf.demo.dao.UserMapper;
import com.ndf.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

/**
 * Created by lin on 4/20/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryByName(){
        String userName = "test";
        User user = userMapper.queryByName(userName);
        System.out.println(user);
    }

    @Test
    public void insert(){
        User user = new User();
        user.setUserName("user" + (new Random().nextInt(10000)));
        user.setPassword("123456");
        user.setStatus(0);
        user.setCreateTime(new Date());

        System.out.println(user);
        userMapper.insert(user);
    }

}
