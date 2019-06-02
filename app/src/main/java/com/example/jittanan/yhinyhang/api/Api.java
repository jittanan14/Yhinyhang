package com.example.jittanan.yhinyhang.api;

import com.example.jittanan.yhinyhang.DefaultResponse;
import com.example.jittanan.yhinyhang.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("create_user.php")
    Call<DefaultResponse> createUser(
            @Field("email_user") String email_user,
            @Field("pass_user") String pass_user,
            @Field("name_user") String name_user,
            @Field("gender_user") String gender_user,
            @Field("Birth_user") String Birth_user,
            @Field("element_user") String element_user,
            @Field("food_user") String food_user,
            @Field("Pic_user") String Pic_user
    );

    @FormUrlEncoded
    @POST("loginuser.php")
    Call<LoginResponse> userLogin(
            @Field("email_user") String email,
            @Field("pass_user") String password
    );
}
