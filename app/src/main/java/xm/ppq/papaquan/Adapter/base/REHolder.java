package xm.ppq.papaquan.Adapter.base;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Administrator on 2017/3/9.
 */

public interface REHolder {

    <T extends View> T getView(int rid);

    REHolder setText(String result, int rid);

    REHolder setText(int colors, int rid);

    REHolder setTextVisi(int rid, int visibility);

    REHolder setOnClickener(View.OnClickListener onClickListener, int rid);

    REHolder setOnClickListener(View.OnClickListener onClickListener, int rid);

    REHolder setTextBackGround(CharSequence result, Drawable drawable, int rid);

    REHolder setTextTime(long time, int rid);

    REHolder setTimeColor(int color, int rid,int type);
}