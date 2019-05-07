package com.alashoo.signmvp.services;

import com.alashoo.signmvp.model.Login;
import com.alashoo.signmvp.model.User;
import com.alashoo.common.network.Response;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {
    @GET("api/v2/user/current")
    Observable<Response<User>> getUser(
            @Header("X-Access-Token") String token
    );

    @FormUrlEncoded
    @POST("api/v2/login/mobile")
    Observable<Response<Login>> passwordLogin(
            @Header("X-Access-Token") String token,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("clientType") String clientType
    );

    @FormUrlEncoded
    @POST("api/v2/login/sms")
    Observable<Response<Login>> smsLogin(
            @Header("X-Access-Token") String token,
            @Field("mobile") String mobile,
            @Field("code") String smsCode,
            @Field("clientType") String clientType
    );
}
