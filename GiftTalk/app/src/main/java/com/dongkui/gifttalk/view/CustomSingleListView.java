package com.dongkui.gifttalk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by dllo on 16/9/22.
 */
public class CustomSingleListView extends ListView implements AbsListView.OnScrollListener {

    private int firstVisibleItem;
    //    private CustomSingleListView menuListView;
    public CustomSingleListView menuListView;

    public CustomSingleListView(Context context) {
        super(context);
    }

    public CustomSingleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSingleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = getMeasuredHeight();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
                width = Math.max(width, view.getMeasuredWidth());
            }
        }
        setMeasuredDimension(width, height);
    }


    public void onTouch(MotionEvent ev) {
        super.onTouchEvent(ev);
    }

    public void setMenuListView(CustomSingleListView menuListView) {
        this.menuListView = menuListView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (menuListView != null) {
            menuListView.onTouch(ev);
        }
        return super.onTouchEvent(ev);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
    }
}
