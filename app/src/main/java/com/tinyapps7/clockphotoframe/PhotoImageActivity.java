package com.tinyapps7.clockphotoframe;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tinyapps7.clockphotoframe.AnalogClock.MyView;

import com.tinyapps7.clockphotoframe.adapter.BackgroundImage;
import com.tinyapps7.clockphotoframe.adapter.MyAdapter;
import com.tinyapps7.clockphotoframe.stickerview.StickerTextView;
import com.wang.avi.indicators.BallPulseIndicator;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PhotoImageActivity extends AppCompatActivity implements
        Animation.AnimationListener,
        MyAdapter.ItemClickListener, BackgroundImage.ItemClickListener_new1 {

    @InjectView(R.id.sample)
    RelativeLayout samples;
    private String imgPath;

    private static final int BACKGROUNDIMAGE = 2;
    private static final int CAMERA_REQUEST = 4;
    private static final int CLOCKIMAGE = 1;
    private static final int REQUEST_CODE_CROP_IMAGE = 3;
    private final int[] clock_icons;
    private final int[] bg_icons;
    private final int[] backgrounds;
    private final int[] hrs;
    private final int[] mins;
    private final int[] secs;
    private final int[] clocks;
    private final int[] masks;
    Animation slideUp;
    Animation slideDown;
    private Bitmap result;
    Animation popup;
    @InjectView(R.id.backgroundimage)
    ImageView backgroundimage;
    @InjectView(R.id.galleryimage)
    ImageView galleryimage;
    @InjectView(R.id.clockimage)
    ImageView clockimage;
    @InjectView(R.id.clockandimageview)
    RelativeLayout clockandimageview;
    @InjectView(R.id.relative)
    RelativeLayout relative;
    @InjectView(R.id.dummybg)
    ImageView dummybg;
    @InjectView(R.id.dummygalleryimage)
    ImageView dummygalleryimage;
    @InjectView(R.id.dummyclockimage)
    ImageView dummyclockimage;
    @InjectView(R.id.controls)
    RelativeLayout controls;
    @InjectView(R.id.setwallbutton)
    ImageButton setwallbutton;
    @InjectView(R.id.clockShow)
    RelativeLayout clockShow;
    @InjectView(R.id.addphoto)
    ImageView addphoto;
    @InjectView(R.id.addClock)
    ImageView addClock;
    @InjectView(R.id.addbg)
    ImageView addbg;
    @InjectView(R.id.addText)
    ImageView addText;

    @InjectView(R.id.addSave)
    ImageView addSave;
    @InjectView(R.id.linerBottom)
    LinearLayout linerBottom;
    @InjectView(R.id.clock_img)
    RecyclerView clockImg;
    @InjectView(R.id.drop_click)
    ImageView dropClick;
    @InjectView(R.id.recl_layout)
    LinearLayout reclLayout;
    @InjectView(R.id.swathi)
    TextView swathi;
    @InjectView(R.id.gallery)
    Button gallery;
    @InjectView(R.id.camera)
    Button camera;
    @InjectView(R.id.deleteimage)
    Button deleteimage;
    @InjectView(R.id.gallerydialogone)
    RelativeLayout gallerydialogone;
    @InjectView(R.id.gallerydialog)
    RelativeLayout gallerydialog;
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private Bitmap sample;
    private Bitmap mask;
    private Bitmap photo;
    private Matrix matrix;
    private Bitmap bmp;
    private Resources mResources;
    private int postion_ck_img = 1;
    MyAdapter myAdapter;
    String cam_selectedImagePath;

    int anim_val = 10;
    MarshMallowPermission marshMallowPermission;
    private String clockimagepath;
    private Bitmap galBitmap;
    private int position;
    private Bitmap camera_pic;
    private File fileclock;
    private Bitmap background_photo;
    private File bgimg;
    private int swipevalue_photo = 1;
    private String gal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_image);
        ButterKnife.inject(this);
        marshMallowPermission = new MarshMallowPermission(this);
        if (Build.VERSION.SDK_INT >= 23) {
            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                marshMallowPermission.requestPermissionForExternalStorage();
            } else if (!marshMallowPermission.checkPermissionForCamera())

            {
                marshMallowPermission.requestPermissionForCamera();
            }
        }
        this.pref = PreferenceManager.getDefaultSharedPreferences(this);
        this.editor = this.pref.edit();
        this.position = this.pref.getInt("photo_clock_pos", 1);


        slideUp = AnimationUtils.loadAnimation(this, R.anim.layout_up);
        slideDown = AnimationUtils.loadAnimation(this, R.anim.layout_down);
        popup = AnimationUtils.loadAnimation(this, R.anim.bounce);
        popup.setAnimationListener(this);
        mResources = getResources();
        context = PhotoImageActivity.this;


        this.controls.addView(new MyView(this));
        this.controls.setGravity(17);
        this.controls.invalidate();
        this.matrix = new Matrix();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.mipmap.clock1);
        this.galleryimage.setImageBitmap(decodeResource);
        galleryimage.setOnTouchListener(new Masking_touchclass());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i / BACKGROUNDIMAGE, i / BACKGROUNDIMAGE);
        layoutParams.addRule(13);
        this.galleryimage.setLayoutParams(layoutParams);
        this.clockimage.setLayoutParams(layoutParams);
        dummygalleryimage.setLayoutParams(layoutParams);
        dummyclockimage.setLayoutParams(layoutParams);
