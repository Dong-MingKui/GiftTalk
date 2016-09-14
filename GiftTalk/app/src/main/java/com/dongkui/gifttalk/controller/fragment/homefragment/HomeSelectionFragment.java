package com.dongkui.gifttalk.controller.fragment.homefragment;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.ItemHomeListViewAdapter;
import com.dongkui.gifttalk.controller.adapter.ItemHomeRotateAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.model.bean.ItemHomeListViewBean;
import com.dongkui.gifttalk.model.bean.ItemHomeRotateBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.dongkui.gifttalk.utils.ValueTools;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class HomeSelectionFragment extends AbsBaseFragment {

    private ViewPager viewPager;
    private LinearLayout pointLl;// 轮播图状态改变的小圆点容器
    private List<ItemHomeRotateBean> datas = new ArrayList<>();
    private ItemHomeRotateAdapter vpAdapter;
    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;
    private ListView listView;
    private List<ItemHomeListViewBean> listViewBeen;
    private ItemHomeListViewAdapter listViewAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_selection;
    }

    @Override
    protected void initView() {
        viewPager = byView(R.id.home_rotate_vp);
        pointLl = byView(R.id.home_rotate_point_container);
        listView = byView(R.id.home_list_view);
    }

    @Override
    protected void initDatas() {


        // 请求数据
//        RequestDatas();
        // 构造数据
        buildDatas();
        vpAdapter = new ItemHomeRotateAdapter(datas, context);
        viewPager.setAdapter(vpAdapter);
        // ViewPager的页数为int最大值, 设置当前页多一些, 可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0, 因此设置数据集合大小的倍数
        viewPager.setCurrentItem(datas.size() * 100);
        // 开始轮播
        handler = new Handler();
        startRotate();
        // 添加小圆点
        addPoints();
        // 随着轮播改变小圆点
        changePoints();

        listViewBeen = new ArrayList<>();
        listViewBeenRequest();
        listViewAdapter = new ItemHomeListViewAdapter(context);
        listViewAdapter.setDatas(listViewBeen);
        listView.setAdapter(listViewAdapter);
    }


    /**
     * 随着轮播图改变小点
     */
    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把小点设置为黑点
                    for (int i = 0; i < datas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.btn_check_normal_nightmode);
                        pointIv.setAlpha(0.3f);
                    }
                    // 设置当前位置小点为白点
                    ImageView iv = (ImageView) pointLl.getChildAt(position % datas.size());
                    iv.setImageResource(R.mipmap.btn_check_normal);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 添加轮播切换的小圆点
     */
    private void addPoints() {
        // 添加多少张图片加载多少个小圆点
        for (int i = 0; i < datas.size(); i++) {
            ImageView pointIv = new ImageView(context);
            pointIv.setPadding(15, 5, 15, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60, 50);
            pointIv.setLayoutParams(params);
            // 设置第0页小点为灰色
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.btn_check_normal);
            } else {
                pointIv.setImageResource(R.mipmap.btn_check_normal_nightmode);
                pointIv.setAlpha(0.3f);
            }
            pointLl.addView(pointIv);
        }
    }

    /**
     * 开始轮播的方法
     */
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, ValueTools.ROTATETIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, ValueTools.ROTATETIME);
    }

    /**
     * 构造数据
     */
    private void buildDatas() {
        datas = new ArrayList<>();
        datas.add(new ItemHomeRotateBean("http://img03.liwushuo.com/image/160907/2e7nf9evv.jpg-w720"));
        datas.add(new ItemHomeRotateBean("http://img01.liwushuo.com/image/160912/w27nffhwn.jpg-w720"));
        datas.add(new ItemHomeRotateBean("http://img02.liwushuo.com/image/160912/oc9jytqbo.jpg-w720"));
        datas.add(new ItemHomeRotateBean("http://img01.liwushuo.com/image/160906/4aco2fhmd.jpg-w720"));
        datas.add(new ItemHomeRotateBean("http://img03.liwushuo.com/image/160908/a0h3m4p1p.jpg-w720"));
        datas.add(new ItemHomeRotateBean("http://img02.liwushuo.com/image/160901/2sm8iy4n4.jpg-w720"));
        datas.add(new ItemHomeRotateBean("http://img02.liwushuo.com/image/160905/sfgmt79zc.jpg-w720"));

    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }

    /**
     * 轮播图解析加载数据到实体类
     */
//    private void RequestDatas() {
//        VolleyInstance volleyInstance = VolleyInstance.getInstance();
////        volleyInstance.startRequest(ValueTools.ROTATEPICTUREURL, new OnVolleyResult() {
////            @Override
////            public void success(String resultStr) {
////                // 解析实体类
////                Gson gson = new Gson();
////                // 从gson数据解析到实体类
////                ItemHomeRotateBean rotateBean = gson.fromJson(resultStr, ItemHomeRotateBean.class);
////                // 从实体类获取解析数据集合
////                datas = rotateBean.getData().getBanners();
////                vpAdapter.setDatas(datas);
////                // 网络图片框架:毕加索
////                // 通过context加载图片网址, 到这个ImageView
////            }
////            @Override
////            public void failure() {
////
////            }
////        });
//        OnVolleyResult result = new OnVolleyResult() {
//            @Override
//            public void success(String resultStr) {
//                Gson gson = new Gson();
//                // 从gson数据解析到实体类
//                ItemHomeRotateBean rotateBean = gson.fromJson(resultStr, ItemHomeRotateBean.class);
//                // 从实体类获取解析数据集合
//                datas = rotateBean.getData().getBanners();
//                vpAdapter.setDatas(datas);
//                // 网络图片框架:毕加索
//                // 通过context加载图片网址, 到这个ImageView
//            }
//
//            @Override
//            public void failure() {
//
//            }
//        };
//        volleyInstance.startRequest(ValueTools.ROTATEPICTUREURL,result);
//    }


    /**
     * ListView解析加载数据
     */

    private void listViewBeenRequest() {
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.HOMELISTVIEWURL, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ItemHomeListViewBean bean = gson.fromJson(resultStr,ItemHomeListViewBean.class);
                listViewBeen.add(bean);
            }

            @Override
            public void failure() {

            }
        });

    }



}