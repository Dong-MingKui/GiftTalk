package com.dongkui.gifttalk.utils.minterface;

/**
 * Created by dllo on 16/9/26.
 */
public interface Pullable {
    /**
     *  判断是否可以下拉, 如果不需要下来功能可以直接return false
     * @return true 可以下拉 否则返回false
     */
    boolean canPullDown();

    /**
     *  判断是否可以上拉, 如果不需要上拉功能可以直接安徽
     * @return true可以上拉刷新 否则返回false
     */
    boolean canPullUp();
}
