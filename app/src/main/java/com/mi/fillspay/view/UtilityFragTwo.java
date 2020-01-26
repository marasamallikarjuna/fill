package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.CircleLayoutManager;
import com.mi.fillspay.adapter.SampleAdapter;
import com.mi.fillspay.utilities.FragmentUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragTwo extends Fragment implements View.OnClickListener {

    private RecyclerView list;
    private ViewGroup root;
    private CircleLayoutManager layoutManager;
    private SampleAdapter adapter;
    private ImageView nextPage;

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
        root = (ViewGroup) getActivity().findViewById(R.id.root);
        list = (RecyclerView)getActivity().findViewById(R.id.recycler_view);
        adapter = new SampleAdapter(getActivity());
        final int radius = (int) getResources().getDimension(R.dimen.list_radius);
        final int peek = (int) getResources().getDimension(R.dimen.list_peek);
        nextPage = getActivity().findViewById(R.id.nextPage_id);
        nextPage.setOnClickListener(this);

        layoutManager = new CircleLayoutManager(getActivity(), CircleLayoutManager.Gravity.START, CircleLayoutManager.Orientation.VERTICAL, radius, peek, true);

        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextPage_id:
                FragmentUtil.setFragment(new ElecFragOne(),getActivity(),"Electricity fragment one", R.id.content_frag,true);
        }
    }
}
