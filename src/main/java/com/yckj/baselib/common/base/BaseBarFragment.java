package com.yckj.baselib.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yckj.baselib.R;


/**
 * Created by wpc on 2017/11/29 0029.
 * 使用说明
 * 1.在布局文件中include R.layout.fragment_toolbar
 * 2.重写setToolbar方法
 */

public abstract class BaseBarFragment extends BaseFragment {

    public TextView title, text_right;
    public ImageView icon_left;
    public ImageView icon_right;
    public ImageView icon_right_second;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refeshData();
    }

    public void bindToolbar(View view){
        title= (TextView) view.findViewById(R.id.tv_title);
        text_right=(TextView)view.findViewById(R.id.tv_right);
        icon_left=(ImageView)view.findViewById(R.id.iv_left);

        icon_right= (ImageView) view.findViewById(R.id.iv_right);
        icon_right_second= (ImageView) view.findViewById(R.id.iv_right_second);
    }

    public  void setToolbar(View view){
        bindToolbar(view);
    }

    public void setTitle(String str) {
        title.setText(str);
    }

    public  void setRightIcon(int res_id, View.OnClickListener onClickListener) {
        icon_right.setImageResource(res_id);
        icon_right.setOnClickListener(onClickListener);

    }

    public void setRightSecondIcon(int res_id, View.OnClickListener onClickListener) {
        icon_right_second.setImageResource(res_id);
        icon_right_second.setOnClickListener(onClickListener);
    }

    public void setRightText(String str, View.OnClickListener onClickListener) {
        text_right.setText(str);
        text_right.setOnClickListener(onClickListener);
    }

    public void enableBackIcon(){
        icon_left.setImageResource(R.drawable.ic_arrow_back_white_24dp);
        icon_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
    }

    public abstract void refeshData();

}
