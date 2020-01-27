package com.mi.fillspay.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.UtilitiesRequest;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilitiesRepository {
    private ApiRequest apiRequest;
    public UtilitiesRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<String> getUtilities(UtilitiesRequest utilitiesRequest, String token) {
        final MutableLiveData<String> data = new MutableLiveData<>();
        try {
            apiRequest.getUtilities(utilitiesRequest,token).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
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
