package com.example.com.lunbodata.Utils;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by 老赵的拯救者 on 2018/5/2.
 */

public class MyTask extends AsyncTask<String,Void,String>{

    private Iloveyou iloveyou;

    public MyTask(Iloveyou iloveyou) {
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
        iloveyou.getdata(s);
    }

    public interface Iloveyou{
        void getdata(String s);
    }
}
