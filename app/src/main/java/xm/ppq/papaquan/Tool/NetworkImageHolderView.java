package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by sunshaobei on 2017/5/13.
 */

public class NetworkImageHolderView implements Holder<String> {

    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context).load(data).into(imageView);
    }
}
