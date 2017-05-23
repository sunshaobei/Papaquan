package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/3/29.
 */

public class DynamicDialog extends BaseDialog {

    private TextView yes;
    private TextView no;
    private Activity activity;

    public DynamicDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected int getLayout() {
        return R.layout.dy_dialog;
    }

    @Override
    protected void initUI() {
        yes = bind(R.id.yes);
        no = bind(R.id.no);
        yes.setOnClickListener(v -> {
            dismiss();
            getActivity().finish();
        });
        no.setOnClickListener(v -> {
            dismiss();
        });
    }

}