package com.bwei.cartokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.cartokhttp.adapter.MyAdapter;
import com.bwei.cartokhttp.bean.DataBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView hj;
    TextView zs;
    private CheckBox check;
    private List<DataBean.OrderDataBean.CartlistBean> listc = new ArrayList<>();
    MyAdapter adapter;
    private int price;
    boolean select = true;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        check = (CheckBox) findViewById(R.id.checkbox);
        hj = (TextView) findViewById(R.id.hj);
        zs = (TextView) findViewById(R.id.zs);
        getData();
        //创建线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //调取适配器
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.add(listc);
        //全选按钮点击
        check.setTag(1);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = (Integer) check.getTag();
                if(tag == 1){
                    check.setTag(2);
                    select = true;
                    check.setChecked(true);
                } else {
                    check.setTag(1);
                    select = false;
                    check.setChecked(false);
                }
                for (DataBean.OrderDataBean.CartlistBean bean : listc) {
                    bean.setCheck(select);
                }
                adapter.notifyDataSetChanged();
                sum(adapter.getList());
            }
        });
        adapter.setCheckBoxListener(new MyAdapter.CheckBoxListener() {
            @Override
            public void check(int position, int count, boolean check, List<DataBean.OrderDataBean.CartlistBean> list) {
                sum(list);
            }
        });
        adapter.setCustomViewListener(new MyAdapter.CustomViewListener() {
            @Override
            public void click(int count, List<DataBean.OrderDataBean.CartlistBean> list) {
                sum(list);
            }
        });
        adapter.setDelListener(new MyAdapter.DelListener() {
            @Override
            public void del(int position, List<DataBean.OrderDataBean.CartlistBean> list) {
                sum(list);
            }
        });
    }
    /**
     * 计算总价
     */
    private void sum(List<DataBean.OrderDataBean.CartlistBean> list) {
        price = 0;
        count = 0;
        boolean allCheck = true;
        for (DataBean.OrderDataBean.CartlistBean bean : list) {
            if (bean.isCheck()) {
                //得到总价
                price += bean.getPrice() * bean.getCount();
                //得到商品个数
                count += bean.getCount();
            } else {
                // 只要有一个商品未选中，全选按钮 应该设置成 为选中
                allCheck = false;
            }
        }
        hj.setText("总价: " + price);
        zs.setText("共" + count + "件商品");
        if (allCheck) {
            check.setTag(2);
        } else {
            check.setTag(1);
        }

    }

    private void getData() {
        try {
            //模拟网络请求
            InputStream inputStream = getAssets().open("shop.json");
            String data = convertStreamToString(inputStream);
            Gson gson = new Gson();
            DataBean bean = gson.fromJson(data, DataBean.class);
            System.out.println("请求成功" + bean.toString());
            for (int i = 0; i < bean.getOrderData().size(); i++) {
                int length = bean.getOrderData().get(i).getCartlist().size();
                for (int j = 0; j < length; j++) {
                    listc.add(bean.getOrderData().get(i).getCartlist().get(j));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
