package com.example.last_22.base.v;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;


import com.example.last_22.R;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

public abstract  class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId() > 0){
           View view=getLayoutInflater().inflate(getLayoutId(),findViewById(R.id.content),false);
            setContentView(view);
            bindligView(view);
            intView();
        }
    }

    protected abstract void bindligView(View view);

    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    protected abstract void intView();

    protected abstract  int getLayoutId();




}
