package com.mi.fillspay.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mi.fillspay.R;
import com.mi.fillspay.utilities.FragmentUtil;

public class QuickSendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_send);

        FragmentUtil.setFragment(new QuickSendFrag(),this,"QuickSend fragment one", R.id.content_frag_quicksend,false);

        findViewById(R.id.icon_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
