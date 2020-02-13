package com.mi.fillspay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.mi.fillspay.R;
import com.mi.fillspay.model.IsdCode;
import com.mi.fillspay.utilities.AppUtilities;

import java.util.ArrayList;
import java.util.List;


public class IsoCodesAdapter extends ArrayAdapter<IsdCode> {

    private ArrayList<IsdCode> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        ImageView flag;
    }

    public IsoCodesAdapter(Context context, List<IsdCode> data) {
        super(context, R.layout.iso_code_item, data);
        dataSet = new ArrayList<>();
        dataSet.addAll(data);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        IsdCode dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.iso_code_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textView_countryName);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.textView_code);
            viewHolder.flag = (ImageView) convertView.findViewById(R.id.image_flag);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtName.setText(dataModel.getCountry());
        viewHolder.txtType.setText(dataModel.getIsdCode());
        viewHolder.flag.setImageBitmap(AppUtilities.stringToBitmap(dataModel.getFlag()));

        // Return the completed view to render on screen
        return convertView;
    }
}