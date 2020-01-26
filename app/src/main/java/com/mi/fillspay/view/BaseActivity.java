package com.mi.fillspay.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mi.fillspay.R;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.local.prefe.MarshMallowPermission;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseActivity extends AppCompatActivity {

    Intent intent;

    private ProgressDialog _dialog;
    AppPreferencesHelper _preferencesHelper;
    public ArrayList<String> permissionsToRequest;
    public ArrayList<String> permissionsRejected = new ArrayList<>();
    public ArrayList<String> permissions = new ArrayList<>();
    public final static int ALL_PERMISSIONS_RESULT = 101;

    MarshMallowPermission marshMallowPermission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);
        _dialog = new ProgressDialog(BaseActivity.this);
        _preferencesHelper=new AppPreferencesHelper(this,"Spandana");
        marshMallowPermission=new MarshMallowPermission(this);
    }


    public void showProgress(){
        _dialog.show();
        _dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        _dialog.setContentView(R.layout.progress);
        _dialog.setCancelable(false);
    }


    public void stopProgress() {
        if (_dialog.isShowing()){
            _dialog.dismiss();
        }
    }


    public boolean isValidEmail(String emailAddress){
        String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,12}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }



    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public boolean isNumberValid(String number) {
        final String regrex = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$";
        if (number.matches(regrex))
        {
            ///Toast.makeText(getApplicationContext(),"valid mobile number",Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
           // Toast.makeText(getApplicationContext(),"Invalid mobile number",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public Bitmap getImage(String bitmap){
        byte[] decodedString = Base64.decode(bitmap, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return  decodedByte;
    }



}
