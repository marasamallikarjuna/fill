package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mi.fillspay.model.UserProfile;
import com.mi.fillspay.repository.UserProfileRepository;

public class UserProfileViewModel extends AndroidViewModel {

    private UserProfileRepository userProfileRepository;
    private LiveData<UserProfile> userProfileLiveData;

    public UserProfileViewModel(@NonNull Application application) {
        super(application);
        userProfileRepository = new UserProfileRepository();
    }

    public LiveData<UserProfile> getUserData(String token, Context context) {
        this.userProfileLiveData = userProfileRepository.getUserProfile(token,context);
        return userProfileLiveData;
    }
}
