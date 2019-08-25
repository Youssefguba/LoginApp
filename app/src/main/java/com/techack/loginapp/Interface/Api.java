package com.techack.loginapp.Interface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("LoginUser")
    Call<ResponseBody> userRegisteration(
            @Field("login") String login,
            @Field("password") String password
            );

}
