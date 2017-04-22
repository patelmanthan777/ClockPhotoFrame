package com.tinyapps7.clockphotoframe.animation_services;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.wang.avi.indicators.BallPulseIndicator;

import java.util.Random;

public class PhotoRain {
    private static Paint fadePaint;
    private Bitmap bitmap;
    private float bounceOffset;
    private float count;
    private int heightBound;
    private boolean isFalling;
    private float speedY;
    private boolean touched;
    private float f2293x;
    private float f2294y;

    static {
        fadePaint = new Paint();
    }

    public PhotoRain(Bitmap bitmap, int i, int i2) {
        Random random = new Random();
        this.speedY = (float) (60 + (random.nextInt(91) + 1));
        float nextFloat = random.nextFloat() * 0.5f;
        if (((double) nextFloat) <= 0.1d) {
            nextFloat = 0.1f;
        }
        int width = (int) (((float) bitmap.getWidth()) * nextFloat);
        int height = (int) (nextFloat * ((float) bitmap.getHeight()));
        if (width == 0 || height == 0) {
            width = bitmap.getWidth() / 4;
            height = bitmap.getHeight() / 4;
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        this.f2293x = BallPulseIndicator.SCALE + (random.nextFloat() * ((float) i));
        this.f2294y = (float) (-this.bitmap.getHeight());
        this.heightBound = this.bitmap.getHeight() + i2;
        this.bounceOffset = 8.0f;
        this.touched = false;
        nextFloat = random.nextFloat() * 3.0f;
        if (((double) nextFloat) >= 1.5d) {
            nextFloat -= 3.0f;
        }
        fadePaint.setAlpha(255);
    }

    private void setAplha() {
        if (this.isFalling) {
            if (this.f2294y <= ((float) (this.heightBound / 2))) {
                fadePaint.setAlpha(255);
            } else if (this.f2294y > ((float) (this.heightBound / 2)) && this.f2294y <= ((float) ((this.heightBound * 2) / 3))) {
                fadePaint.setAlpha(190);
            } else if (this.f2294y > ((float) ((this.heightBound * 2) / 3)) && this.f2294y <= ((float) ((this.heightBound * 3) / 4))) {
                fadePaint.setAlpha(150);
            } else if (this.f2294y > ((float) ((this.heightBound * 3) / 4)) && this.f2294y <= ((float) ((this.heightBound * 4) / 5))) {
                fadePaint.setAlpha(125);
            } else if (this.f2294y > ((float) ((this.heightBound * 4) / 5)) && this.f2294y <= ((float) ((this.heightBound * 5) / 6))) {
                fadePaint.setAlpha(100);
            } else if (this.f2294y > ((float) ((this.heightBound * 5) / 6)) && this.f2294y <= ((float) ((this.heightBound * 5) / 7))) {
                fadePaint.setAlpha(75);
            } else if (this.f2294y > ((float) ((this.heightBound * 6) / 7)) && this.f2294y <= ((float) ((this.heightBound * 5) / 8))) {
                fadePaint.setAlpha(50);
            } else if (this.f2294y <= ((float) ((this.heightBound * 7) / 8)) || this.f2294y > ((float) ((this.heightBound * 5) / 9))) {
                fadePaint.setAlpha(0);
            } else {
                fadePaint.setAlpha(25);
            }
        } else if (this.f2294y >= ((float) (this.heightBound / 2))) {
            fadePaint.setAlpha(255);
        } else if (this.f2294y < ((float) (this.heightBound / 2)) && this.f2294y >= ((float) (this.heightBound / 3))) {
            fadePaint.setAlpha(190);
        } else if (this.f2294y < ((float) (this.heightBound / 3)) && this.f2294y >= ((float) (this.heightBound / 4))) {
            fadePaint.setAlpha(170);
        } else if (this.f2294y < ((float) (this.heightBound / 4)) && this.f2294y >= ((float) (this.heightBound / 5))) {
            fadePaint.setAlpha(150);
        } else if (this.f2294y < ((float) (this.heightBound / 5)) && this.f2294y >= ((float) (this.heightBound / 6))) {
            fadePaint.setAlpha(125);
        } else if (this.f2294y < ((float) (this.heightBound / 6)) && this.f2294y >= ((float) (this.heightBound / 7))) {
            fadePaint.setAlpha(100);
        } else if (this.f2294y < ((float) (this.heightBound / 7)) && this.f2294y >= ((float) (this.heightBound / 8))) {
            fadePaint.setAlpha(75);
        } else if (this.f2294y < ((float) (this.heightBound / 8)) && this.f2294y >= ((float) (this.heightBound / 9))) {
            fadePaint.setAlpha(50);
        } else if (this.f2294y >= ((float) (this.heightBound / 9)) || this.f2294y < ((float) (this.heightBound / 10))) {
            fadePaint.setAlpha(0);
        } else {
            fadePaint.setAlpha(25);
        }
    }

    public void drawLeaf(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, this.f2293x, this.f2294y, null);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public float getSpeedY() {
        return this.speedY;
    }

    public float getX() {
        return this.f2293x;
    }

    public float getY() {
        return this.f2294y;
    }

    public void handleFalling(boolean z) {
        this.isFalling = z;
        if (z) {
            this.f2294y += this.speedY;
            setAplha();
            if (this.f2294y >= ((float) this.heightBound)) {
                this.f2294y = (float) (-this.bitmap.getHeight());
                return;
            }
            return;
        }
        this.f2294y -= this.speedY;
        setAplha();
        if (this.f2294y <= ((float) (-this.bitmap.getHeight()))) {
            this.f2294y = (float) this.heightBound;
        }
    }

    public void handleTouched(float f, float f2) {
        float height = this.f2294y + (((float) this.bitmap.getHeight()) / 2.0f);
        if (this.f2293x + (((float) this.bitmap.getWidth()) / 2.0f) <= f) {
            this.f2293x -= this.bounceOffset;
            if (height <= f2) {
                this.f2294y -= this.bounceOffset;
                setAplha();
            } else {
                this.f2294y += this.bounceOffset;
                setAplha();
            }
        } else {
            this.f2293x += this.bounceOffset;
            if (height <= f2) {
                this.f2294y -= this.bounceOffset;
                setAplha();
            } else {
                this.f2294y += this.bounceOffset;
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
        this.f2293x = f;
    }

    public void setY(float f) {
        this.f2294y = f;
    }
}
