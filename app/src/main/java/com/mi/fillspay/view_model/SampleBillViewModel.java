package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.SampleBillResponse;
import com.mi.fillspay.repository.ViewSampleBillRepository;

public class SampleBillViewModel extends AndroidViewModel {
    private ViewSampleBillRepository viewSampleBillRepository;
    private LiveData<SampleBillResponse> data;

    public SampleBillViewModel(@NonNull Application application) {
        super(application);
        viewSampleBillRepository = new ViewSampleBillRepository();
    }

    public LiveData<SampleBillResponse> getSampleBill(String token,String billerid,Context context) {
        this.data = viewSampleBillRepository.getSampleBill(token,billerid,context);
        return data;
    }
}
