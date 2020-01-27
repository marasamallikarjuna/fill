package com.mi.fillspay.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.ConsumerNoFormatRequest;
import com.mi.fillspay.model.ConsumerNoFormatResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsumerNoFormatRepository {

    private ApiRequest apiRequest;
    public ConsumerNoFormatRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ConsumerNoFormatResponse> getConsmerNoFarmat(ConsumerNoFormatRequest consumerNoFormatRequest, String token) {
        final MutableLiveData<ConsumerNoFormatResponse> data = new MutableLiveData<>();
        try {
            apiRequest.getConsmerNoFarmat(consumerNoFormatRequest,token).enqueue(new Callback<ConsumerNoFormatResponse>() {
                @Override
                public void onResponse(Call<ConsumerNoFormatResponse> call, Response<ConsumerNoFormatResponse> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<ConsumerNoFormatResponse> call, Throwable t) {
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
