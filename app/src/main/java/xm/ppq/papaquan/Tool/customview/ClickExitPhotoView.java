package xm.ppq.papaquan.Tool.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by sunshaobei on 2017/4/21.
 */

public class ClickExitPhotoView extends PhotoView {
    private Context context;

    public ClickExitPhotoView(Context context) {
        super(context);
        this.context = context;
    }

    public ClickExitPhotoView(Context context, AttributeSet attr, Context context1) {
        super(context, attr);
        this.context = context1;
    }

    private  boolean JUSTDOWN = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                JUSTDOWN = true;
                break;
            case MotionEvent.ACTION_UP:
                if (JUSTDOWN)
                {
                    ((Activity)context).finish();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                JUSTDOWN = false;
                break;
        }
        return super.onTouchEvent(event);
    }
}
