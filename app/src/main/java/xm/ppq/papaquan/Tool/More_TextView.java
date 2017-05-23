package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.TextView.MovementMethod;
import xm.ppq.papaquan.Tool.TextView.SpanTextView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class More_TextView extends LinearLayout implements
        View.OnClickListener {

    /**
     * default text show max lines
     */
    private static final int DEFAULT_MAX_LINE_COUNT = 3;
    private static final int COLLAPSIBLE_STATE_NONE = 0;
    private static final int COLLAPSIBLE_STATE_SHRINKUP = 1;
    private static final int COLLAPSIBLE_STATE_SPREAD = 2;

    private SpanTextView desc;
    private TextView descOp;

    private String shrinkup;
    private String spread;
    private int mState;
    private boolean flag;
    private MovementMethod movementMethod;

    public More_TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        shrinkup = context.getString(R.string.desc_shrinkup);
        spread = context.getString(R.string.desc_spread);
        View view = inflate(context, R.layout.view_more, this);
        view.setPadding(0, 0, 0, 0);
        desc = (SpanTextView) view.findViewById(R.id.desc_tv);
        descOp = (TextView) view.findViewById(R.id.desc_op_tv);
        descOp.setOnClickListener(this);
        movementMethod = new MovementMethod(getContext());
    }

    public More_TextView(Context context) {
        this(context, null);
    }

    public void setDesc(CharSequence charSequence, String tid) {
        desc.setText(charSequence.toString());
        movementMethod.setTid(tid);
        desc.setMovementMethod(movementMethod);
        mState = COLLAPSIBLE_STATE_SPREAD;
        flag = false;
        requestLayout();
    }

    @Override
    public void onClick(View v) {
        flag = false;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!flag) {
            flag = true;
            if (desc.getLineCount() <= DEFAULT_MAX_LINE_COUNT) {
                mState = COLLAPSIBLE_STATE_NONE;
                descOp.setVisibility(View.GONE);
                desc.setMaxLines(DEFAULT_MAX_LINE_COUNT + 1);
            } else {
                post(new InnerRunnable());
            }
        }
    }

    class InnerRunnable implements Runnable {
        @Override
        public void run() {
            if (mState == COLLAPSIBLE_STATE_SPREAD) {
                desc.setMaxLines(DEFAULT_MAX_LINE_COUNT);
                descOp.setVisibility(View.VISIBLE);
                descOp.setText(spread);
                mState = COLLAPSIBLE_STATE_SHRINKUP;
            } else if (mState == COLLAPSIBLE_STATE_SHRINKUP) {
                desc.setMaxLines(Integer.MAX_VALUE);
                descOp.setVisibility(View.VISIBLE);
                descOp.setText(shrinkup);
                mState = COLLAPSIBLE_STATE_SPREAD;
            }
        }
    }
}