package com.bwei.secondlevelcart.view;

import com.bwei.secondlevelcart.bean.ShopBean;

/**
 * Created by hp on 2017/11/21.
 */
public interface MainViewListener {
    public void success(ShopBean bean);

    public void failure(Exception e);
}
