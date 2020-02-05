package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.model.CheckMobileRequest;
import com.mi.fillspay.model.CheckMobileResponse;
import com.mi.fillspay.repository.BillerDescRepository;
import com.mi.fillspay.repository.CheckNumberRepository;

import java.util.List;

public class CheckNumberViewModel extends AndroidViewModel {

    private CheckNumberRepository checkNumberRepository;
    private LiveData<CheckMobileResponse> data;

    public CheckNumberViewModel(@NonNull Application application) {
        super(application);
        checkNumberRepository = new CheckNumberRepository();
    }

    public LiveData<CheckMobileResponse> checkNumber(CheckMobileRequest checkMobileRequest) {
        this.data = checkNumberRepository.checkMobileNumber(checkMobileRequest);
        return data;
    }
}