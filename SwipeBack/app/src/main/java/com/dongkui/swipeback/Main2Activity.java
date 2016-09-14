package com.dongkui.swipeback;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import me.yokeyword.swipebackfragment.SwipeBackActivity;
import me.yokeyword.swipebackfragment.SwipeBackLayout;

public class Main2Activity extends SwipeBackActivity {

    private SwipeBackLayout sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.frag2_frame,new Frag1());
        ft.commit();

        sb = getSwipeBackLayout();
        sb.setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
        sb.addSwipeListener(new SwipeBackLayout.OnSwipeListener() {
            @Override
            public void onDragStateChange(int state) {

            }

            @Override
            public void onEdgeTouch(int oritentationEdgeFlag) {

            }

            @Override
            public void onDragScrolled(float scrollPercent) {
                if (scrollPercent > 0) {
                    Toast.makeText(Main2Activity.this, "a" + scrollPercent, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(true);
    }
}
