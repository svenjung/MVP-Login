package com.alashoo.signmvp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpHelper {
    private static final String SHARED_PREFS_NAME = "com.sven.mvp-sign";

    private static ExecutorService sExecutorService;

    //==============================================
    // Utils api for access & write share preference
    public static SharedPreferences getSharedPreferences(Context context, String... prefName) {
        if (prefName == null || prefName.length == 0) {
            return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        } else {
            return context.getSharedPreferences(prefName[0], Context.MODE_PRIVATE);
        }
    }

    public static String getValue(Context context, String key, String defaultValue) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getString(key, defaultValue);
    }

    public static int getValue(Context context, String key, int defaultValue) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getInt(key, defaultValue);
    }

    public static boolean getValue(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getBoolean(key, defaultValue);
    }

    public static long getValue(Context context, String key, long defaultValue) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getLong(key, defaultValue);
    }

    public static void setValue(Context context, String prefName, String key, String value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext(), prefName);
        SharedPreferences.Editor editor = prefs.edit().putString(key, value);
        editorCommit(editor);
    }

    public static void setValue(Context context, String key, String value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit().putString(key, value);
        editorCommit(editor);
    }

    public static void setValue(Context context, String prefName, String key, int value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext(), prefName);
        SharedPreferences.Editor editor = prefs.edit().putInt(key, value);
        editorCommit(editor);
    }

    public static void setValue(Context context, String key, int value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit().putInt(key, value);
        editorCommit(editor);
    }

    public static void setValue(Context context, String prefName, String key, long value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext(), prefName);
        SharedPreferences.Editor editor = prefs.edit().putLong(key, value);
        editorCommit(editor);
    }

    public static void setValue(Context context, String key, long value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit().putLong(key, value);
        editorCommit(editor);
    }

    public static void setValue(Context context, String prefName, String key, boolean value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext(), prefName);
        SharedPreferences.Editor editor = prefs.edit().putBoolean(key, value);
        editorCommit(editor);
    }

    public static void setValue(Context context, String key, boolean value) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit().putBoolean(key, value);
        editorCommit(editor);
    }

    public static void clearValue(Context context, String prefName, String key) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext(), prefName);
        SharedPreferences.Editor editor = prefs.edit().remove(key);
        editorCommit(editor);
    }

    public static void clearValue(final Context context, final String key) {
        SharedPreferences prefs = getSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit().remove(key);
        editorCommit(editor);
    }

    private static void editorCommit(final SharedPreferences.Editor editor) {
        if (isMainThread()) {
            ExecutorService service = getExecutorService();
            service.submit(new Runnable() {
                @Override
                public void run() {
                    editor.commit();
                }
            });
        } else {
            editor.commit();
        }
    }

    private static boolean contain(Context context, String key) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.contains(key);
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    private static ExecutorService getExecutorService() {
        if (sExecutorService == null) {
            synchronized (SpHelper.class) {
                if (sExecutorService == null) {
                    sExecutorService = Executors.newCachedThreadPool();
                }
            }
        }

        return sExecutorService;
    }
}
