package com.tinyapps7.clockphotoframe.AnalogClock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

import com.tinyapps7.clockphotoframe.R;
import com.wang.avi.indicators.BallPulseIndicator;

import java.util.TimeZone;

public class MyView extends View {
    public static Drawable mHourHand;
    public static Drawable mMinuteHand;
    public static Drawable mSecondHand;
    MyCount counter;
    private boolean mAttached;
    private Time mCalendar;
    private boolean mChanged;
    Context mContext;
    private Drawable mDial;
    private int mDialHeight;
    private int mDialWidth;
    private final Handler mHandler;
    private float mHour;
    private final BroadcastReceiver mIntentReceiver;
    private float mMinutes;
    float mSecond;
    boolean mSeconds;

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.MyView.1 */
    class C12001 extends BroadcastReceiver {
        C12001() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
                MyView.this.mCalendar = new Time(TimeZone.getTimeZone(intent.getStringExtra("time-zone")).getID());
            }
            MyView.this.onTimeChanged();
            MyView.this.invalidate();
        }
    }

    public class MyCount extends CountDownTimer {
        MyCount(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            MyView.this.counter.start();
        }

        public void onTick(long j) {
            MyView.this.mCalendar.setToNow();
            MyView.this.mSecond = ((float) MyView.this.mCalendar.second) * 6.0f;
            MyView.this.mSeconds = true;
            MyView.this.invalidate();
        }
    }

    public MyView(Context context) {
        super(context);
        this.mHandler = new Handler();
        this.mSeconds = false;
        this.mSecond = 0.0f;
        this.counter = new MyCount(10000, 1000);
        this.mIntentReceiver = new C12001();
        Resources resources = context.getResources();
        this.mContext = context;
        this.mDial = resources.getDrawable(R.mipmap.mask_c2);
        this.mDial.setAlpha(0);
        mHourHand = resources.getDrawable(R.mipmap.c_hr1);
        mMinuteHand = resources.getDrawable(R.mipmap.c_min1);
        mSecondHand = resources.getDrawable(R.mipmap.c_sec1);
        this.mCalendar = new Time();
        this.mDialWidth = this.mDial.getIntrinsicWidth();
        this.mDialHeight = this.mDial.getIntrinsicHeight();
    }

    public MyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandler = new Handler();
        this.mSeconds = false;
        this.mSecond = 0.0f;
        this.counter = new MyCount(10000, 1000);
        this.mIntentReceiver = new C12001();
    }

    private void onTimeChanged() {
        this.mCalendar.setToNow();
        int i = this.mCalendar.hour;
        this.mMinutes = ((float) this.mCalendar.minute) + (((float) this.mCalendar.second) / 60.0f);
        this.mHour = ((float) i) + (this.mMinutes / 60.0f);
        this.mChanged = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            getContext().registerReceiver(this.mIntentReceiver, intentFilter, null, this.mHandler);
        }
        this.mCalendar = new Time();
        onTimeChanged();
        this.counter.start();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            this.counter.cancel();
            getContext().unregisterReceiver(this.mIntentReceiver);
            this.mAttached = false;
        }
    }

    protected void onDraw(Canvas canvas) {
        boolean z = false;
        super.onDraw(canvas);
        boolean z2 = this.mChanged;
        if (z2) {
            this.mChanged = false;
        }
        boolean z3 = this.mSeconds;
        if (z3) {
            this.mSeconds = false;
        }
        int width = getWidth();
        int height = getHeight();
        int i = width / 2;
        int i2 = height / 2;
        Drawable drawable = this.mDial;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (width < intrinsicWidth || height < intrinsicHeight) {
            z = true;
            float min = Math.min(((float) width) / ((float) intrinsicWidth), ((float) height) / ((float) intrinsicHeight));
            canvas.save();
            canvas.scale(min, min, (float) i, (float) i2);
        }
        if (z2) {
            drawable.setBounds(i - (intrinsicWidth / 2), i2 - (intrinsicHeight / 2), (intrinsicWidth / 2) + i, (intrinsicHeight / 2) + i2);
        }
        drawable.draw(canvas);
        canvas.save();
        canvas.rotate((this.mHour / 12.0f) * 360.0f, (float) i, (float) i2);
        Drawable drawable2 = mHourHand;
        if (z3) {
            width = drawable2.getIntrinsicWidth();
            height = drawable2.getIntrinsicHeight();
            drawable2.setBounds(i - (width / 2), i2 - (height / 2), (width / 2) + i, (height / 2) + i2);
        }
        drawable2.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.rotate((this.mMinutes / 60.0f) * 360.0f, (float) i, (float) i2);
        drawable2 = mMinuteHand;
        if (z3) {
            width = drawable2.getIntrinsicWidth();
            height = drawable2.getIntrinsicHeight();
            drawable2.setBounds(i - (width / 2), i2 - (height / 2), (width / 2) + i, (height / 2) + i2);
        }
        drawable2.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.rotate(this.mSecond, (float) i, (float) i2);
        if (z3) {
            int intrinsicWidth2 = mSecondHand.getIntrinsicWidth();
            int intrinsicHeight2 = mSecondHand.getIntrinsicHeight();
            mSecondHand.setBounds(i - (intrinsicWidth2 / 2), i2 - (intrinsicHeight2 / 2), (intrinsicWidth2 / 2) + i, (intrinsicHeight2 / 2) + i2);
        }
        mSecondHand.draw(canvas);
        canvas.restore();
        if (z) {
            canvas.restore();
        }
    }

    protected void onMeasure(int i, int i2) {
        float f = BallPulseIndicator.SCALE;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        float f2 = (mode == 0 || size >= this.mDialWidth) ? BallPulseIndicator.SCALE : ((float) size) / ((float) this.mDialWidth);
        if (mode2 != 0 && size2 < this.mDialHeight) {
            f = ((float) size2) / ((float) this.mDialHeight);
        }
        f = Math.min(f2, f);
        setMeasuredDimension(resolveSize((int) (((float) this.mDialWidth) * f), i), resolveSize((int) (f * ((float) this.mDialHeight)), i2));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mChanged = true;
    }
}
