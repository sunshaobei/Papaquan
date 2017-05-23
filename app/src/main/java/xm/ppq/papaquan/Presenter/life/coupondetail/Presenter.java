package xm.ppq.papaquan.Presenter.life.coupondetail;

import android.content.Context;
import android.widget.RadioButton;

/**
 * Created by sunshaobei on 2017/4/25.
 */

public interface Presenter {
    void getData(String cid);

    void ImageText(String cid);

    RadioButton CreateButton(Context context, String result);

}
