package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mi.fillspay.model.ResponseData;
import com.mi.fillspay.model.VerifyOtpRequest;
import com.mi.fillspay.repository.VerifyOtpRepository;

public class VerifyOtpViewModel extends AndroidViewModel {

    private VerifyOtpRepository verifyMobileRepository;
    private LiveData<ResponseData> data;

    public VerifyOtpViewModel(@NonNull Application application) {
        super(application);
        verifyMobileRepository = new VerifyOtpRepository();
    }

    public LiveData<ResponseData> verifyOtp(VerifyOtpRequest checkMobileRequest, Context context) {
        this.data = verifyMobileRepository.verifyOtp(checkMobileRequest,context);
        return data;
    }

}
