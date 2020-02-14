package com.mi.fillspay.retrofit;

import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.model.CheckMobileRequest;
import com.mi.fillspay.model.CheckMobileResponse;
import com.mi.fillspay.model.ConsumerNoFormatRequest;
import com.mi.fillspay.model.ConsumerNoFormatResponse;
import com.mi.fillspay.model.CountryRequest;
import com.mi.fillspay.model.IsdCode;
import com.mi.fillspay.model.LoginRequest;
import com.mi.fillspay.model.LoginResponse;
import com.mi.fillspay.model.ProcessPaymentRequest;
import com.mi.fillspay.model.ProcessPaymentResponse;
import com.mi.fillspay.model.ProfileImageResponse;
import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.model.ResponseData;
import com.mi.fillspay.model.SubutilityRequest;
import com.mi.fillspay.model.UserProfile;
import com.mi.fillspay.model.UtilitiesRequest;
import com.mi.fillspay.model.VerifyOtpRequest;
import com.mi.fillspay.model.ViewAmountDueRequest;
import com.mi.fillspay.model.ViewAmountDueResponse;

import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiRequest {

    @POST("/user_registration")
    Call<ResponseData> postRegister(@Body RegisterRequest body);

    @POST("/authenticate")
    Call<LoginResponse> postLogin(@Body LoginRequest body);

    @POST("/paykii/getCountries")
    Call<String[]> getCountries(@Body CountryRequest body, @Header("Authorization") String token);

    @POST("/paykii/getutilities")
    Call<String[]> getUtilities(@Body UtilitiesRequest body, @Header("Authorization") String token);

    @POST("/paykii/getsubutility")
    Call<String[]> getSubUtilities(@Body SubutilityRequest body, @Header("Authorization") String token);

    @POST("/paykii/listOfEachBillerTypeCatalog")
    Call<List<BillerDescResponse>> getBillerDesc(@Body BillerDescRequest body, @Header("Authorization") String token);

    @POST("/paykii/consumerNumberFormat")
    Call<ConsumerNoFormatResponse> getConsmerNoFarmat(@Body ConsumerNoFormatRequest body, @Header("Authorization") String token);

    @POST("/paykii/viewAmountDue")
    Call<ViewAmountDueResponse> getViewAmountDue(@Body ViewAmountDueRequest body, @Header("Authorization") String token);

    @POST("/paykii/viewAmountDue")
    Call<ProcessPaymentResponse> processPayment(@Body ProcessPaymentRequest body, @Header("Authorization") String token);

    @POST("/checkMobileNumber")
    Call<CheckMobileResponse> checkMobileNumber(@Body CheckMobileRequest body);

    @POST("/verifyMobileNumber")
    Call<CheckMobileResponse> getOtp(@Body CheckMobileRequest body);

    @POST("/checkVerifyMobileNumber")
    Call<ResponseData> verifyOtp(@Body VerifyOtpRequest body);

    @GET("/isd_codes")
    Call<List<IsdCode>> getIsdCodes();

    @GET("/user_profile")
    Call<UserProfile> getUserProfile(@Header("Authorization") String token);

    @Multipart
    @POST("uploadProfilePicture")
    Call<ProfileImageResponse> uploadProfileImage(@Part MultipartBody.Part file, @Part("name") RequestBody name, @Header("Authorization") String token, @Header("Accept") String contentType);
}
