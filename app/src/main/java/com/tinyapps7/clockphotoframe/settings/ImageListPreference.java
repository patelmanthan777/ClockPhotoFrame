package com.tinyapps7.clockphotoframe.settings;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.ListPreference;
import android.util.AttributeSet;

import com.tinyapps7.clockphotoframe.R;

public class ImageListPreference extends ListPreference {
    public ImageListPreference(Context context) {
        super(context);
    }

    public ImageListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ImageListPreference);
        String[] stringArray = context.getResources().getStringArray(obtainStyledAttributes.getResourceId(obtainStyledAttributes.getIndexCount() - 1, -1));
        int[] iArr = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            iArr[i] = context.getResources().getIdentifier(stringArray[i].substring(stringArray[i].indexOf(47) + 1, stringArray[i].lastIndexOf(46)), null, context.getPackageName());
        }
        obtainStyledAttributes.recycle();
    }

    protected void onPrepareDialogBuilder(Builder builder) {
        builder.setAdapter(new ImageArrayAdapter(getContext(), R.layout.p_single_list_item, getEntries(), findIndexOfValue(getSharedPreferences().getString(getKey(), "1"))), this);
        super.onPrepareDialogBuilder(builder);
    }
}
