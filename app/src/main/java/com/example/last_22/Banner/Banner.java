package com.example.last_22.Banner;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager2.widget.ViewPager2;



import java.util.ArrayList;

public class Banner  extends ConstraintLayout {
    private  int mIds=0x1000;
    private ArrayList<?extends  BannerData> mdata;

    private  static  final  int DEFAULT_INTERVAL=3000;
    private  static  final  int DEFAULT_SCROLL=1000;

    private ViewPager2 viewPager2;
    private ImageView imageView;
    private TextView mTitle1;
    private  Indicator indicator;
    private  int mIndicatorEndMargin;
    private  int MInterval;//间隔切换时间
    private  int ScrollTime;//动画执行时间

    private  boolean isAutoloop=true;//是否自动轮播

    public Banner(Context context) {
        super(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initValue();
        initView();
    }

    private void initValue() {
        mIndicatorEndMargin=dip2px(15);
        MInterval=DEFAULT_INTERVAL;
        ScrollTime=DEFAULT_SCROLL*2;
    }

    private void initView() {
        //添加viewpage2
        viewPager2 = new ViewPager2(getContext());
        viewPager2.setId(mIds++);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTitle1.setText(mdata.get(position%mdata.size()).gettitle());
                indicator.setCurrent(position%mdata.size());
            }
        });
        addView(viewPager2);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(viewPager2.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(viewPager2.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        constraintSet.connect(viewPager2.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        constraintSet.connect(viewPager2.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        constraintSet.constrainWidth(viewPager2.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(viewPager2.getId(),ConstraintSet.MATCH_CONSTRAINT);
       // constraintSet.applyTo(this);
        //添加半透明背景
        imageView = new ImageView(getContext());
        imageView.setId(mIds++);
        addView(imageView);
        imageView.setBackgroundColor(Color.parseColor("#40000000"));
        constraintSet.connect(imageView.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(imageView.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        constraintSet.connect(imageView.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);

        constraintSet.constrainWidth(imageView.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(imageView.getId(),dip2px(50));

//添加指示器
        indicator = new Circledicator(getContext());
        indicator.setId(mIds++);
        indicator.setRadio(10);
        indicator.setmagin(10);
        indicator.setselectColor(Color.RED);
        indicator.setUnselectColor(Color.WHITE);
        addView((View) indicator);


        constraintSet.connect(indicator.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END,mIndicatorEndMargin);
        constraintSet.connect(indicator.getId(),ConstraintSet.BOTTOM, imageView.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(indicator.getId(),ConstraintSet.TOP, imageView.getId(),ConstraintSet.TOP);
        constraintSet.constrainWidth(indicator.getId(),ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(indicator.getId(),ConstraintSet.WRAP_CONTENT);

        //添加title
        mTitle1 = new TextView(getContext());
        mTitle1.setId(mIds++);
        mTitle1.setTextColor(Color.WHITE);
        mTitle1.setEllipsize(TextUtils.TruncateAt.MARQUEE);//设置跑马灯
        mTitle1.setSelected(true);
        mTitle1.setSingleLine(true);
        mTitle1.setMarqueeRepeatLimit(-1);//无限循环
        addView(mTitle1);

        constraintSet.connect(mTitle1.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(mTitle1.getId(),ConstraintSet.END,indicator.getId(),ConstraintSet.START,mIndicatorEndMargin);
        constraintSet.connect(mTitle1.getId(),ConstraintSet.BOTTOM, imageView.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(mTitle1.getId(),ConstraintSet.TOP, imageView.getId(),ConstraintSet.TOP);
        constraintSet.constrainWidth(mTitle1.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mTitle1.getId(),ConstraintSet.WRAP_CONTENT);

        constraintSet.applyTo(this);//进行约束






    }



    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {//事件分发
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                stopLoop();
                break;

            }
            case MotionEvent.ACTION_UP:{
                startLoop();
                break;
            }
        }
        return super.dispatchHoverEvent(event);
    }
    public  void setAdaPter(ArrayList<? extends BannerData> data){
        mdata=data;
        viewPager2.setAdapter(new SimpleAdapetr(data));
        viewPager2.setUserInputEnabled(true);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        ScrollSpeedManger.reflectLayoutManager(this);
        //找到中间数

        int initPosition=Integer.MAX_VALUE/2;
        initPosition=initPosition-(initPosition%mdata.size());
        viewPager2.setCurrentItem(initPosition,false);
        indicator.setCount(data.size());
        indicator.setCurrent(initPosition%mdata.size());


    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(visibility==VISIBLE){
            startLoop();
        }else{
            stopLoop();
        }
    }

    //handle创建一个切换间隔时间的事件
    private  Runnable mLoopTask=new Runnable() {
        @Override
        public void run() {
            int cIndex = viewPager2.getCurrentItem();
            //a++先执行，再自增，++a先自增再执行
            viewPager2.setCurrentItem(++cIndex,true);//平滑的
            getHandler().postDelayed(this,MInterval);
        }
    };



    private   void startLoop(){
        if(isAutoloop&&mdata!=null&&mdata.size()>1){
            getHandler().postDelayed(mLoopTask,MInterval);
        }


    }

    private  void stopLoop(){
        getHandler().removeCallbacks(mLoopTask);


    }

    //缓慢动画
    public int getScrollTime(){

        return  ScrollTime;
    }
    public ViewPager2 getViewPager2()
    {
        return viewPager2;
    }
    //常用的把dip值转换为pix像素值的方法通常是这样的：(半透明背景）
    public  int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
