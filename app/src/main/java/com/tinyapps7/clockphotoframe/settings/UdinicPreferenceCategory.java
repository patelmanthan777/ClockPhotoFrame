package com.tinyapps7.clockphotoframe.settings;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UdinicPreferenceCategory extends PreferenceCategory {
    public UdinicPreferenceCategory(Context context) {
        super(context);
    }

    public UdinicPreferenceCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UdinicPreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected View onCreateView(ViewGroup viewGroup) {
        TextView textView = (TextView) super.onCreateView(viewGroup);
        textView.setBackgroundColor(Color.BLUE);
        textView.setTextColor(Color.RED);
        return textView;
    }
}
