package xm.ppq.papaquan.Tool.xRefreview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;

/**
 * Created by sunshaobei on 2017/4/18.
 */

public class xRefreviewFoot extends FrameLayout implements IFooterCallBack{

    private final LayoutInflater inflater;
    private Context context;
    private View foot;
    @BindView(R.id.foot_image)
    ImageView foot_image;
    @BindView(R.id.foot_text)
    TextView foot_text;

    public xRefreviewFoot(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
        this.context = context;
        initView();
    }

    private void initView() {
        foot = inflater.inflate(R.layout.item_xrefreshfoot, null);
        ButterKnife.bind(this,foot);
        AnimationDrawable animation = (AnimationDrawable) foot_image.getDrawable();
        animation.start();
        this.addView(foot);
    }

    @Override
    public void callWhenNotAutoLoadMore(XRefreshView xRefreshView) {

    }

    @Override
    public void onStateReady() {

    }

    @Override
    public void onStateRefreshing() {

    }

    @Override
    public void onReleaseToLoadMore() {

    }

    @Override
    public void onStateFinish(boolean hidefooter) {

    }

    @Override
    public void onStateComplete() {

    }

    @Override
    public void show(boolean show) {

    }

    @Override
    public boolean isShowing() {
        return true;
    }

    @Override
    public int getFooterHeight() {
        return foot.getHeight();
    }
}
