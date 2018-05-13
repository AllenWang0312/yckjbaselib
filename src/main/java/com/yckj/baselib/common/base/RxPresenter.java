package com.yckj.baselib.common.base;

import java.util.List;

import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.subscriptions.CompositeSubscription;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 上午 9:10
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class RxPresenter implements BasePresenter {
    private CompositeSubscription mCompositeSubscription;


    protected void addSubscribe(Subscription subscription) {
        try {
            if (mCompositeSubscription == null||mCompositeSubscription.isUnsubscribed()) {
                mCompositeSubscription = new CompositeSubscription();
            }
            mCompositeSubscription.add(subscription);
        } catch (CompositeException e) {
            List<Throwable> exceptions = e.getExceptions();
            if (exceptions != null && exceptions.size() > 0){
                for (Throwable exception : exceptions) {
//                    Logger.e(exception, "addSubscribe fail from CompositeException");
                }
            }
        } catch (Exception e) {
//            Logger.e(e, "addSubscribe");
        }

    }

    @Override
    public void detachView() {
        try {
            if (mCompositeSubscription != null&&!mCompositeSubscription.isUnsubscribed()) {
                mCompositeSubscription.unsubscribe();
            }
        } catch (CompositeException e) {
            List<Throwable> exceptions = e.getExceptions();
            if (exceptions != null && exceptions.size() > 0) {
                for (Throwable exception : exceptions) {
//                    Logger.e(exception, "unSubscribe fail from CompositeException");
                }
            }
        } catch (Exception e) {
//            Logger.e(e, "addSubscribe");
        }
    }
}
