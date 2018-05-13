package com.yckj.baselib.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yckj.baselib.R;

public class BaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main);
    }
}
