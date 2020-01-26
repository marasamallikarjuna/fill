package com.mi.fillspay.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.HistoryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFrag extends Fragment {
    HistoryAdapter historyAdapter;
    RecyclerView recyclerView;

    public HistoryFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    private void initValues() {
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        historyAdapter = new HistoryAdapter(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
    }
}
