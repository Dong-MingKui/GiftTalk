package com.dongkui.gifttalk.controller.fragment.giftfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.AllFragmentAdapter;
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
    private AllFragmentAdapter adapter;
    private List<Fragment> fragments;


    @Override
    protected int setLayout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initView() {
        giftTab = byView(R.id.gift_tab);
        giftVp = byView(R.id.gift_vp);

        adapter = new AllFragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();

    }

    @Override
    protected void initDatas() {

        fragments.add(new GiftRecDayFragment());
        fragments.add(new GiftTOP100Fragment());
        fragments.add(new GiftOriginalityFragment());
        fragments.add(new GiftNovaFragment());
//        fragments.add(GiftRecDayFragment.newInstance(ValueTools.GIFTRECYCLERVIEW));
//        fragments.add(GiftRecDayFragment.newInstance(ValueTools.GIFTRECYCLERVIEW2));
//        fragments.add(GiftRecDayFragment.newInstance(ValueTools.GIFTRECYCLERVIEW3));
//        fragments.add(GiftRecDayFragment.newInstance(ValueTools.GIFTRECYCLERVIEW4));

        adapter.setFragments(fragments);
        giftVp.setAdapter(adapter);
        giftTab.setupWithViewPager(giftVp);
        String[] title = {"每日推荐", "TOP100", "独立原创榜", "新星榜"};
        for (int i = 0; i < 4; i++) {
            giftTab.getTabAt(i).setText(title[i]);
        }
    }
}
