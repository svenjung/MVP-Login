package com.alashoo.common.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.alashoo.common.IToast;
import com.alashoo.common.R;
import com.alashoo.common.ToastManager;

public abstract class BaseActivity extends RxAppCompatActivity {
    private Toolbar mToolbar;

    private IToast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initContentView(getContentLayoutId());

        mToast = ToastManager.getInstance(this);

        if (showActionBar()) {
            initToolbar();
        }

        initView();
    }

    @Override
    protected void onDestroy() {
        mToast.cancel();
        super.onDestroy();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            setupActionBar(actionBar);
    }

    protected void setupActionBar(@NonNull ActionBar actionBar) {
        // setup actionbar in sub-activity
    }

    protected abstract @LayoutRes int getContentLayoutId();

    protected abstract void initView();

    protected boolean showActionBar() {
        return true;
    }

    private void initContentView(@LayoutRes int resId) {
        FrameLayout content = findViewById(R.id.content);
        LayoutInflater.from(this).inflate(resId, content);
        mToolbar = findViewById(R.id.toolbar);
        if (!showActionBar()) {
            mToolbar.setVisibility(View.GONE);
        }
    }

    protected void toast(int resId) {
        mToast.show(resId);
    }

    protected void toast(CharSequence text) {
        mToast.show(text);
    }
}
