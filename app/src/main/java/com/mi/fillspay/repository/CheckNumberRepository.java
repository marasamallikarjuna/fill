package com.mi.fillspay.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.model.CheckMobileRequest;
import com.mi.fillspay.model.CheckMobileResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckNumberRepository {

    private ApiRequest apiRequest;
    public CheckNumberRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<CheckMobileResponse> checkMobileNumber(CheckMobileRequest checkMobileRequest) {
        final MutableLiveData<CheckMobileResponse> data = new MutableLiveData<>();
        try {
            apiRequest.checkMobileNumber(checkMobileRequest).enqueue(new Callback<CheckMobileResponse>() {
                @Override
                public void onResponse(Call<CheckMobileResponse> call, Response<CheckMobileResponse> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.d("idfldsf",response.body().toString());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<CheckMobileResponse> call, Throwable t) {
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