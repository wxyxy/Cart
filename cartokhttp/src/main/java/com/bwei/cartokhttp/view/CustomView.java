package com.bwei.cartokhttp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.cartokhttp.R;


/**
 * Created by hp on 2017/11/15.
 */
public class CustomView extends LinearLayout{

    private EditText editText;
    private TextView revserse;
    private TextView add;
    private int mCount = 1 ;
    private int count;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.customview,null,false);

        revserse = (TextView) view.findViewById(R.id.r_r);
        add = (TextView)view.findViewById(R.id.add);
        editText = (EditText) view.findViewById(R.id.et);

        //减号点击事件
        revserse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String content =  editText.getText().toString().trim() ;
                    int count =  Integer.valueOf(content);

                    if(count > 1){
                        mCount = count - 1;
                        editText.setText(mCount+"");
                    }
                    if(listener != null){
                        listener.click(mCount);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        //加号点击事件
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String content =  editText.getText().toString().trim() ;
                    count = Integer.valueOf(content)+1;
                    mCount = count;
                    editText.setText(count +"");
                    if(listener != null){
                        listener.click(count);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        addView(view);
    }

    public  void setEditText(int count){
        editText.setText(count+"");
    }
    public int getCurrentCount(){
        return mCount ;
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //接口回调
    public ClickListener listener;

    public void setListener(ClickListener listener){
        this.listener = listener;
    }

    /**
     * 加减号 点击事件
     */
    public interface ClickListener {
        public void click(int count);
    }
}
