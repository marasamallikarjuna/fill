package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.LoginRequest;
import com.mi.fillspay.model.LoginResponse;
import com.mi.fillspay.repository.LoginRepository;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    private LiveData<LoginResponse> loginResponseLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository=new LoginRepository();
    }

    public LiveData<LoginResponse> getLoginResponseLiveData(LoginRequest loginRequest) {
        this.loginResponseLiveData = loginRepository.postLoginRepository(loginRequest);
        return loginResponseLiveData;
    }
}