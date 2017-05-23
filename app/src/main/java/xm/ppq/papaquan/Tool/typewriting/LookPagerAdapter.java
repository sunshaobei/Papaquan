package xm.ppq.papaquan.Tool.typewriting;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class LookPagerAdapter extends PagerAdapter {

    private Context context;
    private List<GridView> list;

    public LookPagerAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<GridView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

}
