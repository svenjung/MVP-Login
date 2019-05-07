package com.alashoo.signmvp.ui.signin;

import androidx.annotation.NonNull;

import com.alashoo.signmvp.R;
import com.alashoo.signmvp.logger.MLog;
import com.alashoo.signmvp.model.Login;
import com.alashoo.signmvp.services.ApiService;
import com.alashoo.signmvp.services.SmsService;
import com.alashoo.signmvp.services.UserService;
import com.alashoo.signmvp.util.Constant;
import com.alashoo.common.network.Response;
import com.alashoo.common.network.RxCallback;
import com.alashoo.common.network.exception.ErrorHandler;
import com.alashoo.common.network.schedulers.SchedulerProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContact.Presenter {

    private LoginContact.View mView;

    public LoginPresenter(@NonNull LoginContact.View view) {
        mView = view;
    }

    @Override
    public void sendSmsCode(String mobile) {
        SmsService service = ApiService.get().create(SmsService.class);
        service.sendSms(mobile, Constant.SmsType.SMS_TYPE_V2_REGISTER)
                // 绑定生命周期
                .compose(mView.getLifecycleProvider().bindUntilEvent(ActivityEvent.STOP))
                // 错误处理
                .compose(new ErrorHandler().apply())
                // 指定线程
                .compose(new SchedulerProvider().applySchedulers())
                // 订阅结果
                .subscribe(new RxCallback<Response<String>>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        startSmsCount();
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mView.showSendSmsCodeError(e.getMessage());
                    }
                });
    }

    @Override
    public void mobileLogin(String mobile, String password) {
        UserService service = ApiService.get().create(UserService.class);
        service.passwordLogin("", mobile, password, "WEB")
                .compose(mView.getLifecycleProvider().bindUntilEvent(ActivityEvent.STOP))
                .compose(new ErrorHandler().apply())
                .compose(new SchedulerProvider().applySchedulers())
                .subscribe(new RxCallback<Response<Login>>() {
                    @Override
                    public void onSuccess(Response<Login> userResponse) {
                        MLog.get().i("Mobile login success");
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        MLog.get().e(e, "Mobile login failed");
                        mView.showLoginError(e.getMessage());
                    }
                });
    }

    @Override
    public void smsLogin(String mobile, String smsCode) {
        UserService service = ApiService.get().create(UserService.class);
        service.smsLogin("", mobile, smsCode, "WEB")
                .compose(mView.getLifecycleProvider().bindUntilEvent(ActivityEvent.STOP))
                .compose(new ErrorHandler().apply())
                .compose(new SchedulerProvider().applySchedulers())
                .subscribe(new RxCallback<Response<Login>>() {
                    @Override
                    public void onSuccess(Response<Login> userResponse) {
                        MLog.get().i("Sms code login success");
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        MLog.get().i(e, "Sms code login failed");
                        mView.showLoginError(e.getMessage());
                    }
                });
    }

    private void startSmsCount() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .compose(mView.getLifecycleProvider().bindUntilEvent(ActivityEvent.STOP))
                .take(20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.setSendSmsButtonEnable(false);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        String delay = mView.getContext()
                                .getResources()
                                .getString(R.string.login_send_sms_code_delay, (int) (20 - aLong));
                        mView.setSendSmsButtonText(delay);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setSendSmsButtonText(mView.getContext().getResources()
                                .getString(R.string.login_send_sms_code));
                        mView.setSendSmsButtonEnable(true);
                    }

                    @Override
                    public void onComplete() {
                        mView.setSendSmsButtonText(mView.getContext().getResources()
                                .getString(R.string.login_send_sms_code));
                        mView.setSendSmsButtonEnable(true);
                    }
                });
    }
}
