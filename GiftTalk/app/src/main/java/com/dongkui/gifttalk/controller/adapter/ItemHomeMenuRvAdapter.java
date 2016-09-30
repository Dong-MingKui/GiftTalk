package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.model.bean.HomeTobMenuBean;
import com.dongkui.gifttalk.utils.minterface.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by dllo on 16/9/28.
 * 首页PopWindow的适配器
 */
public class ItemHomeMenuRvAdapter extends RecyclerView.Adapter<ItemHomeMenuRvAdapter.HomeMenuViewHolder> {

    private Context context;
    private List<HomeTobMenuBean.DataBean.ChannelsBean> datas;
    private OnRecyclerViewItemClickListener<String> onRecyclerViewItemClickListener;
    protected int setIndex;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener<String> onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void setSetIndex(int selectIndex) {
        this.setIndex = selectIndex;
        Log.d("ItemHomeMenuRvAdapter", "selectIndex:" + selectIndex);
        Log.d("ItemHomeMenuRvAdapter", "setIndex:" + setIndex);
        notifyDataSetChanged();
    }

    public ItemHomeMenuRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<HomeTobMenuBean.DataBean.ChannelsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public HomeMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_top_menu, parent, false);
        HomeMenuViewHolder holder = new HomeMenuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HomeMenuViewHolder holder, int position) {
        holder.menuTv.setText(datas.get(position).getName());
        if (setIndex == position) {
            holder.menuTv.setTextColor(Color.RED);
            holder.menuPwView.setVisibility(View.VISIBLE);
        } else {
            holder.menuTv.setTextColor(Color.BLACK);
            holder.menuPwView.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                String string = datas.get(position).getName();
                onRecyclerViewItemClickListener.OnRecyclerViewItemClick(position, string);
            }
        });


    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class HomeMenuViewHolder extends RecyclerView.ViewHolder {

        TextView menuTv;
        View menuPwView;

        public HomeMenuViewHolder(View itemView) {
            super(itemView);
            menuTv = (TextView) itemView.findViewById(R.id.item_home_menu_pw_content);
            menuPwView = (View) itemView.findViewById(R.id.item_home_menu_pw_view);
        }
    }
}
