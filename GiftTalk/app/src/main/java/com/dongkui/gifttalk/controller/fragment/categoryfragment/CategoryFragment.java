package com.dongkui.gifttalk.controller.fragment.categoryfragment;

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
 * 分类界面
 */
public class CategoryFragment extends AbsBaseFragment {
    private FragmentAdapter adapter;
    private List<Fragment> fragments;
    private TabLayout categoryTab;
    private ViewPager categoryVp;

    @Override
    protected int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        categoryTab = byView(R.id.category_tab);
        categoryVp = byView(R.id.category_vp);
        adapter = new FragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();

    }

    @Override
    protected void initDatas() {
        fragments.add(new CategoryRaidersFragment());
        fragments.add(new CategoryRaidersFragment());
        adapter.setFragments(fragments);
        categoryVp.setAdapter(adapter);
        categoryTab.setupWithViewPager(categoryVp);
        categoryTab.getTabAt(0).setText("攻略");
        categoryTab.getTabAt(1).setText("单品");
    }
}
