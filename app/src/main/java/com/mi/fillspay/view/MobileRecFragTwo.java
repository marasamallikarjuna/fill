package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.utilities.SwipeButton;

import static android.provider.ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY;
import static com.mi.fillspay.interfaces.keys.SCHEME_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileRecFragTwo extends Fragment {

    SwipeButton swipeButton;

    public MobileRecFragTwo() {
        // Required empty public constructor
    }

    public static MobileRecFragTwo newInstance(String country,String schemeType) {
        MobileRecFragTwo myFragment = new MobileRecFragTwo();
        Bundle args = new Bundle();
        args.putString(COUNTRY, country);
        args.putString(SCHEME_TYPE, schemeType);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobile_rec_frag_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        swipeButton = getActivity().findViewById(R.id.swipeButton);
        swipeButton.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                Toast.makeText(getActivity(), "Swipe Completed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
