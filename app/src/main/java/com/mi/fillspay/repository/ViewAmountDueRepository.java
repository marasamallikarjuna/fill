package com.mi.fillspay.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.ViewAmountDueRequest;
import com.mi.fillspay.model.ViewAmountDueResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAmountDueRepository {

    private ApiRequest apiRequest;
    public ViewAmountDueRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ViewAmountDueResponse> getViewAmountDue(ViewAmountDueRequest viewAmountDueRequest, String token) {
        final MutableLiveData<ViewAmountDueResponse> data = new MutableLiveData<>();
        try {
            apiRequest.getViewAmountDue(viewAmountDueRequest,token).enqueue(new Callback<ViewAmountDueResponse>() {
                @Override
                public void onResponse(Call<ViewAmountDueResponse> call, Response<ViewAmountDueResponse> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<ViewAmountDueResponse> call, Throwable t) {
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
