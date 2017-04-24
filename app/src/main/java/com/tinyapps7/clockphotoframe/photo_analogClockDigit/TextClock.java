package com.tinyapps7.clockphotoframe.photo_analogClockDigit;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tinyapps7.clockphotoframe.R;

import java.util.Calendar;
import java.util.TimeZone;

@SuppressLint("AppCompatCustomView")
public class TextClock extends TextView {
    private boolean mAttached;
    private CharSequence mFormat;
    private CharSequence mFormat12;
    private CharSequence mFormat24;
    private final ContentObserver mFormatChangeObserver;
    private boolean mHasSeconds;
    private final BroadcastReceiver mIntentReceiver;
    private CharSequence mSecs;
    private final Runnable mTicker;
    private Calendar mTime;
    private String mTimeZone;

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_digital_clock.custom_clock.TextClock.1 */
    class cb extends ContentObserver {
        cb(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            TextClock.this.chooseFormat();
            TextClock.this.onTimeChanged();
        }

        public void onChange(boolean z, Uri uri) {
            TextClock.this.chooseFormat();
            TextClock.this.onTimeChanged();
        }
    }

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_digital_clock.custom_clock.TextClock.2 */
    class brod extends BroadcastReceiver {
        brod() {
        }

        public void onReceive(Context context, Intent intent) {
            if (TextClock.this.mTimeZone == null && "android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                TextClock.this.createTime(intent.getStringExtra("time-zone"));
            }
            TextClock.this.onTimeChanged();
        }
    }

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_digital_clock.custom_clock.TextClock.3 */
    class runa implements Runnable {
        runa() {
        }

        public void run() {
            TextClock.this.onTimeChanged();
            long uptimeMillis = SystemClock.uptimeMillis();
            TextClock.this.getHandler().postAtTime(TextClock.this.mTicker, uptimeMillis + (1000 - (uptimeMillis % 1000)));
        }
    }

    public TextClock(Context context) {
        super(context);
        this.mFormatChangeObserver = new cb(new Handler());
        this.mIntentReceiver = new brod();
        this.mTicker = new runa();
        init();
    }

    public TextClock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextClock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFormatChangeObserver = new cb(new Handler());
        this.mIntentReceiver = new brod();
        this.mTicker = new runa();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextClock, i, 0);
        try {
//            this.mFormat12 = obtainStyledAttributes.getText(0);
//            this.mFormat24 = obtainStyledAttributes.getText(1);
//            this.mSecs = obtainStyledAttributes.getText(2);
//            this.mTimeZone = obtainStyledAttributes.getString(3);
            init();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private static CharSequence abc(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return charSequence == null ? charSequence2 == null ? charSequence3 : charSequence2 : charSequence;
    }

    private void chooseFormat() {
        chooseFormat(true);
    }

    private void chooseFormat(boolean z) {
        if (is24HourModeEnabled()) {
            this.mFormat = abc(this.mFormat24, this.mFormat12, "HH:mm");
        } else {
            this.mFormat = abc(this.mFormat12, this.mFormat24, "h:mm");
        }
        boolean z2 = this.mHasSeconds;
        this.mHasSeconds =z2;
        if (!z || !this.mAttached || z2 == this.mHasSeconds) {
            return;
        }
        if (z2) {
            getHandler().removeCallbacks(this.mTicker);
        } else {
            this.mTicker.run();
        }
    }

    private void chooseSeconds() {
        chooseSeconds(true);
    }

    private void chooseSeconds(boolean z) {
        this.mFormat = abc(null, null, "ss");
        boolean z2 = this.mHasSeconds;
        this.mHasSeconds = z2;
        if (!z || !this.mAttached || z2 == this.mHasSeconds) {
            return;
        }
        if (z2) {
            getHandler().removeCallbacks(this.mTicker);
        } else {
            this.mTicker.run();
        }
    }

    private void createTime(String str) {
        if (str != null) {
            this.mTime = Calendar.getInstance(TimeZone.getTimeZone(str));
        } else {
            this.mTime = Calendar.getInstance();
        }
    }

    private void init() {
        if (this.mFormat12 == null || this.mFormat24 == null || this.mSecs == null) {
            if (this.mFormat12 == null) {
                this.mFormat12 = "h:mm";
            }
            if (this.mFormat24 == null) {
                this.mFormat24 = "HH:mm";
            }
            if (this.mSecs == null) {
                this.mSecs = "ss";
            }
        }
        createTime(this.mTimeZone);
        chooseFormat(false);
    }

    private void onTimeChanged() {
        this.mTime.setTimeInMillis(Calendar.SECOND);
        setText(DateFormat.format(this.mFormat, this.mTime));
    }

    private void registerObserver() {
        getContext().getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.mFormatChangeObserver);
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        getContext().registerReceiver(this.mIntentReceiver, intentFilter, null, getHandler());
    }

    private void unregisterObserver() {
        getContext().getContentResolver().unregisterContentObserver(this.mFormatChangeObserver);
    }

    private void unregisterReceiver() {
        getContext().unregisterReceiver(this.mIntentReceiver);
    }

    public CharSequence getFormat() {
        return this.mFormat;
    }

    public boolean is24HourModeEnabled() {
        return DateFormat.is24HourFormat(getContext());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            registerReceiver();
            registerObserver();
            createTime(this.mTimeZone);
            if (this.mHasSeconds) {
                this.mTicker.run();
            } else {
                onTimeChanged();
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            unregisterReceiver();
            unregisterObserver();
            getHandler().removeCallbacks(this.mTicker);
            this.mAttached = false;
        }
    }

    public void setFormat12Hour(CharSequence charSequence) {
        this.mFormat12 = charSequence;
        chooseFormat();
        onTimeChanged();
    }

    public void setSecs(CharSequence charSequence) {
        this.mSecs = charSequence;
        chooseSeconds();
        onTimeChanged();
    }
}
