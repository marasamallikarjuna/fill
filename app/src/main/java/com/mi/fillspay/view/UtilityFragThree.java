package com.mi.fillspay.view;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.CountryAdapter;
import com.mi.fillspay.interfaces.OnItemClick;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.model.CountryRequest;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.GradientTextView;
import com.mi.fillspay.utilities.SwipeButton;
import com.mi.fillspay.utilities.circleRecyclerView.HalfCurveLayoutManager;
import com.mi.fillspay.view_model.BillerDescViewModel;
import com.mi.fillspay.view_model.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.mi.fillspay.interfaces.keys.COUNTRY_CODE;
import static com.mi.fillspay.interfaces.keys.UTILITY_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragThree extends Fragment {
    GradientTextView headerName;
    SwipeButton swipeButton;
    TextView tv_boardName;
    AppPreferencesHelper _preferencesHelper;
    BillerDescViewModel billerDescViewModel;
    AutoCompleteTextView autoCompltBillerNames;

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

        if (getArguments() != null) {
            headerName.setText("Pay Your " + getArguments().getString(UTILITY_NAME) + " Bill");
            tv_boardName.setText(getArguments().getString(UTILITY_NAME) + " Board");
            getBillers(getArguments().getString(UTILITY_NAME), getArguments().getString(COUNTRY_CODE));
        }

        swipeButton.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                Fragment frag = new PaymentFrag();
                FragmentUtil.setFragment(frag, getActivity(), "Utility fragment one", R.id.content_frag, true);
            }
        });
    }

    private void getBillers(String billerDescription, String countryCode) {
        //  AppUtilities.showProgress(getActivity());
        _preferencesHelper = new AppPreferencesHelper(getActivity(), "Spandana");
        billerDescViewModel = ViewModelProviders.of(this).get(BillerDescViewModel.class);
        billerDescViewModel.getBillerDesc(new BillerDescRequest("1", "1", countryCode, "Utility", billerDescription),
                _preferencesHelper.getAccessToken()).observe(this, new Observer<List<BillerDescResponse>>() {
            @Override
            public void onChanged(List<BillerDescResponse> billerDescResponses) {
                //  AppUtilities.stopProgress();
                ArrayList<String> billers = new ArrayList<>();
                for (int i = 0; i < billerDescResponses.size(); i++) {
                    billers.add(billerDescResponses.get(i).getBillerName());
                }
                String[] biller = billers.toArray(new String[billers.size()]);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.auto_cmplt_item, biller);
                //Getting the instance of AutoCompleteTextView
                AutoCompleteTextView actv = (AutoCompleteTextView) getActivity().findViewById(R.id.autoComBillers_ids);
                actv.setThreshold(0);//will start working from first character
                actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                actv.setTextColor(Color.BLACK);
            }
        });
    }
}
