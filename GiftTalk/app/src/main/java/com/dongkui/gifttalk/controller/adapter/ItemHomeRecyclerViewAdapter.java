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
import com.dongkui.gifttalk.utils.minterface.OnRecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/19.\
 * 首页横向滑动的RecyclerView适配器
 */
public class ItemHomeRecyclerViewAdapter extends RecyclerView.Adapter<ItemHomeRecyclerViewAdapter.HomeViewHolder> {
    private Context context;
    private List<ItemHomeRecyclerViewBean.DataBean.SecondaryBannersBean> datas;
    private OnRecyclerViewItemClickListener<String> onRecyclerViewItemClickListener;

    public ItemHomeRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ItemHomeRecyclerViewBean.DataBean.SecondaryBannersBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener<String> onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recycler_view, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {

        Picasso.with(context).load(datas.get(position).getImage_url()).config(Bitmap.Config.RGB_565).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                String bean = datas.get(position).getImage_url();
                onRecyclerViewItemClickListener.OnRecyclerViewItemClick(position, bean);
            }
        });

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
