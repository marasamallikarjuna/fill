package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mi.fillspay.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.SampleViewHolder> {

    private final LayoutInflater layoutInflater;

    private Boolean expanded = false;

    public HistoryAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_history_item, parent, false);

        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

        holder.bind(expanded);

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    expanded = false;
                } else {
                    expanded = true;
                }
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        Switch aSwitch;
        RelativeLayout repeat_layout, expanded_layout;

        private SampleViewHolder(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView.findViewById(R.id.textView);
            this.aSwitch = itemView.findViewById(R.id.switch_item);
            this.repeat_layout = itemView.findViewById(R.id.layout_repeat);
            this.expanded_layout = itemView.findViewById(R.id.expanded_layout_id);
        }

        public void bind(boolean expanded) {
            repeat_layout.setVisibility(expanded ? View.GONE : View.VISIBLE);
            expanded_layout.setVisibility(expanded ? View.VISIBLE : View.GONE);
        }
    }

}
