package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    private final LayoutInflater layoutInflater;

    public SampleAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sample, parent, false);



        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 31;
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public SampleViewHolder(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
