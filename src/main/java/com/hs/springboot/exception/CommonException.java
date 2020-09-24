package com.hs.springboot.exception;

import com.hs.springboot.result.ResultCode;

/**
 * @author bys
 * @date 2020/9/24 10:11
 */
public class CommonException extends RuntimeException {

    private int code;
    private String msg;

    public CommonException() {
        this(ResultCode.FAILED);
    }

    public CommonException(ResultCode failed) {
        this.code=failed.getRtnCode();
        this.msg=failed.getRtnMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
