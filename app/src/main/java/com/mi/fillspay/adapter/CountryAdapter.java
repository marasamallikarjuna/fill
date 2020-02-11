package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnItemClick;
import com.mi.fillspay.utilities.GradientTextView;
import static android.view.LayoutInflater.from;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private RecyclerView manager;
    private Context context;
    private OnItemClick onItemClick;
    private String[] stringArray;
    private int row_index=-1;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        manager = recyclerView;
    }

    public CountryAdapter(Context context, String[] stringArray,OnItemClick onItemClick) {
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
        hold.tv.setText(stringArray[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
                manager.smoothScrollToPosition(position);
                onItemClick.Onclick(stringArray[position]);
            }
        });

        if(row_index==position){
            hold.imageView.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_logo));
        }
        else
        {
            hold.imageView.setBackgroundResource(0);
        }
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
        }
    }
}