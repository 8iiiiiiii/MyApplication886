package com.example.com.rk0502_demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> list;
    private final int A = 0;
    private final int B = 1;
    private final int C = 2;
    private final int D = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
         list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("标题"+i);
        }
        listView.setAdapter(new MyBase());
    }
    class MyBase extends BaseAdapter{

        @Override
        public int getViewTypeCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            if(position%4==0){
                return A;
            }else if(position%4==1){
                return B;
            }
            else if(position%4==2){
                return C;
            }
            else {
                return D;
            }
        }

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
            if(getItemViewType(i)==A){
            ViewHodler1 hodler1;
            if(view==null){
            view = View.inflate(MainActivity.this,R.layout.mybase1,null);
            hodler1 = new ViewHodler1();
            hodler1.title = view.findViewById(R.id.title);
            hodler1.image1 = view.findViewById(R.id.imageView);
            view.setTag(hodler1);
            }else{
                hodler1 = (ViewHodler1) view.getTag();
            }
            hodler1.title.setText(list.get(i));
            hodler1.image1.setImageResource(R.mipmap.ic_launcher);
                return view;
            }else if(getItemViewType(i)==B){
                ViewHodler2 hodler2;
                if(view==null){
                    view = View.inflate(MainActivity.this,R.layout.mybase2,null);
                    hodler2 = new ViewHodler2();
                    hodler2.title = view.findViewById(R.id.title);
                    hodler2.image1 = view.findViewById(R.id.imageView);
                    hodler2.image2 = view.findViewById(R.id.imageView2);
                    view.setTag(hodler2);
                }else{
                    hodler2 = (ViewHodler2) view.getTag();
                }
                hodler2.title.setText(list.get(i));
                hodler2.image1.setImageResource(R.mipmap.ic_launcher);
                hodler2.image2.setImageResource(R.mipmap.ic_launcher);
                return view;
            }else if(getItemViewType(i)==C){
                ViewHodler3 hodler3;
                if(view==null){
                    view = View.inflate(MainActivity.this,R.layout.mybase3,null);
                    hodler3 = new ViewHodler3();
                    hodler3.title = view.findViewById(R.id.title);
                    hodler3.image1 = view.findViewById(R.id.imageView);
                    hodler3.image2 = view.findViewById(R.id.imageView2);
                    hodler3.image3 = view.findViewById(R.id.imageView3);
                    view.setTag(hodler3);
                }else{
                    hodler3 = (ViewHodler3) view.getTag();
                }
                hodler3.title.setText(list.get(i));
                hodler3.image1.setImageResource(R.mipmap.ic_launcher);
                hodler3.image2.setImageResource(R.mipmap.ic_launcher);
                hodler3.image3.setImageResource(R.mipmap.ic_launcher);
                return view;
            }else{
                ViewHodler4 hodler4;
                if(view==null){
                    view = View.inflate(MainActivity.this,R.layout.mybase4,null);
                    hodler4 = new ViewHodler4();
                    hodler4.title = view.findViewById(R.id.title);
                    hodler4.image1 = view.findViewById(R.id.imageView);
                    hodler4.image2 = view.findViewById(R.id.imageView2);
                    hodler4.image3 = view.findViewById(R.id.imageView3);
                    hodler4.image4 = view.findViewById(R.id.imageView4);
                    view.setTag(hodler4);
                }else{
                    hodler4 = (ViewHodler4) view.getTag();
                }
                hodler4.title.setText(list.get(i));
                hodler4.image1.setImageResource(R.mipmap.ic_launcher);
                hodler4.image2.setImageResource(R.mipmap.ic_launcher);
                hodler4.image3.setImageResource(R.mipmap.ic_launcher);
                hodler4.image4.setImageResource(R.mipmap.ic_launcher);
                return view;
            }
        }
        class ViewHodler1{
            ImageView image1;
            TextView title;
        }
        class ViewHodler2{
            ImageView image1,image2;
            TextView title;
        }
        class ViewHodler3{
            ImageView image1,image2,image3;
            TextView title;
        }
        class ViewHodler4{
            ImageView image1,image2,image3,image4;
            TextView title;
        }
    }
}
