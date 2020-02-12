package com.mi.fillspay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mi.fillspay.R;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.speeddial.SpeedDialActionItem;
import com.mi.fillspay.utilities.speeddial.SpeedDialView;

public class UtilityActivity extends AppCompatActivity {

    SpeedDialView speedDialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utitity_main);

        FragmentUtil.setFragment(new UtilityFragOne(), this, "Utility fragment one", R.id.content_frag, false);

        initValues();

    }

    private void initValues() {

        speedDialView = findViewById(R.id.speedDial_id);

        speedDialView.setBackground(getResources().getDrawable(R.drawable.fab_open_bg));

        findViewById(R.id.icon_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initSpeedDial();
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
                        Intent in = new Intent(UtilityActivity.this,NotificationActivity.class);
                        startActivity(in);
                        break;
                    case R.id.fab_item2:
                        Intent in1 = new Intent(UtilityActivity.this,HowToUseActivity.class);
                        startActivity(in1);
                        break;
                    case R.id.fab_item3:
                        break;
                }
                return false;
            }
        });

    }
}
