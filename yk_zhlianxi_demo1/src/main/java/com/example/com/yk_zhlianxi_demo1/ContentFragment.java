package com.example.com.yk_zhlianxi_demo1;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.com.yk_zhlianxi_demo1.Utils.MyAsynTask;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 老赵的拯救者 on 2018/4/28.
 */

public class ContentFragment extends Fragment{

    private PullToRefreshListView plv;
    private int url = 10;
    private int type = 1;
    private String path;
    private String key;
    private Cont_MyBase base;
    private List<OneBean.NewslistBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content, container, false);
        plv = v.findViewById(R.id.cont_plv);
        Bundle b = getArguments();
        key = b.getString("key");
        path = "http://api.tianapi.com/"+key+"/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
        initPlv();
        getdata();
        return v;
    }

    //初始化PLV
    private void initPlv() {
        plv.setMode(PullToRefreshBase.Mode.BOTH);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                url = 10;
                type = 1;
                path = "http://api.tianapi.com/"+key+"/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
                getdata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                url ++;
                type = 2;
                path = "http://api.tianapi.com/"+key+"/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
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
                OneBean oneBean = gson.fromJson(s, OneBean.class);
                List<OneBean.NewslistBean> news = oneBean.getNewslist();
                if(type==1){
                    list.clear();
                }
                list.addAll(news);
                setAdpter();
                plv.onRefreshComplete();
            }
        });
       task.execute(path);
    }
    //获取数据
    class Cont_MyAsynTsk extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(strings[0]);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                if(response.getStatusLine().getStatusCode()==200){
                    String s = EntityUtils.toString(response.getEntity());
                    Log.d("kkk", "doInBackground: "+s);
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

        }
    }

    //设置适配器
    private void setAdpter() {
        if(base==null){
            plv.setAdapter(new Cont_MyBase());
        }else{
            base.notifyDataSetChanged();
        }

    }

    //适配器
    class Cont_MyBase extends BaseAdapter {

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
            Cont_ViewHodler hodler;
            if(view==null){
                view= View.inflate(getActivity(),R.layout.cont_base,null);
                hodler = new Cont_ViewHodler();
                hodler.cont_image = view.findViewById(R.id.cont_image);
                hodler.cont_title = view.findViewById(R.id.cont_title);
                hodler.cont_time = view.findViewById(R.id.cont_time);
                view.setTag(hodler);
            }else{
                hodler = (Cont_ViewHodler) view.getTag();
            }
            hodler.cont_title.setText(list.get(i).getTitle());
            hodler.cont_time.setText(list.get(i).getCtime());
            ImageLoader.getInstance().displayImage(list.get(i).getPicUrl(),hodler.cont_image,options);
            return view;
        }
        class Cont_ViewHodler{
            ImageView cont_image;
            TextView cont_title,cont_time;
        }
    }
}
