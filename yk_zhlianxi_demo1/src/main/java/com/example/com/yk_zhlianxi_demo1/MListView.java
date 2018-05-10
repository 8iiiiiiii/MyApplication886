package com.example.com.yk_zhlianxi_demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 老赵的拯救者 on 2018/4/27.
 */

public class MListView extends ListView{
    public MListView(Context context) {
        super(context);
    }

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
