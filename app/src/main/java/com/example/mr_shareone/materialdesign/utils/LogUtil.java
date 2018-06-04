package com.example.mr_shareone.materialdesign.utils;

import android.util.Log;

/**
 * Autor：created by MR-SHAREONE on 2018/6/4 14 43
 * Emain:13437105740@163.com
 * 逻辑：这个位置我们在代码里面手动更改level的级别，以此来限制我们测试或者上线版本的日志输出。
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUGE = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int level = VERBOSE;

    public static void v(String tag,String msg){
        if(level <= VERBOSE){
            Log.v(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if(level <= DEBUGE){
            Log.v(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if(level <= INFO){
            Log.v(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if(level <= WARN){
            Log.v(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if(level <= ERROR){
            Log.v(tag,msg);
        }
    }


}
