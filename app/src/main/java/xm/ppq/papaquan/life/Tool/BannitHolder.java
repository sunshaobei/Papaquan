package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/4/19.
 */

public class BannitHolder implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        ImageLoading.common(context, BaseUrl.BITMAP + data, imageView, R.mipmap.bananer_1);
    }
}
