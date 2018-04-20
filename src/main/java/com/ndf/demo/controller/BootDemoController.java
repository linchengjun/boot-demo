package com.ndf.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lin on 4/20/18.
 */
@Controller
@RequestMapping(value = "/")
public class BootDemoController {

    @Value("${demo.time}")
    private String demoTime;

    @Value("${domain.url}")
    private String domainUrl;

    @RequestMapping("/defConfig")
    @ResponseBody
    public Map<String, Object> defConfig(){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("demoTime", demoTime);
        resultMap.put("domainUrl", domainUrl);
        return resultMap;
    }

}
