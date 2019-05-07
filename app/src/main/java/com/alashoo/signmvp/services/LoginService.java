package com.alashoo.signmvp.services;

import com.alashoo.signmvp.model.Login;
import com.alashoo.common.network.Response;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {
    @POST("api/v2/login/sms")
    @FormUrlEncoded
    Observable<Response<Login>> loginSms(@Header("X-Access-Token") String token,
                                         @Field("mobile") String mobile,
                                         @Field("code") String code,
                                         @Field("clientType") String type);

    @POST("api/v2/login/mobile")
    @FormUrlEncoded
    Observable<Response<Login>> loginMobile(@Header("X-Access-Token") String token,
                                            @Field("mobile") String mobile,
                                            @Field("password") String password,
                                            @Field("clientType") String type);
}
