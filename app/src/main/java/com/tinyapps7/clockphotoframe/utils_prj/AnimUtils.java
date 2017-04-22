package com.tinyapps7.clockphotoframe.utils_prj;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.tinyapps7.clockphotoframe.R;


/**
 * Created by Blurpixel on 4/19/2017.
 */

public class AnimUtils
{
    public  void SlideUp(View view, Context context)
    {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.layout_up));
    }
    public void SlideDown(View v,Context mContex)
    {
        v.startAnimation(AnimationUtils.loadAnimation(mContex, R.anim.layout_down));

    }
    public void bounce(View v,Context mContex)
    {
        v.startAnimation(AnimationUtils.loadAnimation(mContex, R.anim.bounce));

    }
}
