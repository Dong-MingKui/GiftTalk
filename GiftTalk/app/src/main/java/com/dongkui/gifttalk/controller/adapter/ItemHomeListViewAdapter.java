package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.ItemHomeListViewBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/14.
 * 首页ListView的适配器
 */
public class ItemHomeListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ItemHomeListViewBean> datas;
    public ItemHomeListViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ItemHomeListViewBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView ==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_list_view,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.categoryTv.setText(datas.get(position).getData().getItems().get(position).getColumn().getCategory());
        viewHolder.titleTv.setText(datas.get(position).getData().getItems().get(position).getColumn().getTitle());
        viewHolder.nickNameTv.setText(datas.get(position).getData().getItems().get(position).getAuthor().getNickname());
        Picasso.with(context).load(datas.get(position).getData().getItems().get(position).getAuthor().getAvatar_url()).into(viewHolder.avatarImg);
        Picasso.with(context).load(datas.get(position).getData().getItems().get(position).getColumn().getBanner_image_url()).into(viewHolder.bannerImg);
        viewHolder.contentTv.setText(datas.get(position).getData().getItems().get(position).getTitle());
        viewHolder.likeCount.setText(datas.get(position).getData().getItems().get(position).getLikes_count());
        return convertView;
    }

    class ViewHolder {
        TextView categoryTv;
        TextView titleTv;
        TextView nickNameTv;
        ImageView avatarImg;
        ImageView bannerImg;
        TextView contentTv;
        TextView likeCount;

        public ViewHolder(View view) {
            categoryTv = (TextView) view.findViewById(R.id.item_home_list_category);
            titleTv = (TextView) view.findViewById(R.id.item_home_list_title);
            nickNameTv = (TextView) view.findViewById(R.id.item_home_list_nick_name);
            avatarImg = (ImageView) view.findViewById(R.id.item_home_list_user_avatar);
            bannerImg = (ImageView) view.findViewById(R.id.item_home_list_banner_image);
            contentTv = (TextView) view.findViewById(R.id.item_home_list_content);
            likeCount = (TextView) view.findViewById(R.id.item_home_list_like_count);
        }
    }



}
