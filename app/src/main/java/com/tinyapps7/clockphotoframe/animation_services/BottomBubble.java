package com.tinyapps7.clockphotoframe.animation_services;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.wang.avi.indicators.BallPulseIndicator;

import java.util.Random;

public class BottomBubble {
    private static Paint fadePaint;
    private Bitmap bitmap;
    private float bounceOffset;
    private int heightBound;
    private int f2286i;
    private float rawX;
    private float speedY;
    private boolean touched;
    private float f2287x;
    private float xOffset;
    private float f2288y;

    static {
        fadePaint = new Paint();
    }

    public BottomBubble(Bitmap bitmap, int i, int i2) {
        this.f2286i = 255;
        Random random = new Random();
        this.speedY = BallPulseIndicator.SCALE + (random.nextFloat() * 4.0f);
        float nextFloat = random.nextFloat() * 0.4f;
        if (((double) nextFloat) <= 0.01d) {
            nextFloat = 0.1f;
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * nextFloat), (int) (nextFloat * ((float) bitmap.getHeight())), true);
        this.rawX = random.nextFloat() * ((float) i2);
        this.f2287x = this.rawX;
        this.f2288y = (float) (-this.bitmap.getHeight());
        this.heightBound = this.bitmap.getHeight() + i;
        this.bounceOffset = 8.0f;
        this.touched = false;
        this.xOffset = random.nextFloat() * 4.0f;
        if (((double) this.xOffset) >= 1.0d) {
            this.xOffset -= 3.0f;
        }
        fadePaint.setAlpha(255);
    }

    private void setAplha() {
        if (this.f2287x <= ((float) this.heightBound)) {
            fadePaint.setAlpha(this.f2286i);
            this.f2286i -= 15;
            if (this.f2286i <= 0) {
                this.f2286i = 255;
                return;
            }
            return;
        }
        fadePaint.setAlpha(this.f2286i);
        this.f2286i -= 15;
        if (this.f2286i <= 0) {
            this.f2286i = 255;
        }
    }

    public void drawLeaf(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, this.f2287x, this.f2288y, fadePaint);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public float getSpeedY() {
        return this.speedY;
    }

    public float getX() {
        return this.f2287x;
    }

    public float getY() {
        return this.f2288y;
    }

    public void handleFalling() {
        this.f2288y -= this.speedY;
        this.f2287x += this.xOffset;
        setAplha();
        if (this.f2288y <= ((float) (-this.bitmap.getHeight()))) {
            this.f2288y = (float) this.heightBound;
            this.f2287x = this.rawX;
        }
    }

    public void handleTouched(float f, float f2) {
        float height = this.f2288y + (((float) this.bitmap.getHeight()) / 2.0f);
        if (this.f2287x + (((float) this.bitmap.getWidth()) / 2.0f) <= f) {
            this.f2287x -= this.bounceOffset;
            if (height <= f2) {
                this.f2288y -= this.bounceOffset;
                setAplha();
            } else {
                this.f2288y += this.bounceOffset;
                setAplha();
            }
        } else {
            this.f2287x += this.bounceOffset;
            if (height <= f2) {
                this.f2288y -= this.bounceOffset;
                setAplha();
            } else {
                this.f2288y += this.bounceOffset;
                setAplha();
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
        this.f2287x = f;
    }

    public void setY(float f) {
        this.f2288y = f;
    }
}
