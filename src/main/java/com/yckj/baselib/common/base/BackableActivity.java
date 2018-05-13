package com.yckj.baselib.common.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by wpc on 2017/4/13.
 */

public class BackableActivity extends BaseActivity {


    public void enableBackPress(Toolbar toolbar, @Nullable String title){
        if(title!=null)toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        EditText et;
//        et.addTextChangedListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
