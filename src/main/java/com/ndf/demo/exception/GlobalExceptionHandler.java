package com.ndf.demo.exception;

import com.ndf.demo.common.Response;
import com.ndf.demo.common.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lin on 4/20/18.
 * 统一异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = RestApiException.class)
    @ResponseBody
    public Response<String> jsonErrorHandler(HttpServletRequest req, RestApiException e) {
        Response<String> r = new Response<>(ResponseCode.ErrorCommon);
        if(StringUtils.isNotBlank(e.getErrCode())){
            r.setCode(e.getErrCode());
        }
        if(StringUtils.isNotBlank(e.getErrMsg())){
            r.setMsg(e.getErrMsg());
        }

        r.setData(e.getMessage());

        String errMsg = String.format("Exception Occur => requestUrl:%s, params:%s, errorMsg:"
                , req.getRequestURL().toString()
                , req.getParameterMap()
                , e.getMessage()
        );
        logger.error(errMsg, e);
        return r;
    }

}
