package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.ProcessPaymentRequest;
import com.mi.fillspay.model.ProcessPaymentResponse;
import com.mi.fillspay.repository.ProcessPaymentRepository;

public class ProcessPaymentViewModel extends AndroidViewModel {

    private ProcessPaymentRepository processPaymentRepository;
    private LiveData<ProcessPaymentResponse> data;

    public ProcessPaymentViewModel(@NonNull Application application) {
        super(application);
        processPaymentRepository=new ProcessPaymentRepository();
    }

    public LiveData<ProcessPaymentResponse> processPayment(ProcessPaymentRequest processPaymentRequest, String token) {
        this.data = processPaymentRepository.processPayment(processPaymentRequest,token);
        return data;
    }
}