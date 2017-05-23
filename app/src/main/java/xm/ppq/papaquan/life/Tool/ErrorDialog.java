package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;

/**
 * Created by EdgeDi on 18:50.
 */

public class ErrorDialog extends BaseDialog {

    private TextView plug_leak;
    private ImageView imageView8;

    public ErrorDialog(Context context) {
        super(context, 0);
    }

    @Override
    protected int getLayout() {
        return R.layout.rush_lose;
    }

    @Override
    protected void initUI() {
        plug_leak = bind(R.id.plug_leak);
        imageView8 = bind(R.id.imageView8);
        plug_leak.setOnClickListener(v -> {
            hide();
        });
        imageView8.setOnClickListener(v -> hide());
    }
}