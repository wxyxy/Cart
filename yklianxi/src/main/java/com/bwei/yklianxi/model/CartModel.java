package com.bwei.yklianxi.model;

import com.bwei.yklianxi.bean.ShopBean;
import com.bwei.yklianxi.okhttp.AbstractUiCallBack;
import com.bwei.yklianxi.okhttp.OkhttpUtils;

/**
 * Created by hp on 2017/11/22.
 */
public class CartModel {
    public void getData(final CartMoudelCallBack callBack){
        String path = "http://120.27.23.105/product/getCarts?uid=100";
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<ShopBean>() {
            @Override
            public void success(ShopBean bean) {
                callBack.success(bean);
            }

            @Override
            public void failure(Exception e) {
                callBack.failure(e);
            }
        });

    }
}
