package com.example.com.lunbodata;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.lunbodata.Bean.ImgBean;
import com.example.com.lunbodata.Bean.UserBean;
import com.example.com.lunbodata.Utils.MyTask;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshScrollView psv;
    private ViewPager pager;
    private MlistView mlist;
    private int url = 1;
    private int type = 1;
    private Mybase base;
    private  List<ImgBean.DataBean> datas;
    private List<UserBean.DataBean> list = new ArrayList<>();
    private String path = "https://www.zhaoapi.cn/product/searchProducts?keywords=%E6%89%8B%E6%9C%BA&source=android&page="+url;
    private String imgpath = "https://www.zhaoapi.cn/quarter/getAd";
    private LinearLayout line;
    private List<ImageView> imgList = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = pager.getCurrentItem();
            item++;
            pager.setCurrentItem(item);
            setIndex(item%imgList.size());
            sendEmptyMessageDelayed(1,1000);
        }
    };

    private void setIndex(int index) {
        for (int i = 0; i < imgList.size(); i++) {
            if(i==index){
                imgList.get(i).setImageResource(R.drawable.select);
            }else{
                imgList.get(i).setImageResource(R.drawable.unselect);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        psv = findViewById(R.id.psv);
        pager = findViewById(R.id.viewPager);
        mlist = findViewById(R.id.mlist);
        line = findViewById(R.id.line);

        initPsv();
        getdata();
        getimg();

        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeMessages(1,null);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        handler.removeMessages(1,null);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(1,1000);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        handler.sendEmptyMessageDelayed(1,1000);
                        break;
                }
                return true;
            }
        });
    }

    //调用图片
    private void getimg() {
        MyTask myTask = new MyTask(new MyTask.Iloveyou() {
            @Override
            public void getdata(String s) {
            Gson gson = new Gson();
                datas = gson.fromJson(s, ImgBean.class).getData();
                pager.setAdapter(new Mypager());
                initPager();
                pager.setCurrentItem(datas.size()*10);
                handler.sendEmptyMessageDelayed(1,1000);
            }
        });
        myTask.execute(imgpath);
    }

    private void initPager() {
        for (int i = 0; i < datas.size(); i++) {
        ImageView imageView = new ImageView(MainActivity.this);
        if(i==0){
            imageView.setImageResource(R.drawable.select);
        }else{
            imageView.setImageResource(R.drawable.unselect);
        }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,15,0);
            line.addView(imageView,params);
            imgList.add(imageView);

        }
    }

    //调用
    private void getdata() {
        final MyTask myTask = new MyTask(new MyTask.Iloveyou() {
            @Override
            public void getdata(String s) {
                Gson gson = new Gson();
                List<UserBean.DataBean> data = gson.fromJson(s, UserBean.class).getData();
                if(type==1){
                    list.clear();
                }
                list.addAll(data);
                setAdpter();
                psv.onRefreshComplete();

            }
        }); myTask.execute(path);
    }

    //设置适配
    private void setAdpter() {
        if(base==null){
            mlist.setAdapter(new Mybase());
        }else{
            base.notifyDataSetChanged();
        }
    }

    //获取
    private void initPsv() {
        psv.setMode(PullToRefreshBase.Mode.BOTH);
        psv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
           url = 1;
           type = 1;
                path = "https://www.zhaoapi.cn/product/searchProducts?keywords=%E6%89%8B%E6%9C%BA&source=android&page="+url;
                getdata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                url ++;
                type = 2;
                path = "https://www.zhaoapi.cn/product/searchProducts?keywords=%E6%89%8B%E6%9C%BA&source=android&page="+url;
                getdata();
            }
        });
    }
    //适配器
    class Mybase extends BaseAdapter{

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//使用内存缓存
                .cacheOnDisk(true)//使用磁盘缓存
                .showImageOnFail(R.mipmap.ic_launcher)//下载失败时显示的图片
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片色彩模式
                .imageScaleType(ImageScaleType.EXACTLY)//设置图片的缩放模式
                .build();

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
           ViewHodler hodler;
           if(view==null){
               view = View.inflate(MainActivity.this,R.layout.mybase,null);
               hodler = new ViewHodler();
               hodler.image = view.findViewById(R.id.imageee);
               hodler.title = view.findViewById(R.id.titleee);
               hodler.price = view.findViewById(R.id.priceee);
               view.setTag(hodler);
           }else{
               hodler = (ViewHodler) view.getTag();
           }
            ImageLoader.getInstance().displayImage(list.get(i).getImages().split("\\|")[0],hodler.image,options);
            hodler.title.setText(list.get(i).getTitle());
            hodler.price.setText("￥"+(float)list.get(i).getPrice()+"0");
            return view;
        }
        class ViewHodler{
            ImageView image;
            TextView title,price;
        }
    }
    //pager适配器
    class Mypager extends PagerAdapter{

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//使用内存缓存
                .cacheOnDisk(true)//使用磁盘缓存
                .showImageOnFail(R.mipmap.ic_launcher)//下载失败时显示的图片
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片色彩模式
                .imageScaleType(ImageScaleType.EXACTLY)//设置图片的缩放模式
                .build();

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.getInstance().displayImage(datas.get(position%datas.size()).getIcon(),imageView,options);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, datas.get(position%datas.size()).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
