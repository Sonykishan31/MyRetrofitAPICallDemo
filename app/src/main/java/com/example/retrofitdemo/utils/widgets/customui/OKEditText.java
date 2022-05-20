package com.example.retrofitdemo.utils.widgets.customui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class OKEditText extends AppCompatEditText {
    public OKEditText(Context context) {
        super(context);
    }

    public OKEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OKEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (this.getTag() != null) {
        }

    }

}
