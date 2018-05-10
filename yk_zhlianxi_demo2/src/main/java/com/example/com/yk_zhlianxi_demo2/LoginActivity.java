package com.example.com.yk_zhlianxi_demo2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.com.yk_zhlianxi_demo2.Fragment.Fragment01;
import com.example.com.yk_zhlianxi_demo2.Fragment.Fragment02;
import com.example.com.yk_zhlianxi_demo2.Fragment.Fragment03;

public class LoginActivity extends AppCompatActivity {

    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        group = findViewById(R.id.mn2_group);
        setFragment(new Fragment01());
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
             switch (i){
                 case R.id.mn2_but01:
                     setFragment(new Fragment01());
                     break;
                 case R.id.mn2_but02:
                     setFragment(new Fragment02());
                     break;
                 case R.id.mn2_but03:
                     setFragment(new Fragment03());
                     break;
             }
            }
        });
    }

    //设置Fragment
    private void setFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction().replace(R.id.mn2_frame,fragment).commit();
    }


}
