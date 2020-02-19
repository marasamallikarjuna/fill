package com.mi.fillspay.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;
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
import android.widget.Toast;

import com.mi.fillspay.model.ViewAmountDueRequest;
import com.mi.fillspay.model.ViewAmountDueResponse;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.utilities.FragmentUtil;
import com.mi.fillspay.utilities.GradientTextView;
import com.mi.fillspay.utilities.SwipeButton;
import com.mi.fillspay.utilities.ZoomableImageView;
import com.mi.fillspay.utilities.circleRecyclerView.MainActivity;
import com.mi.fillspay.view_model.ConsumerNoFormatViewModel;
import com.mi.fillspay.view_model.SampleBillViewModel;
import com.mi.fillspay.view_model.ViewAmountDueViewModel;

import java.util.ArrayList;

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
    TextView tv_boardName, tv_viewsample_bill;
    AppCompatEditText actv, et_bill_amount;
    ConsumerNoFormatViewModel consumerViewModel;
    ViewAmountDueViewModel viewAmountDueViewModel;
    LinearLayout consumerLayout;
    ArrayList<EditText> et_list;
    private int et_id;
    Button viewbill;
    String biller_id;
    String sku_id;
    private String sampleBill_id;

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

        _preferencesHelper = new AppPreferencesHelper(getActivity(), "Spandana");

        headerName = getActivity().findViewById(R.id.header_name);
        tv_boardName = getActivity().findViewById(R.id.board_name);
        swipeButton = getActivity().findViewById(R.id.swipe_btn_id);
        actv = getActivity().findViewById(R.id.autoComBillers_ids);
        consumerLayout = getActivity().findViewById(R.id.layout_cnum_format);
        viewbill = getActivity().findViewById(R.id.view_bill);
        et_bill_amount = getActivity().findViewById(R.id.et_amount_id);
        tv_viewsample_bill = getActivity().findViewById(R.id.tv_viewsample_bill);

        if (getArguments() != null) {
            headerName.setText(String.valueOf("Pay Your " + getArguments().getString(UTILITY_NAME) + " Bill"));
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

        tv_viewsample_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (biller_id != null)
                    viewSampleBill(biller_id);
            }
        });

        viewbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                if (et_list.size() != 0) {
                    for (int j = 0; j < et_list.size(); j++) {
                        if (!TextUtils.isEmpty(et_list.get(j).getText().toString())) {
                            stringBuilder.append(et_list.get(j).getText().toString());
                            if (j != et_list.size() - 1)
                                stringBuilder.append("|");
                        }
                    }
                    //  Log.d("dhfgdgf", String.valueOf(stringBuilder));
                    if (!TextUtils.isEmpty(String.valueOf(stringBuilder))) {
                        viewBillAmount(String.valueOf(stringBuilder));
                    } else {
                        Toast.makeText(getActivity(), "Please enter required fields", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void viewSampleBill(String biller_id) {

        SampleBillViewModel sampleBillViewModel = ViewModelProviders.of(this).get(SampleBillViewModel.class);

        AppUtilities.showProgress(getActivity());

        sampleBillViewModel.getSampleBill(_preferencesHelper.getAccessToken(), biller_id, getActivity()).observe(this, sampleBillResponse -> {

            AppUtilities.stopProgress();

            if (sampleBillResponse.getPicBytes() != null) {
                if (getActivity() != null)
                    AppUtilities.fullScreenImageDialog(getActivity(), sampleBillResponse.getPicBytes());
            }

        });

    }

    private void viewBillAmount(String input) {

        AppUtilities.showProgress(getActivity());

        viewAmountDueViewModel = ViewModelProviders.of(this).get(ViewAmountDueViewModel.class);

        viewAmountDueViewModel.getViewAmountDue(new ViewAmountDueRequest("1", "1", biller_id, input, sku_id, ""), _preferencesHelper.getAccessToken(), getActivity())
                .observe(this, new Observer<ViewAmountDueResponse>() {
                    @Override
                    public void onChanged(ViewAmountDueResponse viewAmountDueResponse) {
                        AppUtilities.stopProgress();
                        // Log.d("sjhfasf", viewAmountDueResponse.getBillAmountDue() + "");
                        et_bill_amount.setText(String.valueOf(viewAmountDueResponse.getBillAmountDue()));
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_RESULT) {

            if (data != null) {

                BillerDescResponse item = data.getParcelableExtra("MESSAGE");

                actv.setText(String.valueOf(item.getBillerName()));

                consumerLayout.removeAllViewsInLayout();

                consumerLayout.removeAllViews();

                biller_id = item.getBillerID();

                getConsumerNumberFormat(item.getBillerID());

            }
        }
    }

    private void getConsumerNumberFormat(String biller_id) {

        consumerViewModel = ViewModelProviders.of(getActivity()).get(ConsumerNoFormatViewModel.class);

        consumerViewModel.getConsmerNoFarmat(new ConsumerNoFormatRequest("1", "1", biller_id), _preferencesHelper.getAccessToken(), getActivity()).observe(this, consumerNoFormatResponse -> {
            AppUtilities.showProgress(getActivity());
            et_id = 0;
            et_list = new ArrayList<>();
            sku_id = consumerNoFormatResponse.getListOfIOCatalog().get(0).getSKU();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    for (int i = 0; i < consumerNoFormatResponse.getListOfIOCatalog().size(); i++) {
                        onAddField(consumerNoFormatResponse.getListOfIOCatalog().get(i));
                    }
                    viewbill.setVisibility(View.VISIBLE);
                    AppUtilities.stopProgress();
                }
            }, 1000);
        });
    }

    private void onAddField(ListOfIOCatalog itemConstrains) {
        // add text view
        final TextView tv = new TextView(getActivity());
        tv.setText(itemConstrains.getName().trim());
        tv.setPadding(3, 6, 3, 3);
        tv.setTextColor(getResources().getColor(R.color.greyTextcolor));
        tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        consumerLayout.addView(tv);
        // add edit text
        final EditText et_ = new EditText(getActivity());
        int img = R.drawable.ic_country_icon;
        et_.setTextColor(getResources().getColor(R.color.black));
        et_.setCompoundDrawablesWithIntrinsicBounds(img, 0, 0, 0);
        et_.setCompoundDrawablePadding(10);
        et_.setHighlightColor(getResources().getColor(R.color.black));
        et_.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        et_.getBackground().setColorFilter(getResources().getColor(R.color.colorEdit),
                PorterDuff.Mode.SRC_ATOP);
        Typeface typeface = ResourcesCompat.getFont(getActivity(), R.font.amarante);
        et_.setTypeface(typeface);
        if (itemConstrains.getDatatype().equalsIgnoreCase("Numeric")) {
            et_.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            et_.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        setEditTextMaxLength(et_, itemConstrains.getMaxLength());
        et_.setId(et_id);
        et_id++;
        et_list.add(et_);
        consumerLayout.addView(et_);
    }

    private static void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(FilterArray);
    }
}
