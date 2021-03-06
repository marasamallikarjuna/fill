package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.CountryAdapter;
import com.mi.fillspay.adapter.UtilityAdapter;
import com.mi.fillspay.interfaces.OnClickView;
import com.mi.fillspay.interfaces.OnItemClick;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.model.SubutilityRequest;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.circleRecyclerView.CenterEdgeItemsRecyclerView;
import com.mi.fillspay.utilities.circleRecyclerView.HalfCurveLayoutManager;
import com.mi.fillspay.view_model.SubutilityViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.mi.fillspay.interfaces.keys.COUNTRY_CODE;
import static com.mi.fillspay.interfaces.keys.UTILITY_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragTwo extends Fragment implements View.OnClickListener {

    private CenterEdgeItemsRecyclerView recyclerView;
    private ImageView nextPage;
    SubutilityViewModel utilitiesViewModel;
    UtilityAdapter adapter;
    AppPreferencesHelper _preferencesHelper;
    String utilityName;
    Bundle bundle;
    public List<UtilityResponse> utilities = new ArrayList<>();

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

        bundle = getArguments();

        if (bundle != null)
            getUtilities(String.valueOf(bundle.getString(COUNTRY_CODE)));
    }


    private void getUtilities(String countryCode) {

        AppUtilities.showProgress(getActivity());

        _preferencesHelper = new AppPreferencesHelper(getActivity(), "Spandana");

        utilitiesViewModel = ViewModelProviders.of(this).get(SubutilityViewModel.class);

        utilitiesViewModel.getSubUtilities(new SubutilityRequest("1", "1","Utility",countryCode), _preferencesHelper.getAccessToken(),getActivity()).observe(this, new Observer<List<UtilityResponse>>() {
            @Override
            public void onChanged(List<UtilityResponse> utilityResponses) {
                if(utilityResponses != null){
                    AppUtilities.stopProgress();
                    recyclerView.setCenterEdgeItems(true);
                    HalfCurveLayoutManager manager = new HalfCurveLayoutManager(getActivity(), 1.0f);
                    recyclerView.setLayoutManager(manager);
                    adapter = new UtilityAdapter(getActivity(), utilityResponses, new OnClickView() {
                        @Override
                        public void onClickView(int position) {
                            utilityName = utilityResponses.get(position).getUtilityname();
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }
                AppUtilities.stopProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextPage_id:
                if (utilityName != null) {
                    Fragment frag = new UtilityFragThree();
                    Bundle appbundle = new Bundle();
                    appbundle.putString(UTILITY_NAME, utilityName);
                    appbundle.putString(COUNTRY_CODE, String.valueOf(bundle.getString(COUNTRY_CODE)));
                    frag.setArguments(appbundle);
                    FragmentUtil.setFragment(frag, getActivity(), "Utility fragment three", R.id.content_frag, true);
                } else {
                    Toast.makeText(getActivity(), "Please choose utility", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
