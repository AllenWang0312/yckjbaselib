package com.yckj.baselib.common.base;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yckj.baselib.util.L;

/**
 * Created by wpc on 2018/1/6.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    ProgressDialog dialog;
    protected String TAG = getClass().getSimpleName();
    protected String title;

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        L.i(TAG,"onViewStateRestored");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i(TAG,"onResume");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.i(TAG,"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.i(TAG,"onCreateView");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.i(TAG,"onPause");
    }

    public boolean backable = false;

    public abstract void onBack();

    public boolean isBackable() {
        return backable;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void showProgress() {
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("正在加载");
        }
        dialog.show();
    }

    @Override
    public void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean backable() {
        return backable;
    }

    ;
}
