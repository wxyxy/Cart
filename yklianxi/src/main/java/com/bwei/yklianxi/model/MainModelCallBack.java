package com.bwei.yklianxi.model;

import com.bwei.yklianxi.bean.SongBean;

/**
 * Created by hp on 2017/11/22.
 */
public interface MainModelCallBack {
    public void success(SongBean bean);
    public void failure(Exception e);
}
