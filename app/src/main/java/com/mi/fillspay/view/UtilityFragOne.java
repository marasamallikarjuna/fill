package com.mi.fillspay.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.mi.fillspay.R;
import com.mi.fillspay.adapter.CountryAdapter;
import com.mi.fillspay.interfaces.OnItemClick;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.model.CountryRequest;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.circleRecyclerView.CenterEdgeItemsRecyclerView;
import com.mi.fillspay.utilities.circleRecyclerView.HalfCurveLayoutManager;
import com.mi.fillspay.view_model.CountryViewModel;

import static com.mi.fillspay.interfaces.keys.COUNTRY_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragOne extends Fragment implements View.OnClickListener {

    private CenterEdgeItemsRecyclerView recyclerView;
    private ImageView nextPage;
    CountryViewModel countryViewModel;
    AppPreferencesHelper _preferencesHelper;
    String country;

    public UtilityFragOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_utility_frag_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        recyclerView = (CenterEdgeItemsRecyclerView) getActivity().findViewById(R.id.wrv);
        nextPage = getActivity().findViewById(R.id.nextPage_id);
        nextPage.setOnClickListener(this);
        getCountries();
    }

    private void getCountries() {

        AppUtilities.showProgress(getActivity());

        _preferencesHelper = new AppPreferencesHelper(getActivity(), "Spandana");

        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        countryViewModel.getCountries(new CountryRequest("1", "1", "Utility"), _preferencesHelper.getAccessToken(),getActivity()).observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(String[] utilityResponses) {
                AppUtilities.stopProgress();
                recyclerView.setCenterEdgeItems(true);
                HalfCurveLayoutManager manager = new HalfCurveLayoutManager(getActivity(), 1.0f);
                recyclerView.setLayoutManager(manager);
                CountryAdapter adapter = new CountryAdapter(getActivity(), utilityResponses, new OnItemClick() {
                    @Override
                    public void Onclick(String str) {
                        country = str;
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextPage_id:
                if (country != null) {
                    Fragment frag = new UtilityFragTwo();
                    Bundle bundle = new Bundle();
                    bundle.putString(COUNTRY_CODE, country);
                    frag.setArguments(bundle);
                    FragmentUtil.setFragment(frag, getActivity(), "Utility fragment one", R.id.content_frag, true);
                } else {
                    Toast.makeText(getActivity(), "Please choose country", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
