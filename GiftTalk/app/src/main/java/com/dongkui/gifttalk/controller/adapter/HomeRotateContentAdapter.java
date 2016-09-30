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
import com.dongkui.gifttalk.model.bean.HomeRotateContentCollectionBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class HomeRotateContentAdapter extends BaseAdapter {
    private Context context;
    private List<HomeRotateContentCollectionBean.DataBean.PostsBean> postsBeen;

    public HomeRotateContentAdapter(Context context) {
        this.context = context;
    }

    public void setPostsBeen(List<HomeRotateContentCollectionBean.DataBean.PostsBean> postsBeen) {
        this.postsBeen = postsBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return postsBeen == null ? 0 : postsBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return postsBeen == null ? null : postsBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RotateViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_list_view, parent, false);
            viewHolder = new RotateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RotateViewHolder) convertView.getTag();
        }

        viewHolder.categoryTv.setText(postsBeen.get(position).getColumn().getCategory());
        viewHolder.titleTv.setText(postsBeen.get(position).getColumn().getTitle());
        viewHolder.nickNameTv.setText(postsBeen.get(position).getAuthor().getNickname());
        // 头像
        Picasso.with(context).load(postsBeen.get(position).getAuthor().getAvatar_url()).config(Bitmap.Config.RGB_565).into(viewHolder.avatarImg);
        Picasso.with(context).load(postsBeen.get(position).getCover_image_url()).config(Bitmap.Config.RGB_565).into(viewHolder.coverImg);
        viewHolder.contentTv.setText(postsBeen.get(position).getTitle());
        viewHolder.likeCount.setText(String.valueOf(postsBeen.get(position).getLikes_count()));

        return convertView;
    }


    class RotateViewHolder {
        TextView categoryTv;
        TextView titleTv;
        TextView nickNameTv;
        ImageView avatarImg;
        ImageView coverImg;
        TextView contentTv;
        TextView likeCount;

        public RotateViewHolder(View view) {
            categoryTv = (TextView) view.findViewById(R.id.item_home_list_category);
            titleTv = (TextView) view.findViewById(R.id.item_home_list_title);
            nickNameTv = (TextView) view.findViewById(R.id.item_home_list_nick_name);
            avatarImg = (ImageView) view.findViewById(R.id.item_home_list_user_avatar);
            coverImg = (ImageView) view.findViewById(R.id.item_home_list_cover_image);
            contentTv = (TextView) view.findViewById(R.id.item_home_list_content);
            likeCount = (TextView) view.findViewById(R.id.item_home_list_like_count);
        }
    }
}
