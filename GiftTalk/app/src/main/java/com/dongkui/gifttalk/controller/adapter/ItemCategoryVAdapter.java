package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemCategoryVBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 分类界面攻略的 下面三个的RecyclerView的适配器
 */
public class ItemCategoryVAdapter extends RecyclerView.Adapter<ItemCategoryVAdapter.VViewHolder> {

    private Context context;
    private List<ItemCategoryVBean.DataBean.ChannelGroupsBean.ChannelsBean> datas;

    public ItemCategoryVAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ItemCategoryVBean.DataBean.ChannelGroupsBean.ChannelsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public VViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_raiders_kind, parent, false);
        VViewHolder holder = new VViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getCover_image_url()).config(Bitmap.Config.RGB_565).into(holder.kindCoverImg);
    }

    @Override
    public int getItemCount() {
        return datas.size() == 0 ? 0 : datas.size();
    }

    class VViewHolder extends RecyclerView.ViewHolder {
        ImageView kindCoverImg;

        public VViewHolder(View itemView) {
            super(itemView);
            kindCoverImg = (ImageView) itemView.findViewById(R.id.category_kind_cover_img);
        }
    }
}
