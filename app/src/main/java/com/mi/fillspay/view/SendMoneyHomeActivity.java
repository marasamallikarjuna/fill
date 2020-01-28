package com.mi.fillspay.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.CircleLayoutManager;
import com.mi.fillspay.adapter.SampleAdapter;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.speeddial.SpeedDialActionItem;
import com.mi.fillspay.utilities.speeddial.SpeedDialView;


public class SendMoneyHomeActivity extends AppCompatActivity {

    SpeedDialView speedDialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_main);

        initValues();

        FragmentUtil.setFragment(new SendMoneyFragOne(),this,"Send Mobile fragment one", R.id.content_frag,false);

    }

    private void initValues() {

        speedDialView = findViewById(R.id.speedDial_id);

        speedDialView.setBackground(getResources().getDrawable(R.drawable.fab_open_bg));

        initSpeedDial();

        findViewById(R.id.icon_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void initSpeedDial() {

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_item2, R.drawable.ic_fabmenu_item3).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_item1, R.drawable.ic_fabmenu_item1).create());

        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_item3, R.drawable.ic_fabmenu_item3).create());

        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()){
                    case R.id.fab_item1:
                        Toast.makeText(SendMoneyHomeActivity.this, "one", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.fab_item2:
                        Toast.makeText(SendMoneyHomeActivity.this, "two", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.fab_item3:
                        Toast.makeText(SendMoneyHomeActivity.this, "three", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }
}