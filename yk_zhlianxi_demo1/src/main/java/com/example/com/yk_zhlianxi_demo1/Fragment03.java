package com.example.com.yk_zhlianxi_demo1;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.com.yk_zhlianxi_demo1.Utils.MyAsynTask;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
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

public class Fragment03 extends Fragment{

    private PullToRefreshGridView pgv;
    private int url = 10;
    private int type = 1;
    private F3_ImgBase base;
    private List<FourBean.NewslistBean> list = new ArrayList<>();
    private String path = "http://api.tianapi.com/meinv/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment03, container, false);

        pgv = v.findViewById(R.id.pgv);
        initPgv();
        getdata();
        return v;
    }

    //初始化pgv
    private void initPgv() {
        pgv.setMode(PullToRefreshBase.Mode.BOTH);
        pgv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                url = 10;
                type = 1;
                path = "http://api.tianapi.com/meinv/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
                getdata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                url ++;
                type = 2;
                path = "http://api.tianapi.com/meinv/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
                getdata();
            }
        });
    }

    //调用
    private void getdata() {
        MyAsynTask task = new MyAsynTask(new MyAsynTask.Iloveyou() {
            @Override
            public void getdata(String s) {
                Gson gson = new Gson();
                FourBean fourBean = gson.fromJson(s, FourBean.class);
                List<FourBean.NewslistBean> news = fourBean.getNewslist();
                if(type==1){
                    list.clear();
                }
                list.addAll(news);
                setAdpter();
                pgv.onRefreshComplete();
            }
        });
    task.execute(path);
    }
    //设置适配器
    private void setAdpter() {
    if(base==null){
        pgv.setAdapter(new F3_ImgBase());
    }else{
        base.notifyDataSetChanged();
    }
    }
    class F3_ImgBase extends BaseAdapter{

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
                view = View.inflate(getActivity(),R.layout.mybase3,null);
                hodler = new ViewHodler();
                hodler.f3_iamge = view.findViewById(R.id.f3_image);
                view.setTag(hodler);
            }else{
                hodler = (ViewHodler) view.getTag();
            }
            ImageLoader.getInstance().displayImage(list.get(i).getPicUrl(),hodler.f3_iamge,options);
            return view;
        }
        class ViewHodler{
            ImageView f3_iamge;
        }
    }
}
