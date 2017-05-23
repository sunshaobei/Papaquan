package com.example.lib_sunshaobei2017.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by sunshaobei on 2017/4/11.
 */

public class SunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

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

    protected int on = Color.parseColor("#e60012");
    protected int off = Color.parseColor("#555555");

    protected void TextViewColor(int position, TextView... textViews) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == position) {
                textViews[i].setTextColor(on);
            } else {
                textViews[i].setTextColor(off);
            }
        }
    }

    protected Long getTime() {
        return System.currentTimeMillis() / 1000;
    }

    public void ShortToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void LongToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void CustomsToast(int v) {
        Toast toast = new Toast(this);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题  
//        toast.setGravity(Gravity.TOP, 0, height / 3);
        toast.setDuration(Toast.LENGTH_SHORT);
        View inflate = getLayoutInflater().inflate(v, null);
        toast.setView(inflate);
        toast.show();
    }

    public void CustomsfullscreamToast(int v) {
        Toast toast = new Toast(this);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题  
//        toast.setGravity(Gravity.TOP, 0, height / 3);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        RelativeLayout.LayoutParams vlp = new RelativeLayout.LayoutParams(outMetrics.widthPixels,
                outMetrics.heightPixels);
        vlp.setMargins(0, 0, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        RelativeLayout inflate = (RelativeLayout) getLayoutInflater().inflate(v, null);
        inflate.setLayoutParams(vlp);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.setView(inflate);
        toast.show();
    }

    public void CustomlToast(int v) {
        Toast toast = new Toast(this);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题  
        toast.setGravity(Gravity.TOP, 0, height / 3);
        toast.setDuration(Toast.LENGTH_LONG);
        View inflate = getLayoutInflater().inflate(v, null);
        toast.setView(inflate);
        toast.show();
    }


    public void Go2(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    public void Go2(Class c, String key, String value) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void Go2(Class c, String key, int value) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void Go2(Class c, String key, boolean value) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void Go2(Class c, String key, ArrayList value) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void Go2(Class c, int requestcode) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, requestcode);
    }

    public void Go2(Class c, String key, String value, int requestcode) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestcode);
    }
    public void Go2(Class c, String key, int value, int requestcode,String key2,String valus2) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        intent.putExtra(key2,valus2);
        startActivityForResult(intent, requestcode);
    }

    public void Go2(Class c, String key, boolean value, int requestcode) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestcode);
    }

    public void Go2(Class c, String key, int value, int requestcode) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestcode);
    }

    public void Go2(Class c, String key, ArrayList<?> value, int requestcode) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, value);
        startActivityForResult(intent, requestcode);
    }

    public void Go2(Class c, String key, Bundle bundle, int requestcode) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, bundle);
        startActivityForResult(intent, requestcode);
    }

}
