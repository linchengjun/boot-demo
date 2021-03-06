package com.ndf.demo.dao;

import com.ndf.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by lin on 4/20/18.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE user_name = #{userName}")
    @Results(id = "userMap", value = {
            @Result(property = "userName", column = "user_name"),
            @Result(property = "createTime", column = "create_time")
    })
    User queryByName(@Param("userName") String userName);

    @Select("SELECT * FROM t_user")
    @ResultMap("userMap")
    List<User> findAll();

    @Insert("INSERT INTO t_user(user_name, password, status, create_time) VALUES(#{userName}, #{password}, #{status}, #{createTime})")
    void insert(User user);

}
