package com.mi.fillspay.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mi.fillspay.model.SampleBillResponse;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSampleBillRepository {
    private ApiRequest apiRequest;

    public ViewSampleBillRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<SampleBillResponse> getSampleBill(String token,String billerId, Context context) {
        final MutableLiveData<SampleBillResponse> data = new MutableLiveData<>();
        try {
            apiRequest.viewSampleBill(token,billerId).enqueue(new Callback<SampleBillResponse>() {
                @Override
                public void onResponse(Call<SampleBillResponse> call, Response<SampleBillResponse> response) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            if (jObjError.has("message")) {
                                Toast.makeText(context, jObjError.get("message").toString(), Toast.LENGTH_LONG).show();
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
                public void onFailure(Call<SampleBillResponse> call, Throwable t) {
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
