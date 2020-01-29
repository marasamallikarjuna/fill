package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mi.fillspay.model.UtilitiesRequest;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.repository.UtilitiesRepository;

import java.util.ArrayList;

public class UtilitiesViewModel extends AndroidViewModel {

    private UtilitiesRepository utilitiesRepository;
    private LiveData<ArrayList<UtilityResponse>> data;

    public UtilitiesViewModel(@NonNull Application application) {
        super(application);
        utilitiesRepository=new UtilitiesRepository();
    }

    public LiveData<ArrayList<UtilityResponse>> getUtilities(UtilitiesRequest utilitiesRequest, String token) {
        this.data = utilitiesRepository.getUtilities(utilitiesRequest,token);
        return data;
    }
}