package com.example.last_22.auth.pwd;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.last_22.R;
import com.example.last_22.base.MvpBaseFragment;
import com.example.last_22.data.entity.User;
import com.example.last_22.data.repository.MvpResponse;
import com.example.last_22.widgets.CleanEditButton;
import com.example.last_22.widgets.EditTextButton;
import com.example.last_22.widgets.TogglePasswordButton;

public class PasswordFragment extends MvpBaseFragment<PasswordContract.IPwordPresetener> implements  PasswordContract.IPasswordView {
    private EditText mEdtCount;
    private EditText mEdtPassword;
    private CleanEditButton mBtnCleanAccount;
    private CleanEditButton mBtnCleanPassword;
    private TogglePasswordButton mBtnTogglePassword;

    private TextView mTvCodeLogin;
    private TextView mTvRegister;

    private EditTextButton mBtnLogin;
    @Override
    public void Onsuccess(MvpResponse<User> response) {
        Toast.makeText(getMvpContent(), "登录成功！", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onShowLoading() {
        showPopLoading();

    }

    @Override
    public void onCloseing() {
        closeLoading();

    }

    @Override
    public void onInput(String msg) {
        Toast.makeText(getMvpContent(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pwd_login;
    }

    @Override
    protected void initView() {
        mEdtCount = findViewById(R.id.auth_password_login_edt_phone_number);
        mEdtPassword = findViewById(R.id.auth_password_login_edt_password);
        mBtnLogin = findViewById(R.id.auth_password_login_btn_login);

        mBtnCleanAccount = findViewById(R.id.auth_password_login_btn_phone_number_clean);
        mBtnCleanPassword = findViewById(R.id.auth_password_login_btn_password_clean);

        mBtnTogglePassword = findViewById(R.id.auth_password_login_btn_toggle_password);


        mTvCodeLogin = findViewById(R.id.auth_password_login_tv_go_code_login);
        mTvRegister = findViewById(R.id.auth_password_login_tv_go_regiester);


//        mBtnCleanPassword.bindEditText(mEdtPassword);
        mBtnTogglePassword.bindEditText(mEdtPassword);
        mBtnCleanAccount.bindEditText(mEdtCount);

        mBtnLogin.bindEditText(mEdtPassword);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.Login(mEdtCount.getText().toString().trim(),mEdtPassword.getText().toString().trim());
            }
        });



        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvCodeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




    @Override
    public PasswordContract.IPwordPresetener createPresetener() {
        return new PasswordPrestener();
    }


}
