package com.dongkui.gifttalk.controller.fragment.giftfragment;

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
 * 榜首界面
 */
public class GiftFragment extends AbsBaseFragment {
    private TabLayout giftTab;
    private ViewPager giftVp;
    private FragmentAdapter adapter;
    private List<Fragment> fragments;

    @Override
    protected int setLayout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initView() {
        giftTab = byView(R.id.gift_tab);
        giftVp = byView(R.id.gift_vp);
        adapter = new FragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();

    }

    @Override
    protected void initDatas() {
        for (int i = 0; i < 4; i++) {
            fragments.add(new GiftRecDayFragment());
        }
        adapter.setFragments(fragments);
        giftVp.setAdapter(adapter);
        giftTab.setupWithViewPager(giftVp);
        String[] title = {"每日推荐", "TOP100", "独立原创榜", "新星榜"};
        for (int i = 0; i < 4; i++) {
            giftTab.getTabAt(i).setText(title[i]);
        }
    }
}
