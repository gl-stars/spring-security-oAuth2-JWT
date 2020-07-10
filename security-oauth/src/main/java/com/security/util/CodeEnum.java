package com.security.util;

/**
 * 成功和失败的状态吗
 * <p>0：成功，1：失败</p>
 * @date 2020年 07月 10日 16:11
 **/
public enum CodeEnum {
    SUCCESS(0),
    ERROR(1);

    private Integer code;
    CodeEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
