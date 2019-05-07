package com.alashoo.signmvp.ui.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.alashoo.signmvp.R;
import com.alashoo.signmvp.ui.AbsActivity;
import com.alashoo.signmvp.util.Utils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AbsActivity implements LoginContact.View {
    private static final int TYPE_PWD = 1;
    private static final int TYPE_SMS = 2;

    @BindView(R.id.bt_send_sms)
    TextView mBtSendSms;

    @BindView(R.id.row_edit_mobile)
    EditText mEtMobile;

    @BindView(R.id.row_edit_pwd)
    EditText mEtPwd;

    @BindView(R.id.row_edit_sms_code)
    EditText mEtSmsCode;

    @BindView(R.id.row_clear_mobile)
    ImageButton mIbClearMobile;

    @BindView(R.id.bt_login_type)
    Button mLoginModel;

    @BindView(R.id.cb_save_pwd)
    CheckBox mCbSavePwd;

    @BindView(R.id.row_password)
    View mRowPassword;

    @BindView(R.id.row_sms)
    View mRowSmsCode;

    private LoginPresenter mPresenter;
    private int mLoginType = TYPE_SMS;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();

        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {
        super.setupActionBar(actionBar);
        actionBar.hide();
    }

    @OnClick(R.id.bt_send_sms)
    void sendSmsCode() {
        String phone = mEtMobile.getText().toString().trim();
        if (isValidPhone(phone)) {
            mPresenter.sendSmsCode(phone);
        }
    }

    @OnClick(R.id.bt_login)
    void startLogin() {
        if (mLoginType == TYPE_SMS) {
            smsLogin();
        } else {
            passwordLogin();
        }
    }

    @OnTextChanged(value = R.id.row_edit_mobile, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onPhoneTextChanged(CharSequence s, int start, int before, int count) {
        String phone = mEtMobile.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            mIbClearMobile.setVisibility(View.GONE);
        } else {
            mIbClearMobile.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.row_clear_mobile)
    void clearPhoneText() {
        mEtMobile.setText("");
    }

    @OnClick(R.id.bt_login_type)
    void changeLoginModel() {
        if (mLoginType == TYPE_SMS) {
            mLoginModel.setText(R.string.login_type_sms);
            mCbSavePwd.setVisibility(View.VISIBLE);
            mRowPassword.setVisibility(View.VISIBLE);
            mRowSmsCode.setVisibility(View.GONE);
            mLoginType = TYPE_PWD;
        } else {
            mLoginModel.setText(R.string.login_type_mobile);
            mCbSavePwd.setVisibility(View.GONE);
            mRowPassword.setVisibility(View.GONE);
            mRowSmsCode.setVisibility(View.VISIBLE);
            mLoginType = TYPE_SMS;
        }
    }

    // MVP-View method
    @Override
    public void setSendSmsButtonEnable(boolean enable) {
        mBtSendSms.setEnabled(enable);
    }

    @Override
    public void setSendSmsButtonText(String text) {
        mBtSendSms.setText(text);
    }

    @Override
    public void showSendSmsCodeError(String errMessage) {
        toast("发送验证码失败:" + errMessage);
    }

    @Override
    public void showLoginError(String errMessage) {
        toast("登录失败:" + errMessage);
    }

    @Override
    public void showLoginSuccess() {
        // 登录成功
    }

    @Override
    @NonNull
    public LifecycleProvider<ActivityEvent> getLifecycleProvider() {
        return this;
    }

    @Override
    public void setPresenter(@NonNull LoginContact.Presenter presenter) {
        // None use for this view
    }

    @Override
    public Context getContext() {
        return this;
    }
    // end MVP-View method

    private void passwordLogin() {
        String phone = mEtMobile.getText().toString().trim();
        String password = mEtPwd.getText().toString().trim();

        if (!isValidPhone(phone)) {
            return;
        }
        if (!isValidPassword(password)) {
            return;
        }
        mPresenter.mobileLogin(phone, password);
    }

    private void smsLogin() {
        String phone = mEtMobile.getText().toString().trim();
        String smsCode = mEtSmsCode.getText().toString().trim();

        if (!isValidPhone(phone)) {
            return;
        }

        if (!isValidSmsCode(smsCode)) {
            return;
        }

        mPresenter.smsLogin(phone, smsCode);
    }

    private boolean isValidPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            toast(R.string.toast_empty_phone);
            mEtMobile.requestFocus();
            return false;
        }

        if (!Utils.isValidPhone(phone)) {
            toast(R.string.toast_invalid_phone);
            mEtMobile.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isValidPassword(String password) {
        return true;
    }

    private boolean isValidSmsCode(String smsCode) {
        if (TextUtils.isEmpty(smsCode)) {
            toast(R.string.toast_empty_sms_code);
            return false;
        }
        return true;
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
