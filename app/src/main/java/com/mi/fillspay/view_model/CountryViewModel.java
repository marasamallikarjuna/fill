package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.CountryPojo;
import com.mi.fillspay.model.CountryRequest;
import com.mi.fillspay.repository.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepository countryRepository;
    private LiveData<List<CountryPojo>> data;
    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository=new CountryRepository();
    }
    public LiveData<List<CountryPojo>> getCountries(CountryRequest contryreq, String token, Context context) {
        this.data = countryRepository.getCountries(contryreq,token,context);
        return data;
    }
}
