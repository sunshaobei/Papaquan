package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2017/4/11.
 */

public class CreateView {

    private View view;
    private SparseArray<View> sparseArray = new SparseArray<>();
    private Context context;

    public CreateView(Activity activity) {
        context = activity;
        view = activity.getWindow().getDecorView();
    }

    public CreateView(View view) {
        this.view = view;
        context = view.getContext();
    }

    public CreateView(Context context, int rid) {
        this.context = context;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(rid, null);
        }
    }

    public <T extends View> T $(int rid) {
        View viewchild = null;
        if (sparseArray.get(rid) == null) {
            viewchild = view.findViewById(rid);
            sparseArray.append(rid, view);
        } else {
            viewchild = sparseArray.get(rid);
        }
        if (viewchild == null) {
            new NullPointerException("找不到该控件");
            return null;
        } else {
            return (T) viewchild;
        }
    }

    public int GetColor(int cid) {
        return context.getResources().getColor(cid);
    }

    public Bitmap GetBitmap(int did) {
        return BitmapFactory.decodeResource(context.getResources(), did);
    }

    public View getView() {
        return view;
    }
}