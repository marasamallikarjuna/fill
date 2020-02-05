package com.mi.fillspay.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.model.CheckMobileRequest;
import com.mi.fillspay.model.CheckMobileResponse;
import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.repository.CheckNumberRepository;
import com.mi.fillspay.view_model.CheckNumberViewModel;
import com.mi.fillspay.view_model.RegisterViewModel;

public class RegistrationActivity extends BaseActivity {

    RegisterViewModel registerViewModel;

    AppCompatEditText emailEdit, mobileEdit, passwordEdit, confirmEdit;

    CheckNumberViewModel checkNumberViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        initValues();
    }

    private void initValues() {

        emailEdit = findViewById(R.id.emailEdit);
        mobileEdit = findViewById(R.id.mobileEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        confirmEdit = findViewById(R.id.confirmEdit);

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        checkNumberViewModel = ViewModelProviders.of(this).get(CheckNumberViewModel.class);

        findViewById(R.id.loginTextview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.registerImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkConnected()) {
                    if (!isValidEmail(emailEdit.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "enter valid email id", Toast.LENGTH_SHORT).show();
                    } else if (!isNumberValid(mobileEdit.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "enter valid mobile number", Toast.LENGTH_SHORT).show();
                    } else if (passwordEdit.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getApplicationContext(), "enter valid password ", Toast.LENGTH_SHORT).show();
                    } else if (confirmEdit.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getApplicationContext(), "enter valid confirm password ", Toast.LENGTH_SHORT).show();
                    } else if (!confirmEdit.getText().toString().equalsIgnoreCase(passwordEdit.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "confirm password not matched", Toast.LENGTH_SHORT).show();
                    } else {
                        sendLoginDetails(new RegisterRequest(mobileEdit.getText().toString(), emailEdit.getText().toString(), passwordEdit.getText().toString()));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        passwordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkMobileNumber(mobileEdit.getText().toString());
            }
        });
    }

    private void checkMobileNumber(String mobilenumber) {
        checkNumberViewModel.checkNumber(new CheckMobileRequest(mobilenumber)).observe(this, checkMobileResponse -> {
            if (checkMobileResponse.getMessage() != null && checkMobileResponse.getMessage().equalsIgnoreCase("Unavailable")) {
                mobileEdit.setError(checkMobileResponse.getMessage());
            }
        });

    }

    private void sendLoginDetails(RegisterRequest data) {

        registerViewModel.getRegisterResponseLiveData(data).observe(this, responseData -> {

            if (responseData.getMessage().equals("Success")) {

            }

        });
    }

}
