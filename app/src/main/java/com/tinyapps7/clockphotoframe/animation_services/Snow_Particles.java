package com.tinyapps7.clockphotoframe.animation_services;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.wang.avi.indicators.BallPulseIndicator;

import java.util.Random;

public class Snow_Particles {
    private Bitmap bitmap;
    private float bounceOffset;
    private float count;
    private int heightBound;
    private float rawX;
    private float speedY;
    private boolean touched;
    private float f2299x;
    private float xOffset;
    private float f2300y;

    public Snow_Particles(Bitmap bitmap, int i, int i2) {
        Random random = new Random();
        this.speedY = BallPulseIndicator.SCALE + (random.nextFloat() * 20.0f);
        float nextFloat = random.nextFloat() * 0.2f;
        if (((double) nextFloat) <= 0.01d) {
            nextFloat = 0.1f;
        }
        int width = (int) (((float) bitmap.getWidth()) * nextFloat);
        int height = (int) (nextFloat * ((float) bitmap.getHeight()));
        if (width == 0 || height == 0) {
            width = bitmap.getWidth() / 4;
            height = bitmap.getHeight() / 4;
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        this.rawX = random.nextFloat() * ((float) i2);
        this.f2299x = this.rawX;
        this.f2300y = (float) (-this.bitmap.getHeight());
        this.heightBound = this.bitmap.getHeight() + i;
        this.bounceOffset = 8.0f;
        this.touched = false;
        this.xOffset = random.nextFloat() * 8.0f;
        if (((double) this.xOffset) >= 1.5d) {
            this.xOffset -= 3.0f;
        }
    }

    public void drawLeaf(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, this.f2299x + ((float) (this.bitmap.getWidth() / 2)), this.f2300y + ((float) (this.bitmap.getHeight() / 2)), null);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public float getSpeedY() {
        return this.speedY;
    }

    public float getX() {
        return this.f2299x;
    }

    public float getY() {
        return this.f2300y;
    }

    public void handleFalling(boolean z) {
        if (z) {
            this.f2300y += this.speedY;
            this.f2299x += this.xOffset;
            if (this.f2300y >= ((float) this.heightBound)) {
                this.f2300y = (float) (-this.bitmap.getHeight());
                this.f2299x = this.rawX;
                return;
            }
            return;
        }
        this.f2300y -= this.speedY;
        this.f2299x += this.xOffset;
        if (this.f2300y <= ((float) (-this.bitmap.getHeight()))) {
            this.f2300y = (float) this.heightBound;
            this.f2299x = this.rawX;
        }
    }

    public void handleTouched(float f, float f2) {
        float height = this.f2300y + (((float) this.bitmap.getHeight()) / 2.0f);
        if (this.f2299x + (((float) this.bitmap.getWidth()) / 2.0f) <= f) {
            this.f2299x -= this.bounceOffset;
            if (height <= f2) {
                this.f2300y -= this.bounceOffset;
            } else {
                this.f2300y += this.bounceOffset;
            }
        } else {
            this.f2299x += this.bounceOffset;
            if (height <= f2) {
                this.f2300y -= this.bounceOffset;
            } else {
                this.f2300y += this.bounceOffset;
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
//        this.count = (float) PhotoOnClockSettings.bubblecount1;
        this.count = (float) 20;
    }

    public void setSpeedY(float f) {
        this.speedY = f;
    }

    public void setTouched(boolean z) {
        this.touched = z;
    }

    public void setX(float f) {
        this.f2299x = f;
    }

    public void setY(float f) {
        this.f2300y = f;
    }
}
