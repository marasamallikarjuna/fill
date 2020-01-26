package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;

public class ReccmdAdapter extends RecyclerView.Adapter<ReccmdAdapter.SampleViewHolder> {

    private final LayoutInflater layoutInflater;

    public ReccmdAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recmd_layout, parent, false);

        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 11;
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {
        ImageView imag_backround;

        private SampleViewHolder(View itemView) {
            super(itemView);
            ;
            this.imag_backround = itemView.findViewById(R.id.image_bcgrnd);
        }
    }

}