package com.ndf.demo.controller;

import com.ndf.demo.common.Response;
import com.ndf.demo.domain.User;
import com.ndf.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lin on 4/20/18.
 */
@Controller
public class BootDemoController {

    @Value("${demo.time}")
    private String demoTime;

    @Value("${domain.url}")
    private String domainUrl;

    @Autowired
    private UserService userService;

    @RequestMapping("/defConfig")
    @ResponseBody
    public Map<String, Object> defConfig(){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("demoTime", demoTime);
        resultMap.put("domainUrl", domainUrl);
        return resultMap;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
        mv.addObject("msg", "Hello Everyone!");
        return "/index";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response<Boolean> register(User user, HttpServletRequest request, HttpServletResponse response){
        boolean flag = userService.register(user);
        return new Response<>(flag);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
        mv.setViewName("/login");
        return mv;
    }

    @RequestMapping(value = "/hello")
    public ModelAndView showHello(ModelAndView mv, @RequestParam(value = "name", required = false) String name
            , HttpServletRequest request, HttpServletResponse response){

        mv.addObject("name", StringUtils.isBlank(name) ? "anonymous" : name.trim());

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(currentDate);

        mv.addObject("time", currentTime);

        mv.setViewName("/hello");
        return mv;
    }

    @RequestMapping(value = "/testException")
    @ResponseBody
    public Response<Integer> testException(HttpServletRequest request, HttpServletResponse response) throws Exception{
        throw new RuntimeException("test exception");
    }

}
