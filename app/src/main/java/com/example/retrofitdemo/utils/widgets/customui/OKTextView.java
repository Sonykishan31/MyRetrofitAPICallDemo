package com.example.retrofitdemo.utils.widgets.customui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class OKTextView extends AppCompatTextView {
    public OKTextView(Context context) {
        super(context);
    }

    public OKTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OKTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (this.getTag() != null) {
        }

    }

}
