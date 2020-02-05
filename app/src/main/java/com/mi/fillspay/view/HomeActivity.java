package com.mi.fillspay.view;

import android.app.ActivityOptions;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.mi.fillspay.R;

public class HomeActivity extends BaseActivity {

    ImageView reImageView,utilImageView,sendImageView;
    LinearLayout enterImageView;
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        titleTextView=findViewById(R.id.titleTextView);

        enterImageView=findViewById(R.id.enterImageView);
        reImageView=findViewById(R.id.rechargImageView);
        utilImageView=findViewById(R.id.utilImageView);
        sendImageView=findViewById(R.id.sendImageView);

        sendImageView.setOnLongClickListener(longClickListener);
        utilImageView.setOnLongClickListener(longClickListener);
        reImageView.setOnLongClickListener(longClickListener);
        enterImageView.setOnDragListener(dragListener);

        findViewById(R.id.bottomImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetViewFragment bottomSheetViewFragment =
                        BottomSheetViewFragment.newInstance();
                bottomSheetViewFragment.show(getSupportFragmentManager(),
                        BottomSheetViewFragment.TAG);
            }
        });

    }

    public void transfer(){

    }

    View.OnLongClickListener longClickListener=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {

            ClipData data= ClipData.newPlainText("","");
            View.DragShadowBuilder dragShadowBuilder=new View.DragShadowBuilder(view);
            view.startDrag(data,dragShadowBuilder,view,1);
            return true;
        }
    };

    View.OnDragListener dragListener=new View.OnDragListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            int event= dragEvent.getAction();

            Log.i("Mallikajuna","++++++"+event);

            switch (event){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View v=(View) dragEvent.getLocalState();
                    if (v.getId()== R.id.sendImageView){
                       // Toast.makeText(HomeActivity.this,"Send Money",Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Intent intent   = new Intent(HomeActivity.this, SendMoneyHomeActivity.class);
                            Pair[] pairs    = new Pair[1];
                            pairs[0] = new Pair<View,String>(titleTextView,"tvLogin");
                            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,pairs);
                            startActivity(intent,activityOptions.toBundle());
                        }

                    }else if (v.getId()== R.id.utilImageView){
                      //  Toast.makeText(HomeActivity.this,"Util",Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Intent intent   = new Intent(HomeActivity.this, UtilityActivity.class);
                            Pair[] pairs    = new Pair[1];
                            pairs[0] = new Pair<View,String>(titleTextView,"tvLogin");
                            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,pairs);
                            startActivity(intent,activityOptions.toBundle());
                        }

                    }else if (v.getId()== R.id.rechargImageView){
                      //  Toast.makeText(HomeActivity.this,"Mobile Recharge",Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Intent intent   = new Intent(HomeActivity.this, MobileRechargeHomeActivity.class);
                            Pair[] pairs    = new Pair[1];
                            pairs[0] = new Pair<View,String>(titleTextView,"tvLogin");
                            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,pairs);
                            startActivity(intent,activityOptions.toBundle());
                        }

                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }

            return true;
        }
    };


}
