package com.dongkui.gifttalk.controller.fragment.homefragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.FragmentAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 首页界面
 */
public class HomeFragment extends AbsBaseFragment {
    private FragmentAdapter adapter;
    private TabLayout homeTab;
    private ViewPager homeVp;
    private List<Fragment> fragments;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeTab = byView(R.id.home_tab);
        homeVp = byView(R.id.home_vp);
        adapter = new FragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();
    }

    @Override
    protected void initDatas() {
        for (int i = 0; i < 13; i++) {
            fragments.add(new HomeSelectionFragment());
        }
//        fragments.add(new HomeGiftGFFragment());
        adapter.setFragments(fragments);
        homeVp.setAdapter(adapter);
        homeTab.setupWithViewPager(homeVp);
        homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        String[] headers = {"精选", "送女票", "海淘", "创意生活", "送基友", "送爸妈", "送通知", "送宝贝", "设计感", "文艺风", "奇葩怪谈", "科技范", "萌萌哒"};
        for (int i = 0; i < 13; i++) {
            homeTab.getTabAt(i).setText(headers[i]);
        }
    }
}
