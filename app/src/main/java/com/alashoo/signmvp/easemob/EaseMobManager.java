package com.alashoo.signmvp.easemob;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.alashoo.signmvp.AlaApplication;
import com.alashoo.signmvp.logger.MLog;
import com.alashoo.signmvp.ui.activities.SignInActivity;
import com.alashoo.signmvp.util.UserManager;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.util.EMLog;

/**
 * 环信全局管理器
 * <p>
 * 初始化后注册全局连接监听，账号异常后退到登录界面
 */
public class EaseMobManager {

    private static EaseMobManager instance;

    private Application application;

    private EaseMobManager() {
        application = AlaApplication.getInstance();
    }

    public static EaseMobManager get() {
        if (instance == null) {
            synchronized (EaseMobManager.class) {
                if (instance == null) {
                    instance = new EaseMobManager();
                }
            }
        }

        return instance;
    }

    public void initEaseMobSdk() {
        if (!EaseUI.getInstance().isMainProcess(application)) {
            return;
        }
        EMLog.debugMode = true;
        EaseUI.getInstance().init(application, null);
        EMClient.getInstance().setDebugMode(true);

        application.registerActivityLifecycleCallbacks(lifecycleCallbacks);
        EMClient.getInstance().addConnectionListener(connectionListener);
    }

    public void restartLogin() {
        Activity topActivity = EaseUI.getInstance().getTopActivity();
        if (topActivity != null) {
            UserManager.getInstance().clear();

            Intent intent = new Intent(topActivity, SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            topActivity.startActivity(intent);
            topActivity.finish();
        }
    }

    private EMConnectionListener connectionListener = new EMConnectionListener() {

        @Override
        public void onDisconnected(int error) {
            MLog.get().e("[EaseMobManager]EaseMob onDisconnected : " + error);
            if (error == EMError.USER_REMOVED || error == EMError.USER_LOGIN_ANOTHER_DEVICE
                    || error == EMError.USER_KICKED_BY_CHANGE_PASSWORD
                    || error == EMError.USER_KICKED_BY_OTHER_DEVICE) {
                // isConflict = true;
                // 账号登录异常，需要重新登录
                UserManager.getInstance().clear();
                restartLogin();
            }
        }

        @Override
        public void onConnected() {
            MLog.get().e("[EaseMobManager]EaseMob onConnected");
        }
    };

    private Application.ActivityLifecycleCallbacks lifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            EaseUI.getInstance().pushActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            EaseUI.getInstance().popActivity(activity);
        }
    };
}
