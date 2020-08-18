package com.lvsiqianlib_banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Banner extends ConstraintLayout {
   // int mTime=3000;
    private static final int DEFAULT_INTERVAL = 3000;//默认时间
    private static final int SCROLL_INTERVAL = 1000;//执行动画默认时间

    private  int mIds=0x1000;
    private  Indicator indicator;
    public  ViewPager2 mPager;
    private  ArrayList<? extends BannerData> mdata;

    private TextView mTitle;

    private  static  final  int SCROLL_MODE_VERTICAL=1;
    private  static  final  int SCROLL_MODE_HORIZONTAL=0;
    private  static  final  int DEFAULT_MARGIN_DP=12;
    private  static  final  int DEFAULT_INDICATOR_RADIO=3;
    private  static  final  int DEFAULT_TITLE_SIZE_DP=12;

    private int mIndicatorEndMargin;//距离右边的距离
    private  int MInterval;//切换间隔时间
    private  int ScrollTime;//动画执行时间
    private  int mScrollMode;//切换模式，垂直或者水平
    private int mMarginTitleStart;
    private  int mTitleTextSize;
    private  int mTitleTextColor;
    private  int mIndicatorRadio;
    private  int mIndicatorMargin;
    private  int mIndicatorSelectColor;
    private  int mIndicatorUnSelectColor;
    private  int mTitleMarginTop;
    private  boolean mTitleMarquee;

    private  int mTitleMarginBottom;
//变量怎么读取出来？




    private  boolean mShowTitleBgView;//是否显示title半透明背景
    private  boolean isAutoLoop=true;//是否自动循环



    public Banner(Context context) {
        super(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValue(attrs);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue(attrs);
    }




    @SuppressLint("ResourceType")
    @Override
    protected void onFinishInflate() {//解析完毕之后
        super.onFinishInflate();

        initview();


    }

    private void initValue( AttributeSet attributeSet){
        //通过解析器解析出来
        TypedArray  array=getContext().obtainStyledAttributes(attributeSet,R.styleable.Banner);
        mIndicatorEndMargin=array.getDimensionPixelSize(R.styleable.Banner_bannerIndicatorEndMargin,dip2px(DEFAULT_MARGIN_DP));

        mIndicatorMargin=array.getDimensionPixelSize(R.styleable.Banner_bannerIndicatorMargin,dip2px(DEFAULT_INDICATOR_RADIO));

        mTitleMarginTop=array.getDimensionPixelSize(R.styleable.Banner_bannerTitleMarginTop,DEFAULT_MARGIN_DP);
        mTitleMarginBottom=array.getDimensionPixelSize(R.styleable.Banner_bannerTitleMarginBottom,DEFAULT_MARGIN_DP);
   ;
      mShowTitleBgView=array.getBoolean(R.styleable.Banner_bannerIsShowTitleBgView,true);
        isAutoLoop=array.getBoolean(R.styleable.Banner_bannerIsAutoLoop,true);
       mIndicatorSelectColor=array.getColor(R.styleable.Banner_bannerIndicatorSelectColor,Color.GRAY);
       mIndicatorUnSelectColor=array.getColor(R.styleable.Banner_bannerIndicatorUnSelectColor,Color.WHITE);

        MInterval = array.getInt(R.styleable.Banner_bannerPagerSwichInterval, DEFAULT_INTERVAL);//默认切换时间
        ScrollTime=array.getInt(R.styleable.Banner_bannerPagerScrollTime,SCROLL_INTERVAL);
        mScrollMode=array.getInt(R.styleable.Banner_bannerPagerScrollMode,SCROLL_MODE_HORIZONTAL);
        mMarginTitleStart=array.getDimensionPixelSize(R.styleable.Banner_bannerMarginTitleStart,DEFAULT_MARGIN_DP);
        mTitleTextSize=array.getDimensionPixelSize(R.styleable.Banner_bannerTitleTextSize,dip2px(DEFAULT_TITLE_SIZE_DP));
        mTitleTextColor=array.getColor(R.styleable.Banner_bannerTitleTextColor,Color.WHITE);
        mIndicatorRadio=array.getDimensionPixelOffset(R.styleable.Banner_bannerIndicatorRadio,DEFAULT_INDICATOR_RADIO);
        mTitleMarquee=array.getBoolean(R.styleable.Banner_bannerTitleMarquee,true);



        array.recycle();//在 Android 自定义 View 的时候，需要使用 TypedArray 来获取 XML layout 中的属性值，使用完之后，需要调用 recyle() 方法将 TypedArray 回收。


//        mIndicatorEndMargin = dip2px(15);
//        MInterval = DEFAULT_INTERVAL;
//        ScrollTime=SCROLL_INTERVAL*2;
    }





    private  void initview(){
        //第一步添加一个Viewpage2
        mPager = new ViewPager2(getContext());
        mPager.setId(mIds++);
        //把title传过去
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //每次选中的时候把title设置进去
                super.onPageSelected(position);
                mTitle.setText(mdata.get(position % mdata.size()).getitle());

                indicator.setCurrent(position % mdata.size());
            }
        });

        addView(mPager);//添加布局
        //ScrollSpeedManger.reflectLayoutManager(this);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(mPager.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(mPager.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        constraintSet.connect(mPager.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        constraintSet.connect(mPager.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        constraintSet.constrainWidth(mPager.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mPager.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.applyTo(this);//进行约束
        //第二步添加半透明背景
        ImageView iv = new ImageView(getContext());
        iv.setBackgroundColor(Color.parseColor("#40000000"));
        iv.setId(mIds++);
        addView(iv);
        constraintSet.connect(iv.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(iv.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        constraintSet.connect(iv.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        constraintSet.constrainWidth(iv.getId(),ConstraintSet.MATCH_CONSTRAINT);


        TextView textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PT,mTitleTextSize);
        textView.setSingleLine(true);

        //测量字体的高度
        int measreWidth = MeasureSpec.makeMeasureSpec(1080, MeasureSpec.AT_MOST);
        int measreHeight = MeasureSpec.makeMeasureSpec(1920, MeasureSpec.AT_MOST);
        textView.measure(measreWidth,measreHeight);
        int height=textView.getMeasuredHeight();

        constraintSet.constrainHeight(iv.getId(),height+mTitleMarginTop+mTitleMarginBottom);//给半透明背景设置高度
      if(!mShowTitleBgView){//不显示半透明背景
          iv.setVisibility(INVISIBLE);//不可见
      }

       // constraintSet.applyTo(this);



        //第三步添加indicator
        indicator = new CirCleIndicator(getContext());
        indicator.setId(mIds++);
        indicator.setUnSelectColor(mIndicatorUnSelectColor);
        indicator.setSelect(mIndicatorSelectColor);
        indicator.setRadio(mIndicatorRadio);

        indicator.setMargin(mIndicatorMargin);
        addView((View) indicator);



        constraintSet.connect(indicator.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END,mIndicatorEndMargin);

        constraintSet.connect(indicator.getId(),ConstraintSet.BOTTOM,iv.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(indicator.getId(),ConstraintSet.TOP,iv.getId(),ConstraintSet.TOP);
        constraintSet.constrainWidth(indicator.getId(),ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(indicator.getId(),ConstraintSet.WRAP_CONTENT);

        //第四步添加title
        mTitle = new TextView(getContext());
        mTitle.setId(mIds++);//设置id自增


        mTitle.setTextColor(mTitleTextColor);
        mTitle.setSingleLine(true);//顯示一行


        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,mTitleTextSize);
        mTitle.setTextColor(mTitleTextColor);
        if(mTitleMarquee){
            mTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);//设置跑马灯
            mTitle.setSelected(true);

            mTitle.setMarqueeRepeatLimit(-1);//无限循环
        }else{
            mTitle.setEllipsize(TextUtils.TruncateAt.END);//设置跑马灯
        }


        addView(mTitle);
        constraintSet.connect(mTitle.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START,mMarginTitleStart);
        constraintSet.connect(mTitle.getId(),ConstraintSet.END,indicator.getId(),ConstraintSet.START,mIndicatorEndMargin);

        constraintSet.connect(mTitle.getId(),ConstraintSet.BOTTOM,iv.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(mTitle.getId(),ConstraintSet.TOP,iv.getId(),ConstraintSet.TOP);

        constraintSet.constrainWidth(mTitle.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mTitle.getId(),ConstraintSet.WRAP_CONTENT);


        constraintSet.applyTo(this);//进行约束


    }


    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {//事件分发
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                stopLoop();

            }
            case MotionEvent.ACTION_UP:{
                startLoop();

            }
        }
        return super.dispatchHoverEvent(event);
    }


    public  void setAdaPter(ArrayList<? extends BannerData> data){
       mdata=data;
        mPager.setAdapter(new SimpeBannerAdapter(data));
        mPager.setUserInputEnabled(true);
        if(mScrollMode==SCROLL_MODE_HORIZONTAL){
            mPager.setOrientation(mPager.ORIENTATION_HORIZONTAL);//水平
        }else{
            mPager.setOrientation(mPager.ORIENTATION_VERTICAL);//垂直


        }


        ScrollSpeedManger.reflectLayoutManager(this);
             //找到中间数
        int initPosition=Integer.MAX_VALUE/2;
        initPosition=initPosition-(initPosition % data.size());
        //调用方法

        mPager.setCurrentItem(initPosition,false);//设置下标

        indicator.setCount(data.size());//指示器的个数
        indicator.setCurrent(initPosition% mdata.size());//指示器的下标

    }


    //页面可见性

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
            int cIndex = mPager.getCurrentItem();
            //a++先执行，再自增，++a先自增再执行
            mPager.setCurrentItem(++cIndex,true);//平滑的
            getHandler().postDelayed(this,MInterval);
        }
    };

    private void startLoop() {
        if(isAutoLoop&&mdata!=null&&mdata.size()>1){
            getHandler().postDelayed(mLoopTask,MInterval);
        }
    }

    private void stopLoop () {
        getHandler().removeCallbacks(mLoopTask);
    }
    //缓慢动画
    public int getScrollTime(){
        return  ScrollTime;
    }
    public ViewPager2 getViewPager2()
    {
        return mPager;
    }
    //常用的把dip值转换为pix像素值的方法通常是这样的：(半透明背景）
    public  int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
