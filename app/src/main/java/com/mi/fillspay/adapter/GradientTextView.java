package com.mi.fillspay.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.mi.fillspay.R;


@SuppressLint("AppCompatCustomView")
public class GradientTextView extends TextView {

    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //Setting the gradient if layout is changed
        if (changed) {
            getPaint().setShader(new LinearGradient(0, 0, getWidth(), getHeight(),
                    getContext().getColor(R.color.startColor),
                    getContext().getColor(R.color.endColor),
                    Shader.TileMode.CLAMP));
        }
    }
}
