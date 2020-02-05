package com.mi.fillspay.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillerDescRepository {

    private ApiRequest apiRequest;
    public BillerDescRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<List<BillerDescResponse>> getBillerDesc(BillerDescRequest billerDescRequest, String token) {
        final MutableLiveData<List<BillerDescResponse>> data = new MutableLiveData<>();
        try {
            apiRequest.getBillerDesc(billerDescRequest,token).enqueue(new Callback<List<BillerDescResponse>>() {
                @Override
                public void onResponse(Call<List<BillerDescResponse>> call, Response<List<BillerDescResponse>> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.d("idfdsf",response.body().toString());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<List<BillerDescResponse>> call, Throwable t) {
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
