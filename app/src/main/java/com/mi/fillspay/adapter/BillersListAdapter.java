package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnBillerDescItemClick;
import com.mi.fillspay.model.BillerDescResponse;

import java.util.List;

public class BillersListAdapter extends BaseAdapter {

    private Context context; //context
    private List<BillerDescResponse> items; //data source of the list adapter
    OnBillerDescItemClick billerItemClick;

    //public constructor
    public BillersListAdapter(Context context,List<BillerDescResponse> items,OnBillerDescItemClick billerItemClick) {
        this.context = context;
        this.items = items;
        this.billerItemClick = billerItemClick;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.auto_cmplt_item, parent, false);
        }
        final BillerDescResponse item = items.get(position);

        // get the TextView for item name and item description
        TextView tv_Biller = (TextView)
                convertView.findViewById(R.id.text_id_);

        //sets the text for item name and item description from the current item object
        tv_Biller.setText(item.getBillerName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billerItemClick.onBillerItemClick(item);
            }
        });

        // returns the view for the current row
        return convertView;
    }
}