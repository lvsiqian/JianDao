package com.example.last_22.Banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lvsiqianlib_banner.CirCleIndicator;

public class Circledicator  extends View implements  Indicator {

    private  static  final  int MAX_COUNT=10;
    private  int MRadio;
    private  int MCount;//最终显示的个数
    private int MReeallCount;//实际的个数
    private  int MWidth;
    private  int MHight;
    private  int MUnselectcolor;
    private  int MSelector;
    private  int MCurrentIndex;
    private  int Margin;
    private Paint paint;


    public Circledicator(Context context) {
        super(context);
        initpain();
    }

    public Circledicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initpain();
    }

    public Circledicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initpain();
    }
    //创建画笔
    private  void initpain(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);//实心圆
        paint.setColor(Color.WHITE);


    }
    private  void calculation(){
        MHight=MRadio*2;
        MCount=Math.min(MReeallCount,MAX_COUNT);//最终显示的个数
        MWidth=MCount*(MRadio*2)+(MCount-1)*Margin;
        invalidate();//刷新界面
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(MWidth,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(MHight,MeasureSpec.EXACTLY));

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < MCount; i++) {
            if(i==MCurrentIndex%MCount){
                paint.setColor(MSelector);
            }else{
                paint.setColor(MUnselectcolor);
            }
            canvas.drawCircle((i * (2 * MRadio) + (i * Margin) + MRadio),MRadio,MRadio,paint);
        }
    }

    @Override
    public void setRadio(int radio) {
        MRadio=radio;

    }

    @Override
    public void setCount(int count) {
        if(MReeallCount!=count){
            MReeallCount= count;
            calculation();
        }


    }

    @Override
    public void setCurrent(int current) {
        if(MCurrentIndex!=current){
            MCurrentIndex=current;
            invalidate();
        }


    }

    @Override
    public void setmagin(int magin) {
        Margin=magin;

    }

    @Override
    public void setUnselectColor(int color) {
        MUnselectcolor=color;

    }

    @Override
    public void setselectColor(int color) {
        MSelector=color;

    }


}
