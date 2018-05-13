package com.yckj.baselib.util;

import java.text.DecimalFormat;

/**
 * Created by wpc on 2017/4/1.
 */

public class StringFormat {

    public static String TwoDecimal(double db) {
        DecimalFormat df = new DecimalFormat("######0.00"); //保留两位小数点
        String str2 = df.format(db);
        return str2;
    }

    public static String FourDecimal(double db) {
        DecimalFormat df = new DecimalFormat("######0.0000"); //保留两位小数点
        String str1 = df.format(db);
        return str1;
    }
    public static String getFormatDouble(double d, int a, int b){
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i <a ; i++) {
            sb.append("#");
        }
        sb.append("0.");
        for (int i = 0; i <b ; i++) {
            sb.append("0");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(d);
    }
}
