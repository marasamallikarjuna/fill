package com.mi.fillspay.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.CircleLayoutManager;
import com.mi.fillspay.adapter.SampleAdapter;
import com.mi.fillspay.utilities.FragmentUtil;


public class SendMoneyHomeActivity extends AppCompatActivity {

    ViewGroup root;
    RecyclerView list;
    LinearLayout circularText;
    private CircleLayoutManager layoutManager;
    private SampleAdapter adapter;
    TextView textViewSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_main);

        FragmentUtil.setFragment(new SendMoneyFragOne(),this,"Send Mobile fragment one", R.id.content_frag,false);

        findViewById(R.id.icon_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

}