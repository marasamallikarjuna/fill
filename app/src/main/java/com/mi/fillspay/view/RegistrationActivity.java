package com.mi.fillspay.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.model.CheckMobileRequest;
import com.mi.fillspay.model.IsdCode;
import com.mi.fillspay.model.RegisterRequest;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.ItemListDialog;
import com.mi.fillspay.utilities.OtpEditText;
import com.mi.fillspay.view_model.CheckNumberViewModel;
import com.mi.fillspay.view_model.IsdCodesViewModel;
import com.mi.fillspay.view_model.RegisterViewModel;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends BaseActivity {

    private RegisterViewModel registerViewModel;

    private IsdCodesViewModel isdCodesViewModel;

    private AppCompatEditText emailEdit, mobileEdit, passwordEdit, confirmEdit;

    private CheckNumberViewModel checkNumberViewModel;

    private RelativeLayout countryPicker;

    private AppCompatEditText tv_countrycode;

    private ImageView img_flag;

    private ItemListDialog itemListDialog;

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
        countryPicker = findViewById(R.id.countryCodeHolder);
        img_flag = findViewById(R.id.image_flag);
        tv_countrycode = findViewById(R.id.text_countrycode);

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        checkNumberViewModel = ViewModelProviders.of(this).get(CheckNumberViewModel.class);

        isdCodesViewModel = ViewModelProviders.of(this).get(IsdCodesViewModel.class);

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
                        sendRegDetails(new RegisterRequest(mobileEdit.getText().toString(), emailEdit.getText().toString(), passwordEdit.getText().toString()));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        passwordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!TextUtils.isEmpty(mobileEdit.getText().toString().trim()))
                    mobileEdit.setError(null);
                checkMobileNumber(tv_countrycode.getText().toString().trim() + mobileEdit.getText().toString());
            }
        });

        countryPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListDialog.show(getSupportFragmentManager(), "ItemList Dialog");
            }
        });
        getIsoCodes();
    }

    private void getIsoCodes() {

        AppUtilities.showProgress(this);

        isdCodesViewModel.getCountries(this).observe(this, isdCodes -> {
            List<IsdCode> codes = new ArrayList<>();
            for (int j = 0; j < isdCodes.size(); j++) {
                String test = isdCodes.get(j).getIsdCode();
                test = test.replaceAll("[\\(\\)\\[\\]\\{\\}]", "");
                String[] arr = singleChars(test);
                for (String str : arr) {
                    codes.add(new IsdCode(isdCodes.get(j).getCountry(), str, isdCodes.get(j).getFlag()));
                }
            }
            if (isdCodes.size() != 0) {
                AppUtilities.stopProgress();
                itemListDialog = new ItemListDialog(this, codes, img_flag, tv_countrycode);
            }
        });
    }

    public static String[] singleChars(String s) {
        return s.split(",");
    }

    private void checkMobileNumber(String mobilenumber) {
        checkNumberViewModel.checkNumber(new CheckMobileRequest(mobilenumber)).observe(this, checkMobileResponse -> {
            if (checkMobileResponse != null) {
                if (checkMobileResponse.getMessage().equalsIgnoreCase("Unavailable")) {
                    mobileEdit.setError(checkMobileResponse.getMessage());
                }
            }
        });
    }

    private void sendRegDetails(RegisterRequest data) {
        registerViewModel.getRegisterResponseLiveData(data, this).observe(this, responseData -> {
            if (responseData.getMessage().equals("Success")) {

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
