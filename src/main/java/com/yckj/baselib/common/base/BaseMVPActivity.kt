package com.yckj.baselib.common.base

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 12:33
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

abstract class BaseMVPActivity<P : BasePresenter> : BaseActivity(), BaseView {

    protected var presenter: P? = null


    override fun showToast(msg: String) {

    }

    override fun showDialog(msg: String) {

    }

    override fun showProgress() {

    }

    override fun dismissProgress() {

    }

    override fun onDestroy() {
        presenter!!.detachView()
        super.onDestroy()
    }
}
