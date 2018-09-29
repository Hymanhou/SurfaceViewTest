package com.example.houyuan.surfaceviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    private Bitmap bitmap;
    private Canvas mCanvas;
    private Paint mPaint;


    private void init() {
        mCanvas = new Canvas();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fengye);
    }

    public MyView(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(400, 400);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas.drawColor(Color.RED);
//        myDraw(canvas);
    }

    private int x = 0;

    private void myDraw(Canvas canvas) {
        try {
            mCanvas.drawColor(Color.RED);
            mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
        } catch (Exception e){

        } finally {

        }
    }
}
