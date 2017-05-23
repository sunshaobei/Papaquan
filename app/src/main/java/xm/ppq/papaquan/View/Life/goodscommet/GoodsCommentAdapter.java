package xm.ppq.papaquan.View.Life.goodscommet;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.JudgePanicBuyBean;
import xm.ppq.papaquan.Bean.life.JudgeRebateBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by sunshaobei on 2017/4/27.
 */

public class GoodsCommentAdapter<T> extends ConcreteAdapter<T> {

    private String type;

    public GoodsCommentAdapter(Context context, List<T> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder viewHolder, T t, int position) {
        if (type.equals("抢购")) {
            JudgePanicBuyBean.DataBean.ListBean item = (JudgePanicBuyBean.DataBean.ListBean) t;
            viewHolder.setText(item.getNickname(), R.id.name)
                    .setText(item.getCreatetime(), R.id.time)
                    .setText(item.getContent(), R.id.content);
            ImageView image = viewHolder.getView(R.id.headicon);

            Glide.with(getContext()).load(item.getHeadurl()).into(image);
            if (item.getBusiness_reply() != null) {
                viewHolder.setVisibility(View.VISIBLE, R.id.reply)
                        .setText("商家回复：" + item.getBusiness_reply().toString(), R.id.reply);
            } else viewHolder.setVisibility(View.GONE, R.id.reply);
            GridView gridView = viewHolder.getView(R.id.gridview);
            if (item.getPicture() != null) {
                gridView.setVisibility(View.VISIBLE);
                gridView.setAdapter(new ConcreteAdapter<String>(getContext(), item.getPicture(), R.layout.item_goodscommentgridview) {
                    @Override
                    protected void convert(ViewHolder viewHolder, String item, int position) {
                        ImageView imageView = viewHolder.getView(R.id.imageView);
                        Glide.with(getContext()).load(BaseUrl.BITMAP + item).into(imageView);
                    }
                });
            } else {
                viewHolder.setVisibility(View.GONE, R.id.gridview);
            }
        } else {
            JudgeRebateBean.DataBean item = (JudgeRebateBean.DataBean) t;
            viewHolder.setText(item.getNickname(), R.id.name)
                    .setText(item.getCreatetime(), R.id.time)
                    .setText(item.getContent(), R.id.content);
            ImageLoading.Circular((Activity) getContext(), item.getHeadurl(), viewHolder.getView(R.id.headicon), R.drawable.default_icon);
            if (item.getBusiness_reply() != null) {
                viewHolder.setVisibility(View.VISIBLE, R.id.reply)
                        .setText("商家回复：" + item.getBusiness_reply(), R.id.reply);
            } else {
                viewHolder.setVisibility(View.GONE, R.id.reply);
            }
            GridView view = viewHolder.getView(R.id.gridview);
            if (item.getPicture() != null) {
                view.setAdapter(new ConcreteAdapter<String>(getContext(), item.getPicture(), R.layout.item_goodscommentgridview) {
                    @Override
                    protected void convert(ViewHolder viewHolder, String item, int position) {
                        ImageLoading.common(getContext(), BaseUrl.BITMAP + item, viewHolder.getView(R.id.imageView), R.drawable.default_icon_zheng);
                    }
                });
            }
        }
    }
}
