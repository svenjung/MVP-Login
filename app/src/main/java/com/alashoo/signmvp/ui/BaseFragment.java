package com.alashoo.signmvp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alashoo.common.components.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment {

    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
