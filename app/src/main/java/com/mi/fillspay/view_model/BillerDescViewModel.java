package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.repository.BillerDescRepository;

import java.util.List;

public class BillerDescViewModel extends AndroidViewModel {

    private BillerDescRepository billerDescRepository;
    private LiveData<List<BillerDescResponse>> data;

    public BillerDescViewModel(@NonNull Application application) {
        super(application);
        billerDescRepository=new BillerDescRepository();
    }

    public LiveData<List<BillerDescResponse>> getBillerDesc(BillerDescRequest billerDescRequest, String token) {
        this.data = billerDescRepository.getBillerDesc(billerDescRequest,token);
        return data;
    }
}
