package com.bwei.secondlevelcart.model;

import com.bwei.secondlevelcart.bean.ShopBean;

/**
 * Created by hp on 2017/11/21.
 */
public interface MainModelCallBack {
    public void success(ShopBean bean);
    public void failure(Exception e);
}
