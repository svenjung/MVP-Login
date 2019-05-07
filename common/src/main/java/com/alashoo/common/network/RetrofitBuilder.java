package com.alashoo.common.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.alashoo.common.network.converter.FastJsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitBuilder {
    private final static String TAG = "Retrofit-Log";

    private Retrofit retrofit;

    private RetrofitBuilder(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    public static class Builder {
        private String baseUrl;
        private HttpLogger logger;
        private final List<Interceptor> interceptors;

        public Builder() {
            interceptors = new ArrayList<>();
        }

        public Builder setUrl(@NonNull String url) {
            this.baseUrl = url;
            return this;
        }

        public Builder setLogger(HttpLogger logger) {
            this.logger = logger;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public RetrofitBuilder build() {
            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

            HttpLoggingInterceptor loggingInterceptor;
            if (logger != null) {
                loggingInterceptor = new HttpLoggingInterceptor(new Logger() {
                    @Override
                    public void log(String message) {
                        logger.log(message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpBuilder.addInterceptor(loggingInterceptor);
            }

            for (Interceptor interceptor : interceptors) {
                httpBuilder.addInterceptor(interceptor);
            }

            httpBuilder.connectTimeout(20, TimeUnit.SECONDS);
            httpBuilder.readTimeout(20, TimeUnit.SECONDS);

            OkHttpClient client = httpBuilder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .build();

            return new RetrofitBuilder(retrofit);
        }
    }

    public interface HttpLogger {
        void log(String message);

        HttpLogger DEFAULT = message -> Log.i(TAG, message);
    }
}
