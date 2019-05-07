package com.alashoo.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ToastManager implements IToast {

    private volatile static ToastManager instance = null;

    private Toast mToast;
    private final WeakReference<Context> contextReference;

    private ToastManager(Context context) {
        contextReference = new WeakReference<>(context);
    }

    public static ToastManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ToastManager.class) {
                if (instance == null) {
                    instance = new ToastManager(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    @SuppressLint("ShowToast")
    public void show(CharSequence text) {
        Context context = contextReference.get();
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }

        mToast.show();
    }

    public void show(int resId) {
        String text = contextReference.get().getResources().getString(resId);
        show(text);
    }

    public void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
