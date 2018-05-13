package com.yckj.baselib.common.base;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.yckj.baselib.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/8 0008 下午 4:26
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class BaseToolbarActivity extends BaseActivity {

    Toolbar toolbar;


   public void bindToolbar(int id){
        toolbar=  findViewById(id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
