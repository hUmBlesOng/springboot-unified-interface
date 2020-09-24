package com.hs.springboot.result;

/**
 * @author bys
 * @date 2020/9/24 10:08
 */
public class ResponseData<T> {

    /**
     * 状态码，比如1000代表响应成功
     */
    private int rtnCode;
    /**
     * 响应信息，用来说明响应情况
     */
    private String rtnMsg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResponseData(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResponseData(ResultCode resultCode, T data) {
        this.rtnCode = resultCode.getRtnCode();
        this.rtnMsg = resultCode.getRtnMsg();
        this.data = data;
    }

    public int getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(int rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
