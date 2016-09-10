package com.dongkui.gifttalk.utils;

import android.widget.Toast;
import com.dongkui.gifttalk.controller.app.MyApp;

/**
 * Created by dllo on 16/9/9.
 */
public final class Toasts {
    // final修饰,不能继承

    /**
     * 私有构造方法: 不能创建对象
     * 锁死这个类
     */
    private Toasts(){

    }

    private static boolean isDebug = true;

    /**
     * 短的Toast
     */
    public static void shorMsg(String msg){
        if (isDebug){
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 长的Toast
     */
    public static void longMsg(String msg){
        if (isDebug){
            Toast.makeText(MyApp.getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }


}
