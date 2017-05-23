package xm.ppq.papaquan.Tool.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;

import xm.ppq.papaquan.R;

public class Xffoot extends LinearLayout implements IFooterCallBack {
    private Context mContext;

    private View mContentView;
    private TextView mHintView;
    private TextView mClickView;
    private boolean showing = true;

    public Xffoot(Context context) {
        super(context);
        initView(context);
    }

    public Xffoot(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    public void callWhenNotAutoLoadMore(final XRefreshView xRefreshView) {
        mClickView.setText(com.andview.refreshview.R.string.xrefreshview_footer_hint_click);
        mClickView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                xRefreshView.notifyLoadMore();
            }
        });
    }

    @Override
    public void onStateReady() {
        mHintView.setVisibility(View.GONE);
        mClickView.setText(com.andview.refreshview.R.string.xrefreshview_footer_hint_click);
        mClickView.setVisibility(View.VISIBLE);
//        show(true);
    }

    @Override
    public void onStateRefreshing() {
        mHintView.setVisibility(View.GONE);
//        mClickView.setText("正在加载");
        mClickView.setVisibility(View.VISIBLE);
        show(true);
    }

    @Override
    public void onReleaseToLoadMore() {
        mHintView.setVisibility(View.GONE);
        mClickView.setText(com.andview.refreshview.R.string.xrefreshview_footer_hint_release);
        mClickView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStateFinish(boolean hideFooter) {
        if (hideFooter) {
            mHintView.setText(com.andview.refreshview.R.string.xrefreshview_footer_hint_normal);
        } else {
            //处理数据加载失败时ui显示的逻辑，也可以不处理，看自己的需求
            mHintView.setText(com.andview.refreshview.R.string.xrefreshview_footer_hint_fail);
        }
        mHintView.setVisibility(View.VISIBLE);
        mClickView.setVisibility(View.GONE);
    }

    @Override
    public void onStateComplete() {
        mHintView.setText(com.andview.refreshview.R.string.xrefreshview_footer_hint_complete);
        mHintView.setVisibility(View.VISIBLE);
        mClickView.setVisibility(View.GONE);
    }

    @Override
    public void show(final boolean show) {
        if (show == showing) {
            return;
        }
        showing = show;
        LayoutParams lp = (LayoutParams) mContentView
                .getLayoutParams();
        lp.height = show ? LayoutParams.WRAP_CONTENT : 0;
        mContentView.setLayoutParams(lp);
//        setVisibility(show?VISIBLE:GONE);

    }

    @Override
    public boolean isShowing() {
        return showing;
    }

    private void initView(Context context) {
        mContext = context;
        ViewGroup moreView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.xfreshviewfoot, this);
        moreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        mContentView = moreView.findViewById(R.id.xrefreshview_footer_content);
        mHintView = (TextView) moreView
                .findViewById(R.id.xrefreshview_footer_hint_textview);
        mClickView = (TextView) moreView
                .findViewById(R.id.xrefreshview_footer_click_textview);
    }

    @Override
    public int getFooterHeight() {
        return getMeasuredHeight();
    }
}
