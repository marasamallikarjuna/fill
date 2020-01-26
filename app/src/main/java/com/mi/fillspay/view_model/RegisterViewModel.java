package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.model.ResponseData;
import com.mi.fillspay.repository.RegisterRepository;

public class RegisterViewModel extends AndroidViewModel {

    private RegisterRepository registerRepository;
    private LiveData<ResponseData> responseDataLiveData;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        registerRepository=new RegisterRepository();
    }
    public LiveData<ResponseData> getRegisterResponseLiveData(RegisterRequest registerRequest) {
        this.responseDataLiveData = registerRepository.postRegisterRepository(registerRequest);
        return responseDataLiveData;
    }
}