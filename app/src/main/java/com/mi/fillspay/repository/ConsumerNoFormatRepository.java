package com.mi.fillspay.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.ConsumerNoFormatRequest;
import com.mi.fillspay.model.ConsumerNoFormatResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsumerNoFormatRepository {

    private ApiRequest apiRequest;
    public ConsumerNoFormatRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
    public LiveData<ConsumerNoFormatResponse> getConsmerNoFarmat(ConsumerNoFormatRequest consumerNoFormatRequest, String token, Context context) {
        final MutableLiveData<ConsumerNoFormatResponse> data = new MutableLiveData<>();
        try {
            apiRequest.getConsmerNoFarmat(consumerNoFormatRequest,token).enqueue(new Callback<ConsumerNoFormatResponse>() {
                @Override
                public void onResponse(Call<ConsumerNoFormatResponse> call, Response<ConsumerNoFormatResponse> response) {
                    if (response.code()==200){
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
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
