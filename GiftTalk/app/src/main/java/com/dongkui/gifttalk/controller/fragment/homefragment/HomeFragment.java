package com.dongkui.gifttalk.controller.fragment.homefragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.AllFragmentAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.utils.ValueTools;

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

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeTab = byView(R.id.home_tab);
        homeVp = byView(R.id.home_vp);
        homeMeanArrow = byView(R.id.home_mean_arrow);

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
        String[] headers = {"精选", "送女票", "海淘", "创意生活", "送基友", "送爸妈", "送通知", "送宝贝", "设计感", "文艺风", "奇葩怪谈", "科技范", "萌萌哒"};
        for (int i = 0; i < 13; i++) {
            homeTab.getTabAt(i).setText(headers[i]);
        }

        homeMeanArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation anim = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setDuration(100);
                anim.setFillAfter(true);
                homeMeanArrow.clearAnimation();
                homeMeanArrow.setAnimation(anim);
                popWindowShow();

            }
        });

    }


    private void popWindowShow() {

        PopupWindow window = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_top_mean_pw, null);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setContentView(view);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(homeTab);
    }


}
