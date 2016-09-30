package com.dongkui.gifttalk.controller.fragment.profilefragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.activity.ProfileContentActivity;
import com.dongkui.gifttalk.controller.adapter.AllFragmentAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 我界面
 */
public class ProfileFragment extends AbsBaseFragment implements View.OnClickListener {
    private TabLayout profileTab;
    private ViewPager profileVp;
    private AllFragmentAdapter profileAdapter;
    private List<Fragment> fragments;
    private ImageView messageImg;
    private ImageView iconScan;
    private ImageView meSettings;
    private LinearLayout avatarLl;
    private LinearLayout profileMeanll;

    @Override
    protected int setLayout() {
        return R.layout.fragment_profile;

    }

    @Override
    protected void initView() {
        profileTab = byView(R.id.profile_tab);
        profileVp = byView(R.id.profile_vp);

        messageImg = byView(R.id.profile_avatar_message);
        iconScan = byView(R.id.avatar_scan);
        meSettings = byView(R.id.profile_avatar_settings);
        avatarLl = byView(R.id.profile_avatar_sex);
        profileMeanll = byView(R.id.profile_mean_ll);

        messageImg.setOnClickListener(this);
        iconScan.setOnClickListener(this);
        meSettings.setOnClickListener(this);
        avatarLl.setOnClickListener(this);
        profileMeanll.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_avatar_message:
                Intent intent0 = new Intent(context, ProfileContentActivity.class);
                context.startActivity(intent0);
                break;
            case R.id.avatar_scan:
                Intent intent1 = new Intent(context, ProfileContentActivity.class);
                context.startActivity(intent1);
                break;
            case R.id.profile_avatar_settings:
                Intent intent2 = new Intent(context, ProfileContentActivity.class);
                context.startActivity(intent2);
                break;
            case R.id.profile_avatar_sex:
                Intent intent3 = new Intent(context, ProfileContentActivity.class);
                context.startActivity(intent3);
                break;
            case R.id.profile_mean_ll:
                Intent intent4 = new Intent(context, ProfileContentActivity.class);
                context.startActivity(intent4);
                break;
        }
    }
}
