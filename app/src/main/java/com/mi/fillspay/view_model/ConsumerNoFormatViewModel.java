package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.ConsumerNoFormatRequest;
import com.mi.fillspay.model.ConsumerNoFormatResponse;
import com.mi.fillspay.repository.ConsumerNoFormatRepository;

public class ConsumerNoFormatViewModel extends AndroidViewModel {

    private ConsumerNoFormatRepository consumerNoFormatRepository;
    private LiveData<ConsumerNoFormatResponse> data;

    public ConsumerNoFormatViewModel(@NonNull Application application) {
        super(application);
        consumerNoFormatRepository=new ConsumerNoFormatRepository();
    }

    public LiveData<ConsumerNoFormatResponse> getConsmerNoFarmat(ConsumerNoFormatRequest consumerNoFormatRequest, String token) {
        this.data = consumerNoFormatRepository.getConsmerNoFarmat(consumerNoFormatRequest,token);
        return data;
    }
}
