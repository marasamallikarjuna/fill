package com.mi.fillspay.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.UtilitiesRequest;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilitiesRepository {
    private ApiRequest apiRequest;

    public UtilitiesRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }
  /*  public LiveData<ArrayList<UtilityResponse>> getUtilities(UtilitiesRequest utilitiesRequest, String token) {
        final MutableLiveData<ArrayList<UtilityResponse>> data = new MutableLiveData<>();
        try {
            apiRequest.getUtilities(utilitiesRequest,token).enqueue(new Callback<ArrayList<UtilityResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<UtilityResponse>> call, Response<ArrayList<UtilityResponse>> response) {
                    if (response.body() != null) {
                        data.setValue(response.body());
                        Log.i("Mallikarjuna","+++sucess+++"+response.toString());
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<UtilityResponse>> call, Throwable t) {
                    data.setValue(null);
                    Log.i("Mallikarjuna","+++error+++"+t.getMessage());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }*/

    public LiveData<String[]> getUtilities(UtilitiesRequest utilitiesRequest, String token, Context context) {
        final MutableLiveData<String[]> data = new MutableLiveData<>();
        try {
            apiRequest.getUtilities(utilitiesRequest, token).enqueue(new Callback<String[]>() {
                @Override
                public void onResponse(Call<String[]> call, Response<String[]> response) {
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
                public void onFailure(Call<String[]> call, Throwable t) {
                    data.setValue(null);
                    Log.i("Mallikarjuna", "+++error+++" + t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
