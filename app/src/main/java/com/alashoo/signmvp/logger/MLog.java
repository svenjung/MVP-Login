package com.alashoo.signmvp.logger;

import timber.log.Timber;

public class MLog implements Logger {
    private String mTag = "Alasioo";

    private static volatile MLog log = null;

    private MLog() {
        Timber.plant(new Timber.DebugTree());
    }

    public static MLog get() {
        if (log == null) {
            synchronized (MLog.class){
                if (log == null) {
                    log = new MLog();
                }
            }
        }

        return log;
    }

    public MLog tag(String tag) {
        mTag = tag;
        return this;
    }

    @Override
    public void d(String message) {
        Timber.tag(mTag).d(message);
    }

    @Override
    public void d(String format, Object... formatArgs) {
        Timber.tag(mTag).d(format, formatArgs);
    }

    @Override
    public void d(Throwable e, String format, Object... formatArgs) {
        Timber.tag(mTag).d(e, format, formatArgs);
    }

    @Override
    public void i(String message) {
        Timber.tag(mTag).i(message);
    }

    @Override
    public void i(String format, Object... formatArgs) {
        Timber.tag(mTag).i(format, formatArgs);
    }

    @Override
    public void i(Throwable e, String format, Object... formatArgs) {
        Timber.tag(mTag).i(e, format, formatArgs);
    }

    @Override
    public void w(String message) {
        Timber.tag(mTag).i(message);
    }

    @Override
    public void w(String format, Object... formatArgs) {
        Timber.tag(mTag).i(format, formatArgs);
    }

    @Override
    public void w(Throwable e, String format, Object... formatArgs) {
        Timber.tag(mTag).i(e, format, formatArgs);
    }

    @Override
    public void e(String message) {
        Timber.tag(mTag).e(message);
    }

    @Override
    public void e(String format, Object... formatArgs) {
        Timber.tag(mTag).e(format, formatArgs);
    }

    @Override
    public void e(Throwable e, String format, Object... formatArgs) {
        Timber.tag(mTag).e(e, format, formatArgs);
    }
}
