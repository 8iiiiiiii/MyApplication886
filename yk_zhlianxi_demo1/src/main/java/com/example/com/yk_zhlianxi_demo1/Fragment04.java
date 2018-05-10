package com.example.com.yk_zhlianxi_demo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.yk_zhlianxi_demo1.Utils.MyTask;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 老赵的拯救者 on 2018/4/27.
 */

public class Fragment04 extends Fragment{

    private PullToRefreshScrollView psv;
    private ViewPager pager;
    private LinearLayout line;
    private MListView mListView;
    private int url = 1;
    private int type = 1;
    private Mybase base;
    private List<ThreeBean.DataBean> imglist;
    private List<ImageView> img = new ArrayList<>();
    private Context context;

    private List<TwoBean.DataBean> list = new ArrayList<>();
    private String path = "https://www.zhaoapi.cn/product/searchProducts?keywords=%E6%89%8B%E6%9C%BA&source=android&page="+url;
    private String imgpath = "https://www.zhaoapi.cn/quarter/getAd";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = pager.getCurrentItem();
            item++;
            pager.setCurrentItem(item);
            setIndex(item%img.size());
            sendEmptyMessageDelayed(1,1000);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    //设置指示器
    private void setIndex(int index) {
        for (int i = 0; i < img.size(); i++) {
        if(index == i){
            img.get(i).setImageResource(R.drawable.select);
        }else{
            img.get(i).setImageResource(R.drawable.unselect);
        }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment04, container, false);
        psv = v.findViewById(R.id.psv);
        pager = v.findViewById(R.id.f4_pager);
        line = v.findViewById(R.id.line);
        mListView = v.findViewById(R.id.mlist);

        initPsv();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getdata();
        showImg();

        //按住轮播图暂停 松开继续自动轮播
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
                }
                return true;
            }
        });
    }

    //掉用轮播图
    private void showImg() {
        new ImgTask().execute(imgpath);
    }


    //初始化
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

    //调用
    private void getdata() {
        com.example.com.yk_zhlianxi_demo1.Utils.MyTask myTask = new com.example.com.yk_zhlianxi_demo1.Utils.MyTask(new com.example.com.yk_zhlianxi_demo1.Utils.MyTask.IfuckU() {
            @Override
            public void getdata(String s) {
                Gson gson = new Gson();
                List<TwoBean.DataBean> data = gson.fromJson(s, TwoBean.class).getData();
                if(type==1){
                    list.clear();
                }
                list.addAll(data);
                setAdpter();
                psv.onRefreshComplete();
            }
        });
        myTask.execute(path);
    }

    //设置适配器
    private void setAdpter() {
        if(base==null){
            mListView.setAdapter(new Mybase());
        }else{
            base.notifyDataSetChanged();
        }
    }

    //适配器
    class Mybase extends BaseAdapter {
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
                view = View.inflate(context,R.layout.mybase2,null);
                hodler = new ViewHodler();
                hodler.image = view.findViewById(R.id.zk_image);
                hodler.title = view.findViewById(R.id.zk_title);
                hodler.price = view.findViewById(R.id.zk_price);
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

    //获取img数据
    class ImgTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(strings[0]);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                if(response.getStatusLine().getStatusCode()==200){
                    String s = EntityUtils.toString(response.getEntity());
                    return s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            imglist = gson.fromJson(s, ThreeBean.class).getData();
            Log.d("zzz", "onPostExecute: "+imglist.size());
            MyPager ppp = new MyPager();
            pager.setAdapter(ppp);
            pager.setCurrentItem(imglist.size()*10);
            initPager();
            handler.sendEmptyMessageDelayed(1,1000);
        }
    }


    //初始化pager
    private void initPager() {

        for (int i = 0; i < imglist.size(); i++) {
            if(getActivity()==null){
                Log.d("zzz"," is null");
            }else {
                Log.d("zzz"," is not null");
            }
            ImageView imageView = new ImageView(context);
            if(i==0){
                imageView.setImageResource(R.drawable.select);
            }else{
                imageView.setImageResource(R.drawable.unselect);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,15,0);
            line.addView(imageView,params);
            img.add(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    //Pager适配器
    class MyPager extends PagerAdapter{

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
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            ImageLoader.getInstance().displayImage(imglist.get(position%imglist.size()).getIcon(),imageView,options);
            final String title = imglist.get(position%imglist.size()).getTitle();
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, ""+title, Toast.LENGTH_SHORT).show();
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
