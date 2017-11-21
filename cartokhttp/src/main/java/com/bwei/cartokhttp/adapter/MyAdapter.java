package com.bwei.cartokhttp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.cartokhttp.R;
import com.bwei.cartokhttp.bean.DataBean;
import com.bwei.cartokhttp.view.CustomView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2017/11/16.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DataBean.OrderDataBean.CartlistBean> list;

    public MyAdapter(Context context) {
        this.context = context;
    }


    public void add(List<DataBean.OrderDataBean.CartlistBean> list) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_item, null);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((MyViewHolder) holder).checkbox.setChecked(list.get(position).isCheck());
        Glide.with(context).load(list.get(position).getDefaultPic()).into(((MyViewHolder) holder).item_tp);
        ((MyViewHolder) holder).item_title.setText(list.get(position).getProductName());
        ((MyViewHolder) holder).item_price.setText("￥" + list.get(position).getPrice());
        ((MyViewHolder) holder).customView.setEditText(list.get(position).getCount());
        //checkbox点击
        ((MyViewHolder) holder).checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setCheck(((MyViewHolder) holder).checkbox.isChecked());
                notifyDataSetChanged();

                if (checkBoxListener != null) {
                    checkBoxListener.check(position,
                            ((MyViewHolder) holder).customView.getCurrentCount(),
                            ((MyViewHolder) holder).checkbox.isChecked(),
                            list);
                }
            }
        });

        //加减号点击
        ((MyViewHolder) holder).customView.setListener(new CustomView.ClickListener() {
            @Override
            public void click(int count) {
                list.get(position).setCount(count);
                notifyDataSetChanged();
                if(listener != null){
                    listener.click(count,list);
                }
            }
        });
        //删除点击
        ((MyViewHolder) holder).del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
                if(delListener != null){
                    delListener.del(position,list);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView item_tp;
        private TextView item_title, item_price;
        private CheckBox checkbox;
        private CustomView customView;
        private Button del;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_tp = itemView.findViewById(R.id.item_tp);
            item_title = itemView.findViewById(R.id.item_title);
            item_price = itemView.findViewById(R.id.item_price);
            checkbox = itemView.findViewById(R.id.checkbox);
            customView = itemView.findViewById(R.id.item_num);
            del = itemView.findViewById(R.id.del);
        }
    }

    public List<DataBean.OrderDataBean.CartlistBean> getList() {
        return list;
    }

    CheckBoxListener checkBoxListener;

    /**
     * checkbox 点击事件
     * @param listener
     */

    public void setCheckBoxListener(CheckBoxListener listener) {
        this.checkBoxListener = listener;
    }

    public interface CheckBoxListener {
        public void check(int position, int count, boolean check, List<DataBean.OrderDataBean.CartlistBean> list);
    }

    CustomViewListener listener;

    /**
     * 加减号 点击事件
     * @param listener
     */
    public void setCustomViewListener(CustomViewListener listener){
        this.listener = listener;
    }
    public interface CustomViewListener {
        public void click(int count, List<DataBean.OrderDataBean.CartlistBean> list);
    }

    DelListener delListener ;
    /**
     * 删除按钮事件
     * @param listener
     */
    public void setDelListener(DelListener listener) {
        this.delListener = listener;
    }
    public interface DelListener {
        public void del(int position, List<DataBean.OrderDataBean.CartlistBean> list);
    }
}
