package com.chzu.ice.chat.pojo;

public class ResponseJ<T> {
    private String code;
    private String msg;
    private T data;

    public ResponseJ(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
