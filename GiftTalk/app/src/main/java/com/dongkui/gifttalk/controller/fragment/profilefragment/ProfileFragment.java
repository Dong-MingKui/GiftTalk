package com.dongkui.gifttalk.controller.fragment.profilefragment;

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
 * 我界面
 */
public class ProfileFragment extends AbsBaseFragment {
    private TabLayout profileTab;
    private ViewPager profileVp;
    private AllFragmentAdapter profileAdapter;
    private List<Fragment> fragments;

    @Override
    protected int setLayout() {
        return R.layout.fragment_profile;

    }

    @Override
    protected void initView() {
        profileTab = byView(R.id.profile_tab);
        profileVp = byView(R.id.profile_vp);

    }

    @Override
    protected void initDatas() {
        profileAdapter = new AllFragmentAdapter(getChildFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new ProfileItemFragment());
        fragments.add(new ProfileRaidersFragment());
        profileAdapter.setFragments(fragments);
        profileVp.setAdapter(profileAdapter);
        profileTab.setupWithViewPager(profileVp);
        profileTab.getTabAt(0).setText("单品");
        profileTab.getTabAt(1).setText("攻略");

    }
}
