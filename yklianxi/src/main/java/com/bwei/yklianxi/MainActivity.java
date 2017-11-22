package com.bwei.yklianxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwei.yklianxi.adapter.SongAdpter;
import com.bwei.yklianxi.bean.SongBean;
import com.bwei.yklianxi.presenter.MainPresenter;
import com.bwei.yklianxi.view.MainViewListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewListener {

    RecyclerView recyclerView;
    MainPresenter presenter;
    SongAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rl);
        presenter = new MainPresenter(this);
        presenter.getData();
    }

    @Override
    public void success(SongBean bean) {
        List<SongBean.SongListBean> list = bean.getSong_list();
        adpter = new SongAdpter(this, list);
        System.out.println("数据为" + list.toString());
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adpter);
        //点击条目跳转页面
        adpter.setClickListener(new SongAdpter.onClickListener() {
            @Override
            public void click(View view, int postion) {
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
