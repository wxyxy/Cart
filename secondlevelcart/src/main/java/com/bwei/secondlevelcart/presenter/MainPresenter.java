package com.bwei.secondlevelcart.presenter;

import com.bwei.secondlevelcart.bean.ShopBean;
import com.bwei.secondlevelcart.model.MainModel;
import com.bwei.secondlevelcart.model.MainModelCallBack;
import com.bwei.secondlevelcart.view.MainViewListener;

/**
 * Created by hp on 2017/11/21.
 */
public class MainPresenter {

    private MainViewListener listener;
    private MainModel mainModel;

    public MainPresenter(MainViewListener listener) {
        this.listener = listener;
        this.mainModel = new MainModel();
    }

    public void getData() {
        mainModel.getData(new MainModelCallBack() {

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
