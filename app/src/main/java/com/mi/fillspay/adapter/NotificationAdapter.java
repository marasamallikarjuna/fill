package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.SampleViewHolder> {

    private final LayoutInflater layoutInflater;

    private Boolean expanded = false;

    public NotificationAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);

        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img_close;

        private SampleViewHolder(View itemView) {
            super(itemView);
          //  this.tv = (TextView) itemView.findViewById(R.id.textView);
            this.img_close = itemView.findViewById(R.id.not_close);
        }
    }

}