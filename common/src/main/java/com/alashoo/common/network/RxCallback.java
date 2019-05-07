package com.alashoo.common.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class RxCallback<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
    }

    @Override
    public void onComplete() {
    }

    public void onStart() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailed(Throwable e);
}
