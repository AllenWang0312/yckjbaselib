package com.yckj.baselib.common.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by wpc on 2017/6/13.
 */

public class ProgressBarActivity extends BackableActivity {

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("请稍后");
        mProgressDialog.setMessage("数据加载中");

        super.onCreate(savedInstanceState, persistentState);
    }

    public void showProgressDialog() {
        mProgressDialog.show();
    }

    public void showProgressDialog(String title, String content) {
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(content);

    }

    public void dismissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

//    class ProgressAsy extends AsyncTask{
//
//        @Override
//        protected Object doInBackground(Object[] params) {
//            return null;
//        }
//    }
}
