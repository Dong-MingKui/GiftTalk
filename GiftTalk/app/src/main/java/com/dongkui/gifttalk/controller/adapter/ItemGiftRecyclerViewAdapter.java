package com.dongkui.gifttalk.controller.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemGiftRecyclerViewBean;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by dllo on 16/9/18.
 * 榜单界面的适配器
 */
public class ItemGiftRecyclerViewAdapter extends RecyclerView.Adapter<ItemGiftRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<ItemGiftRecyclerViewBean.DataBean.ItemsBean> datas;

    public ItemGiftRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ItemGiftRecyclerViewBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gift_recycler_view, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        if (datas.get(position).getCategory_id() != 0) {
//            holder.orderLl.setVisibility(View.GONE);
            Picasso.with(context).load(datas.get(position).getCover_image_url()).config(Bitmap.Config.RGB_565).into(holder.coverImageUrl);
            holder.shortDescriptionTv.setText(datas.get(position).getShort_description());
            holder.nameTv.setText(datas.get(position).getName());
            holder.priceTv.setText(datas.get(position).getPrice());
//        } else {
            holder.orderTv.setText(datas.get(position).getOrder() + "");
//            Picasso.with(context).load(datas.get(position).getCover_image_url()).config(Bitmap.Config.RGB_565).into(holder.coverImageUrl);
//            holder.shortDescriptionTv.setText(datas.get(position).getShort_description());
//            holder.nameTv.setText(datas.get(position).getName());
//            holder.priceTv.setText(datas.get(position).getPrice());
//        }

    }

    @Override
    public int getItemCount() {
        return datas.size() == 0 ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView coverImageUrl;
        TextView shortDescriptionTv;
        TextView nameTv;
        TextView priceTv;
        TextView orderTv;
        LinearLayout orderLl;

        public MyViewHolder(View view) {
            super(view);
            coverImageUrl = (ImageView) view.findViewById(R.id.item_gift_cover_image);
            shortDescriptionTv = (TextView) view.findViewById(R.id.item_gift_short_description);
            nameTv = (TextView) view.findViewById(R.id.item_gift_name);
            priceTv = (TextView) view.findViewById(R.id.item_gift_price);
            orderTv = (TextView) view.findViewById(R.id.item_gift_order);
            orderLl = (LinearLayout) view.findViewById(R.id.item_gift_order_ll);
        }
    }


}
