package com.example.com.yk_zhlianxi_demo2;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView time;
    int i = 3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
          time.setText(msg.what+"S");
          if(msg.what==0){
              Intent it = new Intent(MainActivity.this,LoginActivity.class);
              startActivity(it);
          }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.ttime);
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (i>0){
                i--;
                    try {
                        sleep(1000);
                       handler.sendEmptyMessage(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
