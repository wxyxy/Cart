package com.bwei.secondlevelcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.secondlevelcart.bean.ShopBean;
import com.bwei.secondlevelcart.presenter.MainPresenter;
import com.bwei.secondlevelcart.view.MainViewListener;

public class MainActivity extends AppCompatActivity implements MainViewListener {

    private MainPresenter presenter;
    private ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView thirdRecyclerview = (RecyclerView) findViewById(R.id.third_recyclerview);
        final CheckBox checkBoxAll = (CheckBox) findViewById(R.id.third_allselect);
        final TextView thirdTotalprice = (TextView) findViewById(R.id.third_totalprice);
        final TextView thirdTotalnum = (TextView) findViewById(R.id.third_totalnum);
        TextView thirdSubmit = (TextView) findViewById(R.id.third_submit);
        LinearLayout thirdPayLinear = (LinearLayout) findViewById(R.id.third_pay_linear);

        presenter = new MainPresenter(this);
        presenter.getData();

        adapter = new ShopAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        thirdRecyclerview.setLayoutManager(manager);
        thirdRecyclerview.setAdapter(adapter);


        adapter.setListener(new ShopAdapter.UpdateUiListener() {
            @Override
            public void setTotal(String total, String num,boolean allCheck) {

                checkBoxAll.setChecked(allCheck);
                thirdTotalnum.setText(num);
                thirdTotalprice.setText(total);
            }
        });

        checkBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.selectAll(checkBoxAll.isChecked());
            }
        });
    }

    @Override
    public void success(ShopBean bean) {
        adapter.add(bean);
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
