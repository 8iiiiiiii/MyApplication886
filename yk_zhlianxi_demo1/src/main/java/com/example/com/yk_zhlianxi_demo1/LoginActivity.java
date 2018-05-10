package com.example.com.yk_zhlianxi_demo1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.yk_zhlianxi_demo1.Utils.MyAsynTask;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private List<Fragment> flist = new ArrayList<>();
    private RadioGroup group;
    private PullToRefreshListView plv;
    private int url = 10;
    private int type = 1;
    private MyBase base;
    private List<OneBean.NewslistBean> list = new ArrayList<>();
    private String path = "http://api.tianapi.com/it/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
    private ImageView img;
    private DrawerLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        group = findViewById(R.id.group);
        plv = findViewById(R.id.plv);
        img = findViewById(R.id.cd_image);
        login = findViewById(R.id.login);

        //默认显示的Fragment
        setFragment(new Fragment01());
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.but01:
                       setFragment(new Fragment01());
                        break;
                    case R.id.but02:
                        setFragment(new Fragment02());
                        break;
                    case R.id.but03:
                        setFragment(new Fragment03());
                        break;
                    case R.id.but04:
                        setFragment(new Fragment04());
                        break;
                }
            }
        });

        //点击切换
        plv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                login.closeDrawer(Gravity.START);
                setFragment(new Fragment04());
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.closeDrawer(Gravity.START);
                setFragment(new Fragment04());
            }
        });

        initPlv();
        getdata();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }

    //初始化PLV
    private void initPlv() {
        plv.setMode(PullToRefreshBase.Mode.BOTH);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
            url = 10;
            type = 1;
                path = "http://api.tianapi.com/it/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
                getdata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                url ++;
                type = 2;
                path = "http://api.tianapi.com/it/?key=0678218dc644ad08e9108a02bdf21d19&num="+url;
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
    //设置适配器
    private void setAdpter() {
        /*List<String> titleList = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            String title = list.get(i).getTitle();
            titleList.add(title);
        }
        ArrayAdapter<String> base = new ArrayAdapter<String>(LoginActivity.this,android.R.layout.simple_expandable_list_item_1,titleList);
            plv.setAdapter(base);*/
        if(base==null){
            plv.setAdapter(new MyBase());
        }else{
            base.notifyDataSetChanged();
        }

    }

    //适配器
    class MyBase extends BaseAdapter{
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
                view= View.inflate(LoginActivity.this,R.layout.mybase,null);
                hodler = new ViewHodler();
                hodler.plv_title = view.findViewById(R.id.plv_title);
                view.setTag(hodler);
            }else{
                hodler = (ViewHodler) view.getTag();
            }
            hodler.plv_title.setText(list.get(i).getTitle());
            return view;
        }
        class ViewHodler{
            TextView plv_title;
        }
    }

    //设置Fragment
    private void setFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
    }
}
