package com.yckj.baselib.util;

import android.app.Dialog;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;

import java.lang.reflect.Field;

/**
 * Created by wpc on 2017/3/30.
 */

public class ReflectUtils {

    public static void ForceShowIcon(PopupMenu p){
        try {
            Field field = p.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(p);
            mHelper.setForceShowIcon(true);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public static void disableClose(Dialog dialog)
    {
        try
        {
            Field field = dialog.getClass().getSuperclass()
                    .getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, false);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void enableColse(Dialog dialog)
    {
        try
        {
            Field field = dialog.getClass().getSuperclass()
                    .getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
