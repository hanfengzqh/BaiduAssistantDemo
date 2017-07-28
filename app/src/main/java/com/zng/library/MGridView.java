package com.zng.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决ScrollView嵌套GridView(ListView方法同理)显示不全的问题
 * 
 * @author mrsimple
 */
public class MGridView extends GridView {

    public boolean hasScrollBar = true;

    /**
     * @param context
     */
    public MGridView(Context context) {
        this(context, null);
    }

    public MGridView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = heightMeasureSpec;
        if (hasScrollBar) {
            expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
