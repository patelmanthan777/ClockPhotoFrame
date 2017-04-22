package com.tinyapps7.clockphotoframe.animation_services;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.wang.avi.indicators.BallPulseIndicator;

import java.util.Random;

public class Bubble_to_up {
    private static Paint fadePaint;
    private Bitmap bitmap;
    private float bounceOffset;
    private int heightBound;
    private float rawX;
    private float speedY;
    private boolean touched;
    private float f2289x;
    private float xOffset;
    private float f2290y;

    static {
        fadePaint = new Paint();
    }

    public Bubble_to_up(Bitmap bitmap, int i, int i2) {
        Random random = new Random();
        this.speedY = BallPulseIndicator.SCALE + (random.nextFloat() * 5.0f);
        float nextFloat = random.nextFloat() * 0.4f;
        if (((double) nextFloat) <= 0.3d) {
            nextFloat = 0.2f;
        }
        int width = (int) (((float) bitmap.getWidth()) * nextFloat);
        int height = (int) (nextFloat * ((float) bitmap.getHeight()));
        if (width == 0 || height == 0) {
            width = bitmap.getWidth() / 4;
            height = bitmap.getHeight() / 4;
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width / 2, height / 2, true);
        this.rawX = ((float) (i2 / 3)) + ((random.nextFloat() * ((float) i2)) / 3.0f);
        this.f2289x = this.rawX;
        this.f2290y = (float) (-this.bitmap.getHeight());
        this.heightBound = this.bitmap.getHeight() + i;
        this.bounceOffset = 8.0f;
        this.touched = false;
        this.xOffset = random.nextFloat() * 3.0f;
        if (((double) this.xOffset) >= 1.5d) {
            this.xOffset -= 3.0f;
        }
        fadePaint.setAlpha(255);
    }

    public void drawLeaf(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, this.f2289x - 50.0f, this.f2290y, fadePaint);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public float getSpeedY() {
        return this.speedY;
    }

    public float getX() {
        return this.f2289x;
    }

    public float getY() {
        return this.f2290y;
    }

    public void handleFalling(boolean z) {
        if (z) {
            this.f2290y += this.speedY;
            this.f2289x += this.xOffset;
            if (this.f2290y >= ((float) this.heightBound)) {
                this.f2290y = (float) (-this.bitmap.getHeight());
                this.f2289x = this.rawX;
                return;
            }
            return;
        }
        this.f2290y -= this.speedY;
        this.f2289x += this.xOffset;
        if (this.f2290y <= ((float) (-this.bitmap.getHeight()))) {
            this.f2290y = (float) this.heightBound;
            this.f2289x = this.rawX;
        }
    }

    public void handleTouched(float f, float f2) {
        float height = this.f2290y + (((float) this.bitmap.getHeight()) / 2.0f);
        if (this.f2289x + (((float) this.bitmap.getWidth()) / 2.0f) <= f) {
            this.f2289x -= this.bounceOffset;
            if (height <= f2) {
                this.f2290y -= this.bounceOffset;
            } else {
                this.f2290y += this.bounceOffset;
            }
        } else {
            this.f2289x += this.bounceOffset;
            if (height <= f2) {
                this.f2290y -= this.bounceOffset;
            } else {
                this.f2290y += this.bounceOffset;
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
//        float f = (float) PhotoOnClockSettings.bubblecount1;
        float f = (float) 20;
    }

    public void setSpeedY(float f) {
        this.speedY = f;
    }

    public void setTouched(boolean z) {
        this.touched = z;
    }

    public void setX(float f) {
        this.f2289x = f;
    }

    public void setY(float f) {
        this.f2290y = f;
    }
}
