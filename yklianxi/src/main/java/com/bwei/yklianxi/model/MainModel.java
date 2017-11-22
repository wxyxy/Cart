package com.bwei.yklianxi.model;

import com.bwei.yklianxi.bean.SongBean;
import com.bwei.yklianxi.okhttp.AbstractUiCallBack;
import com.bwei.yklianxi.okhttp.OkhttpUtils;

/**
 * Created by hp on 2017/11/22.
 */
public class MainModel {
    public void getData(final MainModelCallBack callBack) {
        String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0";
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<SongBean>() {
            @Override
            public void success(SongBean bean) {
                callBack.success(bean);
            }

            @Override
            public void failure(Exception e) {
                callBack.failure(e);
            }
        });
    }
}
