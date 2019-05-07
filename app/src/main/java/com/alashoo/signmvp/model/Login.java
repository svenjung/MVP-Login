package com.alashoo.signmvp.model;

public class Login {
    private String accessToken;
    private boolean bind;
    private String bindingToken;
    private String userId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isBind() {
        return bind;
    }

    public void setBind(boolean bind) {
        this.bind = bind;
    }

    public String getBindingToken() {
        return bindingToken;
    }

    public void setBindingToken(String bindingToken) {
        this.bindingToken = bindingToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
