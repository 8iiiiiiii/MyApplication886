package com.example.com.yk_zhlianxi_demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 老赵的拯救者 on 2018/4/27.
 */

public class Fragment02 extends Fragment{

    private TabLayout mytab;
    private ViewPager pager;
    private List<TabBean> menus = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment02, container, false);
        mytab = v.findViewById(R.id.mytab);
        pager = v.findViewById(R.id.f2_pager);

        initMenus();
        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return menus.get(position).getTitle();
            }

            @Override
            public Fragment getItem(int position) {
                ContentFragment con = new ContentFragment();
                Bundle b = new Bundle();
                b.putString("key",menus.get(position).getType());
                con.setArguments(b);
                return con;
            }

            @Override
            public int getCount() {
                return menus.size();
            }
        });
            //禁止滑动
            mytab.setTabMode(TabLayout.MODE_FIXED);

        mytab.setupWithViewPager(pager);
        pager.setOffscreenPageLimit(menus.size());
        return v;
    }

    //初始化菜单
    private void initMenus() {
        menus.add(new TabBean("社会","social"));
        menus.add(new TabBean("娱乐","huabian"));
        menus.add(new TabBean("体育","tiyu"));
        menus.add(new TabBean("NBA","nba"));
        menus.add(new TabBean("足球","football"));
        menus.add(new TabBean("科技","keji"));
        menus.add(new TabBean("创业","startup"));
        menus.add(new TabBean("苹果","apple"));
    }
}
