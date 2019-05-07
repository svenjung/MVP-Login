package com.alashoo.signmvp.services;

import com.alashoo.signmvp.model.Login;
import com.alashoo.signmvp.model.User;
import com.alashoo.signmvp.util.UserManager;
import com.alashoo.common.network.Response;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class ApiUtil {

    public static Observable<Response<Boolean>> loginSms(String mobile, String code) {
        final ApiService apiService = ApiService.get();
        return apiService.create(LoginService.class)
                // 获取登录Token
                .loginSms("", mobile, code, "WEB")
                // 获取账号信息
                .flatMap((Function<Response<Login>, ObservableSource<Response<User>>>) login -> {
                    // 保存登录token
                    UserManager userManager = UserManager.getInstance();
                    String token = login.getData().getAccessToken();
                    userManager.setAccessToken(token);
                    return getUser(login.getData());
                })
                // 登录环信SDK
                .flatMap(userResponse -> esmaLogin(userResponse.getData()));
    }

    public static Observable<Response<Boolean>> loginMobile(String mobile, String password) {
        final ApiService apiService = ApiService.get();
        return apiService.create(LoginService.class)
                .loginMobile("", mobile, password, "WEB")
                // 获取账号信息
                .flatMap((Function<Response<Login>, ObservableSource<Response<User>>>) login -> {
                    // 保存登录token
                    UserManager userManager = UserManager.getInstance();
                    String token = login.getData().getAccessToken();
                    userManager.setAccessToken(token);
                    return getUser(login.getData());
                })
                // 登录环信SDK
                .flatMap(userResponse -> esmaLogin(userResponse.getData()));
    }

    public static Observable<Response<User>> getUser(final Login login) {
        ApiService service = ApiService.get();
        return service.create(UserService.class).getUser(login.getAccessToken());
    }

    private static Observable<Response<Boolean>> esmaLogin(final User user) {
        return Observable.defer((Callable<ObservableSource<Response<Boolean>>>) () -> {
            // 保存用户信息
            UserManager userManager = UserManager.getInstance();
            userManager.setUser(user);

            EaseLogin easeLogin = new EaseLogin();
            easeLogin.login(user.getHxId(), user.getHxPwd());
            return Observable.just(easeLogin.getResponse());
        });
    }
}
