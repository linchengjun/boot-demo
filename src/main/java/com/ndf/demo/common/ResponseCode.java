package com.ndf.demo.common;

/**
 * Created by lin on 4/20/18.
 */
public enum ResponseCode {

    Success("0", "OK")
    , ErrorCommon("110", "Error")
    ;

    private String code;

    private String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

}
