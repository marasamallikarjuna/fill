package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mi.fillspay.model.IsdCode;
import com.mi.fillspay.repository.IsdCodesRepository;

import java.util.List;

public class IsdCodesViewModel extends AndroidViewModel {

    private IsdCodesRepository isdCodesRepository;
    private LiveData<List<IsdCode>> data;

    public IsdCodesViewModel(@NonNull Application application) {
        super(application);
        isdCodesRepository = new IsdCodesRepository();
    }

    public LiveData<List<IsdCode>> getCountries(Context context) {
        this.data = isdCodesRepository.getIsdCodes(context);
        return data;
    }
}
