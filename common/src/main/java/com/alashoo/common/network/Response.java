package com.alashoo.common.network;

/**
 * 接口数据基本模型
 *
 * FastJson在序列化和反序列化操作时，是根据getter和setter匹配的。可以在实际业务的子类中重载
 */
public class Response<T> {
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
