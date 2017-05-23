package com.example.lib_sunshaobei2017.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by sunshaobei on 2017/4/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(oncreate());
        ButterKnife.bind(this);
        initView();
        initListener();
    }


    @Override
    protected void onStart() {
        super.onStart();
        onstart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onresume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        onstop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ondestroy();
    }

    public abstract int oncreate();
    public abstract void initView();
    public abstract void initListener();

    public abstract void onstart();
    public abstract void onresume();
    public abstract void onstop();
    public abstract void ondestroy();

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

    public void ShortToast(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    public void LongToast(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
    public void CustomsToast(View v)
    {
        Toast toast = new Toast(this);
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题  
        toast.setGravity(Gravity.TOP, 0, height / 3);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(v);
    }
    public void CustomlToast(View v)
    {
        Toast toast = new Toast(this);
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题  
        toast.setGravity(Gravity.TOP, 0, height / 3);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(v);
    }

    public void Go2(Class c)
    {
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }
    public void Go2(Class c,String key,String value)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivity(intent);
    }
    public void Go2(Class c,String key,int value)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivity(intent);
    }
    public void Go2(Class c,String key,boolean value)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivity(intent);
    }
    public void Go2(Class c,String key,ArrayList value)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivity(intent);
    }

    public void Go2(Class c,int requestcode)
    {
        Intent intent = new Intent(this,c);
        startActivityForResult(intent,requestcode);
    }
    public void Go2(Class c,String key,String value,int requestcode)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivityForResult(intent,requestcode);
    }
    public void Go2(Class c,String key,boolean value,int requestcode)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivityForResult(intent,requestcode);
    }
    public void Go2(Class c,String key,int value,int requestcode)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivityForResult(intent,requestcode);
    }
    public void Go2(Class c, String key, ArrayList<?> value, int requestcode)
    {
        Intent intent = new Intent(this,c);
        intent.putExtra(key,value);
        startActivityForResult(intent,requestcode);
    }
}
