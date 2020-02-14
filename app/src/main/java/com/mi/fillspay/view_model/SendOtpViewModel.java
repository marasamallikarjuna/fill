package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.CheckMobileRequest;
import com.mi.fillspay.model.CheckMobileResponse;
import com.mi.fillspay.repository.CheckNumberRepository;
import com.mi.fillspay.repository.VerifyMobileRepository;

public class SendOtpViewModel extends AndroidViewModel {
    private VerifyMobileRepository verifyMobileRepository;
    private LiveData<CheckMobileResponse> data;

    public SendOtpViewModel(@NonNull Application application) {
        super(application);
        verifyMobileRepository = new VerifyMobileRepository();
    }

    public LiveData<CheckMobileResponse> sendOtp(CheckMobileRequest checkMobileRequest, Context context) {
        this.data = verifyMobileRepository.verifyMobile(checkMobileRequest,context);
        return data;
    }
}
