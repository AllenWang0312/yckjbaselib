package com.yckj.baselib.common.base;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 上午 9:03
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface BaseView {

    void showToast(String msg);

    void showDialog(String msg);

    void showProgress();

    void dismissProgress();

}
