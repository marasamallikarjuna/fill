package com.mi.fillspay.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.model.ConsumerNoFormatRequest;
import com.mi.fillspay.model.ConsumerNoFormatResponse;
import com.mi.fillspay.model.ListOfIOCatalog;

import android.widget.LinearLayout.LayoutParams;

import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.GradientTextView;
import com.mi.fillspay.utilities.SwipeButton;
import com.mi.fillspay.view_model.ConsumerNoFormatViewModel;

import static com.mi.fillspay.interfaces.keys.ACTIVITY_RESULT;
import static com.mi.fillspay.interfaces.keys.COUNTRY_CODE;
import static com.mi.fillspay.interfaces.keys.UTILITY_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class UtilityFragThree extends Fragment {
    AppPreferencesHelper _preferencesHelper;
    GradientTextView headerName;
    SwipeButton swipeButton;
    TextView tv_boardName;
    AppCompatEditText actv, et_bill_amount;
    ConsumerNoFormatViewModel consumerViewModel;
    LinearLayout consumerLayout;


    public UtilityFragThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elec_frag_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    private void initValues() {
        headerName = getActivity().findViewById(R.id.header_name);
        tv_boardName = getActivity().findViewById(R.id.board_name);
        swipeButton = getActivity().findViewById(R.id.swipe_btn_id);
        actv = getActivity().findViewById(R.id.autoComBillers_ids);
        consumerLayout = getActivity().findViewById(R.id.layout_cnum_format);
        et_bill_amount = getActivity().findViewById(R.id.amount_et_billerdes);

        if (getArguments() != null) {
            headerName.setText("Pay Your " + getArguments().getString(UTILITY_NAME) + " Bill");
            tv_boardName.setText(getArguments().getString(UTILITY_NAME) + " Board");
        }

        actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent billersIntent = new Intent(getActivity(), BillersListActivity.class);
                billersIntent.putExtra(COUNTRY_CODE, getArguments().getString(COUNTRY_CODE));
                billersIntent.putExtra(UTILITY_NAME, getArguments().getString(UTILITY_NAME));
                startActivityForResult(billersIntent, ACTIVITY_RESULT);// Activity is started with requestCode 2
            }
        });

        swipeButton.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                Fragment frag = new PaymentFrag();
                FragmentUtil.setFragment(frag, getActivity(), "Utility fragment one", R.id.content_frag, true);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_RESULT) {
            if (data != null) {
                BillerDescResponse item = data.getParcelableExtra("MESSAGE");
                actv.setText(item.getBillerName());

                consumerLayout.removeAllViewsInLayout();

                consumerLayout.removeAllViews();

                getConsumerNumberFormat(item.getBillerID());
            }
        }
    }

    private void getConsumerNumberFormat(String biller_id) {

        _preferencesHelper = new AppPreferencesHelper(getActivity(), "Spandana");

        consumerViewModel = ViewModelProviders.of(getActivity()).get(ConsumerNoFormatViewModel.class);

        consumerViewModel.getConsmerNoFarmat(new ConsumerNoFormatRequest("1", "1", biller_id), _preferencesHelper.getAccessToken(),getActivity()).observe(this, new Observer<ConsumerNoFormatResponse>() {
            @Override
            public void onChanged(ConsumerNoFormatResponse consumerNoFormatResponse) {

                for (int i = 0; i < consumerNoFormatResponse.getListOfIOCatalog().size(); i++) {
                    onAddField(consumerNoFormatResponse.getListOfIOCatalog().get(i));
                }
            }
        });
    }

    public void onAddField(ListOfIOCatalog itemConstrains) {

        // add text view
        final TextView tv = new TextView(getActivity());
        tv.setText(itemConstrains.getName().trim());
        tv.setPadding(3, 6, 3, 3);
        tv.setTextColor(getResources().getColor(R.color.greyTextcolor));
        tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        consumerLayout.addView(tv);

        // add edit text
        final EditText et_ = new EditText(getActivity());
        et_.setTextSize(R.dimen._13ssp);
        int img = R.drawable.ic_country_icon;
        et_.setCompoundDrawablesWithIntrinsicBounds(img, 0, 0, 0);
        et_.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        et_.getBackground().setColorFilter(getResources().getColor(R.color.colorEdit),
                PorterDuff.Mode.SRC_ATOP);
        setEditTextMaxLength(et_, itemConstrains.getMaxLength());
        consumerLayout.addView(et_);
    }

    public static void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(FilterArray);
    }
}
