package com.alashoo.signmvp.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class Utils {
    // 简单的11位号码正则表达式
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[1]([3-9])[0-9]{9}$");

    public static boolean isValidPhone(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            return PHONE_PATTERN.matcher(phone).matches();
        }

        return false;
    }
}
