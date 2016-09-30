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
     * 首页PopWindow数据接口
     */
    public static final String HOMEMENURV = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2";

    /**
     * 首页轮播图接口
     */
    public static final String ROTATEPICTUREURL = "http://api.liwushuo.com/v2/banners";
    /**
     * 首页轮播图拼接的网址
     */
    public static final String HOME_ROTATE_CONTENT_HEAD = "http://api.liwushuo.com/v2/collections/";
    public static final String HOME_ROTATE_CONTENT_FOOT = "/posts?gender=1&generation=2&limit=20&offset=0";
    public static final String HOME_ROTATE_CONTENT_POST = "http://api.liwushuo.com/v2/posts_v2/";
    public static final String HOME_ROTATE_CONTENT_URL = "http://api.liwushuo.com/v2/url/";
    /**
     * 首页横向的RecyclerView图片接口
     */
    public static final String HOMERECYCLERVIEW = "http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=2";
    /**
     * 首页精选数据接口
     */
    public static final String HOMELISTVIEWURL = "http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0";
    /**
     * 首页送女数据接口
     */
    public static final String HOMEGIFTGFLISTVIEW = "http://api.liwushuo.com/v2/channels/10/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页海淘数据接口
     */
    public static final String HOME129LISTVIEW = "http://api.liwushuo.com/v2/channels/129/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页创意生活数据接口
     */
    public static final String HOME125LISTVIEW = "http://api.liwushuo.com/v2/channels/125/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页送基友数据接口
     */
    public static final String HOME26LISTVIEW = "http://api.liwushuo.com/v2/channels/26/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页送爸妈数据接口
     */
    public static final String HOME6LISTVIEW = "http://api.liwushuo.com/v2/channels/6/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页送同事数据接口
     */
    public static final String HOME17LISTVIEW = "http://api.liwushuo.com/v2/channels/17/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页送宝贝数据接口
     */
    public static final String HOME24LISTVIEW = "http://api.liwushuo.com/v2/channels/24/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页设计感数据接口
     */
    public static final String HOME127LISTVIEW = "http://api.liwushuo.com/v2/channels/127/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页文艺风数据接口
     */
    public static final String HOME14LISTVIEW = "http://api.liwushuo.com/v2/channels/14/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页奇葩搞怪数据接口
     */
    public static final String HOME126LISTVIEW = "http://api.liwushuo.com/v2/channels/126/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页科技范数据接口
     */
    public static final String HOME28LISTVIEW = "http://api.liwushuo.com/v2/channels/28/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 首页萌萌哒数据接口
     */
    public static final String HOME11LISTVIEW = "http://api.liwushuo.com/v2/channels/11/items_v2?gender=1&limit=20&offset=0&generation=2";
    /**
     * 榜单每日精选数据接口
     */
    public static final String GIFTRECYCLERVIEW = "http://api.liwushuo.com/v2/ranks_v2/ranks/1?limit=20&offset=0";
    /**
     * 榜单TOP100数据接口
     */
    public static final String GIFTRECYCLERVIEW2 = "http://api.liwushuo.com/v2/ranks_v2/ranks/2?limit=20&offset=0";
    /**
     * 榜单独立原创榜数据接口
     */
    public static final String GIFTRECYCLERVIEW3 = "http://api.liwushuo.com/v2/ranks_v2/ranks/3?limit=20&offset=0";
    /**
     * 榜单新星榜数据接口
     */
    public static final String GIFTRECYCLERVIEW4 = "http://api.liwushuo.com/v2/ranks_v2/ranks/4?limit=20&offset=0";
    /**
     * 分类界面攻略fragment栏目的接口
     */
    public static final String CATEGORYRAIDERSCOLUNM = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";
    /**
     * 分类界面攻略的品种, 风格, 对象的接口
     */
    public static final String CATEGORYRAIDERV = "http://api.liwushuo.com/v2/channel_groups/all";
    /**
     * 分类界面单品界面数据接口
     */
    public static final String CATEGORYSINGLE = "http://api.liwushuo.com/v2/item_categories/tree";
}
