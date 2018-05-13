package com.yckj.baselib.common.base;

import com.yckj.baselib.util.T;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 5:10
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public abstract class BaseBackNoticeActivity extends BaseActivity {
    long lastBackTime;

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastBackTime< 1000) {
            super.onBackPressed();
        } else {
            lastBackTime = System.currentTimeMillis();
            T.show(this, "双击返回按钮退出应用");
        }
    }
}
