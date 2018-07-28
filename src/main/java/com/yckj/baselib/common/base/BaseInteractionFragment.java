package com.yckj.baselib.common.base;

import android.content.Context;
import android.os.Binder;

/**
 * Created by wpc on 2018/5/28.
 */

public class BaseInteractionFragment extends BaseFragment {
    private InteractionListener activityInteractionCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof InteractionListener) {
            activityInteractionCallback = (InteractionListener) context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        activityInteractionCallback = null;
    }

    @Override
    public void onBack() {

    }

    public interface InteractionListener {
        void onFragmentInteractionCallback(int tag, Binder data);
    }
}
