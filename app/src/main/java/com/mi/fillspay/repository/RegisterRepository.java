package com.mi.fillspay.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.model.ResponseData;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {
    private ApiRequest apiRequest;

    public RegisterRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ResponseData> postRegisterRepository(RegisterRequest registerRequest) {
        final MutableLiveData<ResponseData> data = new MutableLiveData<>();
        try {
            apiRequest.postRegister(registerRequest).enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    data.setValue(null);
                    Log.i("Mallikarjuna","+++error+++"+t.getMessage());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}

