package com.bwei.yklianxi.view;

import com.bwei.yklianxi.bean.SongBean;

/**
 * Created by hp on 2017/11/22.
 */
public interface MainViewListener {
    public void success(SongBean bean);
    public void failure(Exception e);
}
