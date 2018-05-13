package com.yckj.baselib.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/9 0009 上午 10:34
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class ItemInflater<T extends ItemInflater.Item> {
    public ItemInflater(T data) {
        this.data = data;
    }

    T data;

    Context mContext;
    View rootView;

    int layoutid;
    int[] viewsid;

    public ItemInflater layoutId(Context context, int id) {
        mContext=context;
        rootView = LayoutInflater.from(context).inflate(id, null);
        this.layoutid = id;
        return this;
    }

    public ItemInflater viewsid(int... ids) {
        this.viewsid = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            viewsid[i] = ids[i];
        }
        return this;
    }

    public ItemInflater withItemClick(View.OnClickListener onClickListener){
        rootView.setOnClickListener(onClickListener);
        return this;
    }

    public View  inflater(){
        for (int i = 0; i <viewsid.length ; i++) {
            View v=rootView.findViewById(viewsid[i]);
            if(data.getViewInfo(viewsid[i]) instanceof Boolean){
                v.setVisibility((Boolean)data.getViewInfo(viewsid[i])? View.VISIBLE:View.INVISIBLE);
            }
            if(v instanceof ImageView){
                Glide.with(mContext).load((String)data.getViewInfo(viewsid[i])).into((ImageView) v);
            }else if(v instanceof TextView){
                ((TextView) v).setText((String)data.getViewInfo(viewsid[i]));
            }
        }
        return rootView;
    }


    public abstract class Item {
       public abstract Object getViewInfo(int id);
    }

}
