package com.mi.fillspay.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.model.LoginRequest;
import com.mi.fillspay.view_model.LoginViewModel;

public class LoginActivity extends BaseActivity {

    ImageView logoImageView;
    TextView titleTextView;
    LoginViewModel loginViewModel;

    AppCompatEditText emailEdit, passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        logoImageView = findViewById(R.id.logoImageView);
        titleTextView = findViewById(R.id.titleTextView);
        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        findViewById(R.id.loginImageView).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if (isNetworkConnected()) {
                    _preferencesHelper.setAccessToken(getResources().getString(R.string.token));
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(titleTextView, "tvLogin");
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                    startActivity(intent, activityOptions.toBundle());

                /*    if (!isValidEmail(emailEdit.getText().toString())){
                        Toast.makeText(getApplicationContext(), "enter valid email id", Toast.LENGTH_SHORT).show();
                    }else if (passwordEdit.getText().toString().equalsIgnoreCase("")){
                        Toast.makeText(getApplicationContext(), "enter valid password", Toast.LENGTH_SHORT).show();
                    }else{
                        sendLoginDetails(new LoginRequest(emailEdit.getText().toString(),passwordEdit.getText().toString()));
                    }*/

                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.regTextView).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(titleTextView, "tvLogin");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, activityOptions.toBundle());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendLoginDetails(LoginRequest data) {
        showProgress();
        loginViewModel.getLoginResponseLiveData(data).observe(this, loginResponse -> {
            stopProgress();
            if (loginResponse!=null) {
                if (loginResponse.getToken() != null) {
                    if (!loginResponse.getToken().equalsIgnoreCase("")) {
                        _preferencesHelper.setAccessToken(loginResponse.getToken());
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Pair[] pairs = new Pair[1];
                        pairs[0] = new Pair<View, String>(titleTextView, "tvLogin");
                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                        startActivity(intent, activityOptions.toBundle());
                    }
                }
            }
        });
    }
}
