package com.tinyapps7.clockphotoframe.settings;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.Html.ImageGetter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tinyapps7.clockphotoframe.BuildConfig;
import com.tinyapps7.clockphotoframe.Photo_Clock_ConstantsTime;
import com.tinyapps7.clockphotoframe.R;

import java.util.Calendar;

@SuppressLint({"InlinedApi"})
public class PhotoOnClockSettings extends PreferenceActivity implements OnSharedPreferenceChangeListener, ImageGetter {
    static final int END_DATE = 3;
    static final int END_TIME = 4;
    public static int bubblecount1;
    public static String currentvalue;
    CheckBoxPreference animation;
    private ImageListPreference animationtype;
    ImageView bckbtnonclock;
    final Calendar calendar;
    Preference endDate;
    Preference endTime;
    public Handler f2283h;
    SeekBarDialogPreference hcount;
    UdinicPreferenceCategory heart;
    private ListPreference list4cd;
    private ListPreference list4style;
    SharedPreferences pref;
    Preference text1;

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.PhotoOnClockSettings.1 */
    class handler extends Handler {
        handler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0 /*0*/:
                    PhotoOnClockSettings.this.finish();
                default:
            }
        }
    }

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.PhotoOnClockSettings.2 */
    class onprefrence implements OnPreferenceChangeListener {

        /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.PhotoOnClockSettings.2.1 */
        class click implements OnClickListener {
            final /* synthetic */ EditText val$et1;

            click(EditText editText) {
                this.val$et1 = editText;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                PhotoOnClockSettings.currentvalue = this.val$et1.getText().toString();
                if (PhotoOnClockSettings.currentvalue.equals(BuildConfig.FLAVOR)) {
                    PhotoOnClockSettings.currentvalue = BuildConfig.FLAVOR;
                }
            }
        }

        /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.PhotoOnClockSettings.2.2 */
        class click1 implements OnClickListener {
            click1() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        onprefrence() {
        }

        public boolean onPreferenceChange(Preference preference, Object obj) {
            if (PhotoOnClockSettings.this.list4cd.findIndexOfValue(obj.toString()) == 0) {
                Builder builder = new Builder(PhotoOnClockSettings.this);
                LinearLayout linearLayout = new LinearLayout(PhotoOnClockSettings.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                EditText editText = new EditText(PhotoOnClockSettings.this);
                editText.setHint(PhotoOnClockSettings.this.getResources().getString(R.string.count_down_title));
                linearLayout.addView(editText);
                builder.setView(linearLayout);
                builder.setTitle(PhotoOnClockSettings.this.getResources().getString(R.string.select_text));
                builder.setPositiveButton(PhotoOnClockSettings.this.getResources().getString(R.string.ok), new click(editText));
                builder.setNegativeButton(PhotoOnClockSettings.this.getResources().getString(R.string.cancel), new click1());
                PhotoOnClockSettings.this.showDialog(PhotoOnClockSettings.END_TIME);
                PhotoOnClockSettings.this.showDialog(PhotoOnClockSettings.END_DATE);
                builder.show();
            }
            return true;
        }
    }

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.PhotoOnClockSettings.3 */
    class click2 implements View.OnClickListener {
        click2() {
        }

        public void onClick(View view) {
            Toast.makeText(PhotoOnClockSettings.this, "Pagal", Toast.LENGTH_SHORT).show();
        }
//            Intent intent = new Intent(PhotoOnClockSettings.this.getApplicationContext(), MainActivity.class);
//            intent.addFlags(872448000);
//            PhotoOnClockSettings.this.startActivity(intent);
//            PhotoOnClockSettings.this.finish();
//        }
    }

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.PhotoOnClockSettings.4 */
    class onDatea implements OnDateSetListener {
        onDatea() {
        }

        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            Photo_Clock_ConstantsTime.INSTANCE.setEndYear(i);
            Photo_Clock_ConstantsTime.INSTANCE.setEndMonth(i2);
            Photo_Clock_ConstantsTime.INSTANCE.setEndDay(i3);
        }
    }

    /* renamed from: com.apptrends.photo.analog.clock.digital.clock.photo.editor.photo_on_clock.PhotoOnClockSettings.5 */
    class listnertime implements OnTimeSetListener {
        listnertime() {
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            Photo_Clock_ConstantsTime.INSTANCE.setEndHours(i);
            Photo_Clock_ConstantsTime.INSTANCE.setEndMinutes(i2);
            Photo_Clock_ConstantsTime.INSTANCE.setEndSeconds(0);
        }
    }

    static {
        bubblecount1 = 10;
        currentvalue = BuildConfig.FLAVOR;
    }

    public PhotoOnClockSettings() {
        this.calendar = Calendar.getInstance();
    }

    private void setLeafFallingSpeedSummary() {
    }

    private void setLeafNumberSummary() {
    }

    private void setStyleSummary() {
    }

    public Drawable getDrawable(String str) {
        return null;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.photo_on_clock_setting);
        setContentView(R.layout.photo_on_clock_pref_main);
        //((AdView) findViewById(R.id.adView)).m5116a(new C1416a().m5171b("2572C6D8A61EB429D8DDE8A79434CA3B").m5170a());
        this.f2283h = new handler();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        //this.bckbtnonclock = (ImageView) findViewById(R.id.bckbtnonclock);
        this.pref = PreferenceManager.getDefaultSharedPreferences(this);
        this.pref.registerOnSharedPreferenceChangeListener(this);
        setLeafNumberSummary();
        setLeafFallingSpeedSummary();
        this.animation = (CheckBoxPreference) findPreference("animations_photo");
        this.animationtype = (ImageListPreference) findPreference("Heart_direction_photo");
        this.hcount = (SeekBarDialogPreference) findPreference("bubblenumber_photo");
        this.heart = (UdinicPreferenceCategory) findPreference("love_key");
        if (this.pref.getBoolean("animations_photo", true)) {
            this.heart.addPreference(this.animationtype);
            this.heart.addPreference(this.hcount);
        } else {
            this.heart.removePreference(this.animationtype);
            this.heart.removePreference(this.hcount);
        }
        this.list4style = (ListPreference) findPreference("style_photo");
        setStyleSummary();
        this.list4cd = (ListPreference) findPreference("countdown_photo");
        this.endDate = findPreference("timePrefA_Key");
        this.endTime = findPreference("timePrefB_Key");
        this.text1 = findPreference("editText");
        this.list4cd.setOnPreferenceChangeListener(new onprefrence());
        this.bckbtnonclock.setOnClickListener(new click2());
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case END_DATE /*3*/:
                return new DatePickerDialog(this, new onDatea(), this.calendar.get(Calendar.HOUR), this.calendar.get(2), this.calendar.get(5));
            case END_TIME /*4*/:
                return new TimePickerDialog(this, new listnertime(), this.calendar.get(Calendar.SECOND), this.calendar.get(12), false);
            default:
                return null;
        }
    }

    protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != END_TIME || keyEvent.getRepeatCount() == 0) {
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.equals("style_photo")) {
            this.list4style.getValue();
            setStyleSummary();
        }
        if (str.equals("animations_photo")) {
            try {
                if (sharedPreferences.getBoolean("animations_photo", true)) {
                    this.heart.addPreference(this.animationtype);
                    this.heart.addPreference(this.hcount);
                    return;
                }
                this.heart.removePreference(this.animationtype);
                this.heart.removePreference(this.hcount);
            } catch (Exception e) {
            }
        }
    }
}
