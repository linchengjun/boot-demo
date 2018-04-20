package com.ndf.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lin on 4/20/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private String code;
    private String msg;
    private T data;

    public Response() {
        this(ResponseCode.Success.getCode(), ResponseCode.Success.getDescription());
    }

    public Response(ResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getDescription());
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(T data) {
        this(ResponseCode.Success.getCode(), ResponseCode.Success.getDescription());
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response wrapCode(String code) {
        this.code = code;
        return this;
    }

    public Response wrapMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response wrapData(T data) {
        this.data = data;
        return this;
    }

    public Response addField(String key, Object value) {
        if (data == null) {
            data = (T) new HashMap<String, Object>();
        }
        if (data instanceof Map) {
            ((Map) data).put(key, value);
        }
        return this;
    }

}
