package com.bwei.yklianxi.view;

import com.bwei.yklianxi.bean.ShopBean;

/**
 * Created by hp on 2017/11/22.
 */
public interface CartViewListener {
    public void success(ShopBean bean);
    public void failure(Exception e);
}
