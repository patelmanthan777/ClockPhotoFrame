package com.tinyapps7.clockphotoframe.AnalogClock;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.graphics.Rect;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tinyapps7.clockphotoframe.R;
import com.tinyapps7.clockphotoframe.Settings;


/**
 * Analog Clock view
 *
 * @author sylsau - sylvain.saurel@gmail.com - http://www.ssaurel.com
 */
public class ClockService extends View {
    private Bitmap secondsBmp = BitmapFactory.decodeResource(getResources(), R.drawable.secs_analog);
    private Bitmap minutesBmp = BitmapFactory.decodeResource(getResources(), R.drawable.mins_analog);
    private Bitmap hoursBmp = BitmapFactory.decodeResource(getResources(), R.drawable.hrs_analog);
    private Bitmap leaf = BitmapFactory.decodeResource(getResources(), R.mipmap.clock7);
    private float x;
    private float y;
    private int radius;
    private Calendar cal;
    private Paint paint;
    private int sizeScaled = -1;
    private Bitmap clockDialScaled;
    private Bitmap clock_second;
    private Bitmap clock_Minute;
    private Bitmap clock_Houres;
    private Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.c_bg1);
    /**
     * Hands colors.
     */
    private int[] colors;
    private boolean displayHandSec;
    Settings settings;

    public ClockService(Context context) {
        super(context);
       cal = Calendar.getInstance();
        settings = new Settings();
        settings.init(context);
        String s = settings.Clock_read(settings.Clock,"");
        if(Integer.parseInt(s)==0)
        {}
            else
        {
          updateclock(Integer.parseInt(s));
        }


    }

    public void config(float x, float y, int size, Date date, Paint paint, int[] colors, boolean displayHandSec) {
        this.x = x;
        this.y = y;
        this.paint = paint;
        this.colors = colors;
        this.displayHandSec = displayHandSec;

      cal.setTime(date);

        // scale bitmap if needed
        if (size != sizeScaled) {
            clockDialScaled = Bitmap.createScaledBitmap(leaf, size, size, false);
            clock_second = Bitmap.createScaledBitmap(secondsBmp, size, size, false);
            clock_Houres = Bitmap.createScaledBitmap(hoursBmp, size, size, false);
            clock_Minute = Bitmap.createScaledBitmap(minutesBmp, size, size, false);
            radius = size / 2;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(background, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(scaledBitmap, 0, 0, null);
        Log.e("vkm", "" + canvas.getWidth() + "" + canvas.getHeight());
        if (paint != null) {
            // draw clock img

            canvas.drawBitmap(clockDialScaled, x - radius, y - radius, null);
            float hour = cal.get(Calendar.HOUR_OF_DAY);


            float seconds = 360f * ((float) cal.get(Calendar.SECOND) / 60f);
            float minute = 360f * ((float) cal.get(Calendar.MINUTE) / 60f);


            canvas.drawBitmap(rotateBitmap(clock_Minute, minute), x - radius, y - radius, null);
            canvas.drawBitmap(rotateBitmap(clock_Houres, hour * 10), x - radius, y - radius, null);

            if (displayHandSec) {
                canvas.drawBitmap(rotateBitmap(clock_second, seconds), x - radius, y - radius, null);


            }
        }

    }

    private Bitmap rotateBitmap(Bitmap bitmap, float rotationAngleDegree) {

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int

                newW = w, newH = h;
        if (rotationAngleDegree == 0 || rotationAngleDegree == 270) {
            newW = h;
            newH = w;
        }
        Bitmap rotatedBitmap = Bitmap.createBitmap(newW, newH, bitmap.getConfig());
        Canvas canvas = new Canvas(rotatedBitmap);

        Rect rect = new Rect(0, 0, newW, newH);
        Matrix matrix = new Matrix();
        float px = rect.exactCenterX();
        float py = rect.exactCenterY();
        matrix.postTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);
        matrix.postRotate(rotationAngleDegree);
        matrix.postTranslate(px, py);
        canvas.drawBitmap(bitmap, matrix, new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG));
        matrix.reset();

        return rotatedBitmap;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            Log.e("vkm", e.toString());
            return null;
        }
    }

    private void updateclock(int i) {
        Log.e("testyash", "in Update Clock");

        switch (i) {
            case 0 /*0*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock1);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr1);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min1);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec1);
                break;
            case 1 /*1*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock2);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr2);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min2);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec2);
                break;
            case 2 /*2*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock3);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr3);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min3);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min3);
                break;
            case 3 /*3*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock4);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr4);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min4);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec4);
                break;
            case 4 /*4*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock5);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr3);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min3);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec3);
                break;
            case 5 /*5*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock6);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr6);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min6);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec6);
                break;

            case 6/*6*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock7);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr2);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min2);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec2);
                break;

            case 7 /*7*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock8);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr8);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min8);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec8);
                break;

            case 8 /*8*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock9);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr9);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min9);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec9);
                break;

            case 9 /*9*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock10);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr10);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min10);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec10);
                break;

            case 10 /*10*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock11);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr11);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min11);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec11);
                break;

            case 11 /*11*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock12);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr12);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min12);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec12);
                break;

            case 12 /*12*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock13);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr13);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min13);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec13);
                break;

            case 13 /*13*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock14);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr14);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min14);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec14);
                break;

            case 14 /*14*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock15);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr2);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min2);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec2);
                break;

            case 15 /*15*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock16);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr16);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min16);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec16);
                break;

            case 16/*16*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock17);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr2);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min2);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec17);
                break;

            case 17 /*17*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock18);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr16);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min16);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec16);
                break;

            case 18 /*18*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock19);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr12);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min12);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec12);
                break;

            case 19 /*19*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock20);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr20);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min20);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec20);
                break;

            case 20 /*20*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock21);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr20);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min20);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec20);
                break;

            case 21 /*21*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock22);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr20);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min20);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec20);
                break;

            case 22 /*22*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock23);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr2);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min2);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec2);
                break;

            case 23 /*23*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock24);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr24);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min24);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec24);
                break;

            case 24 /*24*/:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock25);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr2);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min2);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec2);
                break;

            default:
                this.leaf = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.clock1);
                this.hoursBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_hr1);
                this.minutesBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_min1);
                this.secondsBmp = BitmapFactory.decodeResource(ClockService.this.getResources(), R.mipmap.c_sec1);
                break;

        }

    }
}