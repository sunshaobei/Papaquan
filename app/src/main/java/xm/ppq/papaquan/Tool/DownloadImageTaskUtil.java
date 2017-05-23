package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.ViewGroup;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import xm.ppq.papaquan.Tool.customview.CustomImageView;
import xm.ppq.papaquan.Tool.customview.ScreenTools;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/4/7.
 */
public class DownloadImageTaskUtil extends AsyncTask<String, Void, DownloadImageTaskUtil.WH> {

    private CustomImageView onimage;
    private String url;
    private Activity activity;
    private int i = 0;

    public DownloadImageTaskUtil(CustomImageView onimage, String url, Activity activity) {
        this.onimage = onimage;
        this.activity = activity;
        this.url = BaseUrl.BITMAP + url;
    }

    public DownloadImageTaskUtil(CustomImageView onimage, String url, Activity activity, int i) {
        this.onimage = onimage;
        if (i == 0) {
            this.url = BaseUrl.BITMAP + url;
        } else {
            this.url = url;
        }
        this.i = i;
        this.activity = activity;
    }

    protected WH doInBackground(String... urls) {
        try {
            URL m_url = null;
            if (i == 0) {
                m_url = new URL(BaseUrl.BITMAP + urls[0]);
            } else {
                m_url = new URL(urls[0]);
            }
            HttpURLConnection con = (HttpURLConnection) m_url.openConnection();
            InputStream in = con.getInputStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            int height = options.outHeight;
            int width = options.outWidth;
            WH wh = new WH(height, width);
            return wh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void handlerOneImage(Activity activity, CustomImageView oneimage, Image image) {
        int totalWidth;
        int imageWidth;
        int imageHeight;
        ScreenTools screentools = ScreenTools.instance(activity);
        totalWidth = screentools.getScreenWidth() - screentools.dip2px(80);
        imageWidth = screentools.dip2px(image.getWidth());
        imageHeight = screentools.dip2px(image.getHeight());
        if (image.getWidth() <= image.getHeight()) {
            if (imageHeight > totalWidth) {
                imageHeight = totalWidth;
                imageWidth = (imageHeight * image.getWidth()) / image.getHeight();
            }
        } else {
            if (imageWidth > totalWidth) {
                imageWidth = totalWidth;
                imageHeight = (imageWidth * image.getHeight()) / image.getWidth();
            }
        }
        ViewGroup.LayoutParams layoutparams = oneimage.getLayoutParams();
        layoutparams.height = imageHeight;
        layoutparams.width = imageWidth;
        oneimage.setLayoutParams(layoutparams);
        oneimage.setClickable(true);
        oneimage.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        oneimage.setImageUrl(image.getUrl());
    }

    protected void onPostExecute(WH wh) {
        if (wh != null) {
            handlerOneImage(activity, onimage, new Image(url, wh.getWidth(), wh.getHeight()));
        }
    }

    class WH {
        int height;
        int width;

        public WH(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}