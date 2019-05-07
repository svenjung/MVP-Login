package com.alashoo.signmvp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alashoo.signmvp.R;
import com.alashoo.signmvp.ui.AbsActivity;
import com.alashoo.signmvp.ui.fragments.HomeFragment;

public class HomeActivity extends AbsActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActionBar(@NonNull ActionBar actionBar) {
        super.setupActionBar(actionBar);
        actionBar.setIcon(R.mipmap.ic_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    protected void initView() {
        super.initView();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = new HomeFragment();
        ft.replace(R.id.home_content, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

    public static void lunch(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
