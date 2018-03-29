package com.zk.photoview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewPager mVp;
    private List<View> list = new ArrayList<>();
    private int[] imgs = {R.mipmap.ceshi, R.mipmap.ceshi2, R.mipmap.ceshi3, R.mipmap.ceshi4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        mVp = findViewById(R.id.mVp);
        mVp.setAdapter(new VPAdapter());


    }

    /**
     * 初始化数据源
     */
    private void initList() {

        for (int i = 0; i < imgs.length; i++) {
            View v = View.inflate(MainActivity.this, R.layout.adapter, null);
            PhotoView photoView = v.findViewById(R.id.photo_view);
            photoView.setImageResource(imgs[i]);
            list.add(photoView);
        }
    }

    /**
     * 适配器
     **/
    public class VPAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int p = position % list.size();
            //获取当前的照片
            PhotoView img = (PhotoView) list.get(p);
            //获取照片的父容器
            ViewParent parent = img.getParent();
            //判断父容器不是空的话,删除的当前位置的照片,进行下一步操作，增加新的照片
            if (parent != null) {
                ViewGroup vp = (ViewGroup) parent;
                vp.removeView(list.get(p));
            }
            container.addView(list.get(p));
            return list.get(p);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //		super.destroyItem(container, position, object);
//		container.removeView(list.get(position%list.size()));
        }
    }
}
