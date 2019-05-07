package com.alashoo.signmvp.services;

import com.alashoo.common.network.RetrofitBuilder;

public class ApiService {
    private static final String HOST = "https://app.alashoo.com/xalashoo/";

    private RetrofitBuilder mRetrofit;

    private ApiService() {
        mRetrofit = new RetrofitBuilder.Builder()
                .setLogger(RetrofitBuilder.HttpLogger.DEFAULT)
                .setUrl(HOST)
                .build();
    }

    private static volatile ApiService instance;

    public static ApiService get() {
        if (instance == null) {
            synchronized (ApiService.class) {
                if (instance == null) {
                    instance = new ApiService();
                }
            }
        }

        return instance;
    }

    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }
}
