package com.mi.fillspay.retrofit;


import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.model.ConsumerNoFormatRequest;
import com.mi.fillspay.model.ConsumerNoFormatResponse;
import com.mi.fillspay.model.CountryRequest;
import com.mi.fillspay.model.LoginRequest;
import com.mi.fillspay.model.LoginResponse;
import com.mi.fillspay.model.ProcessPaymentRequest;
import com.mi.fillspay.model.ProcessPaymentResponse;
import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.model.ResponseData;
import com.mi.fillspay.model.SubutilityRequest;
import com.mi.fillspay.model.UtilitiesRequest;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.model.ViewAmountDueRequest;
import com.mi.fillspay.model.ViewAmountDueResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiRequest {

    @POST("/user_registration")
    Call<ResponseData> postRegister(@Body RegisterRequest body);

    @POST("/authenticate")
    Call<LoginResponse> postLogin(@Body LoginRequest body);

    @POST("/paykii/getCountries")
    Call<String[]> getCountries(@Body CountryRequest body, @Header("Authorization") String token );

    @POST("/paykii/getutilities")
    Call<String[]> getUtilities(@Body UtilitiesRequest body, @Header("Authorization") String token );

    @POST("/paykii/getsubutility")
    Call<String> getSubUtilities(@Body SubutilityRequest body, @Header("Authorization") String token );

    @POST("/paykii/listOfEachBillerTypeCatalog")
    Call<List<BillerDescResponse>> getBillerDesc(@Body BillerDescRequest body, @Header("Authorization") String token );

    @POST("/paykii/consumerNumberFormat")
    Call<ConsumerNoFormatResponse> getConsmerNoFarmat(@Body ConsumerNoFormatRequest body, @Header("Authorization") String token );

    @POST("/paykii/viewAmountDue")
    Call<ViewAmountDueResponse> getViewAmountDue(@Body ViewAmountDueRequest body, @Header("Authorization") String token );

    @POST("/paykii/viewAmountDue")
    Call<ProcessPaymentResponse> processPayment(@Body ProcessPaymentRequest body, @Header("Authorization") String token );

}
