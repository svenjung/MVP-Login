package com.alashoo.common.network.exception;

import com.alashoo.common.network.Response;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class ErrorHandler {

    /**
     * 网络请求全局错误处理
     * 在api service请求调用后添加
     * Usage :
     * o.compose(apply());
     */
    public <T> ObservableTransformer<Response<T>, Response<T>> apply() {
        return upstream -> upstream
                .onErrorResumeNext(new RequestError<>())
                .flatMap(new ResponseError<>());
    }

    // 请求层错误处理
    public static class RequestError<T> implements
            Function<Throwable, ObservableSource<? extends Response<T>>> {

        @Override
        public ObservableSource<? extends Response<T>> apply(Throwable throwable) {
            return Observable.error(ServerException.newRequestException(throwable));
        }
    }

    // 返回层错误处理
    public static class ResponseError<T> implements
            Function<Response<T>, ObservableSource<? extends Response<T>>> {

        @Override
        public ObservableSource<Response<T>> apply(Response<T> response) throws Exception {
            int code = response.getStatus();
            if (code == 200) {
                return Observable.just(response);
            } else {
                return Observable.error(ServerException.newResponseException(code, response.getMessage()));
            }
        }
    }
}
