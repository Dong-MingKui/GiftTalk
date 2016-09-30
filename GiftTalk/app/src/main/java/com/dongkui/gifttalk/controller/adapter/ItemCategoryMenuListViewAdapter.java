package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemCategorySingleBean;

import java.util.List;


/**
 * Created by dllo on 16/9/22.
 * 分类界面左面的ListView的适配器
 */
public class ItemCategoryMenuListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ItemCategorySingleBean.DataBean.CategoriesBean> singleBeen;
    public int selectIndex;

    public ItemCategoryMenuListViewAdapter(Context context) {
        this.context = context;
    }

    public void setSingleBeen(List<ItemCategorySingleBean.DataBean.CategoriesBean> singleBeen) {
        this.singleBeen = singleBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return singleBeen == null ? 0 : singleBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return singleBeen == null ? null : singleBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_single_menu_list_view, parent, false);
            holder = new MenuViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MenuViewHolder) convertView.getTag();
        }
        holder.menuName.setText(singleBeen.get(position).getName());
        Log.d("ItemCategoryMenuListVie", "position:" + position);
//        holder.menuView.setVisibility(View.VISIBLE);

        if (selectIndex == position) {
            holder.menuName.setTextColor(Color.RED);
            holder.menuView.setVisibility(View.VISIBLE);
        } else {
            holder.menuName.setTextColor(Color.BLACK);
            holder.menuView.setVisibility(View.GONE);
        }
        return convertView;
    }


    class MenuViewHolder {
        TextView menuName;
        View menuView;

        public MenuViewHolder(View view) {
            menuName = (TextView) view.findViewById(R.id.item_category_single_menu_name);
            menuView = view.findViewById(R.id.item_category_single_menu_view);
        }
    }
}
