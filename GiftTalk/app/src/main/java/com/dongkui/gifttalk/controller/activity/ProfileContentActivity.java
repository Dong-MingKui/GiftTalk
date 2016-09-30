package com.dongkui.gifttalk.controller.activity;

import android.view.View;
import android.widget.ImageView;

import com.dongkui.gifttalk.R;

/**
 * Created by dllo on 16/9/29.
 */
public class ProfileContentActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView closeImg;

    @Override
    protected int setLayout() {
        return R.layout.activity_profile_content;
    }

    @Override
    protected void initView() {
        closeImg = byView(R.id.profile_close_img);
        closeImg.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_close_img:
                finish();
                break;
        }
    }
}
