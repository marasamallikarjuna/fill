package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.SubutilityRequest;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.repository.SubutilityRepository;

import java.util.List;

public class SubutilityViewModel extends AndroidViewModel {

    private SubutilityRepository utilitiesRepository;
    private LiveData<List<UtilityResponse>> data;

    public SubutilityViewModel(@NonNull Application application) {
        super(application);
        utilitiesRepository=new SubutilityRepository();
    }

    public LiveData<List<UtilityResponse>> getSubUtilities(SubutilityRequest utilitiesRequest, String token, Context context) {
        this.data = utilitiesRepository.getSubUtilities(utilitiesRequest,token,context);
        return data;
    }
}
