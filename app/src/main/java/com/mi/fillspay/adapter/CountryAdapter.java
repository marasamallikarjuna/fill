package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.mi.fillspay.R;
import com.mi.fillspay.utilities.GradientTextView;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private RecyclerView manager;
    private Context context;
    private String[] stringArray;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        manager = recyclerView;
    }

    public CountryAdapter(Context context, String[] stringArray) {
        inflater = from(context);
        this.context = context;
        this.stringArray = stringArray;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_rv_text, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder hold = (Holder) holder;
        hold.tv.setText(stringArray[position]);
    }

    @Override
    public int getItemCount() {
        return stringArray.length;
    }

    private class Holder extends RecyclerView.ViewHolder {
        GradientTextView tv;
        ImageView imageView;

        Holder(View itemView) {
            super(itemView);
            tv = (GradientTextView) itemView.findViewById(R.id.tvText);
            imageView = (ImageView) itemView.findViewById(R.id.img_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_logo));
                    int adapterPosition = getAdapterPosition();
                    manager.smoothScrollToPosition(adapterPosition);
                }
            });
        }
    }
}