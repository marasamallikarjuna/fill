package com.mi.fillspay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.view_model.UserProfileViewModel;


public class SettingsActivity extends BaseActivity {
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initValues();
    }

    private void initValues() {
        profileImage = findViewById(R.id.circle_image_id);
        findViewById(R.id.edit_profile_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(edit);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNetworkConnected()) {
            updateuserProfile();
        }
    }

    public void updateuserProfile() {

       UserProfileViewModel userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);

        _preferencesHelper = new AppPreferencesHelper(this, "Spandana");

        userProfileViewModel.getUserData(_preferencesHelper.getAccessToken(), this).observe(this, userProfile -> {
            if (userProfile != null) {
                _preferencesHelper.setUserProfileImage(userProfile.getProfilePic());
                profileImage.setImageBitmap(AppUtilities.stringToBitmap(_preferencesHelper.getUserProfileImage()));
            }
        });
    }
}
