package com.yckj.baselib.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yckj.baselib.R;

public class FragmentActivity extends BaseActivity {

    public static BaseFragment content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, content)
                .commit();
//                .add(content,"content")
//                .show(content).commit();
    }

    public static void start(Context context, BaseFragment fragment) {
        content = fragment;
        context.startActivity(new Intent(context, FragmentActivity.class));
    }

    @Override
    public void onBackPressed() {
        if(content.backable()){
            content.onBack();
        }else {
            super.onBackPressed();
        }
    }
}
