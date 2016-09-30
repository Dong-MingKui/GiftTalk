package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemCategoryColumnBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 分类界面栏目的适配器
 */
public class ItemCategoryColumnAdapter extends RecyclerView.Adapter<ItemCategoryColumnAdapter.ColumnViewHolder> {

    private Context context;
    private List<ItemCategoryColumnBean> datas;


    public ItemCategoryColumnAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ItemCategoryColumnBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ColumnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_raiders_column, parent, false);
        ColumnViewHolder holder = new ColumnViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ColumnViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getData().getColumns().get(position).getBanner_image_url()).into(holder.bannerImageUrl);
        holder.titleTv.setText(datas.get(position).getData().getColumns().get(position).getTitle());
        holder.subtitleTv.setText(datas.get(position).getData().getColumns().get(position).getSubtitle());
        holder.authorTv.setText(datas.get(position).getData().getColumns().get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return datas.size() == 0 ? 0 : datas.size();
    }

    class ColumnViewHolder extends RecyclerView.ViewHolder {
        ImageView bannerImageUrl;
        TextView titleTv;
        TextView subtitleTv;
        TextView authorTv;

        public ColumnViewHolder(View view) {
            super(view);
            bannerImageUrl = (ImageView) view.findViewById(R.id.category_column_banner_image_url);
            titleTv = (TextView) view.findViewById(R.id.category_column_title);
            subtitleTv = (TextView) view.findViewById(R.id.category_column_subtitle);
            authorTv = (TextView) view.findViewById(R.id.category_column_author);
        }
    }
}
