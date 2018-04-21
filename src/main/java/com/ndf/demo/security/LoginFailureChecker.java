package com.ndf.demo.security;

import org.apache.commons.lang3.StringUtils;
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
public class LoginFailureChecker {

    private static final String EMPTY_USER_NAME = "empty";

    private static final long MAX_FAIL_TIMES = 5;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String buildCurrentDayStr(){
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(currentDate);
    }

    public String buildFailureCacheKey(String userName){
        if(StringUtils.isBlank(userName)){
            userName = EMPTY_USER_NAME;
        }
        String dayStr = buildCurrentDayStr();
        String cacheKey = String.format("login-fail-check-%s-%s", dayStr, userName);
        return  cacheKey;
    }

    public void incrFailureTimes(String userName){
        String cacheKey = buildFailureCacheKey(userName);
        Long cacheVal = redisTemplate.opsForValue().increment(cacheKey, 1L);
        if(cacheVal == 1L){
            redisTemplate.expire(cacheKey, 1L, TimeUnit.DAYS);
        }
    }

    public boolean hasOverLimit(String userName){
        String cacheKey = buildFailureCacheKey(userName);
        String cacheVal = redisTemplate.opsForValue().get(cacheKey);
        if(cacheVal == null){
            return false;
        }
        Long cacheLongVal = Long.valueOf(cacheVal);
        if(cacheLongVal > MAX_FAIL_TIMES){
            return true;
        }
        return false;
    }

    public void removeFailureCheck(String userName){
        String cacheKey = buildFailureCacheKey(userName);
        redisTemplate.delete(cacheKey);
    }

}
