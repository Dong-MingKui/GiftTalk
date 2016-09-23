package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemCategorySingleBean;
import com.dongkui.gifttalk.view.CustomGridView;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public class ItemCategorySingleContentListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ItemCategorySingleBean.DataBean.CategoriesBean> singleBeen;
    private ItemCategorySingleContentGridViewAdapter gridViewAdapter;
    public int position;

    public ItemCategorySingleContentListViewAdapter(Context context) {
        this.context = context;
    }

    public void setSingleBeen(List<ItemCategorySingleBean.DataBean.CategoriesBean> singleBeen) {
        this.singleBeen = singleBeen;
        notifyDataSetChanged();
    }

    public void setIndex(int position) {
        this.position = position;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_single_content_list_view, parent, false);
            holder = new ListViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ListViewHolder) convertView.getTag();
        }
        holder.topName.setText(singleBeen.get(position).getName());
        gridViewAdapter = new ItemCategorySingleContentGridViewAdapter(context);
        gridViewAdapter.setSingleBeen(singleBeen.get(position).getSubcategories());
        holder.gridView.setAdapter(gridViewAdapter);
        return convertView;
    }

    class ListViewHolder {
        TextView topName;
        GridView gridView;

        public ListViewHolder(View view) {
            topName = (TextView) view.findViewById(R.id.item_category_single_content_top_name);
            gridView = (CustomGridView) view.findViewById(R.id.item_category_single_content_grid_view);
        }
    }
}
