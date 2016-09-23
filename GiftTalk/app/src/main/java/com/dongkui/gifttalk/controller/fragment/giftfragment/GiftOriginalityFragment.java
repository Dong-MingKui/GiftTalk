package com.dongkui.gifttalk.controller.fragment.giftfragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.ItemGiftRecyclerViewAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.model.bean.ItemGiftRecyclerViewBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.dongkui.gifttalk.utils.ValueTools;
import com.dongkui.gifttalk.view.CustomRecyclerView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 榜首界面中的每日推荐
 */
public class GiftOriginalityFragment extends AbsBaseFragment {
    private CustomRecyclerView recyclerView;
    private ImageView coverImage;
    private ItemGiftRecyclerViewAdapter recyclerViewAdapter;

    public static GiftFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("gift", url);
        GiftFragment fragment = new GiftFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_gift_recday;
    }

    @Override
    protected void initView() {
        recyclerView = byView(R.id.profile_recycler_view);
        coverImage = byView(R.id.profile_cover_image);

    }

    @Override
    protected void initDatas() {
        recyclerViewAdapter = new ItemGiftRecyclerViewAdapter(context);
        RecyclerViewRequest();
        GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    private void RecyclerViewRequest() {
//        Bundle bundle = getArguments();
//        String allUrl = bundle.getString("gift");
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.GIFTRECYCLERVIEW3, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                // 将解析结果放到类中
                ItemGiftRecyclerViewBean bean = gson.fromJson(resultStr, ItemGiftRecyclerViewBean.class);

                List<ItemGiftRecyclerViewBean.DataBean.ItemsBean> recyclerViewBean = bean.getData().getItems();
                Picasso.with(context).load(bean.getData().getCover_image()).config(Bitmap.Config.ALPHA_8).into(coverImage);
                Log.d("GiftRecDayFragment", "recyclerViewBean:" + recyclerViewBean);
                recyclerViewAdapter.setDatas(recyclerViewBean);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void failure() {

            }
        });
    }
}
