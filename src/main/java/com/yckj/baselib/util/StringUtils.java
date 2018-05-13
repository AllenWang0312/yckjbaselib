package com.yckj.baselib.util;


import java.util.List;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static Pattern IMG_URL = Pattern
            .compile(".*?(gif|jpeg|png|jpg|bmp)");

    private final static Pattern URL = Pattern
            .compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

    //验证手机格式
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、177（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][134578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }
    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }
    //TextUtil.isEmpty
//    public static boolean isEmpty(CharSequence string) {
//        return string == null || string.length() == 0;
//    }

    //  * 判断字符串是否为null或全为空格
    public static boolean isSpace(String string) {
        return (string == null || string.trim().length() == 0);
    }

    public static String null2Length0(String string) {
        return string == null ? "" : string;
    }


    //返回字符串长度

    public static int length(CharSequence string) {
        return string == null ? 0 : string.length();
    }


    //首字母大写
    public static String upperFirstLetter(String string) {
        if (isEmpty(string) || !Character.isLowerCase(string.charAt(0))) {
            return string;
        }
        return String.valueOf((char) (string.charAt(0) - 32)) + string.substring(1);
    }

    //   首字母小写

    public static String lowerFirstLetter(String string) {
        if (isEmpty(string) || !Character.isUpperCase(string.charAt(0))) {
            return string;
        }
        return String.valueOf((char) (string.charAt(0) + 32)) + string.substring(1);
    }

    //   转化为半角字符

    public static String toDBC(String string) {
        if (isEmpty(string)) {
            return string;
        }
        char[] chars = string.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    //转化为全角字符

    public static String toSBC(String string) {
        if (isEmpty(string)) {
            return string;
        }
        char[] chars = string.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    public static String getString(String[] childs) {
        StringBuffer sb = new StringBuffer();
        for (String str : childs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static boolean isEmpty(String dir) {
        if (dir == null || dir.equals("")) {
            return true;
        }
        return false;
    }

    public static String getEndString(String name) {
        if(name.contains(".")){
            return name.substring(name.lastIndexOf("."), name.length() - 1);

        }else {
            return "";
        }
    }

    public static String getHexString(int value, int length) {
        String str = Integer.toHexString(value);
        if (length > str.length()) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length - str.length(); i++) {
                sb.append("0");
            }
            return sb.append(str).toString();
        } else {
            return str.substring(str.length() - length, str.length());
        }

    }


    public static String join(List<String> entrys, String s) {
            if(entrys.isEmpty()){
                return "";
            }else {
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<entrys.size();i++){
                    sb.append(entrys.get(i));
                    if(i!=entrys.size()-1){
                        sb.append(s);
                    }
                }
                return sb.toString();
            }

    }
}