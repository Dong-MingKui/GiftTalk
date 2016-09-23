package com.dongkui.gifttalk.controller.fragment.categoryfragment;

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
 * 分类界面
 */
public class CategoryFragment extends AbsBaseFragment {
    private AllFragmentAdapter adapter;
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

    }

    @Override
    protected void initDatas() {
        adapter = new AllFragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new CategoryRaidersFragment());
        fragments.add(new CategorySingleFragment());
        adapter.setFragments(fragments);
        categoryVp.setAdapter(adapter);
        categoryTab.setupWithViewPager(categoryVp);
        categoryTab.getTabAt(0).setText("攻略");
        categoryTab.getTabAt(1).setText("单品");
    }
}
