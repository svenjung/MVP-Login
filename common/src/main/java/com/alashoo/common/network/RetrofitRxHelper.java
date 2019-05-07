package com.alashoo.common.network;

import com.alashoo.common.network.exception.ErrorHandler;
import com.alashoo.common.network.schedulers.SchedulerProvider;

import io.reactivex.ObservableTransformer;

public class RetrofitRxHelper {

    private static ErrorHandler errorHandler = new ErrorHandler();
    private static SchedulerProvider schedulerProvider = new SchedulerProvider();

    // 添加错误处理和线程切换
    public static <T> ObservableTransformer<Response<T>, Response<T>> apply() {
        return upstream -> upstream.compose(errorHandler.apply())
                .compose(schedulerProvider.applySchedulers());
    }
}
