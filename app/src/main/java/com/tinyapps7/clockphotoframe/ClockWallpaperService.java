package com.tinyapps7.clockphotoframe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.service.wallpaper.WallpaperService.Engine;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.tinyapps7.clockphotoframe.animation_services.BottomBubble;
import com.tinyapps7.clockphotoframe.animation_services.Bubble_to_up;
import com.tinyapps7.clockphotoframe.animation_services.Heart_ToUp;
import com.tinyapps7.clockphotoframe.animation_services.PhotoRain;
import com.tinyapps7.clockphotoframe.animation_services.RosepetalsFalling;
import com.tinyapps7.clockphotoframe.animation_services.ShivaFlower2;
import com.tinyapps7.clockphotoframe.animation_services.Snow_Particles;
import com.wang.avi.indicators.BallPulseIndicator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

     class ClockWallpaperService extends WallpaperService {
    private Bitmap backgroundBitmap_photo;
    private Bitmap background_photo;
    private Bitmap background_photo_viewpager;
    public Bitmap bgBitmap_photo;
    private Bitmap cBitmap;
    Canvas canvas;
    private String galImagePath_photo;
    Matrix matrix;
    private WallpaperEngine myEngine;
    SharedPreferences pref;
    private Bitmap samplebg;
    Matrix savedMatrix;
    private boolean swathi;
    private Bitmap text;
    private int value_photo;

    private class WallpaperEngine extends Engine implements OnSharedPreferenceChangeListener, OnDoubleTapListener, OnGestureListener {
        static final int DRAG = 1;
        private static final int DRAW_MSG = 0;
        private static final int HIGH_DPI_STATUS_BAR_HEIGHT = 38;
        private static final int LOW_DPI_STATUS_BAR_HEIGHT = 19;
        private static final int MEDIUM_DPI_STATUS_BAR_HEIGHT = 25;
        static final int NONE = 0;
        static final int ZOOM = 2;
        private int amount2;
        float angle;
        private int arou1;
        private String backgroundImagePath_photo;
        private String backgroundImagePath_photo_viewpager;
        private int birdsdir;
        private String birdsdirection;
        private Bitmap bitmap11;
        private Bitmap bitmap12;
        private Bitmap bitmap13;
        private Bitmap bitmap14;
        private Bitmap bitmap15;
        private Bitmap bitmap3;
        private Bitmap bitmap4;
        private Bitmap bitmap5;
        private Bitmap bitmap6;
        private Bitmap bitmap7;
        private Bitmap bitmap8;
        private Bitmap bitmap9;
        private Bitmap bubble1;
        private Bitmap bubble2;
        private ArrayList<Heart_ToUp> bubbleList;
        private int bubble_size;
        Calendar ca;
        Calendar cal;
        Calendar calTarget;
        private String cd;
        private int count;
        Date d1;
        Date d2;
        private Float degreeForOneHour;
        private Float degreeForOneMinuteOrSecond;
        private GestureDetector detector;
        long diffDays;
        long diffH;
        long diffMin;
        long diffSec;
        private boolean double1;
        private ArrayList<BottomBubble> fireflies;
        int height;
        private int heightOfCanvas;
        private Bitmap hoursBmp;
        private long interval;
        float[] lastEvent;
        private Bitmap leaf;
        private ArrayList<ShivaFlower2> leafList;
        private ArrayList<RosepetalsFalling> leafList1;
        private String mBgImageString_photo;
        private int mDisplayWidth;
        @SuppressLint({"HandlerLeak"})
        private Handler mHandler;
        float mOffset;
        private float mXOff;
        private int mXOffPix;
        private float mYOff;
        private int mYOffPix;
        private Bubble_to_up m_botton_top2;
        private Heart_ToUp m_heaart_up;
        private PhotoRain m_photo_rain;
        private int m_size;
        private Snow_Particles m_snow_particle;
        PointF mid;
        private long millisecsInDay;
        private Bitmap minutesBmp;
        int mode;
        private ArrayList<PhotoRain> myleafList;
        private ArrayList<Snow_Particles> myleafList1;
        float oldDist;
        private Paint paint;
        private Paint paint11;
        private Paint paint2;
        private Random rand;
        Point screenSize;
        boolean secneedel;
        private Bitmap secondsBmp;
        boolean showsparkle;
        PointF start;
        private int statusBarHeight;
        boolean symbolheart;
        Bubble_to_up tb2;
        private Bitmap temp;
        private Bitmap temp2;
        private Bitmap temp3;
        String textStyle;
        String title;
        private ArrayList<Bubble_to_up> tleafList2;
        private float touchX;
        private float touchY;
        Typeface tragic;
        int width;
        private int widthOfCanvas;
        Canvas lockCanvas;
        /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.ClockWallpaperService.WallpaperEngine.1 */
        class vkm extends Handler {
            vkm() {
            }

            @SuppressLint({"HandlerLeak"})
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case WallpaperEngine.NONE /*0*/:
                        try {
                            WallpaperEngine.this.drawPaper();
                        } catch (NullPointerException e) {
                        }
                    default:
                }
            }
        }

        WallpaperEngine() {

            this.millisecsInDay = 86400000;
            this.degreeForOneHour = Float.valueOf(30.0f);
            this.degreeForOneMinuteOrSecond = Float.valueOf(6.0f);
            this.rand = new Random();
            this.screenSize = new Point();
            this.mBgImageString_photo = null;
            this.d1 = null;
            this.d2 = null;
            this.tb2 = null;
            this.mXOff = 0.0f;
            this.mYOff = 0.0f;
            this.mXOffPix = NONE;
            this.mYOffPix = NONE;
            this.mode = NONE;
            this.start = new PointF();
            this.mid = new PointF();
            this.lastEvent = null;
            this.oldDist = BallPulseIndicator.SCALE;
            this.mHandler = new vkm();
            this.interval = 10;
            this.mDisplayWidth = ClockWallpaperService.this.getResources().getDisplayMetrics().widthPixels;
            Display defaultDisplay = ((WindowManager) ClockWallpaperService.this.getApplicationContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
            this.screenSize.x = defaultDisplay.getWidth();
            this.screenSize.y = defaultDisplay.getHeight();
        }

        private void bubbleCount() {
            Iterator it = this.bubbleList.iterator();
            while (it.hasNext()) {
                ((Heart_ToUp) it.next()).setCount();
            }
            it = this.tleafList2.iterator();
            while (it.hasNext()) {
                ((Bubble_to_up) it.next()).setCount();
            }
            it = this.myleafList.iterator();
            while (it.hasNext()) {
                ((PhotoRain) it.next()).setCount();
            }
            it = this.myleafList1.iterator();
            while (it.hasNext()) {
                ((Snow_Particles) it.next()).setCount();
            }
            it = this.fireflies.iterator();
            while (it.hasNext()) {
                ((BottomBubble) it.next()).setCount();
            }
        }

        private void changeBgImagePref(String str) {
            this.mBgImageString_photo = str;
            System.gc();
            loadBgBitmap();
        }

        private void drawBackground(Canvas canvas) {
            if (!ClockWallpaperService.this.swathi) {
                canvas.drawBitmap(ClockWallpaperService.this.samplebg, new Rect(NONE, NONE, ClockWallpaperService.this.samplebg.getWidth(), ClockWallpaperService.this.samplebg.getHeight()), new Rect(NONE, NONE, this.width, this.height), null);
                canvas.drawBitmap(ClockWallpaperService.this.background_photo_viewpager, new Rect(NONE, NONE, ClockWallpaperService.this.background_photo_viewpager.getWidth(), ClockWallpaperService.this.background_photo_viewpager.getHeight()), new Rect(NONE, NONE, this.width, this.height), null);
            } else if (ClockWallpaperService.this.background_photo == null) {
                canvas.save();
                canvas.drawBitmap(ClockWallpaperService.this.backgroundBitmap_photo, new Rect(NONE, NONE, ClockWallpaperService.this.backgroundBitmap_photo.getWidth(), ClockWallpaperService.this.backgroundBitmap_photo.getHeight()), new Rect(NONE, NONE, this.width, this.height), null);
                canvas.restore();
            } else if (ClockWallpaperService.this.value_photo == 0) {
                canvas.drawBitmap(ClockWallpaperService.this.bgBitmap_photo, (float) this.mXOffPix, (float) this.mYOffPix, null);
            } else if (ClockWallpaperService.this.value_photo == DRAG) {
                canvas.drawBitmap(ClockWallpaperService.this.samplebg, new Rect(NONE, NONE, ClockWallpaperService.this.background_photo.getWidth(), ClockWallpaperService.this.background_photo.getHeight()), new Rect(NONE, NONE, this.width, this.height), null);
                canvas.drawBitmap(ClockWallpaperService.this.background_photo, new Rect(NONE, NONE, ClockWallpaperService.this.background_photo.getWidth(), ClockWallpaperService.this.background_photo.getHeight()), new Rect(NONE, NONE, this.width, this.height), null);
            }
        }

        private void drawPaper() {
            SurfaceHolder surfaceHolder;
            Throwable th;
            Throwable th2;
            this.count += DRAG;
            if (this.count >= 10000) {
                this.count = NONE;
            }
            if (this.symbolheart) {
                if (this.count % 5 == 0 && this.tleafList2.size() < 10)//..PhotoOnClockSettings.bubblecount1
                     {
                    this.temp3 = this.bubble1;
                    this.tb2 = new Bubble_to_up(this.temp3, this.heightOfCanvas, this.widthOfCanvas);
                    this.tleafList2.add(this.tb2);
                }
                //PhotoOnClockSettings.bubblecount1 =10
                if (this.bubbleList.size() < 100) {
                    this.temp3 = this.bubble2;
                    this.bubbleList.add(new Heart_ToUp(this.temp3, this.heightOfCanvas, this.widthOfCanvas, this.bubble_size));
                }
                if (this.count % 5 == 0 && this.myleafList1.size() < 10) {
                    this.temp2 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.snowflake0);
                    this.myleafList1.add(new Snow_Particles(this.temp2, this.heightOfCanvas, this.widthOfCanvas));
                }
                if (this.count % 5 == 0 && this.myleafList.size() < 20) {
                    this.myleafList.add(new PhotoRain(BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.rainbig), this.widthOfCanvas, this.heightOfCanvas));
                }
                if (this.count % 5 == 0 && this.leafList.size() < 20) {
                    this.temp2 = this.bitmap3;
                    switch (this.rand.nextInt(5) + DRAG) {
                        case DRAG /*1*/:
                            this.temp2 = this.bitmap3;
                            break;
                        case ZOOM /*2*/:
                            this.temp2 = this.bitmap4;
                            break;
                        case R.styleable.View_paddingEnd /*3*/:
                            this.temp2 = this.bitmap5;
                            break;
                        case R.styleable.View_theme /*4*/:
                            this.temp2 = this.bitmap6;
                            break;
                        default:
                            this.temp2 = this.bitmap4;
                            break;
                    }
                    this.leafList.add(new ShivaFlower2(this.temp2, this.heightOfCanvas, this.widthOfCanvas));
                }
                if (this.count % 5 == 0 && this.leafList1.size() < 20) {
                    this.temp2 = this.bitmap7;
                    switch (this.rand.nextInt(4) + DRAG) {
                        case DRAG /*1*/:
                            this.temp2 = this.bitmap7;
                            break;
                        case ZOOM /*2*/:
                            this.temp2 = this.bitmap8;
                            break;
                        case R.styleable.View_paddingEnd /*3*/:
                            this.temp2 = this.bitmap9;
                            break;
                        default:
                            this.temp2 = this.bitmap8;
                            break;
                    }
                    this.leafList1.add(new RosepetalsFalling(this.temp2, this.heightOfCanvas, this.widthOfCanvas));
                }
                if (this.count % 5 == 0 && this.fireflies.size() < 20) {
                    this.temp2 = this.bitmap11;
                    switch (this.rand.nextInt(5) + DRAG) {
                        case DRAG /*1*/:
                            this.temp2 = this.bitmap11;
                            break;
                        case ZOOM /*2*/:
                            this.temp2 = this.bitmap12;
                            break;
                        case R.styleable.View_paddingEnd /*3*/:
                            this.temp2 = this.bitmap13;
                            break;
                        case R.styleable.View_theme /*4*/:
                            this.temp2 = this.bitmap14;
                            break;
                        default:
                            this.temp2 = this.bitmap15;
                            break;
                    }
                    this.fireflies.add(new BottomBubble(this.temp2, this.heightOfCanvas, this.widthOfCanvas));
                }
            }
            SurfaceHolder surfaceHolder2 = getSurfaceHolder();
            Canvas canvas = null;

            try {
                this.title = PhotoOnClockSettings.currentvalue;
                surfaceHolder = getSurfaceHolder();
                try {
                    lockCanvas = surfaceHolder.lockCanvas();
                } catch (Exception e) {
                    surfaceHolder2 = surfaceHolder;
                    if (canvas == null) {
                        try {
                            surfaceHolder2.unlockCanvasAndPost(canvas);
                            this.mHandler.sendEmptyMessageDelayed(NONE, this.interval);
                        } catch (IllegalArgumentException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    lockCanvas = null;
                    th2 = th;
                    if (lockCanvas != null) {
                        try {
                            surfaceHolder.unlockCanvasAndPost(lockCanvas);
                            this.mHandler.sendEmptyMessageDelayed(NONE, this.interval);
                        } catch (IllegalArgumentException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th2;
                }
                try {
                    drawBackground(lockCanvas);
                    Display defaultDisplay = ((WindowManager) ClockWallpaperService.this.getApplicationContext().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                    Point point = new Point();
                    point.x = defaultDisplay.getWidth();
                    point.y = defaultDisplay.getHeight();
                  //  Log.e("vkm","Display HXW"+lockCanvas.getWidth()+"+=+"+lockCanvas.getHeight());
                    int width = (lockCanvas.getWidth() / ZOOM) - (this.leaf.getWidth() / ZOOM);
                    int height = (lockCanvas.getHeight() / 3) - (this.leaf.getHeight() / ZOOM);
                  //  Log.e("vkm","Display HXW"+width+"+=+"+height);
                    lockCanvas.save();
                    lockCanvas.translate(0.0f, (float) this.statusBarHeight);
                    Calendar instance = Calendar.getInstance();
                    long timeInMillis = (instance.getTimeInMillis() + ((long) (instance.get(Calendar.MINUTE) + instance.get(Calendar.HOUR)))) % this.millisecsInDay;
                    Float valueOf;
                    Float valueOf2;
                    Float valueOf3;
                    if (this.secneedel) {
                        valueOf = Float.valueOf((((float) timeInMillis) / 1000.0f) % 60.0f);
                        Float valueOf4 = Float.valueOf((((float) timeInMillis) / 60000.0f) % 60.0f);
                        valueOf2 = Float.valueOf(((float) timeInMillis) / 3600000.0f);
                        valueOf3 = Float.valueOf(this.degreeForOneMinuteOrSecond.floatValue() * valueOf.floatValue());
                        valueOf = Float.valueOf(this.degreeForOneMinuteOrSecond.floatValue() * valueOf4.floatValue());
                        valueOf2 = Float.valueOf(valueOf2.floatValue() * this.degreeForOneHour.floatValue());

                        Log.e("vkm","Value of "+valueOf);
                        Log.e("vkm","valueOf2"+valueOf2);
                        Log.e("vkm","valueOf3"+valueOf3);
                        this.paint.setFilterBitmap(true);
                        lockCanvas.save();
                        if (ClockWallpaperService.this.swathi) {
                            if (ClockWallpaperService.this.cBitmap != null) {
                                lockCanvas.drawBitmap(ClockWallpaperService.this.cBitmap, (float) width, (float) height, this.paint);
                                lockCanvas.drawBitmap(this.leaf, (float) width, (float) height, this.paint);
                            } else {
                                lockCanvas.drawBitmap(this.leaf, (float) width, (float) height, this.paint);
                            }
                            lockCanvas.restore();
                            this.angle += (float) this.amount2;
                            lockCanvas.translate((float) (width + (this.leaf.getWidth() / ZOOM)), (float) (height + (this.leaf.getHeight() / ZOOM)));
                            lockCanvas.drawBitmap(this.minutesBmp, getRotator(valueOf, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.hoursBmp, getRotator(valueOf2, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.secondsBmp, getRotator(valueOf3, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.restore();
                        } else {
                            this.angle += (float) this.amount2;
                            lockCanvas.translate((float) (width + (this.leaf.getWidth() / ZOOM)), ((float) height) + (((float) this.leaf.getHeight()) / 2.0f));
                            lockCanvas.drawBitmap(this.minutesBmp, getRotator(valueOf, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.hoursBmp, getRotator(valueOf2, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.secondsBmp, getRotator(valueOf3, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.restore();
                        }
                    } else {
                        long currentTimeMillis = (System.currentTimeMillis() / 1000) % 60;
                        Float valueOf5 = Float.valueOf((((float) timeInMillis) / 60000.0f) % 60.0f);
                        valueOf2 = Float.valueOf(((float) timeInMillis) / 3600000.0f);
                        valueOf3 = Float.valueOf(this.degreeForOneMinuteOrSecond.floatValue() * ((float) currentTimeMillis));
                        valueOf = Float.valueOf(this.degreeForOneMinuteOrSecond.floatValue() * valueOf5.floatValue());
                        //valueOf2 = Float.valueOf(valueOf2.floatValue() * this.degreeForOneHour.floatValue());
                        Log.e("vkm"," "+valueOf);
                        Log.e("vkm",""+valueOf2);
                        Log.e("vkm",""+valueOf3);
                        lockCanvas.save();
                        if (ClockWallpaperService.this.swathi) {
                            if (ClockWallpaperService.this.cBitmap != null) {
                                lockCanvas.drawBitmap(ClockWallpaperService.this.cBitmap, (float) width, (float) height, this.paint);
                                lockCanvas.drawBitmap(this.leaf, (float) width, (float) height, this.paint);
                            } else {
                                lockCanvas.drawBitmap(this.leaf, (float) width, (float) height, this.paint);
                            }
                            lockCanvas.restore();
                            this.angle += (float) this.amount2;
                            lockCanvas.translate((float) (width + (this.leaf.getWidth() / ZOOM)), (float) (height + (this.leaf.getHeight() / ZOOM)));
                            lockCanvas.drawBitmap(this.minutesBmp, getRotator(valueOf, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.hoursBmp, getRotator(valueOf2, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.secondsBmp, getRotator(valueOf3, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.restore();
                        } else {
                            this.angle += (float) this.amount2;
                            lockCanvas.translate((float) (width + (this.leaf.getWidth() / ZOOM)), ((float) height) + (((float) this.leaf.getHeight()) / 2.0f));
                            lockCanvas.drawBitmap(this.minutesBmp, getRotator(valueOf, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.hoursBmp, getRotator(valueOf2, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.drawBitmap(this.secondsBmp, getRotator(valueOf3, this.leaf.getWidth(), this.leaf.getHeight()), this.paint2);
                            lockCanvas.restore();
                        }
                    }
                    if (this.symbolheart) {
                        switch (this.birdsdir) {
                            case NONE /*0*/://buble count
                                this.m_size = Math.min(20, this.myleafList1.size());
                                for (height = NONE; height < this.m_size; height += DRAG) {
                                    this.m_snow_particle = (Snow_Particles) this.myleafList1.get(height);
                                    if (this.m_snow_particle.isTouched()) {
                                        this.m_snow_particle.handleTouched(this.touchX, this.touchY);
                                    } else {
                                        this.m_snow_particle.handleFalling(true);
                                    }
                                    this.m_snow_particle.drawLeaf(lockCanvas);
                                }
                                break;
                            case DRAG /*1*/:
                                this.m_size = Math.min(20, this.leafList1.size());
                                for (height = NONE; height < this.m_size; height += DRAG) {
                                    RosepetalsFalling rosepetalsFalling = (RosepetalsFalling) this.leafList1.get(height);
                                    if (rosepetalsFalling.isTouched()) {
                                        rosepetalsFalling.handleTouched(this.touchX, this.touchY);
                                    } else {
                                        rosepetalsFalling.handleFalling(true);
                                    }
                                    rosepetalsFalling.drawLeaf(lockCanvas, this.paint);
                                }
                                break;
                            case ZOOM /*2*/:
                                this.m_size = Math.min(20, this.tleafList2.size());
                                for (height = NONE; height < this.m_size; height += DRAG) {
                                    this.m_botton_top2 = (Bubble_to_up) this.tleafList2.get(height);
                                    if (this.m_botton_top2.isTouched()) {
                                        this.m_botton_top2.handleTouched(this.touchX, this.touchY);
                                    } else {
                                        this.m_botton_top2.handleFalling(false);
                                    }
                                    this.m_botton_top2.drawLeaf(lockCanvas);
                                }
                                break;
                            case R.styleable.View_paddingEnd /*3*/:
                                this.m_size = Math.min(20, this.fireflies.size());
                                for (height = NONE; height < this.m_size; height += DRAG) {
                                    BottomBubble bottomBubble = (BottomBubble) this.fireflies.get(height);
                                    if (bottomBubble.isTouched()) {
                                        bottomBubble.handleTouched(this.touchX, this.touchY);
                                    } else {
                                        bottomBubble.handleFalling();
                                    }
                                    bottomBubble.drawLeaf(lockCanvas);
                                }
                                break;
                            case R.styleable.View_theme /*4*/:
                                this.m_size = Math.min(20, this.leafList.size());
                                for (height = NONE; height < this.m_size; height += DRAG) {
                                    ShivaFlower2 shivaFlower2 = (ShivaFlower2) this.leafList.get(height);
                                    if (shivaFlower2.isTouched()) {
                                        shivaFlower2.handleTouched(this.touchX, this.touchY);
                                    } else {
                                        shivaFlower2.handleFalling(true);
                                    }
                                    shivaFlower2.drawLeaf(lockCanvas, this.paint);
                                }
                                break;
                            case 5/*5*/:
                                this.m_size = Math.min(20, this.myleafList.size());
                                for (height = NONE; height < this.m_size; height += DRAG) {
                                    this.m_photo_rain = (PhotoRain) this.myleafList.get(height);
                                    if (this.m_photo_rain.isTouched()) {
                                        this.m_photo_rain.handleTouched(this.touchX, this.touchY);
                                    } else {
                                        this.m_photo_rain.handleFalling(true);
                                    }
                                    this.m_photo_rain.drawLeaf(lockCanvas);
                                }
                                break;
                            case R.styleable.Toolbar_contentInsetEnd /*6*/:
                                this.m_size = Math.min(20, this.bubbleList.size());
                                for (height = NONE; height < this.m_size; height += DRAG) {
                                    this.m_heaart_up = (Heart_ToUp) this.bubbleList.get(height);
                                    if (this.m_heaart_up.isTouched()) {
                                        this.m_heaart_up.handleTouched(this.touchX, this.touchY);
                                    } else {
                                        this.m_heaart_up.handleFalling();
                                    }
                                    this.m_heaart_up.drawLeaf(lockCanvas, this.paint);
                                }
                                break;
                        }
                    }
                    float width2 = (float) (lockCanvas.getWidth() / ZOOM);
                    this.paint11.setTextAlign(Align.CENTER);
                    this.paint11.setTypeface(this.tragic);
                    this.paint11.setColor(-1);
                    Paint paint = this.paint11;
                    Object[] objArr = new Object[DRAG];
                    objArr[NONE] = Integer.valueOf(Digital_Clock_Service.BG_COLOR & this.arou1);
                    paint.setColor(Color.parseColor(String.format("#%06X", objArr)));
                    this.d1 = Calendar.getInstance().getTime();
                    timeInMillis = this.d2.getTime() - this.d1.getTime();
                    this.diffSec = (timeInMillis / 1000) % 60;
                    this.diffMin = (timeInMillis / 60000) % 60;
                    this.diffH = (timeInMillis / 3600000) % 24;
                    this.diffDays = timeInMillis / 86400000;
                    this.ca = Calendar.getInstance();
                    this.cal = new GregorianCalendar(this.ca.get(DRAG), 6, 18);
                    if (this.cd.equals("0")) {
                        if (timeInMillis > 0) {
                            lockCanvas.drawText(this.title, width2, (float) (lockCanvas.getHeight() / 8), this.paint11);
                            if (this.diffDays == 1) {
                                lockCanvas.drawText(this.diffDays + " Day  " + this.diffH + "H:" + this.diffMin + "M:" + this.diffSec + "S", width2, (float) (lockCanvas.getHeight() / 5), this.paint11);
                            } else if (this.diffDays != 0) {
                                lockCanvas.drawText(this.diffDays + " Day  " + this.diffH + "H:" + this.diffMin + "M:" + this.diffSec + "S", width2, (float) (lockCanvas.getHeight() / 5), this.paint11);
                            } else if (this.diffH != 0) {
                                lockCanvas.drawText(this.diffH + "H:" + this.diffMin + "M:" + this.diffSec + "S", width2, (float) (lockCanvas.getHeight() / 5), this.paint11);
                            } else if (this.diffMin != 0) {
                                lockCanvas.drawText(this.diffMin + "M:" + this.diffSec + "S", width2, (float) (lockCanvas.getHeight() / 5), this.paint11);
                            } else if (this.diffSec != 0) {
                                lockCanvas.drawText(this.diffSec + "S", width2, (float) (lockCanvas.getHeight() / 5), this.paint11);
                            }
                        } else if (this.cd.equals("1")) {
                        }
                    }
                    if (ClockWallpaperService.this.text != null) {
                        lockCanvas.save();
                        lockCanvas.drawBitmap(ClockWallpaperService.this.text, new Rect(NONE, NONE, ClockWallpaperService.this.text.getWidth(), ClockWallpaperService.this.text.getHeight()), new Rect(NONE, NONE, this.width, this.height), null);
                        lockCanvas.restore();
                    }
                    if (lockCanvas != null) {
                        try {
                            surfaceHolder.unlockCanvasAndPost(lockCanvas);
                            this.mHandler.sendEmptyMessageDelayed(NONE, this.interval);
                        } catch (IllegalArgumentException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Exception e4) {
                    canvas = lockCanvas;
                    surfaceHolder2 = surfaceHolder;
                    if (canvas == null) {
                        surfaceHolder2.unlockCanvasAndPost(canvas);
                        this.mHandler.sendEmptyMessageDelayed(NONE, this.interval);
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    if (lockCanvas != null) {
                        surfaceHolder.unlockCanvasAndPost(lockCanvas);
                        this.mHandler.sendEmptyMessageDelayed(NONE, this.interval);
                    }
                    throw th2;
                }
            } catch (Exception e5) {
                if (canvas == null) {
                    surfaceHolder2.unlockCanvasAndPost(canvas);
                    this.mHandler.sendEmptyMessageDelayed(NONE, this.interval);
                }
            } catch (Throwable th5) {
                th = th5;
                surfaceHolder = surfaceHolder2;
                lockCanvas = null;
                th2 = th;
                if (lockCanvas != null) {
                    surfaceHolder.unlockCanvasAndPost(lockCanvas);
                    this.mHandler.sendEmptyMessageDelayed(NONE, this.interval);
                }
                try {
                    throw th2;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }

        private float getScaleDimension(Options options, int i, int i2) {
            float f = ((float) this.mDisplayWidth) / ((float) i2);
            return ((float) i) * f < ((float) (this.mDisplayWidth * ZOOM)) ? ((float) (this.mDisplayWidth * ZOOM)) / ((float) i) : f;
        }

        private void loadBgBitmap() {
            if (this.mBgImageString_photo != null) {
                Bitmap imageFilePathToBitmap = PhotoBonkUtil.imageFilePathToBitmap(this.mBgImageString_photo, Math.max(this.widthOfCanvas, this.heightOfCanvas));
                if (imageFilePathToBitmap != null) {
                    ClockWallpaperService.this.bgBitmap_photo = scaleBgBitmap(imageFilePathToBitmap);
                }
                if (ClockWallpaperService.this.bgBitmap_photo != null) {
                    this.mXOffPix = (int) (this.mXOff * ((float) (this.widthOfCanvas - ClockWallpaperService.this.bgBitmap_photo.getWidth())));
                    this.mYOffPix = (int) (this.mYOff * ((float) (this.heightOfCanvas - ClockWallpaperService.this.bgBitmap_photo.getHeight())));
                }
            }
        }

        private void midPoint(PointF pointF, MotionEvent motionEvent) {
            pointF.set((motionEvent.getX(NONE) + motionEvent.getX(DRAG)) / 4.0f, (motionEvent.getY(NONE) + motionEvent.getY(DRAG)) / 4.0f);
        }

        private Bitmap scaleBgBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            double d = (double) width;
            width = (int) (d * (((double) this.heightOfCanvas) / ((double) bitmap.getHeight())));
            if (width < this.widthOfCanvas) {
                width = this.widthOfCanvas;
            }
            return Bitmap.createScaledBitmap(bitmap, width, this.heightOfCanvas, false);
        }

        private float spacing(MotionEvent motionEvent) {
            float x = motionEvent.getX(NONE) - motionEvent.getX(DRAG);
            float y = motionEvent.getY(NONE) - motionEvent.getY(DRAG);
            return (float) Math.sqrt((double) ((x * x) + (y * y)));
        }


        Bitmap StringToBitMap(String str) {
            try {
                byte[] decode = Base64.decode(str, NONE);
                return BitmapFactory.decodeByteArray(decode, NONE, decode.length);
            } catch (Exception e) {
                e.getMessage();
                return null;
            }
        }

        Bitmap decodeFile(String str) {
            Bitmap bitmap = null;
            Options options = new Options();
            options.inSampleSize = DRAG;
            while (options.inSampleSize <= 32) {
                try {
                    bitmap = BitmapFactory.decodeFile(str, options);
                    break;
                } catch (OutOfMemoryError e) {
                    options.inSampleSize += DRAG;
                }
            }
            return bitmap;
        }

        Bitmap decodeSampledBitmapFromResource(Resources resources, int i) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, i, options);
            int i2 = options.outHeight;
            int i3 = options.outWidth;
            float scaleDimension = getScaleDimension(options, i3, i2);
            i3 = (int) (((float) i3) * scaleDimension);
            int i4 = (int) (scaleDimension * ((float) i2));
            Options options2 = new Options();
            options2.inDither = false;
            options2.inPurgeable = true;
            options2.inInputShareable = true;
            Bitmap decodeResource = BitmapFactory.decodeResource(resources, i, options2);
            while (true) {
                try {
                    break;
                } catch (OutOfMemoryError e) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e2) {
                        e.printStackTrace();
                    }
                }
            }
            return Bitmap.createScaledBitmap(decodeResource, i3, i4, false);
        }

        Matrix getRotator(Float f, int i, int i2) {
            Matrix matrix = new Matrix();
            int i3 = i / ZOOM;
            int i4 = i2 / ZOOM;
            matrix.postRotate(f.floatValue(), (float) i3, (float) i4);
            matrix.postTranslate((float) (-i3), (float) (-i4));
            return matrix;
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            ClockWallpaperService.this.matrix = new Matrix();
            ClockWallpaperService.this.savedMatrix = new Matrix();
            ClockWallpaperService.this.pref = PreferenceManager.getDefaultSharedPreferences(ClockWallpaperService.this);
            ClockWallpaperService.this.pref.registerOnSharedPreferenceChangeListener(this);
            ClockWallpaperService.this.swathi = ClockWallpaperService.this.pref.getBoolean("swathi", true);
            Log.e("vkm","Swathi"+ClockWallpaperService.this.swathi);
            this.tleafList2 = new ArrayList();
            this.myleafList1 = new ArrayList();
            this.myleafList = new ArrayList();
            this.bubbleList = new ArrayList();
            this.fireflies = new ArrayList();
            this.leafList = new ArrayList();
            this.leafList1 = new ArrayList();
            this.paint = new Paint();
            this.bubble2 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.heartupp);
            this.bitmap3 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.shivaflower4);
            this.bitmap4 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.shivaflower2);
            this.bitmap5 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.shivaflower3);
            this.bitmap6 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.shivaflower1);
            this.bitmap7 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.rose3);
            this.bitmap8 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.rose2);
            this.bitmap9 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.rose3);
            this.bitmap11 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.s1);
            this.bitmap12 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.s4);
            this.bitmap13 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.s6);
            this.bitmap14 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.s9);
            this.bitmap15 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.s10);
            this.bubble1 = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.drawable.waterbubble_2);
            Constants.posval = ClockWallpaperService.this.pref.getInt("photo_clock_pos", NONE);
            updateclock(Constants.posval);
            Log.e("vkm",""+Constants.posval);
            this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr1);
            this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min1);
            this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec1);
            this.temp = BitmapFactory.decodeFile(ClockWallpaperService.this.pref.getString("clockimg_photo", null));
            ClockWallpaperService.this.cBitmap = Bitmap.createBitmap(this.leaf.getWidth(), this.leaf.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(ClockWallpaperService.this.cBitmap);
            if (this.temp != null) {
                canvas.drawBitmap(this.temp, 0.0f, 0.0f, null);
            }
            ClockWallpaperService.this.text = StringToBitMap(ClockWallpaperService.this.pref.getString("textpath", null));
            Bitmap decodeSampledBitmapFromResource = decodeSampledBitmapFromResource(ClockWallpaperService.this.getResources(), Constants.clockId2);
            ClockWallpaperService.this.backgroundBitmap_photo = Bitmap.createScaledBitmap(decodeSampledBitmapFromResource, decodeSampledBitmapFromResource.getWidth(), decodeSampledBitmapFromResource.getHeight(), true);
            this.paint.setAntiAlias(true);
            this.paint.setFilterBitmap(true);
            this.paint2 = new Paint();
            this.paint2.setAntiAlias(true);
            this.paint2.setFilterBitmap(true);
            this.paint11 = new Paint();
            this.paint11.setAntiAlias(true);
            this.paint11.setAlpha(255);
            this.paint11.setTextSize(38.0f);
            this.count = -1;
            this.detector = new GestureDetector(this);
            this.touchX = -1.0f;
            this.touchY = -1.0f;
            this.secneedel = ClockWallpaperService.this.pref.getBoolean("secneedel_photo", false);
            this.cd = ClockWallpaperService.this.pref.getString("countdown_photo", "1");
            ClockWallpaperService.this.pref.getString("clock_color_photo", "1");
            this.arou1 = ClockWallpaperService.this.pref.getInt("text_color_photo", Digital_Clock_Service.BG_COLOR);
            this.textStyle = ClockWallpaperService.this.pref.getString("style_photo", "0");
            this.showsparkle = ClockWallpaperService.this.pref.getBoolean("sparkle_photo", true);
            this.bubble_size = ClockWallpaperService.this.pref.getInt("bubblesize_photo", NONE);
            ClockWallpaperService.this.pref.getString("wq_photo", "0");
            this.double1 = ClockWallpaperService.this.pref.getBoolean("double", true);
            this.birdsdirection = ClockWallpaperService.this.pref.getString("Heart_direction_photo", "0");
            this.birdsdir = Integer.parseInt(this.birdsdirection);
            this.symbolheart = ClockWallpaperService.this.pref.getBoolean("animations_photo", true);
            setTouchEventsEnabled(true);
            textstyle();
            this.leaf = decodeSampledBitmapFromResource(ClockWallpaperService.this.getResources(), R.mipmap.clock1);
            ClockWallpaperService.this.samplebg = decodeSampledBitmapFromResource(ClockWallpaperService.this.getResources(), Constants.digital_clockId2);
            this.leaf.getWidth();
            this.backgroundImagePath_photo = ClockWallpaperService.this.pref.getString("imagepath_photo", "0");
            ClockWallpaperService.this.background_photo = BitmapFactory.decodeFile(this.backgroundImagePath_photo);
            this.backgroundImagePath_photo_viewpager = ClockWallpaperService.this.pref.getString("imagepath_photo_viewpager", "0");
            ClockWallpaperService.this.background_photo_viewpager = StringToBitMap(this.backgroundImagePath_photo_viewpager);
            ClockWallpaperService.this.value_photo = ClockWallpaperService.this.pref.getInt("swipevalue_photo", NONE);
            ClockWallpaperService.this.galImagePath_photo = ClockWallpaperService.this.pref.getString("galimagepath_photo", "0");
            ClockWallpaperService.this.bgBitmap_photo = decodeFile(ClockWallpaperService.this.galImagePath_photo);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) ClockWallpaperService.this.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            switch (displayMetrics.densityDpi) {
                case 120:
                    this.statusBarHeight = LOW_DPI_STATUS_BAR_HEIGHT;
                case 160:
                    this.statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
                case 240:
                    this.statusBarHeight = HIGH_DPI_STATUS_BAR_HEIGHT;
                default:
                    this.statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
            }
        }

        public void onDestroy() {
            this.mHandler.removeMessages(NONE);
            PreferenceManager.getDefaultSharedPreferences(ClockWallpaperService.this).unregisterOnSharedPreferenceChangeListener(this);
            System.gc();
            super.onDestroy();
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (false) {
                Intent intent = new Intent(ClockWallpaperService.this.getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ClockWallpaperService.this.startActivity(intent);
            }
            return false;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            int i;
            float x;
            float y;
            int i2 = NONE;
            this.touchX = motionEvent.getX();
            this.touchY = motionEvent.getY();
            int min = Math.min(PhotoOnClockSettings.bubblecount1, this.tleafList2.size());
            for (i = NONE; i < min; i += DRAG) {
                Bubble_to_up bubble_to_up = (Bubble_to_up) this.tleafList2.get(i);
                    x = bubble_to_up.getX() + (((float) bubble_to_up.getBitmap().getWidth()) / 2.0f);
                 y = bubble_to_up.getY() + (((float) bubble_to_up.getBitmap().getHeight()) / 2.0f);
                if (!bubble_to_up.isTouched() && Math.abs(x - this.touchX) <= 80.0f && Math.abs(y - this.touchY) <= 80.0f && x != this.touchX) {
                    bubble_to_up.setTouched(true);
                }
            }
            min = Math.min(PhotoOnClockSettings.bubblecount1, this.bubbleList.size());
            for (i = NONE; i < min; i += DRAG) {
                Heart_ToUp heart_ToUp = (Heart_ToUp) this.bubbleList.get(i);
                x = heart_ToUp.getX() + (((float) heart_ToUp.getBitmap().getWidth()) / 2.0f);
                y = heart_ToUp.getY() + (((float) heart_ToUp.getBitmap().getHeight()) / 2.0f);
                if (!heart_ToUp.isTouched() && Math.abs(x - this.touchX) <= 100.0f && Math.abs(y - this.touchY) <= 100.0f && x != this.touchX) {
                    heart_ToUp.setTouched(true);
                }
            }
            min = Math.min(PhotoOnClockSettings.bubblecount1, this.myleafList1.size());
            for (i = NONE; i < min; i += DRAG) {
                Snow_Particles snow_Particles = (Snow_Particles) this.myleafList1.get(i);
                x = snow_Particles.getX() + (((float) snow_Particles.getBitmap().getWidth()) / 2.0f);
                y = snow_Particles.getY() + (((float) snow_Particles.getBitmap().getHeight()) / 2.0f);
                if (!snow_Particles.isTouched() && Math.abs(x - this.touchX) <= 80.0f && Math.abs(y - this.touchY) <= 80.0f && x != this.touchX) {
                    snow_Particles.setTouched(true);
                }
            }
            min = Math.min(20, this.leafList.size());
            for (i = NONE; i < min; i += DRAG) {
                ShivaFlower2 shivaFlower2 = (ShivaFlower2) this.leafList.get(i);
                x = shivaFlower2.getX() + (((float) shivaFlower2.getBitmap().getWidth()) / 2.0f);
                y = shivaFlower2.getY() + (((float) shivaFlower2.getBitmap().getHeight()) / 2.0f);
                if (!shivaFlower2.isTouched() && Math.abs(x - this.touchX) <= 80.0f && Math.abs(y - this.touchY) <= 80.0f && x != this.touchX) {
                    shivaFlower2.setTouched(true);
                }
            }
            min = Math.min(20, this.leafList1.size());
            for (i = NONE; i < min; i += DRAG) {
                RosepetalsFalling rosepetalsFalling = (RosepetalsFalling) this.leafList1.get(i);
                x = rosepetalsFalling.getX() + (((float) rosepetalsFalling.getBitmap().getWidth()) / 2.0f);
                y = rosepetalsFalling.getY() + (((float) rosepetalsFalling.getBitmap().getHeight()) / 2.0f);
                if (!rosepetalsFalling.isTouched() && Math.abs(x - this.touchX) <= 80.0f && Math.abs(y - this.touchY) <= 80.0f && x != this.touchX) {
                    rosepetalsFalling.setTouched(true);
                }
            }
            i = Math.min(20, this.fireflies.size());
            while (i2 < i) {
                BottomBubble bottomBubble = (BottomBubble) this.fireflies.get(i2);
                float x2 = bottomBubble.getX() + (((float) bottomBubble.getBitmap().getWidth()) / 2.0f);
                x = bottomBubble.getY() + (((float) bottomBubble.getBitmap().getHeight()) / 2.0f);
                if (!bottomBubble.isTouched() && Math.abs(x2 - this.touchX) <= 80.0f && Math.abs(x - this.touchY) <= 80.0f && x2 != this.touchX) {
                    bottomBubble.setTouched(true);
                }
                i2 += DRAG;
            }
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
        }

        public void onOffsetsChanged(float f, float f2, float f3, float f4, int i, int i2) {
            this.mOffset = f;
            this.mXOff = f;
            this.mYOff = f2;
            if (ClockWallpaperService.this.bgBitmap_photo != null) {
                this.mXOffPix = (int) (this.mXOff * ((float) (this.widthOfCanvas - ClockWallpaperService.this.bgBitmap_photo.getWidth())));
                this.mYOffPix = (int) (this.mYOff * ((float) (this.heightOfCanvas - ClockWallpaperService.this.bgBitmap_photo.getHeight())));
            }
            super.onOffsetsChanged(f, f2, f3, f4, i, i2);
            drawBackground(ClockWallpaperService.this.canvas);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            boolean z = true;
            Log.e("vkm","Str Shared"+str.hashCode());
            switch (str.hashCode()) {
                case -2070212699:
                    if (str.equals("clock_color_photo")) {
                        z = false;
                        break;
                    }
                    break;
                case -1816501176:
                    if (str.equals("bubblenumber_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case -1325958191:
                    if (str.equals("double")) {
                        z = true;
                        break;
                    }
                    break;
                case -1023004160:
                    if (str.equals("bubblesize_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case -961297852:
                    if (str.equals("text_color_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case -632697180:
                    if (str.equals("countdown_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case 44561956:
                    if (str.equals("style_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case 74494465:
                    if (str.equals("secneedel_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case 148288969:
                    if (str.equals("sparkle_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case 661395289:
                    if (str.equals("Heart_direction_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case 850388834:
                    if (str.equals("animations_photo")) {
                        z = true;
                        break;
                    }
                    break;
                case 1271112845:
                    if (str.equals("wq_photo")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch ("vkm") {
//                case NONE /*0*/:
//                    sharedPreferences.getString(str, "1");
//                case DRAG /*1*/:
//                    this.secneedel = sharedPreferences.getBoolean(str, false);
//                case ZOOM /*2*/:
//                    this.arou1 = sharedPreferences.getInt(str, Digital_Clock_Service.BG_COLOR);
//                case R.styleable.View_paddingEnd /*3*/:
//                    this.cd = sharedPreferences.getString(str, "1");
//                case R.styleable.View_theme /*4*/:
//                    this.textStyle = sharedPreferences.getString(str, "0");
//                    textstyle();
//                case true /*5*/:
//                    this.symbolheart = sharedPreferences.getBoolean(str, true);
//                    if (!this.symbolheart) {
//                        this.tleafList2.removeAll(this.tleafList2);
//                        this.myleafList.removeAll(this.myleafList);
//                        this.myleafList1.removeAll(this.myleafList1);
//                        this.leafList.removeAll(this.leafList);
//                        this.leafList1.removeAll(this.leafList1);
//                        this.fireflies.removeAll(this.fireflies);
//                    }
//                case true /*6*/:
//                    this.showsparkle = sharedPreferences.getBoolean(str, true);
//                case false /*7*/:
//                    this.birdsdirection = sharedPreferences.getString(str, "0");
//                    this.birdsdir = Integer.parseInt(this.birdsdirection);
//                case R.styleable.Toolbar_contentInsetRight /*8*/:
//                    bubbleCount();
//                case R.styleable.Toolbar_contentInsetStartWithNavigation /*9*/:
//                    this.bubble_size = sharedPreferences.getInt(str, 3);
//                    this.bubbleList.removeAll(this.bubbleList);
//                case R.styleable.Toolbar_contentInsetEndWithActions /*10*/:
//                    sharedPreferences.getString(str, "0");
//                case R.styleable.Toolbar_popupTheme /*11*/:
//                    this.double1 = sharedPreferences.getBoolean(str, true);
//                default:
            }
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return false;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            super.onSurfaceChanged(surfaceHolder, i, i2, i3);
            this.width = i2;
            this.height = i3;
            if (i2 < i3) {
                this.mDisplayWidth = i2;
            } else {
                this.mDisplayWidth = i3;
            }
            changeBgImagePref(ClockWallpaperService.this.galImagePath_photo);
            loadBgBitmap();
        }

        public void onSurfaceCreated(SurfaceHolder surfaceHolder) {
            super.onSurfaceCreated(surfaceHolder);
            try {
                ClockWallpaperService.this.canvas = surfaceHolder.lockCanvas();
                this.heightOfCanvas = ClockWallpaperService.this.canvas.getHeight();
                this.widthOfCanvas = ClockWallpaperService.this.canvas.getWidth();
                changeBgImagePref(ClockWallpaperService.this.galImagePath_photo);
                loadBgBitmap();
                surfaceHolder.unlockCanvasAndPost(ClockWallpaperService.this.canvas);
                this.mHandler.sendEmptyMessage(NONE);
            } catch (Exception e) {
            }
        }

        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.mHandler.removeMessages(NONE);
            System.gc();
            super.onSurfaceDestroyed(surfaceHolder);
        }

        public void onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case NONE /*0*/:
                    ClockWallpaperService.this.savedMatrix.set(ClockWallpaperService.this.matrix);
                    this.start.set(motionEvent.getX(), motionEvent.getY());
                    this.mode = DRAG;
                    this.lastEvent = null;
                    break;
                case DRAG /*1*/:
                case R.styleable.Toolbar_contentInsetEnd /*6*/:
                    this.mode = NONE;
                    this.lastEvent = null;
                    break;
                case ZOOM /*2*/:
                    if (this.mode != DRAG) {
                        if (this.mode == ZOOM && motionEvent.getPointerCount() == ZOOM) {
                            float spacing = spacing(motionEvent);
                            ClockWallpaperService.this.matrix.set(ClockWallpaperService.this.savedMatrix);
                            if (spacing > 10.0f) {
                                spacing /= this.oldDist;
                                ClockWallpaperService.this.matrix.postScale(spacing, spacing, this.mid.x, this.mid.y);
                            }
                            if (this.lastEvent != null) {
                                break;
                            }
                        }
                    }
                    ClockWallpaperService.this.matrix.set(ClockWallpaperService.this.savedMatrix);
                    ClockWallpaperService.this.matrix.postTranslate(motionEvent.getX() - this.start.x, motionEvent.getY() - this.start.y);
                    break;
               // break;
                case 5 /*5*/:
                    this.oldDist = spacing(motionEvent);
                    ClockWallpaperService.this.savedMatrix.set(ClockWallpaperService.this.matrix);
                    midPoint(this.mid, motionEvent);
                    this.mode = ZOOM;
                    this.lastEvent = new float[4];
                    this.lastEvent[NONE] = motionEvent.getX(NONE);
                    this.lastEvent[DRAG] = motionEvent.getX(DRAG);
                    this.lastEvent[ZOOM] = motionEvent.getY(NONE);
                    this.lastEvent[3] = motionEvent.getY(DRAG);
                    break;
            }
            super.onTouchEvent(motionEvent);
            this.detector.onTouchEvent(motionEvent);
        }

        public void onVisibilityChanged(boolean z) {
            super.onVisibilityChanged(z);
            syncBackgroundImage();
            updateclock(Constants.posval);
            if (z) {
                this.mHandler.sendEmptyMessage(NONE);
                this.calTarget = Calendar.getInstance();
                this.calTarget.set(Photo_Clock_ConstantsTime.INSTANCE.getEndYear(), Photo_Clock_ConstantsTime.INSTANCE.getEndMonth(), Photo_Clock_ConstantsTime.INSTANCE.getEndDay(), Photo_Clock_ConstantsTime.INSTANCE.getEndHours(), Photo_Clock_ConstantsTime.INSTANCE.getEndMinutes(), Photo_Clock_ConstantsTime.INSTANCE.getEndSeconds());
                this.d2 = this.calTarget.getTime();
                return;
            }
            System.gc();
            this.mHandler.removeMessages(NONE);
        }

        void syncBackgroundImage() {
            changeBgImagePref(ClockWallpaperService.this.galImagePath_photo);
        }

        public void textstyle() {
            String str = this.textStyle;
            Object obj = -1;
            switch (str.hashCode()) {
                case R.styleable.AppCompatTheme_spinnerDropDownItemStyle /*48*/:
                    if (str.equals("0")) {
                        obj = null;
                        break;
                    }
                    break;
                case R.styleable.AppCompatTheme_homeAsUpIndicator /*49*/:
                    if (str.equals("1")) {
                        obj = DRAG;
                        break;
                    }
                    break;
                case R.styleable.AppCompatTheme_actionButtonStyle /*50*/:
                    if (str.equals("2")) {
                        obj = ZOOM;
                        break;
                    }
                    break;
                case R.styleable.AppCompatTheme_buttonBarStyle /*51*/:
                    if (str.equals("3")) {
                        obj = 3;
                        break;
                    }
                    break;
                case R.styleable.AppCompatTheme_buttonBarButtonStyle /*52*/:
                    if (str.equals("4")) {
                        obj = 4;
                        break;
                    }
                    break;
                case R.styleable.AppCompatTheme_selectableItemBackground /*53*/:
                    if (str.equals("5")) {
                        obj = 5;
                        break;
                    }
                    break;
            }
//            switch (obj) {
//                case NONE /*0*/:
//                    this.tragic = Typeface.MONOSPACE;
//                case DRAG /*1*/:
//                    this.tragic = Typeface.SERIF;
//                case ZOOM /*2*/:
//                    this.tragic = Typeface.DEFAULT_BOLD;
//                case R.styleable.View_paddingEnd /*3*/:
//                    this.tragic = Typeface.createFromAsset(ClockWallpaperService.this.getAssets(), "fonts/aramisi.ttf");
//                case R.styleable.View_theme /*4*/:
//                    this.tragic = Typeface.createFromAsset(ClockWallpaperService.this.getAssets(), "fonts/Chelives.ttf");
//                case 5 /*5*/:
//                    this.tragic = Typeface.createFromAsset(ClockWallpaperService.this.getAssets(), "fonts/goodfish.ttf");
//                default:
//            }
        }
        private void updateclock(int i) {
            // Log.e("testyash","in Update Clock");

            switch (i) {
                case 0 /*0*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock1);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr1);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min1);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec1);
                    break;
                case 1 /*1*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock2);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr2);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min2);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec2);
                    break;
                case 2 /*2*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock3);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr3);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min3);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min3);
                    break;
                case 3 /*3*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock4);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr4);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min4);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec4);
                    break;
                case 4 /*4*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock5);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr3);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min3);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec3);
                    break;
                case 5 /*5*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock6);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr6);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min6);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec6);
                    break;

                case 6/*6*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock7);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr2);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min2);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec2);
                    break;

                case 7 /*7*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock8);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr8);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min8);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec8);
                    break;

                case 8 /*8*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock9);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr9);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min9);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec9);
                    break;

                case 9 /*9*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock10);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr10);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min10);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec10);
                    break;

                case 10 /*10*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock11);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr11);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min11);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec11);
                    break;

                case 11 /*11*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock12);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr12);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min12);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec12);
                    break;

                case 12 /*12*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock13);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr13);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min13);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec13);
                    break;

                case 13 /*13*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock14);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr14);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min14);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec14);
                    break;

                case 14 /*14*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock15);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr2);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min2);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec2);
                    break;

                case 15 /*15*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock16);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr16);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min16);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec16);
                    break;

                case 16/*16*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock17);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr2);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min2);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec17);
                    break;

                case 17 /*17*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock18);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr16);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min16);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec16);
                    break;

                case 18 /*18*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock19);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr12);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min12);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec12);
                    break;

                case 19 /*19*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock20);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr20);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min20);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec20);
                    break;

                case 20 /*20*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock21);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr20);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min20);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec20);
                    break;

                case 21 /*21*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock22);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr20);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min20);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec20);
                    break;

                case 22 /*22*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock23);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr2);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min2);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec2);
                    break;

                case 23 /*23*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock24);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr24);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min24);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec24);
                    break;

                case 24 /*24*/:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock25);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr2);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min2);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec2);
                    break;

                default:
                    this.leaf = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.clock1);
                    this.hoursBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_hr1);
                    this.minutesBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_min1);
                    this.secondsBmp = BitmapFactory.decodeResource(ClockWallpaperService.this.getResources(), R.mipmap.c_sec1);
                    break;

            }

        }
    }

    public ClockWallpaperService() {
        this.matrix = new Matrix();
        this.savedMatrix = new Matrix();
    }

    public void onCreate() {
        super.onCreate();
    }

    public Engine onCreateEngine() {
        this.myEngine = new WallpaperEngine();
        return this.myEngine;
    }

    public void onDestroy() {
        this.myEngine = null;
        super.onDestroy();
    }

}