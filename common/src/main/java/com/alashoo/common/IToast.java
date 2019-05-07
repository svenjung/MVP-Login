package com.alashoo.common;

// 定义提示接口，可用Toast实现，可用弹框实现
public interface IToast {

    void show(int resId);

    void show(CharSequence text);

    void cancel();
}
