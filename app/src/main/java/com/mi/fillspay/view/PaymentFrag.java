package com.mi.fillspay.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.mi.fillspay.R;
import com.mi.fillspay.interfaces.OnSwipeCompleted;
import com.mi.fillspay.utilities.SwipeButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFrag extends Fragment {

    SwipeButton swipeButton;
    RadioGroup radioGroup;
    EditText card_type;

    public PaymentFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    private void initValues() {
        radioGroup = getActivity().findViewById(R.id.radio_group);
        swipeButton = getActivity().findViewById(R.id.swipe_payment);
        card_type = getActivity().findViewById(R.id.et_card_type);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final String value = ((RadioButton) getActivity().findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                card_type.setText(value);
            }
        });

        swipeButton.setOnSwipedCompletedListener(new OnSwipeCompleted() {
            @Override
            public void onSwipeListener() {
                Toast.makeText(getActivity(), "Swipe Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
