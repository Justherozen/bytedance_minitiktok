package com.example.minitiktok.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 *滚动式TextView
 *参见：https://www.jianshu.com/p/67e74148a122
 */
public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused(){
        return true;
    }

}
