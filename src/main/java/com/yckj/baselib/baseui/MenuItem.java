package com.yckj.baselib.baseui;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/26 0026 下午 4:39
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class MenuItem {
    int res_id;
    String title;
    String summary;
    boolean right_arrow;


    public MenuItem(int res_id, String title, String summary, boolean right_arrow) {
        this.res_id = res_id;
        this.title = title;
        this.summary = summary;
        this.right_arrow = right_arrow;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isRight_arrow() {
        return right_arrow;
    }

    public void setRight_arrow(boolean right_arrow) {
        this.right_arrow = right_arrow;
    }
}