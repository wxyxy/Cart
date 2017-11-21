package com.bwei.recycleviewcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwei.recycleviewcart.MainActivity;
import com.bwei.recycleviewcart.R;

import java.util.List;
import java.util.Random;

/**
 * Created by hp on 2017/11/18.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> list;

    public MyAdapter(MainActivity context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_adapter, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position))
                .placeholder(R.drawable.h).into(((MyViewHolder) holder).tp);

        ((MyViewHolder) holder).tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.check(position,view);
                }
            }

        });

        ViewGroup.LayoutParams params = ((MyViewHolder) holder).tp.getLayoutParams();
        int itemHeight = 300;
        itemHeight = new Random().nextInt(500);
        if (itemHeight < 300) {
            itemHeight = 300;
        }
        params.height = itemHeight;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView tp;

        public MyViewHolder(View itemView) {
            super(itemView);
            tp = itemView.findViewById(R.id.tp);
        }
    }


    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        public void check(int position, View view);
    }
}
