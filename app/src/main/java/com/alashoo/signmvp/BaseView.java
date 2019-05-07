package com.alashoo.signmvp;

import androidx.annotation.NonNull;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(@NonNull T presenter);
}
