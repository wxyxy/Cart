package com.bwei.recycleviewcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.recycleviewcart.R;
import com.bwei.recycleviewcart.bean.Bean;

/**
 * Created by hp on 2017/11/18.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private Context context;
    private Bean bean;

    public RecycleAdapter(Context context, Bean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_item,null);
        MyTwoViewHolder holder = new MyTwoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bean.getData().size();
    }

    static class MyTwoViewHolder extends RecyclerView.ViewHolder{

        private ImageView item_tp;
        private TextView item_title, item_price;
        private CheckBox checkbox;
        private TextView customView;
        private Button del;

        public MyTwoViewHolder(View itemView) {
            super(itemView);
            item_tp = itemView.findViewById(R.id.item_tp);
            item_title = itemView.findViewById(R.id.item_title);
            item_price = itemView.findViewById(R.id.item_price);
            checkbox = itemView.findViewById(R.id.checkbox);
            customView = itemView.findViewById(R.id.item_num);
            del = itemView.findViewById(R.id.del);
        }
    }
}
