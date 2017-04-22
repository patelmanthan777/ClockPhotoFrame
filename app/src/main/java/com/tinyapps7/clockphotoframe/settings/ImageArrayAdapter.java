package com.tinyapps7.clockphotoframe.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.tinyapps7.clockphotoframe.R;


class ImageArrayAdapter extends ArrayAdapter<CharSequence> {
    private int index;

    ImageArrayAdapter(Context context, int i, CharSequence[] charSequenceArr, int i2) {
        super(context, i, charSequenceArr);
        this.index = 0;
        this.index = i2;
    }

    @SuppressLint({"ViewHolder"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.p_radiobtn_list_item, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image1);
        if (i == 0) {
            imageView.setImageResource(R.drawable.snow);
        } else if (i == 1) {
            imageView.setImageResource(R.drawable.roseflingicon);
        } else if (i == 2) {
            imageView.setImageResource(R.drawable.bubble_1);
        } else if (i == 3) {
            imageView.setImageResource(R.drawable.st4);
        } else if (i == 4) {
            imageView.setImageResource(R.drawable.fficon);
        } else if (i == 5) {
            imageView.setImageResource(R.drawable.rain);
        } else if (i == 6) {
            imageView.setImageResource(R.drawable.love);
        }
        CheckedTextView checkedTextView = (CheckedTextView) inflate.findViewById(R.id.check);
        checkedTextView.setText((CharSequence) getItem(i));
        if (i == this.index) {
            checkedTextView.setChecked(true);
        }
        return inflate;
    }
}
