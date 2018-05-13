package com.yckj.baselib.common.base;

import android.app.ProgressDialog;

import com.yckj.baselib.util.T;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 12:31
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment implements BaseView {

    private ProgressDialog progress;
    protected P presenter;

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void showToast(String msg) {
        T.show(getActivity(), msg);
    }

    @Override
    public void showProgress() {
//有swiper 可以不用progressbar
        if (progress == null) {
            progress = new ProgressDialog(getActivity());
            progress.setMessage("正在载入....");
            progress.setCancelable(false);

        }
        progress.show();
    }

    @Override
    public void dismissProgress() {
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
