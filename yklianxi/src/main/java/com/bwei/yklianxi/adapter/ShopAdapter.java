package com.bwei.yklianxi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.yklianxi.R;
import com.bwei.yklianxi.bean.ShopBean;
import com.bwei.yklianxi.view.PlusView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/11/22.
 */
public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<ShopBean.DataBean.ListBean> list;
    private Map<String, String> map = new HashMap<>();

    public ShopAdapter(Context context) {
        this.context = context;
    }

    public void add(ShopBean bean) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        // 遍历商家
        for (ShopBean.DataBean shop : bean.getData()) {
            map.put(shop.getSellerid(), shop.getSellerName());
            // 遍历商品
            for (int i = 0; i < shop.getList().size(); i++) {
                this.list.add(shop.getList().get(i));
            }
        }
        setFirst(this.list);
        notifyDataSetChanged();
    }

    /**
     * 设置数据源， 控制显示商家
     * @param list
     */
    private void setFirst(List<ShopBean.DataBean.ListBean> list) {

        if (list.size() > 0) {
            list.get(0).setIsFirst(1);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).getSellerid() == list.get(i - 1).getSellerid()) {
                    list.get(i).setIsFirst(2);
                } else {
                    list.get(i).setIsFirst(1);
                }
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        // 显示商品图片
        if (list.get(position).getIsFirst() == 1) {
            //显示商家
            ((MyViewHolder) holder).shop_checkbox.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).tvItemShopcartShopname.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).shop_checkbox.setChecked(list.get(position).isShopSelected());
//            显示商家的名称
//            list.get(position).getSellerid() 取到商家的id
//            map.get（）取到 商家的名称
            ((MyViewHolder) holder).tvItemShopcartShopname.setText(map.get(String.valueOf(list.get(position).getSellerid())));
        } else {
            ((MyViewHolder) holder).shop_checkbox.setVisibility(View.GONE);
            ((MyViewHolder) holder).tvItemShopcartShopname.setVisibility(View.GONE);
        }
        //控制 商品的  checkbox
        ((MyViewHolder) holder).item_checkbox.setChecked(list.get(position).isItemSelected());
        String[] url = list.get(position).getImages().split("\\|");
        ImageLoader.getInstance().displayImage(url[0], ((MyViewHolder) holder).item_pic);
        ((MyViewHolder) holder).item_name.setText(list.get(position).getTitle());
        ((MyViewHolder) holder).item_price.setText("总价："+list.get(position).getPrice());
        ((MyViewHolder) holder).plusViewId.setEditText(list.get(position).getNum());
        // 商家的checkbox
        ((MyViewHolder) holder).shop_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setShopSelected(((MyViewHolder) holder).shop_checkbox.isChecked());
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(position).getSellerid() == list.get(i).getSellerid()) {
                        list.get(i).setItemSelected(((MyViewHolder) holder).shop_checkbox.isChecked());
                    }
                }
                notifyDataSetChanged();
                sum(list);
            }
        });
        // 商品的checkbox
        ((MyViewHolder) holder).item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setItemSelected(((MyViewHolder) holder).item_checkbox.isChecked());
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(i).getSellerid() == list.get(j).getSellerid() && !list.get(j).isItemSelected()) {
                            list.get(i).setShopSelected(false);
                            break;
                        } else {
                            list.get(i).setShopSelected(true);
                        }
                    }
                }
                notifyDataSetChanged();
                sum(list);
            }
        });
        ((MyViewHolder) holder).item_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                setFirst(list);
                notifyDataSetChanged();
                sum(list);
            }
        });
        //加减号
        ((MyViewHolder) holder).plusViewId.setListener(new PlusView.ClickListener() {
            @Override
            public void click(int count) {
                list.get(position).setNum(count);
                notifyDataSetChanged();
                sum(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private void sum(List<ShopBean.DataBean.ListBean> list) {
        int totalNum = 0;
        float totalMoney = 0.0f;
        boolean allCheck = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isItemSelected()) {
                totalNum += list.get(i).getNum();
                totalMoney += list.get(i).getNum() * list.get(i).getPrice();
            } else {
                allCheck = false;
            }
        }
        listener.setTotal(totalMoney + "", totalNum + "", allCheck);
    }

    public void selectAll(boolean check) {

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setShopSelected(check);
            list.get(i).setItemSelected(check);
        }
        notifyDataSetChanged();
        sum(list);

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private CheckBox shop_checkbox;
        private TextView tvItemShopcartShopname;
        private LinearLayout llShopcartHeader;
        private CheckBox item_checkbox;
        private ImageView item_pic;
        private TextView item_price;
        private TextView item_name;
        private TextView tvItemShopcartClothSize;
        private PlusView plusViewId;
        private ImageView item_del;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            shop_checkbox = itemView.findViewById(R.id.shop_checkbox);
            tvItemShopcartShopname = itemView.findViewById(R.id.tv_item_shopcart_shopname);
            llShopcartHeader = itemView.findViewById(R.id.ll_shopcart_header);
            item_checkbox = itemView.findViewById(R.id.item_checkbox);
            item_pic = itemView.findViewById(R.id.item_pic);
            item_price = itemView.findViewById(R.id.item_price);
            item_name = itemView.findViewById(R.id.item_name);
            tvItemShopcartClothSize = itemView.findViewById(R.id.tv_item_shopcart_cloth_size);
            plusViewId = itemView.findViewById(R.id.plus_view_id);
            item_del = itemView.findViewById(R.id.item_del);
        }
    }

    public UpdateUiListener listener;

    public void setListener(UpdateUiListener listener) {
        this.listener = listener;
    }

    public interface UpdateUiListener {
        public void setTotal(String total, String num, boolean allCheck);
    }
}
