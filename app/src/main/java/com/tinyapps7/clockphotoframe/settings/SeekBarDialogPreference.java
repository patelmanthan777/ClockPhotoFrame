package com.tinyapps7.clockphotoframe.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tinyapps7.clockphotoframe.BuildConfig;
import com.tinyapps7.clockphotoframe.R;

public class SeekBarDialogPreference extends DialogPreference implements OnSeekBarChangeListener {
    private static final int DEFAULT_VALUE = 10;
    private int maximumValue;
    private int minimumValue;
    private SeekBar seekBar;
    private int stepSize;
    private String units;
    private int value;
    private TextView valueText;
    private View view;

    public SeekBarDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.seekBar = null;
        this.valueText = null;
        this.maximumValue = 100;
        this.minimumValue = 0;
        this.stepSize = 0;
        this.units = null;
        this.value = 0;
//        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SeekBarDialogPreference);
//        this.maximumValue = obtainStyledAttributes.getInteger(0, 100);
//        this.minimumValue = obtainStyledAttributes.getInteger(R.styleable.AVLoadingIndicatorView_minWidth, 0);
//        this.stepSize = obtainStyledAttributes.getInteger(2, 1);
//        this.units = obtainStyledAttributes.getString(3);
//        obtainStyledAttributes.recycle();
    }

    private void updateView() {
        try {
            getView(null, null);
            this.seekBar.setProgress(this.value - this.minimumValue);
        } catch (Exception e) {
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1 && shouldPersist()) {
            persistInt(this.value + this.minimumValue);
        }
        super.onClick(dialogInterface, i);
    }

    protected View onCreateDialogView() {
        this.view = LayoutInflater.from(getContext()).inflate(R.layout.p_seekbardialogpreference_layout, null);
        this.seekBar = (SeekBar) this.view.findViewById(R.id.seekbar);
        this.valueText = (TextView) this.view.findViewById(R.id.valueText);
        this.value = getPersistedInt(this.minimumValue) - this.minimumValue;
        if (this.value < 0) {
            this.value = 0;
        }
        this.seekBar.setOnSeekBarChangeListener(this);
        this.seekBar.setKeyProgressIncrement(this.stepSize);
        this.seekBar.setMax(this.maximumValue - this.minimumValue);
        this.seekBar.setProgress(this.value);
        if (!(this.view == null || this.view.isEnabled())) {
            this.seekBar.setEnabled(false);
        }
        updateView();
        return this.view;
    }

    public void onDependencyChanged(Preference preference, boolean z) {
        super.onDependencyChanged(preference, z);
        if (this.seekBar != null) {
            this.seekBar.setEnabled(!z);
        }
    }

    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, DEFAULT_VALUE));
    }

    @SuppressLint({"SetTextI18n"})
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (this.stepSize >= 1) {
            this.value = Math.round((float) (i / this.stepSize)) * this.stepSize;
        } else {
            this.value = i;
        }
        this.valueText.setText(String.valueOf(this.value + this.minimumValue) + (this.units == null ? BuildConfig.FLAVOR : this.units));
        callChangeListener(Integer.valueOf(this.value));
    }

    protected void onSetInitialValue(boolean z, Object obj) {
        if (z) {
            this.value = getPersistedInt(this.value);
            return;
        }
        int i = 0;
        try {
            i = ((Integer) obj).intValue();
        } catch (Exception e) {
        }
        persistInt(i);
        this.value = i;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        setSummary(BuildConfig.FLAVOR + seekBar.getProgress());
        if (getKey().equals("bubblenumber")) {
            PhotoOnClockSettings.bubblecount1 = seekBar.getProgress();
        }
        notifyChanged();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.seekBar.setEnabled(z);
    }
}
