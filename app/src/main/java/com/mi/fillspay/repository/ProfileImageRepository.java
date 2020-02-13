package com.mi.fillspay.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.ProfileImageResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileImageRepository {

    private ApiRequest apiRequest;

    public ProfileImageRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ProfileImageResponse> uploadProfileImage(File file, String mobile, String token, Context context) {

        // Creating a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);

        // Creating a MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);

        //Creating a request body with text mobile and text media type
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"),mobile);

        final MutableLiveData<ProfileImageResponse> data = new MutableLiveData<>();
        try {
            apiRequest.uploadProfileImage(part,name,token,"application/json").enqueue(new Callback<ProfileImageResponse>() {
                @Override
                public void onResponse(Call<ProfileImageResponse> call, Response<ProfileImageResponse> response) {
                 //   Log.d("idfldsf", response.body()+"");
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.i("Mallikarjuna", "+++sucess+++" + response.toString());
                    }
                }
                @Override
                public void onFailure(Call<ProfileImageResponse> call, Throwable t) {
                    data.setValue(null);
                    Log.i("Mallikarjuna", "+++error+" + t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
