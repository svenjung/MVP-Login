package com.alashoo.signmvp.services;

import com.alashoo.common.network.Response;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SmsService {
    /**
     * 发送验证码
     *
     * @param mobile 接收验证码的手机号码
     * @param type   发送验证码类型 {@link com.alashoo.signmvp.util.Constant.SmsType}
     */
    @POST("api/v2/sms/send-sms")
    @FormUrlEncoded
    Observable<Response<String>> sendSms(@Field("mobile") String mobile,
                                         @Field("type") String type);

    @POST("api/v2/sms/verify")
    @FormUrlEncoded
    Observable<Response<String>> verifySms(@Field("mobile") String mobile,
                                           @Field("type") String type,
                                           @Field("code") String code);
}
