package com.example.last_22.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.last_22.R;
import com.lvsiqian.mvplib.widgets.MvpCommonPopView;

public class AgregreementPop extends MvpCommonPopView {
    private TextView mBtnAgree;
    private TextView mBtnCancel;
    public  IPopClickListener mIPopClickListener;

    public AgregreementPop(Context context) {
        super(context);
        initview(context);
    }

    public void setClickListener(IPopClickListener mIPopClickListener) {
        this.mIPopClickListener = mIPopClickListener;
    }

    private  void initview(Context context){
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_splash_pop, null);
        mBtnAgree=inflate.findViewById(R.id.splash_pop_btn_agree);
        mBtnCancel=inflate.findViewById(R.id.splash_pop_btn_stop);
        mBtnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIPopClickListener!=null){
                    mIPopClickListener.onAgree();
                }

            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIPopClickListener!=null){
                    mIPopClickListener.onCancel();
                }
            }
        });
        setContentView(inflate);
       // setOnBackKeyDismiss(false);
        setOutsideCanTouch(false);
        setTouchOutsideDismiss(false);


    }
    public  interface  IPopClickListener{
        void onCancel();
        void onAgree();
        void onUserAgrement();
        void  onPrivaryPolicy();
    }
}
