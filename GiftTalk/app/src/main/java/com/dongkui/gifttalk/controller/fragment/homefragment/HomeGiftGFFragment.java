package com.dongkui.gifttalk.controller.fragment.homefragment;

import android.os.Bundle;
import android.widget.ListView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.ItemHomeListViewAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.model.bean.ItemHomeListViewBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class HomeGiftGFFragment extends AbsBaseFragment {
    private ListView gtListView;
    private ItemHomeListViewAdapter listViewAdapter;

    public static HomeGiftGFFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("home", url);
        HomeGiftGFFragment fragment = new HomeGiftGFFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_giftgf;
    }

    @Override
    protected void  initView() {
        gtListView = byView(R.id.home_gift_GF_list_view);
    }

    @Override
    protected void initDatas() {
        listViewAdapter = new ItemHomeListViewAdapter(context);
        listViewRequest();

    }

    private void listViewRequest() {
        Bundle bundle = getArguments();
        String homeGiftGF =  bundle.getString("home");
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(homeGiftGF, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ItemHomeListViewBean bean = gson.fromJson(resultStr, ItemHomeListViewBean.class);

                List<ItemHomeListViewBean.DataBean.ItemsBean> datas = bean.getData().getItems();
                listViewAdapter.setDatas(datas);
                gtListView.setAdapter(listViewAdapter);
            }

            @Override
            public void failure() {

            }
        });
    }
}
