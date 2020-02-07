package com.mi.fillspay.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.SwipeButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileRecFrag extends Fragment {

    SwipeButton prepaid_btn, postpaid_btn;

    public MobileRecFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobile_rec, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    private void initValues() {

        postpaid_btn = getActivity().findViewById(R.id.swipe_postpaid);
        prepaid_btn = getActivity().findViewById(R.id.swipe_prepaid);

        prepaid_btn.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                FragmentUtil.setFragment(new MobileRecFragOne(), getActivity(), "Mobile recharge fragment one", R.id.content_frag, true);
            }
        });

        postpaid_btn.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                FragmentUtil.setFragment(new MobileRecFragOne(), getActivity(), "Mobile recharge fragment one", R.id.content_frag, true);
            }
        });

    }
}
