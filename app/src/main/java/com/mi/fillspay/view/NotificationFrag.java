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
import com.mi.fillspay.adapter.NotificationAdapter;
import com.mi.fillspay.adapter.ReccmdAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFrag extends Fragment {

    RecyclerView notifi_listview, recmmd_listview;
    NotificationAdapter notificationAdapter;
    ReccmdAdapter recAdpter;

    public NotificationFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }


    private void initValues() {

        notifi_listview = getActivity().findViewById(R.id.notifi_list);
        recmmd_listview = getActivity().findViewById(R.id.recycler_recmmds_id);

        notificationAdapter = new NotificationAdapter(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        notifi_listview.setLayoutManager(layoutManager);
        notifi_listview.setAdapter(notificationAdapter);
        notificationAdapter.notifyDataSetChanged();

        recAdpter  = new ReccmdAdapter(getActivity());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recmmd_listview.setLayoutManager(layoutManager1);
        recmmd_listview.setAdapter(recAdpter);
        recAdpter.notifyDataSetChanged();

    }

}
