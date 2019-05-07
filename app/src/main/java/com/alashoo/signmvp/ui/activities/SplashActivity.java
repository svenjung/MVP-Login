package com.alashoo.signmvp.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.alashoo.signmvp.R;
import com.alashoo.signmvp.ui.AbsActivity;
import com.alashoo.signmvp.ui.signin.LoginActivity;
import com.alashoo.signmvp.util.Constant;
import com.alashoo.signmvp.util.SpHelper;
import com.alashoo.signmvp.util.UserManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

// 闪屏界面
// 1. 判断是否首次打开APP 是 -> 跳转向导页面
// 2. 判断缓存账号信息是否存在 否 -> 跳转登录页面
// 3. 如若账号未设置地区语言信息 -> 跳转地区语言设置页面
// 4. 进入主页
public class SplashActivity extends AbsActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean showActionBar() {
        return false;
    }

    @Override
    protected void initView() {
        super.initView();
        delayEnter();
    }

    @SuppressLint("CheckResult")
    private void delayEnter() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    boolean firstLaunch = SpHelper.getValue(SplashActivity.this,
                            Constant.SP_KEY_SHOW_GUIDE, true);
                    if (firstLaunch) {
                        showGuide();
                    } else {
                        UserManager userManager = UserManager.getInstance();
                        if (userManager.getUser() == null) {
                            showSignIn();
                        } else {
                            // start home
                            showHome();
                        }
                    }
                });
    }

    private void showGuide() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void showSignIn() {
        LoginActivity.launch(this);
    }

    private void showHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    // 跳转地址语言初始化页面
    private void showInitAddressLanguage() {
    }

}
