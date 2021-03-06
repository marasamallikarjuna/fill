package com.mi.fillspay.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.ResponseData;
import com.mi.fillspay.model.VerifyOtpRequest;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpRepository {
    private ApiRequest apiRequest;

    public VerifyOtpRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ResponseData> verifyOtp(VerifyOtpRequest registerRequest,final Context context) {
        final MutableLiveData<ResponseData> data = new MutableLiveData<>();
        try {
            apiRequest.verifyOtp(registerRequest).enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    if (response.code() == 200){
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    else if(response.code() == 500 ){
                        Toast.makeText(context,response.body().getMessage() + "\n" +response.body().getError(),Toast.LENGTH_LONG).show();
                    }else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            if (jObjError.has("message")){
                                Toast.makeText(context,jObjError.get("message").toString(),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        data.setValue(null);
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
