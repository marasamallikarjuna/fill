package com.mi.fillspay.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.ProcessPaymentRequest;
import com.mi.fillspay.model.ProcessPaymentResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcessPaymentRepository {

    private ApiRequest apiRequest;
    public ProcessPaymentRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ProcessPaymentResponse> processPayment(ProcessPaymentRequest processPaymentRequest, String token) {
        final MutableLiveData<ProcessPaymentResponse> data = new MutableLiveData<>();
        try {
            apiRequest.processPayment(processPaymentRequest,token).enqueue(new Callback<ProcessPaymentResponse>() {
                @Override
                public void onResponse(Call<ProcessPaymentResponse> call, Response<ProcessPaymentResponse> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<ProcessPaymentResponse> call, Throwable t) {
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
