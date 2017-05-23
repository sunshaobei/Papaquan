package xm.ppq.papaquan.Adapter.base;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.View;


/**
 * Created by Administrator on 2017/1/11.
 */

public interface Holder {

    <T extends View> T getView(int rid);

    Holder setLinearLayout(int rid, int left_padding, int right_padding);

    Holder setKtTextView(int rid, CharSequence sequence, int l_r, int t_b);

    Holder setKtClicked(int rid, boolean clicked);

    Holder setFrameLayout(int rid, int width, int height);

    Holder setText(CharSequence result, int rid);

    Holder setTextColor(int cid, int rid);

    Holder setTextColor(ColorStateList color, int rid);

    Holder setSpanned(Spanned spannld, int rid);

    Holder setText(int colors, int rid);

    Holder setText(String result, int rid, int width, int height);

    Holder setText(String result, int rid, int visibility);

    Holder setResources(int did, int rid);

    Holder setResources(int did, int rid, int visibility);

    Holder setVisibility(int visibility, int rid);

    Holder setOnClickListener(View.OnClickListener onClickListener, int rid);

    Holder setBackGround(Drawable drawable, int rid);

    View getHoldView();
}