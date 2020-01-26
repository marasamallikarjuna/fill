package com.mi.fillspay.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mi.fillspay.R;

public class BottomSheetViewFragment extends BottomSheetDialogFragment {
    public static final String TAG = "BottomSheetViewFragment";
    ImageView settings_img;


    public static BottomSheetViewFragment newInstance() {
        return new BottomSheetViewFragment();
    }

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_main, container, false);

        ImageView imageView=(ImageView) view.findViewById(R.id.closeImageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        view.findViewById(R.id.utilImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        view.findViewById(R.id.quicksend_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QuickSendActivity.class));
            }
        });

        view.findViewById(R.id.history_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
            }
        });

        // get the views and attach the listener

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((View) getView().getParent()).setBackgroundColor(Color.TRANSPARENT);
    }
}