package xm.ppq.papaquan.View;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mapsdk.raster.model.LatLng;

import java.lang.reflect.Field;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.MyToast;
import xm.ppq.papaquan.Tool.typewriting.TypewritingUtil;

import static android.view.Gravity.CENTER;

/**
 * EdgeDi
 * Created by 基类Activity on 2017/2/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Intent intent;
    private Toast toast;
    private MyToast myToast;
    private int On = Color.parseColor("#e60012");
    private int off = Color.parseColor("#555555");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Steep();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(getLayout());
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initData();
        setListener();
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void setListener();

    protected void Skip(Class cla) {
        intent = new Intent(this, cla);
        startActivity(intent);
    }

    private SparseArray<View> sparseArray = new SparseArray<>();
    private View view;

    protected AppCompatActivity getActivity() {
        return this;
    }

    protected void addHead(View view) {
        this.view = view;
    }

    protected <T extends View> T $(int rid) {
        if (sparseArray.get(rid) == null) {
            sparseArray.append(rid, view.findViewById(rid));
            return (T) sparseArray.get(rid);
        } else {
            return (T) sparseArray.get(rid);
        }
    }

    protected void Skip(Class cla, String key, String value) {
        intent = new Intent(this, cla);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    protected void Skip(Class cla, String key, String value, String key2, String value2) {
        intent = new Intent(this, cla);
        intent.putExtra(key, value);
        intent.putExtra(key2, value2);
        startActivity(intent);
    }

    protected void SkipForResult(Class cla, String key, String value, int requestcode) {
        intent = new Intent(this, cla);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestcode);
    }

    protected void SkipForResult(Class cla, int requestcode) {
        intent = new Intent(this, cla);
        startActivityForResult(intent, requestcode);
    }

    protected String getData(String key) {
        return getIntent().getStringExtra(key);
    }

    protected void ToastShow(String result) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
        } else {
            toast.setText(result);
        }
        toast.show();
    }

    protected void ToastShow() {
        if (myToast == null) {
            myToast = MyToast.makeText(this, Toast.LENGTH_SHORT);
            myToast.setGravity(CENTER, 0, 0);
        }
        myToast.show();
    }

    public static String Number() {
        long str = System.currentTimeMillis();//获取当前时间戳
        return String.valueOf(str);
    }

    private void Steep() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * created by sunshaobei 2017 3 3 9:03
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void initStatusBar(LinearLayout bar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusbarheight = getStatusBarHeight();
            bar.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) bar.getLayoutParams();
            layoutParams.height = statusbarheight;
            bar.setLayoutParams(layoutParams);
        }
    }

    public void initStatusBar(LinearLayout bar, int h) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bar.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) bar.getLayoutParams();
            layoutParams.height = h;
            bar.setLayoutParams(layoutParams);
        }
    }

    protected String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    protected void TextView_Delete(TextView... textView) {
        for (int i = 0; i < textView.length; i++) {
            textView[i].getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    protected void Checkboxed(int i, CheckBox... checkBoxes) {
        for (int j = 0; j < checkBoxes.length; j++) {
            if (i == j) {
                checkBoxes[j].setChecked(true);
            } else {
                checkBoxes[j].setChecked(false);
            }
        }
    }

    protected AppCompatActivity getThis() {
        return this;
    }

    protected void TextSelect(int position, TextView... textViews) {
        for (int i = 0; i < textViews.length; i++) {
            if (position == i) {
                textViews[i].setTextColor(On);
            } else {
                textViews[i].setTextColor(off);
            }
        }
    }

    protected double Calculated_distance(LatLng start, LatLng end) {
        if (start != null) {
            double lat1 = (Math.PI / 180) * start.getLatitude();
            double lat2 = (Math.PI / 180) * end.getLatitude();
            double lon1 = (Math.PI / 180) * start.getLongitude();
            double lon2 = (Math.PI / 180) * end.getLongitude();
            //地球半径
            double R = 6371;
            //两点间距离 km，如果想要米的话，结果*1000就可以了
            double b = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
            ;
            if (b > 500) {
                b = 500;
            }
            return b;
        } else {
            return 0;
        }
    }

    protected int t_on = Color.parseColor("#e60012");
    protected int t_off = Color.parseColor("#555555");

    protected void TextViewColor(int position, TextView... textViews) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == position) {
                textViews[i].setTextColor(t_on);
            } else {
                textViews[i].setTextColor(t_off);
            }
        }
    }
}