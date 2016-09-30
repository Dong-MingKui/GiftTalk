package com.dongkui.gifttalk.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.HomeRotateContentAdapter;
import com.dongkui.gifttalk.model.bean.HomeRotateContentCollectionBean;
import com.dongkui.gifttalk.model.bean.HomeRotateContentPostBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.dongkui.gifttalk.utils.ValueTools;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class HomeRotateContentActivity extends AbsBaseActivity {

    private ListView rotateContentLv;
    private HomeRotateContentAdapter rotateContentAdapter;
    private TextView homeRotateContentTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_rotate_content;
    }

    @Override
    protected void initView() {
        rotateContentLv = byView(R.id.home_rotate_content_list_view);
        homeRotateContentTv = byView(R.id.home_rotate_content_tv);
    }



    @Override
    protected void initDatas() {
        rotateContentAdapter = new HomeRotateContentAdapter(this);
        Intent intent = getIntent();
        String s = intent.getStringExtra("rotate");
        String type = intent.getStringExtra("type");
        Log.d("HomeRotateContentActivi", s + "");
        if (type.equals("post")) {
            Toast.makeText(this, "这个是网页等会写", Toast.LENGTH_SHORT).show();
            final String postUrl = ValueTools.HOME_ROTATE_CONTENT_POST + s;
            VolleyInstance volleyInstance = VolleyInstance.getInstance();
            volleyInstance.startRequest(postUrl, new OnVolleyResult() {
                @Override
                public void success(String resultStr) {
                    Gson gson = new Gson();
                    HomeRotateContentPostBean postBean = gson.fromJson(resultStr, HomeRotateContentPostBean.class);
                    String html = postBean.getData().getContent_html();
                    Toast.makeText(HomeRotateContentActivity.this, "html:" + html, Toast.LENGTH_SHORT).show();
                }


                @Override
                public void failure() {

                }
            });

        } else if (type.equals("collection")) {
            Toast.makeText(this, ValueTools.HOME_ROTATE_CONTENT_HEAD + s + ValueTools.HOME_ROTATE_CONTENT_FOOT, Toast.LENGTH_LONG).show();

            VolleyInstance volleyInstance = VolleyInstance.getInstance();
            volleyInstance.startRequest(ValueTools.HOME_ROTATE_CONTENT_HEAD + s + ValueTools.HOME_ROTATE_CONTENT_FOOT, new OnVolleyResult() {
                @Override
                public void success(String resultStr) {
                    if (resultStr == null) {
                        Toast.makeText(HomeRotateContentActivity.this, "暂无详情", Toast.LENGTH_SHORT).show();
                    }
                    Gson gson = new Gson();
                    HomeRotateContentCollectionBean bean = gson.fromJson(resultStr, HomeRotateContentCollectionBean.class);
                    List<HomeRotateContentCollectionBean.DataBean.PostsBean> postsBeen = bean.getData().getPosts();
                    rotateContentAdapter.setPostsBeen(postsBeen);
                    rotateContentLv.setAdapter(rotateContentAdapter);
                }

                @Override
                public void failure() {

                }
            });
        } else if (type.equals("url")) {
            Toast.makeText(this, "你的网址在哪里", Toast.LENGTH_SHORT).show();
        }

    }
}
