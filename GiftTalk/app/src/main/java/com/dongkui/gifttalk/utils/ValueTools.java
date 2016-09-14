package com.dongkui.gifttalk.utils;

/**
 * Created by dllo on 16/9/14.
 */
public final class ValueTools {
    /**
     * 私有的构造方法,不能继承不能调用
     */
    private ValueTools() {

    }

    /**
     * 轮播图替换时间
     */
    public static final int ROTATETIME = 2000;
    /**
     * 首页轮播图接口
     */
    public static final String ROTATEPICTUREURL = "http://api.liwushuo.com/v2/banners";
    /**
     *  首页ListView数据接口
     */
    public static final String HOMELISTVIEWURL = "http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0";
}