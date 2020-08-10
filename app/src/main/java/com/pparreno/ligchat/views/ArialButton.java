package com.pparreno.ligchat.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class ArialButton extends androidx.appcompat.widget.AppCompatButton {
    public ArialButton(Context context) {
        super(context);
    }

    public ArialButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void applyCustomFont(Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "arial_mt.ttf");
        this.setTypeface(typeface);
    }

}
