package com.bwei.secondlevelcart.model;

import com.bwei.secondlevelcart.bean.ShopBean;
import com.bwei.secondlevelcart.okhttp.AbstractUiCallBack;
import com.bwei.secondlevelcart.okhttp.OkhttpUtils;

/**
 * Created by hp on 2017/11/21.
 */
public class MainModel {

    public void getData(final MainModelCallBack callBack) {
        OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/getCarts?uid=100", new AbstractUiCallBack<ShopBean>() {
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
