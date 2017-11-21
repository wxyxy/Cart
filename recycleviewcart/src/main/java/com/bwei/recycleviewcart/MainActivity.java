package com.bwei.recycleviewcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.bwei.recycleviewcart.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String tp1 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2505994810,3183814644&fm=11&gp=0.jpg";
    String tp2 = "ttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974953941&di=33361d9cede80679480211707da40c01&imgtype=0&src=http%3A%2F%2Fi6.qhimg.com%2Ft0139931cf3f9f21c6f.jpg";
    String tp3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974953943&di=3503a16a9818e14d49f7eb247a054087&imgtype=0&src=http%3A%2F%2Fpic24.nipic.com%2F20121012%2F3822951_153451269000_2.jpg";
    String tp4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974991828&di=abb537d2177bba31a34ca155f8e3c89c&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D3046105272%2C3838180666%26fm%3D214%26gp%3D0.jpg";
    String tp5 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974953940&di=2d458edae7601695308b5c03c831aa2b&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1111%2F10%2Fc9%2F9572429_9572429_1320917644828.jpg";
    String tp6 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974954211&di=052f76ae4411889dbbcc3f69783ba75c&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fd62a6059252dd42aa4d1884d093b5bb5c8eab878.jpg";
    String tp7 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974953939&di=aecb810eedb54b45bab0432d2c8de5c6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F902397dda144ad3424495453daa20cf430ad8574.jpg";
    String tp8 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974953938&di=25fefc27e1a731bdbac6d6a73af87d77&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4a36acaf2edda3ccc9cc35190ae93901213f92db.jpg";
    String tp9 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974953937&di=07367584b2982eb921ecb6a690bc5cde&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fbaike%2Fpic%2Fitem%2Fa6efce1b9d16fdfad5bde615b48f8c5495ee7b73.jpg";
    String tp10 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510974953937&di=fb452201b5f4cf26153b0114c10011a7&imgtype=0&src=http%3A%2F%2Fpic.lvmama.com%2Fuploads%2Fpc%2Fplace2%2F2014-10-22%2F1413961687755.jpg";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rl);

        List<String> list = new ArrayList<>();

        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2505994810,3183814644&fm=11&gp=0.jpg");

        list.add(tp1);list.add(tp2);list.add(tp3);list.add(tp4);list.add(tp5);
        list.add(tp6);list.add(tp7);list.add(tp8);list.add(tp9);list.add(tp10);
        list.add(tp1);list.add(tp2);list.add(tp3);list.add(tp4);list.add(tp5);
        list.add(tp6);list.add(tp7);list.add(tp8);list.add(tp9);list.add(tp10);
        list.add(tp1);list.add(tp2);list.add(tp3);list.add(tp4);list.add(tp5);
        list.add(tp6);list.add(tp7);list.add(tp8);list.add(tp9);list.add(tp10);
        list.add(tp1);list.add(tp2);list.add(tp3);list.add(tp4);list.add(tp5);
        list.add(tp6);list.add(tp7);list.add(tp8);list.add(tp9);list.add(tp10);
        list.add(tp1);list.add(tp2);list.add(tp3);list.add(tp4);list.add(tp5);
        list.add(tp6);list.add(tp7);list.add(tp8);list.add(tp9);list.add(tp10);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));

        MyAdapter adapter = new MyAdapter(MainActivity.this, list);
        //点击图片跳转到购物车页面
        adapter.setOnClickListener(new MyAdapter.OnClickListener() {
            @Override
            public void check(int position, View view) {
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
