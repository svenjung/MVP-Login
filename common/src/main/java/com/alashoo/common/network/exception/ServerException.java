package com.alashoo.common.network.exception;

import android.net.ParseException;

import com.alibaba.fastjson.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ServerException extends Exception {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;

    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    public static final int HTTP_ERROR = 1003;

    private int errorCode;
    private String errorMessage;

    public static ServerException newResponseException(int code, String message) {
        return new ServerException(code, message);
    }

    public static ServerException newRequestException(Throwable e) {
        ServerException ex;
        if (e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            ex = new ServerException(PARSE_ERROR, e.getMessage(), e);
            return ex;
        } else if (e instanceof ConnectException) {
            //网络错误
            ex = new ServerException(NETWORK_ERROR, e.getMessage(), e);
            return ex;
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            //连接错误
            ex = new ServerException(NETWORK_ERROR, e.getMessage(), e);
            return ex;
        } else {
            //未知错误
            ex = new ServerException(UNKNOWN, e.getMessage(), e);
            return ex;
        }
    }

    public ServerException(int code, String message) {
        super(message);
        errorCode = code;
        errorMessage = message;
    }

    public ServerException(int code, String message, Throwable cause) {
        super(cause);
        errorCode = code;
        errorMessage = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
