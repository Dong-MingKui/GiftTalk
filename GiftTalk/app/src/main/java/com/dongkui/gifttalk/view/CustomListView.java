package com.dongkui.gifttalk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dllo on 16/9/17.
 * 自定义ListView
 */
public class CustomListView extends ListView{

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 展开ListView
     * onMeasure 这个方法是决定view的大小
     * 重新测量 - 规定他的高度是展开的.
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //对于详细测量值（ measureSpec ）需要两样东西来确定它，参数是大小（size）和模式（mode）。
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * 触摸监听函数
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            // 禁止listView滑动
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }



}


