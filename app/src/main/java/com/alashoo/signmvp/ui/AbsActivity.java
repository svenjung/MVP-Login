package com.alashoo.signmvp.ui;

import com.alashoo.common.components.BaseActivity;

import butterknife.ButterKnife;

public abstract class AbsActivity extends BaseActivity {

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }
}
