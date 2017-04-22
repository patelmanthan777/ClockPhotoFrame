package com.tinyapps7.clockphotoframe.animation_services;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.wang.avi.indicators.BallPulseIndicator;

import java.util.Random;

public class Heart_ToUp {
    private Bitmap bitmap;
    private float bounceOffset;
    private float count;
    private int heightBound;
    private float rawX;
    private float scaleRate;
    private float speedY;
    private boolean touched;
    private float f2291x;
    private float xOffset;
    private float f2292y;

    public Heart_ToUp(Bitmap bitmap, int i, int i2, int i3) {
        Random random = new Random();
        this.speedY = BallPulseIndicator.SCALE + (random.nextFloat() * 6.0f);
        if (i3 == 0) {
            this.scaleRate = random.nextFloat() * 0.4f;
            if (((double) this.scaleRate) <= 0.01d) {
                this.scaleRate = 0.2f;
            }
        } else if (i3 == 3) {
            this.scaleRate = 0.3f;
        } else if (i3 == 2) {
            this.scaleRate = 0.2f;
        } else if (i3 == 1) {
            this.scaleRate = 0.1f;
        }
        int width = (int) (((float) bitmap.getWidth()) * this.scaleRate);
        int height = (int) (((float) bitmap.getHeight()) * this.scaleRate);
        if (width == 0 || height == 0) {
            width = bitmap.getWidth() / 4;
            height = bitmap.getHeight() / 4;
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        this.rawX = ((float) (i2 / 3)) + ((random.nextFloat() * ((float) i2)) / 3.0f);
        this.f2291x = this.rawX;
        this.f2292y = (float) (-this.bitmap.getHeight());
        this.heightBound = this.bitmap.getHeight() + i;
        this.bounceOffset = 8.0f;
        this.touched = false;
        this.xOffset = random.nextFloat() * 3.0f;
        if (((double) this.xOffset) >= 1.5d) {
            this.xOffset -= 3.0f;
        }
    }

    public void drawLeaf(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.bitmap, this.f2291x, this.f2292y, paint);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public float getSpeedY() {
        return this.speedY;
    }

    public float getX() {
        return this.f2291x;
    }

    public float getY() {
        return this.f2292y;
    }

    public void handleFalling() {
        this.f2292y -= this.speedY;
        this.f2291x += this.xOffset;
        if (this.f2292y <= ((float) (-this.bitmap.getHeight()))) {
            this.f2292y = (float) this.heightBound;
            this.f2291x = this.rawX;
        }
    }

    public void handleTouched(float f, float f2) {
        float height = this.f2292y + (((float) this.bitmap.getHeight()) / 2.0f);
        if (this.f2291x + (((float) this.bitmap.getWidth()) / 2.0f) <= f) {
            this.f2291x -= this.bounceOffset;
            if (height <= f2) {
                this.f2292y -= this.bounceOffset;
            } else {
                this.f2292y += this.bounceOffset;
            }
        } else {
            this.f2291x += this.bounceOffset;
            if (height <= f2) {
                this.f2292y -= this.bounceOffset;
            } else {
                this.f2292y += this.bounceOffset;
            }
        }
        this.bounceOffset = (float) (((double) this.bounceOffset) * 0.9d);
        if (((double) this.bounceOffset) < 1.0d) {
            this.bounceOffset = 8.0f;
            this.touched = false;
        }
    }

    public boolean isTouched() {
        return this.touched;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setCount() {
      //  this.count = (float) PhotoOnClockSettings.bubblecount1;
        this.count = (float)10;
    }

    public void setSpeedY(float f) {
        this.speedY = f;
    }

    public void setTouched(boolean z) {
        this.touched = z;
    }

    public void setX(float f) {
        this.f2291x = f;
    }

    public void setY(float f) {
        this.f2292y = f;
    }
}
