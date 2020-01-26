package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.utilities.CircleSeekBar;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.GradientTextView;
import com.mi.fillspay.utilities.SwipeButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendMoneyFragThree extends Fragment {
    SwipeButton swipeButton;
    private CircleSeekBar mSeekbar;
    private GradientTextView mTextView;

    public SendMoneyFragThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_money_frag_three, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        swipeButton = getActivity().findViewById(R.id.swipeButton);
        mSeekbar = getActivity().findViewById(R.id.seekbar);
        mTextView= getActivity().findViewById(R.id.current_progress);

        swipeButton.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                FragmentUtil.setFragment(new SendMoneyFragFour(), getActivity(), "Send Mobile fragment three", R.id.content_frag, true);

            }
        });

        mSeekbar.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onChanged(CircleSeekBar seekbar, int curValue) {
                mTextView.setText("" + curValue);
            }
        });

        mSeekbar.setCurProcess(0);
    }

}
