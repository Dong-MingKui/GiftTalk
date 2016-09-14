package com.dongkui.gifttalk.model.net;

/**
 * Created by dllo on 16/9/9.
 * Volley 请求队列的接口
 */
public interface OnVolleyResult {
    void success(String resultStr);

    void failure();
}
