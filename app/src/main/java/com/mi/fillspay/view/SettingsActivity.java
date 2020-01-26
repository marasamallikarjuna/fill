package com.mi.fillspay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mi.fillspay.R;


public class SettingsActivity extends AppCompatActivity {
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        profileImage = findViewById(R.id.circle_image_id);
        findViewById(R.id.edit_profile_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(SettingsActivity.this,ProfileActivity.class);
                startActivity(edit);
            }
        });
    }

}
