package com.mi.fillspay.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mi.fillspay.model.IsdCode;
import com.mi.fillspay.retrofit.ApiRequest;
import com.mi.fillspay.retrofit.RetrofitRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IsdCodesRepository {
    private ApiRequest apiRequest;

    public IsdCodesRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<List<IsdCode>> getIsdCodes(Context context) {
        final MutableLiveData<List<IsdCode>> data = new MutableLiveData<>();
        try {
            apiRequest.getIsdCodes().enqueue(new Callback<List<IsdCode>>() {
                @Override
                public void onResponse(Call<List<IsdCode>> call, Response<List<IsdCode>> response) {
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
                public void onFailure(Call<List<IsdCode>> call, Throwable t) {
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
