package com.example.com.yk_zhlianxi_demo1.Utils;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by 老赵的拯救者 on 2018/5/2.
 */

public class MyAsynTask extends AsyncTask<String,Void,String> {
    //2、得到接口对象
    private Iloveyou iloveyou;
    //3、构造
    public MyAsynTask(Iloveyou iloveyou) {
        this.iloveyou = iloveyou;
    }
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
        //4、调用
        iloveyou.getdata(s);
    }
    //1、定义一个接口
    public interface Iloveyou{
        void getdata(String data);
    }
}
