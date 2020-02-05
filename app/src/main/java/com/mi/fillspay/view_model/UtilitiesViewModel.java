package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mi.fillspay.model.UtilitiesRequest;
import com.mi.fillspay.repository.UtilitiesRepository;

public class UtilitiesViewModel extends AndroidViewModel {

    private UtilitiesRepository utilitiesRepository;
    private LiveData<String[]> data;

    public UtilitiesViewModel(@NonNull Application application) {
        super(application);
        utilitiesRepository=new UtilitiesRepository();
    }

    public LiveData<String[]> getUtilities(UtilitiesRequest utilitiesRequest, String token, Context context) {
        this.data = utilitiesRepository.getUtilities(utilitiesRequest,token,context);
        return data;
    }
}