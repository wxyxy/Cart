package com.bwei.yklianxi.presenter;

import com.bwei.yklianxi.bean.ShopBean;
import com.bwei.yklianxi.model.CartModel;
import com.bwei.yklianxi.model.CartMoudelCallBack;
import com.bwei.yklianxi.view.CartViewListener;

/**
 * Created by hp on 2017/11/22.
 */
public class CartPresenter {
    private CartViewListener listener;
    private CartModel cartModel;

    public CartPresenter(CartViewListener listener) {
        this.listener = listener;
        this.cartModel = new CartModel();
    }


    public void getData() {
        cartModel.getData(new CartMoudelCallBack() {
            @Override
            public void success(ShopBean bean) {
                if (listener != null) {
                    listener.success(bean);
                }
            }

            @Override
            public void failure(Exception e) {
                if (listener != null) {
                    listener.failure(e);
                }
            }
        });
    }

    public void detach() {
        listener = null;
    }
}
