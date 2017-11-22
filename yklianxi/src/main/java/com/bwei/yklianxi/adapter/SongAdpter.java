package com.bwei.yklianxi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.yklianxi.R;
import com.bwei.yklianxi.bean.SongBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by hp on 2017/11/22.
 */
public class SongAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SongBean.SongListBean> list;

    public SongAdpter(Context context, List<SongBean.SongListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.music_item, null);
        MyViewHodler holder = new MyViewHodler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //绑定数据
        ImageLoader.getInstance().displayImage(list.get(position).getPic_big(), ((MyViewHodler) holder).music_pic);
        ((MyViewHodler) holder).music_title.setText(list.get(position).getTitle());
        ((MyViewHodler) holder).music_name.setText(list.get(position).getArtist_name());
        //点击条目跳转
        ((MyViewHodler) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHodler extends RecyclerView.ViewHolder {
        private ImageView music_pic;
        private TextView music_title;
        private TextView music_name;

        public MyViewHodler(View itemView) {
            super(itemView);
            music_pic = itemView.findViewById(R.id.music_pic);
            music_title = itemView.findViewById(R.id.music_title);
            music_name = itemView.findViewById(R.id.music_name);
        }
    }

    private onClickListener listener;
    public void setClickListener(onClickListener listener) {
        this.listener = listener;
    }
    public interface onClickListener {
        void click(View view, int postion);
    }
}
