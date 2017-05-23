package xm.ppq.papaquan.Tool.typewriting;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by 输入法工具类 on 2017/2/27.
 */

public class TypewritingUtil {

    private InputMethodManager inputMethodManager;

    public TypewritingUtil(Activity activity) {
        inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
    }

    public void ShowType(EditText editText) {
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    public void Hide(View v) {
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }

    /**
     * @return true表示打开
     */
    public boolean GainType() {
        return inputMethodManager.isActive();
    }

    /**
     * created by sunshaobei  2017 3 3 9:05
     */
    /***********************************/
    public interface IKeyBoardVisibleListener{void onSoftKeyBoardVisible(boolean visible,int keyboardHeight);}
    boolean isVisiableForLast =false;
     public void addOnSoftKeyBoardVisibleListener(Activity activity, final IKeyBoardVisibleListener listener) {
      final View decorView = activity.getWindow().getDecorView();
         decorView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
             Rect rect = new Rect();
             decorView.getWindowVisibleDisplayFrame(rect);
             //计算出可见屏幕的高度  
              int displayHight = rect.bottom - rect.top;
             //获得屏幕整体的高度  
              int hight = decorView.getHeight();
               //获得键盘高度  
             int keyboardHeight = hight-displayHight;
             boolean visible = (double) displayHight / hight < 0.8;
              if(visible != isVisiableForLast){
                  listener.onSoftKeyBoardVisible(visible,keyboardHeight );
                   }
               isVisiableForLast = visible;
               });
    }

}