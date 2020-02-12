package com.mi.fillspay.view_model;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mi.fillspay.model.ProfileImageResponse;
import com.mi.fillspay.repository.ProfileImageRepository;

import java.io.File;


public class ProfilePictureViewModel extends AndroidViewModel {
    private ProfileImageRepository profileImageRepository;
    private LiveData<ProfileImageResponse> data;

    public ProfilePictureViewModel(@NonNull Application application) {
        super(application);
        profileImageRepository = new ProfileImageRepository();
    }

    public LiveData<ProfileImageResponse> uploadProfileImage(File file,String mobile, String token, Context context) {
        this.data = profileImageRepository.uploadProfileImage(file,mobile,token,context);
        return data;
    }
}
