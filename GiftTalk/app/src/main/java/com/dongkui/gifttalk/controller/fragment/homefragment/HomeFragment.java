package com.dongkui.gifttalk.controller.fragment.homefragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.AllFragmentAdapter;
import com.dongkui.gifttalk.controller.adapter.ItemHomeMenuRvAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.model.bean.HomeTobMenuBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.dongkui.gifttalk.utils.ValueTools;
import com.dongkui.gifttalk.utils.minterface.OnRecyclerViewItemClickListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 首页界面
 */
public class HomeFragment extends AbsBaseFragment {
    private AllFragmentAdapter adapter;
    private TabLayout homeTab;
    private ViewPager homeVp;
    private List<Fragment> fragments;
    public ImageView homeMeanArrow;
    public ImageView homeMeanArrowAfter;
    private RecyclerView homeMenuRv;
    private ItemHomeMenuRvAdapter menuRvAdapter;
    private LinearLayout homeTabLlAfter;
    private PopupWindow popupWindow;


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeTab = byView(R.id.home_tab);
        homeVp = byView(R.id.home_vp);
        homeMeanArrow = byView(R.id.home_mean_arrow);
        homeMeanArrowAfter = byView(R.id.home_mean_arrow_after);
        homeTabLlAfter = byView(R.id.home_tab_ll_after);
    }

    @Override
    protected void initDatas() {
        adapter = new AllFragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();

        fragments.add(HomeSelectionFragment.newInstance());
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOMEGIFTGFLISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME129LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME125LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME26LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME6LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME17LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME24LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME127LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME14LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME126LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME28LISTVIEW));
        fragments.add(HomeGiftGFFragment.newInstance(ValueTools.HOME11LISTVIEW));

        adapter.setFragments(fragments);
        homeVp.setAdapter(adapter);
        homeTab.setupWithViewPager(homeVp);
        homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        String[] headers = {"精选", "送女票", "海淘", "创意生活", "送基友", "送爸妈", "送同事", "送宝贝", "设计感", "文艺风", "奇葩怪谈", "科技范", "萌萌哒"};
        for (int i = 0; i < 13; i++) {
            homeTab.getTabAt(i).setText(headers[i]);
        }
        homeMeanArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HomeFragment", "homeTabLlAfter.getVisibility():" + homeTabLlAfter.getVisibility());
                homeTabLlAfter.setVisibility(View.VISIBLE);
                popWindowShow();

            }
        });

        homeTabLlAfter.setFocusable(true);
        homeTabLlAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeTabLlAfter.setVisibility(View.GONE);
            }
        });
        homeMeanArrowAfter.setFocusable(true);

        homeMeanArrowAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeTabLlAfter.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });

    }

    /**
     * 创建PopupWindow
     */
    private void popWindowShow() {
        popupWindow = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.home_top_mean_pw, null);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // popupWindow绑定视图
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(null);
        // 是否获得焦点
//        popupWindow.setFocusable(true);
        // 点击之外的是否取消
        popupWindow.setOutsideTouchable(true);
        // 出现在谁的下方
        popupWindow.showAsDropDown(homeTab);


        homeMenuRv = (RecyclerView) view.findViewById(R.id.Home_top_menu_rv);
        menuRvAdapter = new ItemHomeMenuRvAdapter(context);

        /**
         * PopWindow数据解析加载到实体类
         */
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.HOMEMENURV, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                HomeTobMenuBean bean = gson.fromJson(resultStr, HomeTobMenuBean.class);
                List<HomeTobMenuBean.DataBean.ChannelsBean> name = bean.getData().getChannels();
                menuRvAdapter.setDatas(name);
                homeMenuRv.setAdapter(menuRvAdapter);
            }

            @Override
            public void failure() {

            }
        });

        GridLayoutManager manager1 = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
        homeMenuRv.setLayoutManager(manager1);

        /**
         *  PopupWindow点击事件
         */
        menuRvAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener<String>() {
            @Override
            public void OnRecyclerViewItemClick(int position, String s) {
                Log.d("HomeFragment", "position:" + position);
                menuRvAdapter.setSetIndex(position);
                homeTab.getTabAt(position).select();
                homeVp.setCurrentItem(position);
                popupWindow.dismiss();
                homeTabLlAfter.setVisibility(View.GONE);
            }
        });
    }
}
