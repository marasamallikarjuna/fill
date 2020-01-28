package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.mi.fillspay.R;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.circleRecyclerView.CenterEdgeItemsRecyclerView;
import com.mi.fillspay.utilities.circleRecyclerView.HalfCurveLayoutManager;
import com.mi.fillspay.adapter.RVAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragOne extends Fragment implements View.OnClickListener{

    private CenterEdgeItemsRecyclerView recyclerView;
    private ImageView nextPage;

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
        recyclerView.setCenterEdgeItems(true);
        HalfCurveLayoutManager manager = new HalfCurveLayoutManager(getActivity(), 1.0f);
        recyclerView.setLayoutManager(manager);
        SnapHelper helper = new LinearSnapHelper();
        // Set the adapter
        RVAdapter adapter = new RVAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextPage_id:
                FragmentUtil.setFragment(new UtilityFragTwo(),getActivity(),"Utility fragment one", R.id.content_frag,true);
        }
    }
}
