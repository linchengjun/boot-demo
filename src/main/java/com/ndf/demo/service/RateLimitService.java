package com.ndf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by lin on 4/20/18.
 */
@Component
public class RateLimitService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final long LIMIT_NUM = 5l;

    private String buildCurrentMinuteKey(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        String key = sdf.format(date);
        return key;
    }

    public boolean hasLimit(){
        String key = buildCurrentMinuteKey();
        Long val = redisTemplate.opsForValue().increment(key, 1l);
        if(val == 1L){
            redisTemplate.expire(key, 1l, TimeUnit.MINUTES);
        }
        if(val > LIMIT_NUM){
            return true;
        }
        return false;
    }

}
