package com.mi.fillspay.retrofit;


import com.mi.fillspay.model.LoginRequest;
import com.mi.fillspay.model.LoginResponse;
import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequest {

    @POST("/user_registration")
    Call<ResponseData> postRegister(@Body RegisterRequest body);

    @POST("/authenticate")
    Call<LoginResponse> postLogin(@Body LoginRequest body);


}
