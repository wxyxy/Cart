package com.bwei.recycleviewcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.bwei.recycleviewcart.bean.Bean;
import com.bwei.recycleviewcart.utils.OkHttp3Utils;
import com.bwei.recycleviewcart.utils.OnUiCallback;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        initData();
    }

    private void initData() {
        String path = "http://120.27.23.105/product/getCarts?uid=100";
        OkHttp3Utils.doGet(path, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result,Bean.class);

            }
        });
    }
}
