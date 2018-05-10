package com.example.com.lunbodata;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 老赵的拯救者 on 2018/5/2.
 */

public class MlistView extends ListView {

    public MlistView(Context context) {
        super(context);
    }

    public MlistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
