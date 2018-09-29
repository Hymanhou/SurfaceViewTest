package com.example.houyuan.surfaceviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback2, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private  boolean mIsDrawing;
    private Path mPath;
    private Paint mPaint;
    private Bitmap bitmap;

    private void initView(){
        this.mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fengye);
    }

    public MySurfaceView(Context context) {
        super(context);
        initView();
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public static final int TIME_IN_FRAME = 30;

    @Override
    public void run() {
        while (mIsDrawing){
            long startTime = System.currentTimeMillis();
            synchronized (mHolder){
                draw();
            }
            long endTime = System.currentTimeMillis();
            int diffTime = (int)(endTime - startTime);
            while (diffTime <= TIME_IN_FRAME){
                diffTime = (int)(System.currentTimeMillis() - startTime);
                Thread.yield();
            }
        }
    }

    private int x = 0;
    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            x += 1;
            if (x > 400){
                x = 1;
            }
            int y = (int)(100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
            Matrix matrix = new Matrix();
            matrix.setRotate(20, x, y);
            matrix.setScale(0.3f, 0.3f);
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0,0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawBitmap(bitmap1, x, y, mPaint);
//            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e){

        } finally {
            if (mCanvas != null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
