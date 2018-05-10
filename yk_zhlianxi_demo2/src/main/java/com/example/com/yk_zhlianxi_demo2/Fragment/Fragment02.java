package com.example.com.yk_zhlianxi_demo2.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.yk_zhlianxi_demo2.R;

/**
 * Created by 老赵的拯救者 on 2018/5/3.
 */

public class Fragment02 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment02, container, false);
        
        return v;
    }
}
