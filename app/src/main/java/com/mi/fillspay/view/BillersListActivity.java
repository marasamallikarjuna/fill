package com.mi.fillspay.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import com.mi.fillspay.R;
import com.mi.fillspay.adapter.BillersListAdapter;
import com.mi.fillspay.interfaces.OnBillerDescItemClick;
import com.mi.fillspay.local.prefe.AppPreferencesHelper;
import com.mi.fillspay.model.BillerDescRequest;
import com.mi.fillspay.model.BillerDescResponse;
import com.mi.fillspay.utilities.AppUtilities;
import com.mi.fillspay.view_model.BillerDescViewModel;
import java.util.List;
import static com.mi.fillspay.interfaces.keys.ACTIVITY_RESULT;
import static com.mi.fillspay.interfaces.keys.COUNTRY_CODE;
import static com.mi.fillspay.interfaces.keys.UTILITY_NAME;

public class BillersListActivity extends AppCompatActivity{

    AppPreferencesHelper _preferencesHelper;
    BillerDescViewModel billerDescViewModel;
    ListView listView;
    BillersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billers_list);
        initValues();
    }

    private void initValues() {
        listView = findViewById(R.id.list_recycler);
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            getBillers(bundle.getString(UTILITY_NAME),bundle.getString(COUNTRY_CODE));
        }
    }

    private void getBillers(String billerDescription, String countryCode) {
        AppUtilities.showProgress(this);
        _preferencesHelper = new AppPreferencesHelper(this, "Spandana");
        billerDescViewModel = ViewModelProviders.of(this).get(BillerDescViewModel.class);
        billerDescViewModel.getBillerDesc(new BillerDescRequest("1", "1", countryCode, "Utility", billerDescription),
                _preferencesHelper.getAccessToken()).observe(this, new Observer<List<BillerDescResponse>>() {
            @Override
            public void onChanged(List<BillerDescResponse> billerDescResponses) {
                AppUtilities.stopProgress();
                adapter = new BillersListAdapter(BillersListActivity.this,billerDescResponses, new OnBillerDescItemClick() {
                    @Override
                    public void onBillerItemClick(BillerDescResponse item) {
                        Intent intent=new Intent();
                        intent.putExtra("MESSAGE",item);
                        setResult(ACTIVITY_RESULT,intent);
                        finish();//finishing activity
                    }
                });
                listView.setAdapter(adapter);
            }
        });
    }
}
