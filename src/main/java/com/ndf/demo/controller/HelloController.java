package com.ndf.demo.controller;

import com.ndf.demo.common.Response;
import com.ndf.demo.exception.RestApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lin on 4/20/18.
 */
@Controller
public class HelloController {

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
        int[] arr = {1,2,3};
        throw new RestApiException("error", "111", "error");
    }

}
