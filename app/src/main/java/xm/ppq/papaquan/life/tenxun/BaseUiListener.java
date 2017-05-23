package xm.ppq.papaquan.life.tenxun;

import android.util.Log;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Created by EdgeDi on 11:15.
 */

public class BaseUiListener implements IUiListener {

    @Override
    public void onComplete(Object o) {

    }

    @Override
    public void onError(UiError uiError) {
        Log.e("onError", uiError.errorDetail + uiError.errorMessage + uiError.errorCode);
    }

    @Override
    public void onCancel() {

    }
}
