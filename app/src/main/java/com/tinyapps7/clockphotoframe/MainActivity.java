package com.tinyapps7.clockphotoframe;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.tinyapps7.clockphotoframe.utils_prj.AnimUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.analog)
    ImageView analog;
    @InjectView(R.id.digital)
    ImageView digital;
    @InjectView(R.id.photoanalog)
    ImageView photoanalog;
    @InjectView(R.id.creation)
    ImageView creation;
    AnimUtils animUtils;
    MarshMallowPermission marshMallowPermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        animUtils=new AnimUtils();
        marshMallowPermission=new MarshMallowPermission(this);
        if(Build.VERSION.SDK_INT>=23)
        {
            if(!marshMallowPermission.checkPermissionForExternalStorage())
            {
                marshMallowPermission.requestPermissionForExternalStorage();
            }
            else if(!marshMallowPermission.checkPermissionForCamera())

            {
                marshMallowPermission.requestPermissionForCamera();
            }
        }
        else
        {}
    }

    @OnClick({R.id.analog, R.id.digital, R.id.photoanalog, R.id.creation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.analog:
                animUtils.bounce(analog,this);
               startActivity(new Intent(this, DigitalClock.class));
                break;
            case R.id.digital:
                animUtils.bounce(digital,this);
                break;
            case R.id.photoanalog:
                animUtils.bounce(photoanalog,this);
                startActivity(new Intent(this,PhotoImageActivity.class));
                break;
            case R.id.creation:
                animUtils.bounce(creation,this);
                break;
        }
    }
}
