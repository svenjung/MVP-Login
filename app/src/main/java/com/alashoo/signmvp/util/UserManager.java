package com.alashoo.signmvp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import com.alashoo.signmvp.AlaApplication;
import com.alashoo.signmvp.logger.MLog;
import com.alashoo.signmvp.model.User;
import com.alibaba.fastjson.JSON;

/**
 * 全局账号信息管理器
 * 这里不存储环信账号相关信息，设置自动登录后托管环信账号
 */
@SuppressLint("StaticFieldLeak")
public class UserManager {

    private static volatile UserManager instance;

    private Context mContext;

    private User mUser;
    private String mAccessToken;

    private UserManager(Context context) {
        mContext = context;
        mUser = getUserFromPreference(context);
        mAccessToken = getTokenFromPreference(context);
    }

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager(AlaApplication.getInstance());
                }
            }
        }

        return instance;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
        setUserToPreference(mContext, user);
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String token) {
        mAccessToken = token;
        setTokenToPreference(mContext, token);
    }

    public void clear() {
        setAccessToken("");
        setUser(null);
    }

    /**
     * 从本地设置中读取用户信息
     */
    private User getUserFromPreference(Context context) {
        String userText = SpHelper.getValue(context, Constant.SP_KEY_USER, "");
        if (TextUtils.isEmpty(userText)) {
            return null;
        }

        try {
            return JSON.parseObject(userText, User.class);
        } catch (Exception e) {
            MLog.get().e("Parse User from preference failed, %s", e.getMessage());
            return null;
        }
    }

    private void setUserToPreference(Context context, User user) {
        String userText = user == null ? "" : JSON.toJSONString(user);
        SpHelper.setValue(context, Constant.SP_KEY_USER, userText);
    }

    private String getTokenFromPreference(Context context) {
        return SpHelper.getValue(context, Constant.SP_KEY_TOKEN, "");
    }

    private void setTokenToPreference(Context context, String token) {
        SpHelper.setValue(context, Constant.SP_KEY_TOKEN, token);
    }
}
