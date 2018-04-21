package com.ndf.demo.common;

/**
 * Created by lin on 4/20/18.
 */
public enum RoleEnum {

    DEVELOP("DEVELOP");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
