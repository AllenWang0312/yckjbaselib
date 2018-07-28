package com.yckj.baselib.common.base;

import com.yckj.baselib.util.T;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 12:33
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public abstract class BaseBarMVPActivity<P extends BasePresenter> extends BaseBarActivity implements BaseView {

    protected  P presenter;


    @Override
    public void showToast(String msg) {
        T.show(mContext,msg);
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
