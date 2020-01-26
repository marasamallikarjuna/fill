package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.SwipeButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendMoneyFragTwo extends Fragment {

    SwipeButton swipeButton;

    public SendMoneyFragTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_money_frag_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }
    private void initViews() {
        swipeButton= getActivity().findViewById(R.id.swipe_sendmoney);

        swipeButton.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                FragmentUtil.setFragment(new SendMoneyFragThree(),getActivity(),"Send Mobile fragment three", R.id.content_frag,true);

            }
        });
    }

}
