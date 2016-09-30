package com.dongkui.gifttalk.controller.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongkui.gifttalk.R;
import com.dongkui.gifttalk.controller.app.MyApp;
import com.dongkui.gifttalk.model.bean.ItemHomeRotateBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/23.
 * 首页轮播图的适配器(自己添加数据的适配器)
 */
public class ItemHomeRotateAdapter extends PagerAdapter {
    private List<ItemHomeRotateBean> datas;
    private LayoutInflater inflater;
    private Context context;


    public ItemHomeRotateAdapter(List<ItemHomeRotateBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<ItemHomeRotateBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // 为了让ViewPager到最后一页不会翻书一样回到第一页
        // 设置页数为int最大值,这样向下滑动永远都是下一页
        return datas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // position是int最大值所以这里可能是几百甚至上千,因此取余避免数组越界
        int newPosition = position % datas.size();
        View convertView = inflater.inflate(R.layout.item_home_rotate_vp, container, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_rotate_img);
//        TextView textView = (TextView) convertView.findViewById(R.id.item_rotate_tv);
//        textView.setText("啦啦啦啦" + newPosition);
        // 网络图片框架:毕加索
        // 通过context加载图片网址, 到这个ImageView
        Picasso.with(MyApp.getContext()).load(datas.get(newPosition).getImgUrl()).into(imageView);
        container.addView(convertView);

        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
