package com.mi.fillspay.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mi.fillspay.model.CountryRequest;
import com.mi.fillspay.repository.CountryRepository;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepository countryRepository;
    private LiveData<String[]> data;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository=new CountryRepository();
    }

    public LiveData<String[]> getCountries(CountryRequest contryreq, String token) {
        this.data = countryRepository.getCountries(contryreq,token);
        return data;
    }
}
