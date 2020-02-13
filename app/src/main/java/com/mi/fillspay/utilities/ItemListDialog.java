package com.mi.fillspay.utilities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;

import com.mi.fillspay.R;
import com.mi.fillspay.adapter.IsoCodesAdapter;
import com.mi.fillspay.model.IsdCode;

import java.util.List;

public class ItemListDialog  extends DialogFragment {

    private ListView listView;
    private Context context;
    private List<IsdCode> isdCodes;
    private ImageView img_dismiss;
    private ImageView img_flag;
    private AppCompatEditText text_countrycode;

    public ItemListDialog(Context context, List<IsdCode> isdCodeList,ImageView img_flag,AppCompatEditText textView) {
     this.context = context;
     this.isdCodes = isdCodeList;
     this.img_flag = img_flag;
     this.text_countrycode = textView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.layout_item_list, null);

         listView = rootView.findViewById(R.id.countryDialog_list);
         img_dismiss = rootView.findViewById(R.id.img_dismiss);

        IsoCodesAdapter adapter = new IsoCodesAdapter(context,isdCodes);
        listView.setAdapter(adapter);

         img_dismiss.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dismiss();
             }
         });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                text_countrycode.setText(isdCodes.get(position).getIsdCode()+" ");
                img_flag.setImageBitmap(AppUtilities.stringToBitmap(isdCodes.get(position).getFlag()));
                dismiss();
            }
        });

        return rootView;

    }
}
