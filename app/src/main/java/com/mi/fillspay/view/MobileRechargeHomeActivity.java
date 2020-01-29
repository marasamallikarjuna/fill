package com.mi.fillspay.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.mi.fillspay.R;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.speeddial.SpeedDialActionItem;
import com.mi.fillspay.utilities.speeddial.SpeedDialView;

public class MobileRechargeHomeActivity extends AppCompatActivity {

    SpeedDialView speedDialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_recharg_main);

        speedDialView = findViewById(R.id.speedDial_id);

        speedDialView.setBackground(getResources().getDrawable(R.drawable.fab_open_bg));

        FragmentUtil.setFragment(new MobileRecFragOne(),this,"Mobile recharge fragment one",R.id.content_frag,false);

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
                        Intent in = new Intent(MobileRechargeHomeActivity.this,NotificationActivity.class);
                        startActivity(in);
                        break;
                    case R.id.fab_item2:
                        Intent in1 = new Intent(MobileRechargeHomeActivity.this,HowToUseActivity.class);
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