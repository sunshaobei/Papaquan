package xm.ppq.papaquan.View.alltopic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.PapaTopicBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageUtil;
import xm.ppq.papaquan.View.BaseUrl;

import static com.alibaba.mobileim.YWChannel.getResources;

/**
 * Created by sunshaobei on 2017/5/3.
 */

public class AlltopicListAdapter extends ConcreteAdapter<PapaTopicBean.DataBean> {

    public AlltopicListAdapter(Context context, List<PapaTopicBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, PapaTopicBean.DataBean item, int position) {
        ImageView image = viewHolder.getView(R.id.icon);
        if (item.getImg() == null || item.getImg().equals("")) {
            image.setImageResource(R.drawable.topic_item);
        } else {
            Picasso.with(getContext()).load(BaseUrl.BITMAP + item.getImg()).into(new com.squareup.picasso.Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Bitmap roundedCornerBitmap = ImageUtil.getRoundedCornerBitmap(bitmap, 10);
                    image.setImageBitmap(roundedCornerBitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }
        viewHolder.setText(item.getTitle(), R.id.title)
                .setText(item.getJoinnum() + " 参与", R.id.takepartin)
                .setText(item.getHeat() + " 阅读", R.id.readtime);
    }
}
