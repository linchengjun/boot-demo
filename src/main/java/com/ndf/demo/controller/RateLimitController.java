package com.ndf.demo.controller;

import com.ndf.demo.common.Response;
import com.ndf.demo.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lin on 4/20/18.
 */
@RestController
public class RateLimitController {

    @Autowired
    private RateLimitService rateLimitService;

    @RequestMapping("/limit")
    public Response<Boolean> limitByMinute(){
        boolean flag = rateLimitService.hasLimit();
        return new Response<>(flag);
    }

}
