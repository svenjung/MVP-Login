package com.alashoo.signmvp.ui.signin;

import android.content.Context;

import com.alashoo.signmvp.BasePresenter;
import com.alashoo.signmvp.BaseView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

public interface LoginContact {

    interface View extends BaseView<Presenter> {
        // 设置获取验证码按钮是否可用
        void setSendSmsButtonEnable(boolean enable);
        // 设置获取验证码按钮文字
        void setSendSmsButtonText(String text);

        void showSendSmsCodeError(String errMessage);

        void showLoginError(String errMessage);

        void showLoginSuccess();

        Context getContext();

        LifecycleProvider<ActivityEvent> getLifecycleProvider();
    }

    interface Presenter extends BasePresenter {
        void sendSmsCode(String mobile);

        void mobileLogin(String mobile, String password);

        void smsLogin(String mobile, String smsCode);
    }
}
