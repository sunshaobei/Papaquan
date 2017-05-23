package com.example.lib_sunshaobei2017.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
	public static Drawable bitmap2Drawable(Bitmap bmp) {
		BitmapDrawable bd = new BitmapDrawable(bmp);
		return bd;
	}

	public static Bitmap drawable2Bitmap(Drawable d) {
		BitmapDrawable bd = (BitmapDrawable) d;
		Bitmap bm = bd.getBitmap();
		return bm;
	}

	public static Bitmap getBitmapFromResources(Activity act, int resId) {
		Resources res = act.getResources();
		return BitmapFactory.decodeResource(res, resId);
	}

	public static Bitmap convertBytes2Bimap(byte[] b) {
		if (b.length == 0) {
			return null;
		}
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}

	public static byte[] convertBitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	
	public static Bitmap compressImageFromFile(String srcPath, int width, int height) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		// 读取出图片实际的宽高
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		int be = 1;
		if (w > h && w > width) {
			be = (int) (w / width);
		} else if (w < h && h > height) {
			be = (int) (h / height);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置压缩比例

		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 当系统内存不够时候图片自动被回收

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return bitmap;
	}

	public static Bitmap compressImageFromFile(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		// 读取出图片实际的宽高
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 800f;//
		float ww = 480f;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (w / ww);
		} else if (w < h && h > hh) {
			be = (int) (h / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置压缩比例

		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 当系统内存不够时候图片自动被回收

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return bitmap;
	}

	public void saveBitmap(Bitmap bm,String name) {
		File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), name);
		if (f.exists()) {
			f.delete();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}finally {
			if (out!=null)
			{
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		}
}
