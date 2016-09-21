package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemHomeRecyclerViewBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/19.\
 */
public class ItemHomeRecyclerViewAdapter extends RecyclerView.Adapter<ItemHomeRecyclerViewAdapter.HomeViewHolder> {
    private Context context;
    private List<ItemHomeRecyclerViewBean> datas;

    public ItemHomeRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ItemHomeRecyclerViewBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recycler_view, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getData().getSecondary_banners().get(position).getImage_url()).config(Bitmap.Config.RGB_565).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return datas.size() == 0 ? 0 : datas.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public HomeViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_home_recycler_view_img);
        }
    }
}
