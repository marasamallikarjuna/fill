package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mi.fillspay.R;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.utilities.GradientTextView;
import java.util.ArrayList;
import static android.view.LayoutInflater.from;

/**
 * Created by Srikar on 7/5/2017.
 */
public class UtilityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private RecyclerView manager;
    private Context context;
    private ArrayList<UtilityResponse> utilityResponses;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        manager = recyclerView;
    }

    public UtilityAdapter(Context context, ArrayList<UtilityResponse> utilityResponse) {
        inflater = from(context);
        this.context = context;
        this.utilityResponses = utilityResponse;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_rv_text, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder hold = (Holder) holder;
        Glide.with(context).load(utilityResponses.get(holder.getAdapterPosition())).placeholder(R.drawable.ic_registr_icon).into(hold.imageView);
        hold.tv.setText(utilityResponses.get(holder.getAdapterPosition()).getUtilityname());

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        }*/

    }

    @Override
    public int getItemCount() {
        return utilityResponses.size();
    }

    private class Holder extends RecyclerView.ViewHolder {
        GradientTextView tv;
        ImageView imageView;

        Holder(View itemView) {
            super(itemView);
            tv = (GradientTextView) itemView.findViewById(R.id.tvText);
            imageView = (ImageView) itemView.findViewById(R.id.img_logo);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.ic_bg_logo));
                    int adapterPosition = getAdapterPosition();
                    manager.smoothScrollToPosition(adapterPosition);
                }
            });*/
        }
    }
}