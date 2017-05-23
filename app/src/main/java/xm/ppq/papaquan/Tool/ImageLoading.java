package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2017/3/10.
 */

public class ImageLoading {

    public static void common(Context activity, String url, ImageView imageView, int bitmap) {
        Glide.with(activity)
                .load(url)
                .dontAnimate()
                .centerCrop()
                .placeholder(bitmap)
                .into(imageView);
    }

    public static void common(Context activity, String url, ImageView imageView, int bitmap, int i) {
        Glide.with(activity)
                .load(url)
                .dontAnimate()
                .centerCrop()
                .bitmapTransform(new BlurTransformation(activity, i))
                .placeholder(bitmap)
                .into(imageView);
    }

    public static void Circular(Activity activity, String url, int bitmap, ImageView imageView, int x) {
        Glide.with(activity)
                .load(url)
                .transform(new GlideRoundTransform(activity, x))
                .dontAnimate()
                .centerCrop()
                .placeholder(bitmap)
                .into(imageView);
    }

    public static void Circular(Activity activity, String url, ImageView imageView, int x) {
        Glide.with(activity)
                .load(url)
                .transform(new GlideRoundTransform(activity, x))
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }

    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(Context context, final String imageUrl, int errorImageId, final ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .placeholder(errorImageId)
                .error(errorImageId)
                .into(imageView);
    }

    public static void setUri(Activity activity, String url, Bitmap bitmap, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .placeholder(new BitmapDrawable(bitmap))
                .into(imageView);
    }

    public static void Circular(Activity activity, String url, int bitmap, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .transform(new MGilde(activity))
                .dontAnimate()
                .placeholder(bitmap)
                .into(imageView);
    }

    public static void Circular(Activity activity, String url, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .transform(new MGilde(activity))
                .dontAnimate()
                .into(imageView);
    }

    public static Bitmap getBitMap(Activity activity, String url) throws ExecutionException, InterruptedException {
        return Glide.with(activity)
                .load(url)
                .asBitmap()
                .centerCrop()
                .into(100, 100)
                .get();
    }

    private static String getPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public String getRealFilePath(final Context context, final Uri uri, String path) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        path = data;
        return path.substring(path.lastIndexOf("/") + 1, path.length());
    }
}