package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.SubutilityRequest;
import com.mi.fillspay.repository.SubutilityRepository;

public class SubutilityViewModel extends AndroidViewModel {

    private SubutilityRepository utilitiesRepository;
    private LiveData<String> data;

    public SubutilityViewModel(@NonNull Application application) {
        super(application);
        utilitiesRepository=new SubutilityRepository();
    }

    public LiveData<String> getSubUtilities(SubutilityRequest utilitiesRequest, String token, Context context) {
        this.data = utilitiesRepository.getSubUtilities(utilitiesRequest,token,context);
        return data;
    }
}
