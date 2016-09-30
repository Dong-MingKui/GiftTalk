package com.dongkui.gifttalk.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dongkui.gifttalk.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/29.
 */
public class HomeRecyclerViewContentActivity extends Activity {
    private LinearLayout contentLl;
    private ImageView contentImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rv_content);
//        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        initView();
        initDatas();
    }

    protected void initView() {
        contentLl = (LinearLayout) findViewById(R.id.home_rv_content_ll);
        contentImg = (ImageView) findViewById(R.id.home_rv_content_img);
    }

    protected void initDatas() {
        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra("picture");
        Picasso.with(this).load(imgUrl).resize(1000, 1000).into(contentImg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
