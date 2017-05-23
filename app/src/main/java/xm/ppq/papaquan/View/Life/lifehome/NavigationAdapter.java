package xm.ppq.papaquan.View.Life.lifehome;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.LifeHomeData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by sunshaobei on 2017/4/25.
 */

public class NavigationAdapter extends ConcreteAdapter<LifeHomeData.DataBean.NavigationIconBean>{

    private Context context;

    public NavigationAdapter(Context context, List<LifeHomeData.DataBean.NavigationIconBean> list, int itemLayout) {
        super(context, list, itemLayout);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder viewHolder, LifeHomeData.DataBean.NavigationIconBean item, int position) {
        ImageView view = viewHolder.getView(R.id.icon);
        if (item.getImg()!=null)
        {
            Glide.with(context).load(BaseUrl.BITMAP+item.getImg()).into(view);
            TextView textView = viewHolder.getView(R.id.title);
            textView.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
            viewHolder.setText(item.getTitle(),R.id.title);
        }else {
            view.setImageDrawable(new ColorDrawable(Color.parseColor("#cdcdcd")));
            TextView textView = viewHolder.getView(R.id.title);
            textView.setBackground(new ColorDrawable(Color.parseColor("#cdcdcd")));
            textView.setText("");
        }
    }
}
