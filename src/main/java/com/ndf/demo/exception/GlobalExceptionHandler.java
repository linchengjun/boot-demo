package com.ndf.demo.exception;

import com.ndf.demo.common.Response;
import com.ndf.demo.common.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lin on 4/20/18.
 * 统一异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<String> jsonErrorHandler(HttpServletRequest req, Exception e) {
        Response<String> r = new Response<>(ResponseCode.ErrorCommon);
        if(e instanceof RestApiException){
            RestApiException ex = (RestApiException)e;
            if(StringUtils.isNotBlank(ex.getErrCode())){
                r.setCode(ex.getErrCode());
            }
            if(StringUtils.isNotBlank(ex.getErrMsg())){
                r.setMsg(ex.getErrMsg());
            }
        }else {
            r.setMsg(e.getMessage());
        }

        String errMsg = String.format("Exception Occur => requestUrl:%s, params:%s, errorMsg:"
                , req.getRequestURL().toString()
                , req.getParameterMap()
                , e.getMessage()
        );
        logger.error(errMsg, e);
        return r;
    }

}
