package com.tinyapps7.clockphotoframe.animation_services;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.wang.avi.indicators.BallPulseIndicator;

import java.util.Random;

public class ShivaFlower2 {
    private int angle;
    private Bitmap bitmap;
    private float bounceOffset;
    private int heightBound;
    private float rawX;
    private float speedY;
    private boolean touched;
    private float f2297x;
    private float xOffset;
    private float f2298y;

    public ShivaFlower2(Bitmap bitmap, int i, int i2) {
        float f = 0.4f;
        this.angle = 0;
        Random random = new Random();
        this.speedY = BallPulseIndicator.SCALE + (random.nextFloat() * 4.0f);
        float nextFloat = random.nextFloat() * 0.4f;
        if (((double) nextFloat) > 0.2d) {
            f = nextFloat;
        }
        int width = (int) (((float) bitmap.getWidth()) * f);
        int height = (int) (f * ((float) bitmap.getHeight()));
        if (width == 0 || height == 0) {
            width = bitmap.getWidth() / 4;
            height = bitmap.getHeight() / 4;
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        this.rawX = random.nextFloat() * ((float) i2);
        this.f2297x = this.rawX;
        this.f2298y = (float) (-this.bitmap.getHeight());
        this.heightBound = this.bitmap.getHeight() + i;
        this.bounceOffset = 8.0f;
        this.touched = false;
        this.xOffset = random.nextFloat() * 3.0f;
        if (((double) this.xOffset) >= 1.5d) {
            this.xOffset -= 3.0f;
        }
    }

    public void drawLeaf(Canvas canvas, Paint paint) {
        Matrix matrix = new Matrix();
        Camera camera = new Camera();
        camera.save();
        matrix.reset();
        matrix.postTranslate((float) ((-this.bitmap.getWidth()) / 2), (float) ((-this.bitmap.getHeight()) / 2));
        matrix.postRotate((float) this.angle);
        camera.rotateX((float) this.angle);
        camera.rotateY(0.0f);
        camera.getMatrix(matrix);
        matrix.postTranslate(this.f2297x + ((float) (this.bitmap.getHeight() / 2)), this.f2298y + ((float) (this.bitmap.getWidth() / 2)));
        canvas.drawBitmap(this.bitmap, matrix, paint);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public float getSpeedY() {
        return this.speedY;
    }

    public float getX() {
        return this.f2297x;
    }

    public float getY() {
        return this.f2298y;
    }

    public void handleFalling(boolean z) {
        if (z) {
            this.f2298y += this.speedY;
            this.f2297x += this.xOffset;
            this.angle++;
            if (this.f2298y >= ((float) this.heightBound)) {
                this.f2298y = (float) (-this.bitmap.getHeight());
                this.f2297x = this.rawX;
                return;
            }
            return;
        }
        this.f2298y -= this.speedY;
        this.f2297x += this.xOffset;
        if (this.f2298y <= ((float) (-this.bitmap.getHeight()))) {
            this.f2298y = (float) this.heightBound;
            this.f2297x = this.rawX;
        }
    }

    public void handleTouched(float f, float f2) {
        float height = this.f2298y + (((float) this.bitmap.getHeight()) / 2.0f);
        if (this.f2297x + (((float) this.bitmap.getWidth()) / 2.0f) <= f) {
            this.f2297x -= this.bounceOffset;
            if (height <= f2) {
                this.f2298y -= this.bounceOffset;
            } else {
                this.f2298y += this.bounceOffset;
            }
        } else {
            this.f2297x += this.bounceOffset;
            if (height <= f2) {
                this.f2298y -= this.bounceOffset;
            } else {
                this.f2298y += this.bounceOffset;
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

    public void setSpeedY(float f) {
        this.speedY = f;
    }

    public void setTouched(boolean z) {
        this.touched = z;
    }

    public void setX(float f) {
        this.f2297x = f;
    }

    public void setY(float f) {
        this.f2298y = f;
    }
}
