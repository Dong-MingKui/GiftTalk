package com.dongkui.gifttalk.controller.fragment.homefragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.HomeRotateAdapter;
import com.dongkui.gifttalk.controller.adapter.ItemHomeListViewAdapter;
import com.dongkui.gifttalk.controller.adapter.ItemHomeRecyclerViewAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.model.bean.HomeRotatePictureUrlBean;
import com.dongkui.gifttalk.model.bean.ItemHomeListViewBean;
import com.dongkui.gifttalk.model.bean.ItemHomeRecyclerViewBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.dongkui.gifttalk.utils.ValueTools;
import com.dongkui.gifttalk.view.CustomListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class HomeSelectionFragment extends AbsBaseFragment {

    private ViewPager viewPager;
    private LinearLayout pointLl;// 轮播图状态改变的小圆点容器
    private List<HomeRotatePictureUrlBean.DataBean.BannersBean> bannersBean;
    private HomeRotateAdapter rotateAdapter;
    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;
    private CustomListView listView;

    private ItemHomeListViewAdapter listViewAdapter;
    private List<ItemHomeRecyclerViewBean> recyclerViewBean;
    private ItemHomeRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView homeRecyclerView;
    // 定义更新时间
    private TextView dateDay;
    private TextView dateTime;

    public static HomeSelectionFragment newInstance() {
        Bundle args = new Bundle();
        HomeSelectionFragment fragment = new HomeSelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_selection;
    }

    @Override
    protected void initView() {
        viewPager = byView(R.id.home_rotate_vp);
        pointLl = byView(R.id.home_rotate_point_container);
        listView = byView(R.id.home_list_view);
        homeRecyclerView = byView(R.id.home_recycler_view);

        // 事件的初始化组件
        dateDay = byView(R.id.home_date_day);
        dateTime = byView(R.id.home_date_time);
    }

    @Override
    protected void initDatas() {

        // 请求数据
        RequestDatas();

        listViewAdapter = new ItemHomeListViewAdapter(context);

        listViewBeenRequest();
        recyclerViewBeanRequest();

        GridLayoutManager manager = new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false);
        homeRecyclerView.setLayoutManager(manager);


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

                    for (int i = 0; i < bannersBean.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.btn_check_normal_nightmode);
                        pointIv.setAlpha(0.3f);
                    }
                    // 设置当前位置小点为白点
                    ImageView iv = (ImageView) pointLl.getChildAt(position % bannersBean.size());
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
        for (int i = 0; i < bannersBean.size(); i++) {
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
    private void RequestDatas() {
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.ROTATEPICTUREURL, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                HomeRotatePictureUrlBean bean = gson.fromJson(resultStr, HomeRotatePictureUrlBean.class);

                Log.d("HomeSelectionFragment", "bean:" + bean);
                bannersBean = bean.getData().getBanners();
                Log.d("HomeSelectionFragment", "bannersBean:" + bannersBean);
                rotateAdapter = new HomeRotateAdapter(bannersBean, context);
                // ViewPager的页数为int最大值, 设置当前页多一些, 可以上来就向前滑动
                // 为了保证第一页始终为数据的第0条 取余要为0, 因此设置数据集合大小的倍数
                viewPager.setCurrentItem(bannersBean.size() * 100);
                viewPager.setAdapter(rotateAdapter);
                // 开始轮播
                handler = new Handler();
                startRotate();
                // 添加小圆点
                addPoints();
                // 随着轮播改变小圆点
                changePoints();
            }

            @Override
            public void failure() {

            }
        });
    }


    /**
     * ListView解析加载数据
     */

    private void listViewBeenRequest() {
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.HOMELISTVIEWURL, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ItemHomeListViewBean bean = gson.fromJson(resultStr, ItemHomeListViewBean.class);

                List<ItemHomeListViewBean.DataBean.ItemsBean> listViewBeen = bean.getData().getItems();

                listViewAdapter.setDatas(listViewBeen);
                listView.setAdapter(listViewAdapter);
//                dateDay.setText(bean);
            }

            @Override
            public void failure() {
                Toast.makeText(context, "网络断开", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * RecyclerView解析数据
     */
    private void recyclerViewBeanRequest() {
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.HOMERECYCLERVIEW, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ItemHomeRecyclerViewBean bean = gson.fromJson(resultStr, ItemHomeRecyclerViewBean.class);

                recyclerViewBean = new ArrayList<>();
                for (int i = 0; i < bean.getData().getSecondary_banners().size(); i++) {
                    recyclerViewBean.add(bean);
                }
                Log.d("HomeSelectionFragment", "recyclerViewBean:" + recyclerViewBean);
                recyclerViewAdapter = new ItemHomeRecyclerViewAdapter(context);
                recyclerViewAdapter.setDatas(recyclerViewBean);
                homeRecyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void failure() {

            }
        });
    }

}