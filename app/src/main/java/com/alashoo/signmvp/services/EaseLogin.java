package com.alashoo.signmvp.services;

import android.util.Log;

import com.alashoo.common.network.Response;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * 环信登录同步调用
 */
public class EaseLogin {

    private Response<Boolean> response = null;

    public Response<Boolean> getResponse() {
        synchronized (this) {
            while (response == null) {
                try {
                    wait();
                } catch (InterruptedException ignored) {
                }
            }
        }

        return response;
    }

    public EaseLogin login(String id, String pwd) {
        EMClient.getInstance().login(id, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.i("EMClientLogin", "## login success");
                synchronized (EaseLogin.this) {
                    response = new Response<>();
                    response.setStatus(200);
                    response.setMessage("环信SDK登录成功");
                    response.setData(true);
                    EaseLogin.this.notifyAll();
                }
            }

            @Override
            public void onError(int statusCode, String error) {
                Log.i("EMClientLogin", "## login error : " + statusCode + ", " + error);
                synchronized (EaseLogin.this) {
                    response = new Response<>();
                    response.setStatus(statusCode);
                    response.setMessage(error);
                    response.setData(false);
                    EaseLogin.this.notifyAll();
                }
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });

        return this;
    }
}
