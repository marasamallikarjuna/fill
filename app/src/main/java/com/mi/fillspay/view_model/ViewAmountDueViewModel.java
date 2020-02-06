package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.ViewAmountDueRequest;
import com.mi.fillspay.model.ViewAmountDueResponse;
import com.mi.fillspay.repository.ViewAmountDueRepository;

public class ViewAmountDueViewModel extends AndroidViewModel {

    private ViewAmountDueRepository viewAmountDueRepository;
    private LiveData<ViewAmountDueResponse> data;

    public ViewAmountDueViewModel(@NonNull Application application) {
        super(application);
        viewAmountDueRepository=new ViewAmountDueRepository();
    }

    public LiveData<ViewAmountDueResponse> getViewAmountDue(ViewAmountDueRequest viewAmountDueRequest, String token, Context context) {
        this.data = viewAmountDueRepository.getViewAmountDue(viewAmountDueRequest,token,context);
        return data;
    }
}