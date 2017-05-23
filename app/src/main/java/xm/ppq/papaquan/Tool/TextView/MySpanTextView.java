package xm.ppq.papaquan.Tool.TextView;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by sunshaobei on 2017/5/5.
 */

public class MySpanTextView extends SpanTextView {
    public MySpanTextView(Context context) {
        super(context);
    }

    public MySpanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySpanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setText(CharSequence charSequence, String tid) {
        setText(charSequence.toString());
        MovementMethod movementMethod = new MovementMethod(getContext());
        movementMethod.setTid(tid);
        setMovementMethod(movementMethod);
        requestLayout();
    }
}
