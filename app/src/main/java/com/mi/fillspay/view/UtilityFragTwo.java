package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.CountryAdapter;
import com.mi.fillspay.adapter.UtilityAdapter;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.model.CountryRequest;
import com.mi.fillspay.model.UtilitiesRequest;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.circleRecyclerView.CenterEdgeItemsRecyclerView;
import com.mi.fillspay.utilities.circleRecyclerView.HalfCurveLayoutManager;
import com.mi.fillspay.adapter.RVAdapter;
import com.mi.fillspay.view_model.CountryViewModel;
import com.mi.fillspay.view_model.UtilitiesViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragTwo extends Fragment implements View.OnClickListener {

    private CenterEdgeItemsRecyclerView recyclerView;
    private ImageView nextPage;
    UtilitiesViewModel utilitiesViewModel;
    AppPreferencesHelper _preferencesHelper;

    public UtilityFragTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_utility_frag_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    private void initValues() {
        recyclerView = (CenterEdgeItemsRecyclerView) getActivity().findViewById(R.id.wrv);
        nextPage = getActivity().findViewById(R.id.nextPage_id);
        nextPage.setOnClickListener(this);
        getUtilities();
    }


    private void getUtilities() {

        _preferencesHelper = new AppPreferencesHelper(getActivity(),"Spandana");

        utilitiesViewModel = ViewModelProviders.of(this).get(UtilitiesViewModel.class);

        utilitiesViewModel.getUtilities(new UtilitiesRequest("1", "1", ""),_preferencesHelper.getAccessToken()).observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(String[] utilityResponses) {
                recyclerView.setCenterEdgeItems(true);
                HalfCurveLayoutManager manager = new HalfCurveLayoutManager(getActivity(), 1.0f);
                recyclerView.setLayoutManager(manager);
                CountryAdapter adapter = new CountryAdapter(getActivity(), utilityResponses);
                recyclerView.setAdapter(adapter);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextPage_id:
                FragmentUtil.setFragment(new ElecFragOne(),getActivity(),"Electricity fragment one", R.id.content_frag,true);
        }
    }
}
