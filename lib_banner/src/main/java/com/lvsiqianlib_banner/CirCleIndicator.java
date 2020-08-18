package com.lvsiqianlib_banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.Telephony;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class CirCleIndicator extends View implements Indicator {
    private Paint mPaint;//画笔
    private static final int MAX_COUNT = 10;
    private  int MCurrentIndex;
    private  int MUnSelector;
    private  int MSelector;
    private  int MHeight;
    private  int mWidth;
    private  int MRatio;
    private  int mMargin;

    private  int MCount;//最终显示个数
    private  int MreealCount;//实际个数

    public CirCleIndicator(Context context) {
        super(context);
        initPain();
    }

    public CirCleIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPain();
    }

    public CirCleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPain();
    }


    //创建画笔的方法
    private  void initPain(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//去锯齿
        mPaint.setStyle(Paint.Style.FILL);//实心圆
        mPaint.setColor(Color.WHITE);

    }
    //计算宽和高
       private  void calculation(){
          MHeight= MRatio*2;//高
          MCount=Math.min(MreealCount,MAX_COUNT);
           mWidth=(MCount*MRatio*2)+(MCount-1)*mMargin;
           invalidate();//刷新页面，重写onMeassure onLayout onDraw

       }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(mWidth,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(MHeight,MeasureSpec.EXACTLY));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.RED);
        for (int i = 0; i < MCount; i++) {
            if(i==MCurrentIndex%MCount){
                mPaint.setColor(MSelector);

            }else{
                mPaint.setColor(MUnSelector);
            }
            canvas.drawCircle((i * (2 * MRatio) + (i * mMargin) + MRatio),MRatio,MRatio,mPaint);

        }
    }

    @Override
    public void setRadio(int radio) {
        MRatio=radio;


    }



    @Override
    public void setCount(int count) {

        if(MreealCount != count){
            MreealCount = count;
            calculation();
        }
    }

    @Override
    public void setCurrent(int index) {
        if(MCurrentIndex != index){
            MCurrentIndex = index;
            invalidate();
        }
    }

    @Override
    public void setUnSelectColor(int color) {
        MUnSelector=color;

    }

    @Override
    public void setSelect(int color) {
        MSelector=color;
    }

    @Override
    public void setMargin(int margin) {
        mMargin=margin;
    }




}
