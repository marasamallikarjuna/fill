package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnItemClick;
import com.mi.fillspay.model.CountryPojo;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.GradientTextView;

import java.util.List;

import static android.view.LayoutInflater.from;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private RecyclerView manager;
    private Context context;
    private OnItemClick onItemClick;
    private List<CountryPojo> stringArray;
    private int row_index=-1;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        manager = recyclerView;
    }

    public CountryAdapter(Context context, List<CountryPojo> stringArray, OnItemClick onItemClick) {
        inflater = from(context);
        this.context = context;
        this.stringArray = stringArray;
        this.onItemClick = onItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_rv_text, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder hold = (Holder) holder;
        hold.tv_active.setText(stringArray.get(position).getCountry());
        hold.tv_inactive.setText(stringArray.get(position).getCountry());
        hold.imageView.setImageBitmap(AppUtilities.stringToBitmap(stringArray.get(position).getLogo()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
                manager.smoothScrollToPosition(position);
                onItemClick.Onclick(stringArray.get(position).getCountryCode());
            }
        });

        if(row_index == position){
            hold.tv_active.setVisibility(View.VISIBLE);
            hold.tv_inactive.setVisibility(View.GONE);
            hold.imageView.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_logo));
        }
        else
        {
            hold.tv_active.setVisibility(View.GONE);
            hold.tv_inactive.setVisibility(View.VISIBLE);
            hold.imageView.setBackgroundResource(0);
        }
    }

    @Override
    public int getItemCount() {
        return stringArray.size();
    }

    private class Holder extends RecyclerView.ViewHolder {
        GradientTextView tv_active;
        TextView tv_inactive;
        ImageView imageView;

        Holder(View itemView) {
            super(itemView);
            tv_active = (GradientTextView) itemView.findViewById(R.id.tvText);
            tv_inactive = (TextView) itemView.findViewById(R.id.tv_util_name);
            imageView = (ImageView) itemView.findViewById(R.id.img_logo);
        }
    }
}