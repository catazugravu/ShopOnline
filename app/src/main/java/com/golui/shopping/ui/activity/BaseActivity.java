package com.golui.shopping.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.golui.shopping.BuildConfig;

/**
 * Created by RAVI on 7/25/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    static {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityContentView();
        initView();
    }

    protected abstract void setActivityContentView();

    protected abstract void initView();
}
