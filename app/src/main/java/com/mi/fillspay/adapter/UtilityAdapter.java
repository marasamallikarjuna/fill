package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnClickView;
import com.mi.fillspay.model.UtilityResponse;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.GradientTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;

/**
 * Created by Srikar on 7/5/2017.
 */
public class UtilityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private RecyclerView manager;
    private Context context;
    private int row_index=-1;
    private List<UtilityResponse> utilityResponses;
    private OnClickView onClickView;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.manager = recyclerView;
    }

    public UtilityAdapter(Context context, List<UtilityResponse> utilityResponse,OnClickView onClickView) {
        inflater = from(context);
        this.context = context;
        this.utilityResponses = utilityResponse;
        this.onClickView = onClickView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_rv_text, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder hold = (Holder) holder;

        hold.imageView.setImageBitmap(AppUtilities.stringToBitmap(utilityResponses.get(holder.getAdapterPosition()).getInActiveLogo()));

        hold.tv_inactive.setVisibility(View.VISIBLE);
        hold.tv_active.setText(utilityResponses.get(holder.getAdapterPosition()).getUtilityname());
        hold.tv_inactive.setText(utilityResponses.get(holder.getAdapterPosition()).getUtilityname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
                manager.smoothScrollToPosition(position);
                onClickView.onClickView(position);
            }
        });

        if(row_index == position){
            hold.tv_active.setVisibility(View.VISIBLE);
            hold.tv_inactive.setVisibility(View.GONE);
            hold.imageView.setImageBitmap(AppUtilities.stringToBitmap(utilityResponses.get(holder.getAdapterPosition()).getActiveLogo()));
        }
        else
        {
            hold.tv_active.setVisibility(View.GONE);
            hold.tv_inactive.setVisibility(View.VISIBLE);
            hold.imageView.setImageBitmap(AppUtilities.stringToBitmap(utilityResponses.get(holder.getAdapterPosition()).getInActiveLogo()));
        }

    }

    @Override
    public int getItemCount() {
        return utilityResponses.size();
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