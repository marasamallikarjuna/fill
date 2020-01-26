package com.mi.fillspay.utilities;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public  class FragmentUtil {

    public static void setFragment(final Fragment fragmnt, final Context context, final String tag, final int resId, final boolean isBack){
        FragmentTransaction fragmentTransaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        ((AppCompatActivity) context).getSupportFragmentManager().executePendingTransactions();
        fragmentTransaction.replace(resId,fragmnt,tag);
        if(isBack)
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

}
