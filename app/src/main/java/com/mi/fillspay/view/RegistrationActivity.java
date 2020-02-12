package com.mi.fillspay.view;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.model.CheckMobileRequest;
import com.mi.fillspay.model.CheckMobileResponse;
import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.repository.CheckNumberRepository;
import com.mi.fillspay.utilities.OtpEditText;
import com.mi.fillspay.view_model.CheckNumberViewModel;
import com.mi.fillspay.view_model.RegisterViewModel;

import org.json.JSONException;

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

                showOtpDialog();

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
            if (checkMobileResponse.getMessage() != null) {
                if (checkMobileResponse.getMessage().equalsIgnoreCase("Unavailable")) {
                    mobileEdit.setError(checkMobileResponse.getMessage());
                }
            }
        });
    }

    private void sendLoginDetails(RegisterRequest data) {
        registerViewModel.getRegisterResponseLiveData(data, this).observe(this, responseData -> {
            if(responseData.getMessage().equals("Success")) {

            }
        });
    }

    //This method would confirm the otp

    private void showOtpDialog() {

        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);

        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.reg_otp_dialog, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        Button buttonConfirm = (Button) confirmDialog.findViewById(R.id.otp_submit_btn);
        OtpEditText editTextConfirmOtp = (OtpEditText) confirmDialog.findViewById(R.id.et_otp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //Displaying the alert dialog
        alertDialog.show();

        confirmDialog.findViewById(R.id.close_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting the user entered otp from edittext
                final String otp = editTextConfirmOtp.getText().toString().trim();
                Toast.makeText(RegistrationActivity.this, otp, Toast.LENGTH_SHORT).show();
                //Hiding the alert dialog
                alertDialog.dismiss();
            }
        });
    }
}
