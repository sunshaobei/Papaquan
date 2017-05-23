package xm.ppq.papaquan.Tool.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import xm.ppq.papaquan.Adapter.FollowerAndFollowAdapter;
import xm.ppq.papaquan.R;

/**
 * Created by sunshaobei on 2017/4/9.
 */

public class PullUprefreListView extends ListView {
    private LinearLayout footview;
    private View showview;
    private View foot;

    public PullUprefreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        foot = LayoutInflater.from(context).inflate(R.layout.item_listviewfoot, null);
//        footview = (LinearLayout) foot.findViewById(R.id.foot);
        this.addFooterView(foot);
//        footview.setVisibility(View.GONE);
//        showview = foot.findViewById(R.id.showview);

    }

    public void addfooterView(LinearLayout v)
    {
        this.footview = v;
//        this.addFooterView(v);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float y = 0;
        float y1 = 0;
        float y2 = 0;
        boolean f = true;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (shouldshow) {
                    if (f)
                    {
                        y1 = ev.getY();
                        f = false;
                    }else {
                        y2 = ev.getY();
                    }
                    float v = y1 - y2;
                    if (v>0)
                    {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) footview.getLayoutParams();
                        layoutParams.height = (int) v;
                        footview.setLayoutParams(layoutParams);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }


    private boolean shouldshow = false;

    public void setFoot(boolean b) {
        shouldshow = b;
    }
}
