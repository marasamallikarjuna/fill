package com.mi.fillspay.utilities.circleRecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import static com.mi.fillspay.utilities.circleRecyclerView.Utils.getPixelFromDp;


@SuppressWarnings("UnusedParameters")
public class CurveView extends View {
    
    private Paint dashPaint;
    private Paint straightPaint;

    public CurveView(Context context) {
        this(context, null, 0);
    }
    
    public CurveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public CurveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    
    private void init(Context context, AttributeSet attrs) {

        dashPaint = new Paint();

        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setPathEffect(new DashPathEffect(new float[]{getPixelFromDp(4f), getPixelFromDp(4f)}, 0));
        dashPaint.setColor(Color.GRAY);
        dashPaint.setStrokeWidth(getPixelFromDp(2f));
        dashPaint.setStrokeJoin(Paint.Join.ROUND);
        dashPaint.setStrokeCap(Paint.Cap.ROUND);
        dashPaint.setAntiAlias(true);
        dashPaint.setDither(true);
        dashPaint.setAlpha(128);
        
        straightPaint = new Paint();
        straightPaint.setStyle(Paint.Style.STROKE);
        straightPaint.setColor(Color.WHITE);
        straightPaint.setStrokeWidth(getPixelFromDp(2f));
        straightPaint.setAntiAlias(true);
        straightPaint.setDither(true);
        straightPaint.setStrokeJoin(Paint.Join.ROUND);
        straightPaint.setStrokeCap(Paint.Cap.ROUND);
        straightPaint.setAlpha(128);
    }
    
    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            super.onDraw(canvas);
            return;
        }
        float centerX = -width / 8;
        float centerY = height / 2;

        canvas.drawCircle(centerX, centerY, (float) (height / 2.4), dashPaint);

    }
}
