package com.mi.fillspay.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.model.ConsumerNoFormatRequest;
import com.mi.fillspay.model.ConsumerNoFormatResponse;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.GradientTextView;
import com.mi.fillspay.utilities.SwipeButton;
import com.mi.fillspay.view_model.BillerDescViewModel;
import com.mi.fillspay.view_model.ConsumerNoFormatViewModel;

import static com.mi.fillspay.interfaces.keys.ACTIVITY_RESULT;
import static com.mi.fillspay.interfaces.keys.COUNTRY_CODE;
import static com.mi.fillspay.interfaces.keys.UTILITY_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragThree extends Fragment {
    AppPreferencesHelper _preferencesHelper;
    GradientTextView headerName;
    SwipeButton swipeButton;
    TextView tv_boardName;
    AppCompatEditText actv;
    ConsumerNoFormatViewModel consumerViewModel;

    public UtilityFragThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elec_frag_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    private void initValues() {
        headerName = getActivity().findViewById(R.id.header_name);
        tv_boardName = getActivity().findViewById(R.id.board_name);
        swipeButton = getActivity().findViewById(R.id.swipe_btn_id);
        actv = getActivity().findViewById(R.id.autoComBillers_ids);

        if (getArguments() != null) {
            headerName.setText("Pay Your " + getArguments().getString(UTILITY_NAME) + " Bill");
            tv_boardName.setText(getArguments().getString(UTILITY_NAME) + " Board");
        }

        actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent billersIntent = new Intent(getActivity(), BillersListActivity.class);
                billersIntent.putExtra(COUNTRY_CODE, getArguments().getString(COUNTRY_CODE));
                billersIntent.putExtra(UTILITY_NAME, getArguments().getString(UTILITY_NAME));
                startActivityForResult(billersIntent, ACTIVITY_RESULT);// Activity is started with requestCode 2
            }
        });

        swipeButton.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                Fragment frag = new PaymentFrag();
                FragmentUtil.setFragment(frag, getActivity(), "Utility fragment one",R.id.content_frag, true);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_RESULT) {
            if (data != null) {
                BillerDescResponse item = data.getParcelableExtra("MESSAGE");
                actv.setText(item.getBillerName());
                getConsumerNumberFormat(item.getBillerID());
            }
        }
    }

    private void getConsumerNumberFormat(String biller_id) {

        _preferencesHelper = new AppPreferencesHelper(getActivity(),"Spandana");

        consumerViewModel = ViewModelProviders.of(getActivity()).get(ConsumerNoFormatViewModel.class);

       /* consumerViewModel.getConsmerNoFarmat(new ConsumerNoFormatRequest("1","1",biller_id),_preferencesHelper.getAccessToken()).observe(new Observer<ConsumerNoFormatResponse>() {
            @Override
            public void onChanged(ConsumerNoFormatResponse consumerNoFormatResponse) {

            }
        });*/

    }
}
