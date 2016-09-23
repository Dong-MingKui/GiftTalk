package com.dongkui.gifttalk.controller.fragment.categoryfragment;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.adapter.ItemCategoryMenuListViewAdapter;
import com.dongkui.gifttalk.controller.adapter.ItemCategorySingleContentListViewAdapter;
import com.dongkui.gifttalk.controller.fragment.AbsBaseFragment;
import com.dongkui.gifttalk.model.bean.ItemCategorySingleBean;
import com.dongkui.gifttalk.model.net.OnVolleyResult;
import com.dongkui.gifttalk.model.net.VolleyInstance;
import com.dongkui.gifttalk.utils.ValueTools;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/21.
 */
public class CategorySingleFragment extends AbsBaseFragment {
    private ListView menuListView;
    private ListView contentListView;
    private ItemCategoryMenuListViewAdapter menuListViewAdapter;
    private ItemCategorySingleContentListViewAdapter contentListViewAdapter;

    private int selectIndex = 0;

    @Override
    protected int setLayout() {
        return R.layout.fragment_category_single;
    }

    @Override
    protected void initView() {
        menuListView = byView(R.id.category_single_menu_list_view);
        contentListView = byView(R.id.category_single_content_list_view);
    }


    @Override
    protected void initDatas() {
        menuListViewAdapter = new ItemCategoryMenuListViewAdapter(context);
        contentListViewAdapter = new ItemCategorySingleContentListViewAdapter(context);

        contentListView.setAdapter(contentListViewAdapter);
        menuListView.setAdapter(menuListViewAdapter);

        menuListViewRequest();

//        contentListView.setMenuListView(menuListView);
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectIndex = position;
                // 把下标传过去, 然后刷新adapter
//                menuListViewAdapter.setIndex(position);
                // 当点击到某个item的时候让这个item自动滑动到ListView的顶部(下面item够多,如果点击的是最后一行就不能到达顶部了)
//                menuListView.smoothScrollToPositionFromTop(position, 0);
//                menuListView.setSelected(true);
                contentListViewAdapter.setIndex(selectIndex);
                contentListView.smoothScrollToPositionFromTop(selectIndex, 0);
                Log.d("CategorySingleFragment", "position:" + position);
                Log.d("CategorySingleFragment", "selectIndex:" + selectIndex);
            }
        });

        contentListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                menuListViewAdapter.setIndex(firstVisibleItem);
                menuListView.smoothScrollToPositionFromTop(firstVisibleItem, 0);
            }
        });
    }

    private void menuListViewRequest() {
        VolleyInstance volleyInstance = VolleyInstance.getInstance();
        volleyInstance.startRequest(ValueTools.CATEGORYSINGLE, new OnVolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                ItemCategorySingleBean bean = gson.fromJson(resultStr, ItemCategorySingleBean.class);
                List<ItemCategorySingleBean.DataBean.CategoriesBean> been = bean.getData().getCategories();
                menuListViewAdapter.setSingleBeen(been);
                contentListViewAdapter.setSingleBeen(been);
            }

            @Override
            public void failure() {

            }
        });
    }


}
