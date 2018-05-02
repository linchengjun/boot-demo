package com.ndf.demo;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ndf.demo.dao.UserMapper;
import com.ndf.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
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

    @Test
    public void listByPage(){
        PageHelper.startPage(2, 2);
        List<User> userList = userMapper.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        String result = JSON.toJSONString(pageInfo);
        System.out.println(result);
    }

}
