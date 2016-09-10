package com.dongkui.gifttalk.model.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dongkui.gifttalk.controller.app.MyApp;

/**
 * Created by dllo on 16/9/9.
 * Volley 设计模式: 使用单例
 * Volley 使用于轻量级的数据请求
 * 应用场景:购物,展示类需要频繁刷新数据的应用
 * <p/>
 * Volley 的使用步骤
 * 1.添加依赖 the4floor-Android-volley
 * 2.定义网络请求队列
 * 3.定义请求, 请求方法.网址,成功监听
 */
public class VolleyInstance {
    /**
     *  单例模式:保证系统中一个类只有一个实例
     *
     *  要点:*1.某个类只能有一个实例
     *      *2.他必须自行创建这个类
     *      *3.他必须自行向整个系统提供这个实例
     *  优点:*1.实例控制: 确保所有对象都访问唯一实例
     *      *2.灵活性: 因为实例控制,所有类可以灵活更改实例化过程
     *      *3.节省空间,避免多线程访问对象冲突
     *  缺点:*1.开销:虽然数量很少,但如果每次对象请求引用是都要检查是否存在类的实例,将仍然需要一些开销.可以通过使用静态初始化解决此问题
     *      *2.可能的开发混淆: 开发人员必须记住自己不能使用new关键字实例化对象!
     *      *3.对象生存期: 不能解决删除单个对象的问题(不太懂!)
     *
     *  单例的七种形式:*1.懒汉(线程不安全)只检查了本类是否有实例化 多线程不能正常工作
     *              *2.懒汉(线程安全) 使用了synchronized,不过效率低99%的情况下不需要同步
     *              *3.饿汉 ?
     *              *4.饿汉(变种)?
     *              *5.静态内部类/
     *              *6.枚举 enum?
     *              *7.双重检验锁:我们就用这个
     *
     *  需要使用单例的部分: 线程池, 网络, 数据库, sp, 下载等.
     *
     *  常用命名:XXInstance XXSingleton
     */

    /**
     * 单例的写法(双重校验锁)
     * 1.私有化构造方法: 外部不能调用构造方法随意的创在对象
     * 2.对外提获取去对象的方法
     * *1.定义静态当前类对象
     * *2.对外提供获取方法: 进行单例判断
     */

    // 2-1
    private static VolleyInstance instance;
    // 定义请求队列
    private RequestQueue requestQueue;

    //1.私用的构造方法
    private VolleyInstance() {
        requestQueue = Volley.newRequestQueue(MyApp.getContext());
    }

    //2-2
    public static VolleyInstance getInstance() {
        // 如果该对象是null
        if (instance == null) {
            // 全部线程扫描
            synchronized (VolleyInstance.class) {
                if (instance == null) {
                    instance = new VolleyInstance();
                }
            }
        }
        return instance;
    }


    /***************************************************************************/
    // 对外提供方法
    public void startRequest(String url, final onVolleyResult result) {
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 如果请求成功将返回数据存储到接口
                result.success(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 请求失败,通知接口通知调用者请求失败
                result.failure();
            }
        });
        requestQueue.add(sr);
    }

}