//        backgroundimage.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_bg1));
//        setClocks(postion_ck_img);
//        this.sample = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
//        // galleryimage.setImageBitmap(this.sample);
//        this.matrix.reset();
//        this.bmp = this.sample;
//        this.photo = this.bmp;
//        masking();
//        setBackgroundImgae(0);
//        this.position = this.pref.getInt("photo_clock_pos", 0);
//        setClocks(this.position);

        //.......................
        setBackgroundImgae(CLOCKIMAGE);
        this.position = this.pref.getInt("photo_clock_pos", 0);
        setClocks(this.position);
        this.gal = this.pref.getString("bitmap", this.clockimagepath);
        if (this.gal != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = CLOCKIMAGE;
            try {
                options.inJustDecodeBounds = true;
                int i2 = options.outWidth;
                i = options.outHeight;
                if (i2 > 1000 || i > 1000) {
                    i2 = CLOCKIMAGE;
                    while (options.outWidth / i2 >= 600 && options.outHeight / i2 >= 600) {
                        i2 += CLOCKIMAGE;
                    }
                    options.inSampleSize = i2;
                }
                options.inJustDecodeBounds = false;
                this.galBitmap = BitmapFactory.decodeFile(this.gal, options);
                this.galBitmap = Bitmap.createBitmap(this.galBitmap, 0, 0, this.galBitmap.getWidth(), this.galBitmap.getHeight(), new Matrix(), true);
            } catch (OutOfMemoryError e2) {
                // e = e2;
                e2.printStackTrace();
                this.galleryimage.setImageBitmap(this.galBitmap);
                this.matrix.reset();
                this.bmp = this.galBitmap;
                this.photo = this.bmp;
                masking();
//                this.clock_icons_view = (RecyclerView) findViewById(R.id.clock_icons_view);
//                linearLayoutManager = new LinearLayoutManager(this);
//                linearLayoutManager.setOrientation(0);
//                this.clock_icons_view.setLayoutManager(linearLayoutManager);
//                this.clock_icons_view.setAdapter(new MyAdapter(this, this.clock_icons, FrameType.CLOCKS, this.position));
//                this.bg_icons_view = (RecyclerView) findViewById(R.id.bg_icons_view);
//                linearLayoutManager = new LinearLayoutManager(this);
//                linearLayoutManager.setOrientation(0);
//                this.bg_icons_view.setLayoutManager(linearLayoutManager);
//                this.bg_icons_view.setAdapter(new MyAdapter(this, this.bg_icons, FrameType.PHOTO_BG, CLOCKIMAGE));
            } catch (Exception e3) {

                e3.printStackTrace();
                this.galleryimage.setImageBitmap(this.galBitmap);
                this.matrix.reset();
                this.bmp = this.galBitmap;
                this.photo = this.bmp;
                masking();
//                this.clock_icons_view = (RecyclerView) findViewById(R.id.clock_icons_view);
//                linearLayoutManager = new LinearLayoutManager(this);
//                linearLayoutManager.setOrientation(0);
//                this.clock_icons_view.setLayoutManager(linearLayoutManager);
//                this.clock_icons_view.setAdapter(new MyAdapter(this, this.clock_icons, FrameType.CLOCKS, this.position));
//                this.bg_icons_view = (RecyclerView) findViewById(R.id.bg_icons_view);
//                linearLayoutManager = new LinearLayoutManager(this);
//                linearLayoutManager.setOrientation(0);
//                this.bg_icons_view.setLayoutManager(linearLayoutManager);
//                this.bg_icons_view.setAdapter(new MyAdapter(this, this.bg_icons, FrameType.PHOTO_BG, CLOCKIMAGE));
            }
            this.galleryimage.setImageBitmap(this.galBitmap);
            this.matrix.reset();
            this.bmp = this.galBitmap;
            this.photo = this.bmp;
            masking();
        } else {
            this.sample = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
            this.galleryimage.setImageBitmap(this.sample);
            this.matrix.reset();
            this.bmp = this.sample;
            this.photo = this.bmp;
            masking();
        }
        //...............................

        StickerTextView  st_text=new StickerTextView(this);
        st_text.setText("vkm");
        st_text.setTColor(Color.BLUE);
        relative.addView(st_text);

        st_text.setControlItemsHidden(true);

    }

    public PhotoImageActivity() {
        this.clock_icons = new int[]{R.mipmap.ic1, R.mipmap.ic2, R.mipmap.ic3, R.mipmap.ic4, R.mipmap.ic5, R.mipmap.ic6, R.mipmap.ic7, R.mipmap.ic8, R.mipmap.ic9, R.mipmap.ic10, R.mipmap.ic11, R.mipmap.ic12, R.mipmap.ic13, R.mipmap.ic14, R.mipmap.ic15, R.mipmap.ic16, R.mipmap.ic17, R.mipmap.ic18, R.mipmap.ic19, R.mipmap.ic20, R.mipmap.ic21, R.mipmap.ic22, R.mipmap.ic23, R.mipmap.ic24, R.mipmap.ic25};
        this.matrix = new Matrix();
        this.bg_icons = new int[]{R.mipmap.c_ic1, R.mipmap.c_ic2, R.mipmap.c_ic3, R.mipmap.c_ic4, R.mipmap.c_ic5, R.mipmap.c_ic6, R.mipmap.c_ic7, R.mipmap.c_ic8, R.mipmap.c_ic9, R.mipmap.c_ic10, R.mipmap.c_ic11, R.mipmap.c_ic12, R.mipmap.c_ic13, R.mipmap.c_ic14, R.mipmap.c_ic15, R.mipmap.c_ic16, R.mipmap.c_ic17, R.mipmap.c_ic18, R.mipmap.c_ic19, R.mipmap.c_ic20, R.mipmap.c_ic21, R.mipmap.c_ic22, R.mipmap.c_ic23, R.mipmap.c_ic24, R.mipmap.c_ic25, R.mipmap.c_ic26, R.mipmap.c_ic27, R.mipmap.c_ic28, R.mipmap.c_ic29, R.mipmap.c_ic30, R.mipmap.c_ic31, R.mipmap.c_ic32, R.mipmap.c_ic33, R.mipmap.c_ic34, R.mipmap.c_ic35, R.mipmap.c_ic36, R.mipmap.c_ic37, R.mipmap.c_ic38, R.mipmap.c_ic39, R.mipmap.c_ic40};
        this.backgrounds = new int[]{R.drawable.c_bg1, R.drawable.c_bg2, R.drawable.c_bg3, R.drawable.c_bg4, R.drawable.c_bg5, R.drawable.c_bg6, R.drawable.c_bg7, R.drawable.c_bg8, R.drawable.c_bg9, R.drawable.c_bg10, R.drawable.c_bg11, R.drawable.c_bg12, R.drawable.c_bg13, R.drawable.c_bg14, R.drawable.c_bg15, R.drawable.c_bg16, R.drawable.c_bg17, R.drawable.c_bg18, R.drawable.c_bg19, R.drawable.c_bg20, R.drawable.c_bg21, R.drawable.c_bg22, R.drawable.c_bg23, R.drawable.c_bg24, R.drawable.c_bg25, R.drawable.c_bg26, R.drawable.c_bg27, R.drawable.c_bg28, R.drawable.c_bg29, R.drawable.c_bg30, R.drawable.c_bg31, R.drawable.c_bg32, R.drawable.c_bg33, R.drawable.c_bg34, R.drawable.c_bg35, R.drawable.c_bg36, R.drawable.c_bg37, R.drawable.c_bg38, R.drawable.c_bg39, R.drawable.c_bg40};
        this.hrs = new int[]{R.mipmap.c_hr1, R.mipmap.c_hr2, R.mipmap.c_hr3, R.mipmap.c_hr4, R.mipmap.c_hr3, R.mipmap.c_hr6, R.mipmap.c_hr2, R.mipmap.c_hr8, R.mipmap.c_hr9, R.mipmap.c_hr10, R.mipmap.c_hr11, R.mipmap.c_hr12, R.mipmap.c_hr13, R.mipmap.c_hr14, R.mipmap.c_hr2, R.mipmap.c_hr16, R.mipmap.c_hr2, R.mipmap.c_hr16, R.mipmap.c_hr12, R.mipmap.c_hr20, R.mipmap.c_hr20, R.mipmap.c_hr20, R.mipmap.c_hr2, R.mipmap.c_hr24, R.mipmap.c_hr2};
        this.mins = new int[]{R.mipmap.c_min1, R.mipmap.c_min2, R.mipmap.c_min3, R.mipmap.c_min4, R.mipmap.c_min3, R.mipmap.c_min6, R.mipmap.c_min2, R.mipmap.c_min8, R.mipmap.c_min9, R.mipmap.c_min10, R.mipmap.c_min11, R.mipmap.c_min12, R.mipmap.c_min13, R.mipmap.c_min14, R.mipmap.c_min2, R.mipmap.c_min16, R.mipmap.c_min2, R.mipmap.c_min16, R.mipmap.c_min12, R.mipmap.c_min20, R.mipmap.c_min20, R.mipmap.c_min20, R.mipmap.c_min2, R.mipmap.c_min24, R.mipmap.c_min2};
        this.secs = new int[]{R.mipmap.c_sec1, R.mipmap.c_sec2, R.mipmap.c_sec3, R.mipmap.c_sec4, R.mipmap.c_sec3, R.mipmap.c_sec6, R.mipmap.c_sec2, R.mipmap.c_sec8, R.mipmap.c_sec9, R.mipmap.c_sec10, R.mipmap.c_sec11, R.mipmap.c_sec12, R.mipmap.c_sec13, R.mipmap.c_sec14, R.mipmap.c_sec2, R.mipmap.c_sec16, R.mipmap.c_sec17, R.mipmap.c_sec16, R.mipmap.c_sec12, R.mipmap.c_sec20, R.mipmap.c_sec20, R.mipmap.c_sec20, R.mipmap.c_sec2, R.mipmap.c_sec24, R.mipmap.c_sec2};
        this.clocks = new int[]{R.mipmap.clock1, R.mipmap.clock2, R.mipmap.clock3, R.mipmap.clock4, R.mipmap.clock5, R.mipmap.clock6, R.mipmap.clock7, R.mipmap.clock8, R.mipmap.clock9, R.mipmap.clock10, R.mipmap.clock11, R.mipmap.clock12, R.mipmap.clock13, R.mipmap.clock14, R.mipmap.clock15, R.mipmap.clock16, R.mipmap.clock17, R.mipmap.clock18, R.mipmap.clock19, R.mipmap.clock20, R.mipmap.clock21, R.mipmap.clock22, R.mipmap.clock23, R.mipmap.clock24, R.mipmap.clock25};
        this.masks = new int[]{R.mipmap.mask_c1, R.mipmap.mask_c2, R.mipmap.mask_round1, R.mipmap.mask_c4, R.mipmap.mask_round1, R.mipmap.mask_c6, R.mipmap.mask_c7, R.mipmap.mask_round1, R.mipmap.mask_round1, R.mipmap.mask_round1, R.mipmap.mask_round2, R.mipmap.mask_c2, R.mipmap.mask_round2, R.mipmap.mask_c14, R.mipmap.mask_round2, R.mipmap.mask_round2, R.mipmap.mask_love, R.mipmap.mask_round2, R.mipmap.mask_round3, R.mipmap.mask_love, R.mipmap.mask_round3, R.mipmap.mask_round4, R.mipmap.mask_round2, R.mipmap.mask_c24, R.mipmap.mask_round2};
    }

    private void save(Bitmap bitmap) {
        if (bitmap != null) {
            File file = new File(Environment.getExternalStorageDirectory() + "/.Photo Clock/");
            file.mkdirs();
            try {
                this.fileclock = new File(file, "clock.png");
                bitmap.compress(Bitmap.CompressFormat.PNG, 30, new FileOutputStream(this.fileclock));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.setwallbutton, R.id.addphoto, R.id.addClock, R.id.addbg, R.id.addText, R.id.addSave, R.id.drop_click, R.id.gallery, R.id.camera, R.id.deleteimage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setwallbutton:

                PhotoImageActivity.this.editor.putString("clockimg_photo", null).apply();
                if (PhotoImageActivity.this.result != null) {
                    PhotoImageActivity.this.save(PhotoImageActivity.this.result);
                    PhotoImageActivity.this.editor.putString("clockimg_photo", PhotoImageActivity.this.fileclock.getAbsolutePath()).apply();
                }
                PhotoImageActivity.this.editor.putString("galimagepath_photo", PhotoImageActivity.this.fileclock.getAbsolutePath()).apply();
                PhotoImageActivity.this.editor.putString("imagepath_photo", PhotoImageActivity.this.bgimg.getAbsolutePath()).apply();
                PhotoImageActivity.this.editor.putInt("swipevalue_photo", PhotoImageActivity.this.swipevalue_photo).apply();
                PhotoImageActivity.this.editor.putInt("photo_clock_pos", PhotoImageActivity.this.position).apply();
                relative.setDrawingCacheEnabled(true);
                PhotoImageActivity.this.editor.putString("textpath", PhotoImageActivity.this.BitMapToString(relative.getDrawingCache())).apply();
                PhotoImageActivity.this.editor.putBoolean("swathi", true).apply();

                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT > 16) {
                    intent.setAction("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
                    intent.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT", new ComponentName(ClockWallpaperService.class.getPackage().getName(), ClockWallpaperService.class.getCanonicalName()));
                } else {
                    // intent.setAction("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
                    //PhotoImageActivity.this.runOnUiThread(new runable2(PhotoImageActivity.this.getLayoutInflater().inflate(R.layout.my_toast, (ViewGroup) PhotoImageActivity.this.findViewById(R.id.custom_toast_layout))));
                }
                PhotoImageActivity.this.startActivityForResult(intent, 0);


                break;
            case R.id.addphoto:
                gallerydialog.setVisibility(View.VISIBLE);
                gallerydialog.startAnimation(slideUp);
                break;
            case R.id.addClock:
                gallerydialog.setVisibility(View.INVISIBLE);
                myAdapter = new MyAdapter(clock_icons, context);
                linerBottom.startAnimation(slideDown);
                linerBottom.setVisibility(View.INVISIBLE);
                reclLayout.setVisibility(View.VISIBLE);
                clockImg.startAnimation(slideUp);
                clockImg.setAdapter(myAdapter);
                myAdapter.setClickListener(this);
                clockImg.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.addbg:
                gallerydialog.setVisibility(View.INVISIBLE);
                BackgroundImage backgroundImage = new BackgroundImage(bg_icons, context);
                linerBottom.startAnimation(slideDown);
                linerBottom.setVisibility(View.INVISIBLE);
                reclLayout.setVisibility(View.VISIBLE);
                clockImg.startAnimation(slideUp);
                clockImg.setAdapter(backgroundImage);
                backgroundImage.setClickListener_new(this);
                clockImg.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

                gallery.startAnimation(popup);
                break;
            case R.id.addText:
                addText.startAnimation(popup);
                this.anim_val=3;
                break;

            case R.id.addSave:
                addSave.startAnimation(popup);
                this.anim_val = 2;
                break;
            case R.id.drop_click:

                clockImg.setAdapter(new MyAdapter(clocks, context));
                clockImg.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                reclLayout.setVisibility(View.INVISIBLE);
                linerBottom.startAnimation(slideUp);
                linerBottom.setVisibility(View.VISIBLE);
                clockImg.startAnimation(slideDown);
                break;
            case R.id.gallery:
                this.anim_val = 0;
                gallery.startAnimation(popup);
                break;
            case R.id.camera:
                this.anim_val = 1;
                camera.startAnimation(popup);
                break;
            case R.id.deleteimage:
                deleteimage.startAnimation(popup);
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        switch (anim_val) {
            case 0:
                startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), CLOCKIMAGE);
                gallerydialog.setVisibility(View.INVISIBLE);
                break;
            case 1:
                final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                startActivityForResult(intent, CAMERA_REQUEST);
                break;
            case 2:
                relative.buildDrawingCache();
                relative.setDrawingCacheEnabled(true);
                storeImage(relative.getDrawingCache());
                break;
            case 3:
                // startActivity(new Intent(this,Text_frame.class));
                break;
            default:
                break;
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }


    public String getImagePath() {
        return imgPath;
    }


    protected void onActivityResult(int i, int i2, Intent intent) {
        Throwable e;
        boolean z;
        Dialog dialog;
        WebView webView;
        super.onActivityResult(i, i2, intent);
        BitmapFactory.Options options;
        int cameraPhotoOrientation;
        int i3;
        int i4;
        Matrix matrix;
        if (i == CLOCKIMAGE && i2 == -1) {
            if (intent != null && intent.getData() != null) {
                Uri data = intent.getData();
                String[] strArr = new String[CLOCKIMAGE];
                strArr[0] = "_data";
                Cursor query = getContentResolver().query(data, strArr, null, null, null);
                if (query != null) {
                    query.moveToFirst();
                    this.clockimagepath = query.getString(query.getColumnIndex(strArr[0]));
                    query.close();
                }
                if (this.clockimagepath != null) {
                    Toast.makeText(context, "dzbchj", Toast.LENGTH_SHORT).show();
                    options = new BitmapFactory.Options();
                    options.inSampleSize = CLOCKIMAGE;
                    cameraPhotoOrientation = getCameraPhotoOrientation(this, new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI).getData(), this.clockimagepath);
                    try {
                        options.inJustDecodeBounds = true;
                        this.galBitmap = BitmapFactory.decodeFile(this.clockimagepath, options);
                        i3 = options.outWidth;
                        i4 = options.outHeight;
                        if (i3 > 1000 || i4 > 1000) {
                            i3 = CLOCKIMAGE;
                            while (options.outWidth / i3 >= 600 && options.outHeight / i3 >= 600) {
                                i3 += CLOCKIMAGE;
                            }
                            options.inSampleSize = i3;
                        }
                        options.inJustDecodeBounds = false;
                        this.galBitmap = BitmapFactory.decodeFile(this.clockimagepath, options);
                        this.editor.putString("bitmap", this.clockimagepath).apply();
                        matrix = new Matrix();
                        matrix.postRotate((float) cameraPhotoOrientation);
                        this.galBitmap = Bitmap.createBitmap(this.galBitmap, 0, 0, this.galBitmap.getWidth(), this.galBitmap.getHeight(), matrix, true);
                    } catch (OutOfMemoryError e2) {
                        Toast.makeText(context, "Out of Memmory", Toast.LENGTH_SHORT).show();
                        e = e2;
                        e.printStackTrace();
                        this.galleryimage.setImageBitmap(this.galBitmap);
                        this.matrix.reset();
                        this.bmp = this.galBitmap;
                        this.photo = this.bmp;
                        masking();
                        z = this.pref.getBoolean("dialog", true);
                        this.editor.remove("seek");
                        this.editor.commit();
                        if (!z) {
                            dialog = new Dialog(this);
                            dialog.getWindow().requestFeature(CLOCKIMAGE);
                            dialog.setContentView(R.layout.custom_dialog_gif);
                            dialog.setCancelable(false);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            webView = (WebView) dialog.findViewById(R.id.message);
                            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                            webView.loadDataWithBaseURL("file:///android_asset/", "<html>\n<body bgcolor=\"white\">\n    <table width=\"100%\" height=\"100%\">\n        <tr>\n            <td align=\"center\" valign=\"center\">\n                <img src=\"gif.gif\">\n            </td>\n        </tr>\n    </table>\n</body>", "text/html", "utf-8", BuildConfig.FLAVOR);
                            dialog.findViewById(R.id.ok).setOnClickListener(new onclicke(dialog));
                            dialog.findViewById(R.id.close).setOnClickListener(new onclicke2(dialog));
                            dialog.show();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        this.galleryimage.setImageBitmap(this.galBitmap);
                        this.matrix.reset();
                        this.bmp = this.galBitmap;
                        this.photo = this.bmp;
                        masking();
                        z = this.pref.getBoolean("dialog", true);
                        this.editor.remove("seek");
                        this.editor.commit();
                        if (!z) {
                            dialog = new Dialog(this);
                            dialog.getWindow().requestFeature(CLOCKIMAGE);
                            dialog.setContentView(R.layout.custom_dialog_gif);
                            dialog.setCancelable(false);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            webView = (WebView) dialog.findViewById(R.id.message);
                            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                            webView.loadDataWithBaseURL("file:///android_asset/", "<html>\n<body bgcolor=\"white\">\n    <table width=\"100%\" height=\"100%\">\n        <tr>\n            <td align=\"center\" valign=\"center\">\n                <img src=\"gif.gif\">\n            </td>\n        </tr>\n    </table>\n</body>", "text/html", "utf-8", BuildConfig.FLAVOR);
                            dialog.findViewById(R.id.ok).setOnClickListener(new onclicke(dialog));
                            dialog.findViewById(R.id.close).setOnClickListener(new onclicke2(dialog));
                            dialog.show();
                        }
                    }
                    this.galleryimage.setImageBitmap(this.galBitmap);
                    this.matrix.reset();
                    this.bmp = this.galBitmap;
                    this.photo = this.bmp;
                    masking();
                    z = this.pref.getBoolean("dialog", true);
                    this.editor.remove("seek");
                    this.editor.commit();
                    if (!z) {
                        dialog = new Dialog(this);
                        dialog.getWindow().requestFeature(CLOCKIMAGE);
                        dialog.setContentView(R.layout.custom_dialog_gif);
                        dialog.setCancelable(false);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        webView = (WebView) dialog.findViewById(R.id.message);
                        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                        webView.loadDataWithBaseURL("file:///android_asset/", "<html>\n<body bgcolor=\"white\">\n    <table width=\"100%\" height=\"100%\">\n        <tr>\n            <td align=\"center\" valign=\"center\">\n                <img src=\"gif.gif\">\n            </td>\n        </tr>\n    </table>\n</body>", "text/html", "utf-8", BuildConfig.FLAVOR);
                        dialog.findViewById(R.id.ok).setOnClickListener(new onclicke(dialog));
                        dialog.findViewById(R.id.close).setOnClickListener(new onclicke2(dialog));
                        dialog.show();
                    }
                }
            }
        } else if (i == CAMERA_REQUEST && i2 == -1) {
            try {
                cam_selectedImagePath = getImagePath();
                this.camera_pic = decodeFile(cam_selectedImagePath);
                galleryimage.setImageBitmap(camera_pic);
                Log.e("vkm", "" + camera_pic.getWidth() + "" + camera_pic.getHeight());
                this.editor.putString("bitmap", cam_selectedImagePath);
                // this.camera_pic = rotateBitmapOrientation(cam_selectedImagePath);
                Log.e("vkm", "" + camera_pic.getWidth() + "" + camera_pic.getHeight());
            } catch (OutOfMemoryError e4) {
                e = e4;
                e.printStackTrace();
                this.galleryimage.setImageBitmap(this.camera_pic);
                this.matrix.reset();
                this.bmp = this.camera_pic;
                this.photo = this.bmp;
                masking();
                z = this.pref.getBoolean("dialog_cam", true);
                this.editor.remove("seek");
                this.editor.commit();
                if (!z) {
                    dialog = new Dialog(this);
                    dialog.getWindow().requestFeature(CLOCKIMAGE);
                    dialog.setContentView(R.layout.custom_dialog_gif);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    webView = (WebView) dialog.findViewById(R.id.message);
                    webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                    webView.loadDataWithBaseURL("file:///android_asset/", "<html>\n<body bgcolor=\"white\">\n    <table width=\"100%\" height=\"100%\">\n        <tr>\n            <td align=\"center\" valign=\"center\">\n                <img src=\"gif.gif\">\n            </td>\n        </tr>\n    </table>\n</body>", "text/html", "utf-8", BuildConfig.FLAVOR);
                    dialog.findViewById(R.id.ok).setOnClickListener(new onclicke3(dialog));
                    dialog.findViewById(R.id.close).setOnClickListener(new onclicke4(dialog));
                    dialog.show();
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                this.galleryimage.setImageBitmap(this.camera_pic);
                this.matrix.reset();
                this.bmp = this.camera_pic;
                this.photo = this.bmp;
                masking();
                z = this.pref.getBoolean("dialog_cam", true);
                this.editor.remove("seek");
                this.editor.commit();
                if (!z) {
                    dialog = new Dialog(this);
                    dialog.getWindow().requestFeature(CLOCKIMAGE);
                    dialog.setContentView(R.layout.custom_dialog_gif);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    webView = (WebView) dialog.findViewById(R.id.message);
                    webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                    webView.loadDataWithBaseURL("file:///android_asset/", "<html>\n<body bgcolor=\"white\">\n    <table width=\"100%\" height=\"100%\">\n        <tr>\n            <td align=\"center\" valign=\"center\">\n                <img src=\"gif.gif\">\n            </td>\n        </tr>\n    </table>\n</body>", "text/html", "utf-8", BuildConfig.FLAVOR);
                    dialog.findViewById(R.id.ok).setOnClickListener(new onclicke3(dialog));
                    dialog.findViewById(R.id.close).setOnClickListener(new onclicke4(dialog));
                    dialog.show();
                }
            }
            this.galleryimage.setImageBitmap(this.camera_pic);
            this.matrix.reset();
            this.bmp = this.camera_pic;
            this.photo = this.bmp;
            masking();
            z = this.pref.getBoolean("dialog_cam", true);
            this.editor.remove("seek");
            this.editor.commit();
            if (!z) {
                dialog = new Dialog(this);
                dialog.getWindow().requestFeature(CLOCKIMAGE);
                dialog.setContentView(R.layout.custom_dialog_gif);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                webView = (WebView) dialog.findViewById(R.id.message);
                webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
                webView.loadDataWithBaseURL("file:///android_asset/", "<html>\n<body bgcolor=\"white\">\n    <table width=\"100%\" height=\"100%\">\n        <tr>\n            <td align=\"center\" valign=\"center\">\n                <img src=\"gif.gif\">\n            </td>\n        </tr>\n    </table>\n</body>", "text/html", "utf-8", BuildConfig.FLAVOR);
                dialog.findViewById(R.id.ok).setOnClickListener(new onclicke3(dialog));
                dialog.findViewById(R.id.close).setOnClickListener(new onclicke4(dialog));
                dialog.show();
            }


        }

    }


    public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }

    private void setClocks(int i) {

        this.clockimage.setImageResource(this.clocks[i]);
        MyView.mHourHand = this.mResources.getDrawable(this.hrs[i]);
        MyView.mMinuteHand = this.mResources.getDrawable(this.mins[i]);
        MyView.mSecondHand = this.mResources.getDrawable(this.secs[i]);
        this.mask = BitmapFactory.decodeResource(getResources(), this.masks[i]);
        if (this.result != null) {
            masking();
        }

    }

    private Bitmap masking() {
        this.result = null;
        try {
            Canvas canvas = new Canvas();
            this.result = Bitmap.createBitmap(this.mask.getWidth(), this.mask.getHeight(), Bitmap.Config.ARGB_8888);
            canvas.setBitmap(this.result);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            canvas.drawBitmap(this.photo, this.matrix, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(this.mask, 0.0f, 0.0f, paint);
            paint.setXfermode(null);
            this.galleryimage.setImageBitmap(this.result);

            //Log.e("vkm", "MAsk" + result.getHeight() + "" + result.getWidth());

        } catch (Exception e) {
        }
        return this.result;

    }

    @Override
    public void onItemClick(View view, int position) {
        this.postion_ck_img = position;
        setClocks(position);
        this.position = position;

    }

    private int getCameraPhotoOrientation(Context context, Uri uri, String str) {
        try {
            context.getContentResolver().notifyChange(uri, null);
            switch (new ExifInterface(new File(str).getAbsolutePath()).getAttributeInt("Orientation", CLOCKIMAGE)) {
                case 3 /*3*/:
                    return 180;
                case 6 /*6*/:
                    return 90;
                case 8 /*8*/:
                    return 270;
                default:
                    return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    private String BitMapToString(Bitmap drawingCache) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        drawingCache.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    @Override
    public void onItemClick1(View view, int position) {
        setBackgroundImgae(position);
    }

    private void setBackgroundImgae(int position) {
        IOException e;
        Throwable th;
        //...............
        this.background_photo = BitmapFactory.decodeResource(getResources(), backgrounds[position]);
        this.backgroundimage.setImageBitmap(this.background_photo);
        Bitmap bitmap = ((BitmapDrawable) this.backgroundimage.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        this.bgimg = new File(Environment.getExternalStorageDirectory() + File.separator + "/.sample.jpg/");
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2;
        try {
            this.bgimg.createNewFile();
            fileOutputStream2 = new FileOutputStream(this.bgimg);
            try {
                fileOutputStream2.write(byteArrayOutputStream.toByteArray());
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e1) {
            } catch (Exception e2) {
            }
        } catch (Exception w) {
        }
        //........................
    }


    class onclicke3 implements View.OnClickListener {
        Dialog dialog;

        public onclicke3(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            this.dialog.dismiss();
        }
    }

    private class Masking_touchclass implements View.OnTouchListener {
        static final int DRAG = 1;
        static final int NONE = 0;
        static final int ZOOM = 2;
        float f2282d;
        float[] lastEvent;
        PointF mid;
        int mode;
        float newRot;
        float oldDist;
        Matrix savedMatrix;
        PointF start;

        public Masking_touchclass() {
            this.savedMatrix = new Matrix();
            this.mode = NONE;
            this.f2282d = 0.0f;
            this.start = new PointF();
            this.mid = new PointF();
            this.oldDist = BallPulseIndicator.SCALE;
            this.lastEvent = null;
            this.newRot = 0.0f;
        }

        private void dumpEvent(MotionEvent motionEvent) {
            int i = NONE;
            String[] strArr = new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
            StringBuilder stringBuilder = new StringBuilder();
            int action = motionEvent.getAction();
            int i2 = action & 255;
            stringBuilder.append("event ACTION_").append(strArr[i2]);
            if (i2 == 5 || i2 == 6) {
                stringBuilder.append("(pid ").append(action >> 8);
                stringBuilder.append(")");
            }
            stringBuilder.append("[");
            while (i < motionEvent.getPointerCount()) {
                stringBuilder.append("#").append(i);
                stringBuilder.append("(pid ").append(motionEvent.getPointerId(i));
                stringBuilder.append(")=").append((int) motionEvent.getX(i));
                stringBuilder.append(",").append((int) motionEvent.getY(i));
                if (i + DRAG < motionEvent.getPointerCount()) {
                    stringBuilder.append(";");
                }
                i += DRAG;
            }
            stringBuilder.append("]");
        }

        private void midPoint(PointF pointF, MotionEvent motionEvent) {
            pointF.set((motionEvent.getX(NONE) + motionEvent.getX(DRAG)) / 2.0f, (motionEvent.getY(NONE) + motionEvent.getY(DRAG)) / 2.0f);
        }

        private float rotation(MotionEvent motionEvent) {
            return (float) Math.toDegrees(Math.atan2((double) (motionEvent.getY(NONE) - motionEvent.getY(DRAG)), (double) (motionEvent.getX(NONE) - motionEvent.getX(DRAG))));
        }

        @SuppressLint({"FloatMath"})
        private float spacing(MotionEvent motionEvent) {
            float x = motionEvent.getX(NONE) - motionEvent.getX(DRAG);
            float y = motionEvent.getY(NONE) - motionEvent.getY(DRAG);
            return (float) Math.sqrt((double) ((x * x) + (y * y)));
        }

        @SuppressLint({"ClickableViewAccessibility"})
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {

            ImageView imageView = (ImageView) v;
            dumpEvent(motionEvent);

            switch (motionEvent.getAction() & 255) {
                case NONE /*0*/:
                    this.savedMatrix.set(PhotoImageActivity.this.matrix);
                    this.start.set(motionEvent.getX(), motionEvent.getY());
                    this.mode = DRAG;
                    this.lastEvent = null;
                    break;
                case DRAG /*1*/:
                    break;
                case 6: /*6*/
                    this.mode = NONE;
                    this.lastEvent = null;
                    break;
                case ZOOM /*2*/:
                    if (this.mode != DRAG) {
                        if (this.mode == ZOOM && motionEvent.getPointerCount() == ZOOM) {
                            float spacing = spacing(motionEvent);
                            PhotoImageActivity.this.matrix.set(this.savedMatrix);
                            if (spacing > 50.0f) {
                                spacing /= this.oldDist;
                                PhotoImageActivity.this.matrix.postScale(spacing, spacing, this.mid.x, this.mid.y);
                            }
                            if (this.lastEvent != null) {
                                this.newRot = rotation(motionEvent);
                                PhotoImageActivity.this.matrix.postRotate(this.newRot - this.f2282d, (float) (imageView.getMeasuredWidth() / ZOOM), (float) (imageView.getMeasuredHeight() / ZOOM));
                                break;
                            }
                        }
                    }
                    PhotoImageActivity.this.matrix.set(this.savedMatrix);
                    PhotoImageActivity.this.matrix.postTranslate(motionEvent.getX() - this.start.x, motionEvent.getY() - this.start.y);
                    break;
                case 5: /*5*/
                    this.oldDist = spacing(motionEvent);
                    this.savedMatrix.set(PhotoImageActivity.this.matrix);
                    midPoint(this.mid, motionEvent);
                    this.mode = ZOOM;
                    this.lastEvent = new float[PhotoImageActivity.CAMERA_REQUEST];
                    this.lastEvent[NONE] = motionEvent.getX(NONE);
                    this.lastEvent[DRAG] = motionEvent.getX(DRAG);
                    this.lastEvent[ZOOM] = motionEvent.getY(NONE);
                    this.lastEvent[PhotoImageActivity.REQUEST_CODE_CROP_IMAGE] = motionEvent.getY(DRAG);
                    this.f2282d = rotation(motionEvent);
                    break;
            }
            imageView.setImageMatrix(PhotoImageActivity.this.matrix);
            PhotoImageActivity.this.masking();
            return true;
        }
    }

    private class onclicke implements View.OnClickListener {
        Dialog dialog;

        public onclicke(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            this.dialog.dismiss();

        }
    }

    private class onclicke2 implements View.OnClickListener {
        Dialog dialog;

        public onclicke2(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            this.dialog.dismiss();

        }
    }

    private class onclicke4 implements View.OnClickListener {
        private Dialog dialog;

        public onclicke4(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            this.dialog.dismiss();
        }
    }

    public Bitmap rotateBitmapOrientation(String photoFilePath) {
        // Create and configure BitmapFactory
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFilePath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(photoFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        // Return result
        return rotatedBitmap;
    }

    public void storeImage(Bitmap bitmap) {

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(path + "/Clock Photo Editor");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String timeStamp = dateFormat.format(new Date());

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + "" + timeStamp + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, out);
            Toast.makeText(this, "Image Path " + myDir + ":", Toast.LENGTH_SHORT).show();
            out.flush();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();

        }
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);


                    }
                });


    }
}