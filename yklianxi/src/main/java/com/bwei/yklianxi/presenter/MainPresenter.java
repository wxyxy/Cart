package com.bwei.yklianxi.presenter;

import com.bwei.yklianxi.bean.SongBean;
import com.bwei.yklianxi.model.MainModel;
import com.bwei.yklianxi.model.MainModelCallBack;
import com.bwei.yklianxi.view.MainViewListener;

/**
 * Created by hp on 2017/11/22.
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
            public void success(SongBean bean) {
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
