package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemCategorySingleBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 * 分类界面右边ListView内的GridView的适配器
 *
 */
public class ItemCategorySingleContentGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<ItemCategorySingleBean.DataBean.CategoriesBean.SubcategoriesBean> singleBeen;

    public ItemCategorySingleContentGridViewAdapter(Context context) {
        this.context = context;
    }

    public void setSingleBeen(List<ItemCategorySingleBean.DataBean.CategoriesBean.SubcategoriesBean> singleBeen) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_single_content_grid_view, parent, false);
            holder = new GridViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(singleBeen.get(position).getIcon_url()).config(Bitmap.Config.RGB_565).into(holder.subcategoriesIconUrl);
        holder.subcategoriesName.setText(singleBeen.get(position).getName());

        return convertView;
    }

    class GridViewHolder {
        ImageView subcategoriesIconUrl;
        TextView subcategoriesName;

        public GridViewHolder(View view) {
            subcategoriesIconUrl = (ImageView) view.findViewById(R.id.item_category_single_content_grid_icon_url);
            subcategoriesName = (TextView) view.findViewById(R.id.item_category_single_content_grid_name);
        }
    }
}
