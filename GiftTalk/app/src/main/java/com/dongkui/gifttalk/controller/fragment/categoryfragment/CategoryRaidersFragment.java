package com.dongkui.gifttalk.controller.fragment.categoryfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.ItemCategoryColumnAdapter;
import com.dongkui.gifttalk.controller.adapter.ItemCategoryVAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.model.bean.ItemCategoryColumnBean;
import com.dongkui.gifttalk.model.bean.ItemCategoryVBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.dongkui.gifttalk.utils.ValueTools;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 分类界面中的攻略界面
 */
public class CategoryRaidersFragment extends AbsBaseFragment {
    private RecyclerView raiderColumnRv;
    private RecyclerView raiderKindRv;
    private RecyclerView raiderStyleRv;
    private RecyclerView raiderTargetRv;
    private ItemCategoryColumnAdapter columnAdapter;
    private List<ItemCategoryColumnBean> columnBean;
    private ItemCategoryVAdapter kindAdapter;
    private ItemCategoryVAdapter styleAdapter;
    private ItemCategoryVAdapter targetAdapter;
    private TextView kindTv;
    private TextView styleTv;
    private TextView targetTv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_category_raiders;
    }

    @Override
    protected void initView() {
        raiderColumnRv = byView(R.id.category_raider_column_rv);
        raiderKindRv = byView(R.id.category_raider_kind_rv);
        raiderStyleRv = byView(R.id.category_raider_style_rv);
        raiderTargetRv = byView(R.id.category_raider_target_rv);
        kindTv = byView(R.id.category_kind_tv);
        styleTv = byView(R.id.category_style_tv);
        targetTv = byView(R.id.category_target_tv);
    }

    @Override
    protected void initDatas() {
        columnAdapter = new ItemCategoryColumnAdapter(context);
        columnBean = new ArrayList<>();
        columRequest();
        GridLayoutManager manager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);
        raiderColumnRv.setLayoutManager(manager);

        kindAdapter = new ItemCategoryVAdapter(context);
        styleAdapter = new ItemCategoryVAdapter(context);
        targetAdapter = new ItemCategoryVAdapter(context);
        kindRequest();
        GridLayoutManager manager1 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager2 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager3 = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        raiderKindRv.setLayoutManager(manager1);
        raiderStyleRv.setLayoutManager(manager2);
        raiderTargetRv.setLayoutManager(manager3);
    }

    private void kindRequest() {
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.CATEGORYRAIDERV, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ItemCategoryVBean bean = gson.fromJson(resultStr, ItemCategoryVBean.class);

                if (bean.getData().getChannel_groups().get(0).getId() == 5) {
                    List<ItemCategoryVBean.DataBean.ChannelGroupsBean.ChannelsBean> vBean = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        vBean.add(bean.getData().getChannel_groups().get(0).getChannels().get(i));
                    }
                    kindAdapter.setDatas(vBean);
                    raiderKindRv.setAdapter(kindAdapter);
                    kindTv.setText(bean.getData().getChannel_groups().get(0).getName());
                }

                if (bean.getData().getChannel_groups().get(1).getId() == 1) {
                    List<ItemCategoryVBean.DataBean.ChannelGroupsBean.ChannelsBean> vBean = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        vBean.add(bean.getData().getChannel_groups().get(1).getChannels().get(i));
                    }
                    styleAdapter.setDatas(vBean);
                    raiderStyleRv.setAdapter(styleAdapter);
                    styleTv.setText(bean.getData().getChannel_groups().get(1).getName());
                }

                if (bean.getData().getChannel_groups().get(2).getId() == 2) {
                    List<ItemCategoryVBean.DataBean.ChannelGroupsBean.ChannelsBean> vBean = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        vBean.add(bean.getData().getChannel_groups().get(2).getChannels().get(i));
                    }
                    targetAdapter.setDatas(vBean);
                    raiderTargetRv.setAdapter(targetAdapter);
                    targetTv.setText(bean.getData().getChannel_groups().get(2).getName());
                }
            }

            @Override
            public void failure() {

            }
        });
    }

    private void columRequest() {
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.CATEGORYRAIDERSCOLUNM, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ItemCategoryColumnBean bean = gson.fromJson(resultStr, ItemCategoryColumnBean.class);

                for (int i = 0; i < 11; i++) {
                    columnBean.add(bean);
                }
                columnAdapter.setDatas(columnBean);
                raiderColumnRv.setAdapter(columnAdapter);
            }

            @Override
            public void failure() {

            }
        });
    }
}
