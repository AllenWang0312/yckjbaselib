package com.yckj.baselib.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by wpc on 2017/4/14.
 * 文件相关util
 */

public class FileUtils {


//    内置sd卡路径
//    String sdcardPath = System.getenv("EXTERNAL_STORAGE");
//    内置sd卡路径
//    String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    //外置置sd卡路径
//    String extSdcardPath = System.getenv("SECONDARY_STORAGE");

    public static String getSDcardPath() {
//        Environment.getDownloadCacheDirectory().getAbsolutePath()
        return Environment.getExternalStorageDirectory().getPath();
    }
    public static String getExtSDCardPath(){
       return System.getenv("SECONDARY_STORAGE");
    }


//    /**
//     * 获取外置SD卡路径
//     * @return 应该就一条记录或空
//     */
//    public static String getExtSDCardPath() {
//        List<String> lResult = new ArrayList<String>();
//        try {
//            Runtime rt = Runtime.getRuntime();
//            Process proc = rt.exec("mount");
//            InputStream is = proc.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                if (line.contains("sdcard")) {
//                    String[] arr = line.split(" ");
//                    String path = arr[1];
//                    File file = new File(path);
//                    if (file.isDirectory()) {
//                        lResult.add(path);
//                    }
//                }
//            }
//            isr.close();
//        } catch (Exception e) {
//        }
//        if (lResult.size() > 0) {
//            return lResult.get(0);
//        }
//        return null;
//    }

    public static String getExtSDCardAPPDataPath(){
        File file=new File(
//                getExtSDCardPath()+"/edu.tjrac.swant/unicorn/wifi/"
                "/storage/sdcard1/edu.tjrac.swant/unicorn/wifi/"
        );
        if(!file.exists()){
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getAppDataDir() {
        String dir = getSDcardPath() + "/unicorn/";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }
        return dir;
    }

    public static String getAppImageDir() {
        String dir = getSDcardPath() + "/unicorn/图库/";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

    public static boolean writeByteArrayToFile(byte[] bytes, String dirPath, String filename) {
        createOrExistsDir(dirPath);
        createFileIfNotExist(dirPath, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(dirPath, filename));
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fos.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static long getTotalSpace(File path) {
        if (path == null) {
            return -1;
        }
        //如果这个sdk大于9 那就使用系统的api
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {

            return path.getTotalSpace();
        } else//小于9 系统没有这个api 我们就自己算吧。
        {
            final StatFs statFs = new StatFs(path.getPath());
            return statFs.getBlockSize() * statFs.getBlockCount();
        }
    }


    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    public static File getFileByPath(String filePath) {
        return StringUtils.isSpace(filePath) ? null : new File(filePath);
    }

    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static void createFileIfNotExist(String dirPath, String filename) {
        File file = new File(dirPath, filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFiles(String path, ArrayList<Integer> select) {
        File dir = new File(path);
        String[] files = dir.list();
        for (Integer index : select) {
            deleteFile(new File(path, files[index]));

        }
    }

    public static void deleteFile(File file) {
        if (file.exists()) {//判断文件是否存在
            if (file.isFile()) {//判断是否是文件
                file.delete();//删除文件
            } else if (file.isDirectory()) {//否则如果它是一个目录
                File[] files = file.listFiles();//声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) {//遍历目录下所有的文件
                    deleteFile(files[i]);//把每个文件用这个方法进行迭代
                }
                file.delete();//删除文件夹
            }
        } else {
        }
    }

    public static String getAbsPath(ArrayList<String> rote) {
        StringBuffer path = new StringBuffer();
        for (String str : rote) {
            path.append("/" + str);
        }
        return path.toString();
    }


    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    public static String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
    /* 获取文件的后缀名*/
        String end = extensionName(fName);
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }

        return type;
    }


    //MIME_MapTable是所有文件的后缀名所对应的MIME类型的一个String数组：
    private static final String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }

    public static boolean endWith(String end, String[] types) {
        for (String item:types){
//            L.i("endWith",item+"_"+end);
            if(item.equals(end)){
                return true;
            }
        }
        return false;
    }

    public static boolean endWith(Context context,String end, int id) {
        String [] types=context.getResources().getStringArray(id);
        for (String item:types){
            L.i("endWith",item+"_"+end);
            if(item.equals(end)){
                return true;
            }
        }
        return false;
    }


    public static String getName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

    public static String  extensionName(String file){
        int dotIndex = file.lastIndexOf(".");
        if(dotIndex<0){
            return "";
        }
        return file.substring(dotIndex, file.length()).toLowerCase();
    }

}
