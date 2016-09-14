package com.dongkui.gifttalk.controller.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.fragment.categoryfragment.CategoryFragment;
import com.dongkui.gifttalk.controller.fragment.giftfragment.GiftFragment;
import com.dongkui.gifttalk.controller.fragment.homefragment.HomeFragment;
import com.dongkui.gifttalk.controller.fragment.ProfileFragment;

/**
 *  构成大体框架的Activity
 */
public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mainRg;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mainRg = byView(R.id.main_rg);

    }

    @Override
    protected void initDatas() {
        mainRg.setOnCheckedChangeListener(this);
        mainRg.check(R.id.main_home);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (checkedId) {
            case R.id.main_home:
                ft.replace(R.id.main_frame, new HomeFragment());
                break;
            case R.id.main_gift:
                ft.replace(R.id.main_frame, new GiftFragment());
                break;
            case R.id.main_category:
                ft.replace(R.id.main_frame, new CategoryFragment());
                break;
            case R.id.main_profile:
                ft.replace(R.id.main_frame, new ProfileFragment());
                break;
        }
        ft.commit();
    }
}
