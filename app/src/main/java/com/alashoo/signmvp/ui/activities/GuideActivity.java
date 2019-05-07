package com.alashoo.signmvp.ui.activities;

import com.alashoo.signmvp.R;
import com.alashoo.signmvp.ui.AbsActivity;
import com.alashoo.signmvp.ui.signin.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AbsActivity {

    @OnClick(R.id.show_home) void showSignIn() {
        LoginActivity.launch(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected boolean showActionBar() {
        return false;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }
}
