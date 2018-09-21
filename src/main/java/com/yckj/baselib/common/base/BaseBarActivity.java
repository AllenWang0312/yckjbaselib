package com.yckj.baselib.common.base;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yckj.baselib.R;
import com.yckj.baselib.util.UiUtils;


/**
 * Created by wpc on 2017/11/29 0029.
 */

public abstract class BaseBarActivity extends BaseActivity {

    protected View rootView;
    protected TextView left_text, title, right_text;
    protected ImageView left_icon, right_second_icon, right_icon;

    public void bindToolbar(View view) {
        rootView=view;
        title = (TextView) view.findViewById(R.id.tv_title);
        left_icon = (ImageView) view.findViewById(R.id.iv_left);
        right_icon=(ImageView)view.findViewById(R.id.iv_right);
        right_second_icon=(ImageView)view.findViewById(R.id.iv_right_second);
        right_text=(TextView)view.findViewById(R.id.tv_right);
    }

    public void bindToolbar(int id) {
        View view = findViewById(id);
        bindToolbar(view);
    }
    public void enableBackIcon(){
        setLeftIcon(R.drawable.ic_arrow_back_white_24dp, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setRightText(String text, View.OnClickListener onClickListener){
        right_text.setText(text);
        right_text.setOnClickListener(onClickListener);
    }
    public void setRightIcon(int res_id,View.OnClickListener onClickListener){
        right_icon.setImageResource(res_id);
        right_icon.setOnClickListener(onClickListener);
    }
    public void setSecondRightIcon(int res_id,View.OnClickListener onClickListener){
        right_second_icon.setImageResource(res_id);
        right_second_icon.setOnClickListener(onClickListener);
    }

    public void setToolbar(View view ){
        bindToolbar(view);
    }
    public void setToolbar(int id){
        this.setToolbar(findViewById(id));
    }

    public void setTitle(String str) {
        this.title.setText(str);
    }

    public void setLeftText(String str, View.OnClickListener onClickListener) {
        left_text.setText(str);
        left_text.setOnClickListener(onClickListener);
    }

    public void setLeftIcon(int res_id, View.OnClickListener onClickListener) {
        left_icon.setImageResource(res_id);
        left_icon.setOnClickListener(onClickListener);
    }
    public void setToolbarBackgroud(int colorId){
        rootView.setBackgroundColor(colorId);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void translateZ(float dp){
        rootView.setTranslationZ(UiUtils.Dp2Px(mContext,dp));
    }
}
