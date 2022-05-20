package com.example.retrofitdemo.utils.widgets.customui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;


public class OKButton extends AppCompatButton {
    public OKButton(Context context) {
        super(context);
    }

    public OKButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OKButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (this.getTag() != null) {
        }

    }

}
