package com.alashoo.signmvp.logger;

public interface Logger {

    void d(String message);

    void d(String format, Object... formatArgs);

    void d(Throwable e, String format, Object... formatArgs);

    void i(String message);

    void i(String format, Object... formatArgs);

    void i(Throwable e, String format, Object... formatArgs);

    void w(String message);

    void w(String format, Object... formatArgs);

    void w(Throwable e, String format, Object... formatArgs);

    void e(String message);

    void e(String format, Object... formatArgs);

    void e(Throwable e, String format, Object... formatArgs);
}
