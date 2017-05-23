package xm.ppq.papaquan.Tool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MakeDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.nick_name_edit)
    EditText nick_name_edit;
    @BindView(R.id.cancel_btn)
    TextView cancel_btn;
    @BindView(R.id.confirm_btn)
    TextView confirm_btn;
    @BindView(R.id.avoid_image)
    ImageView avoid_image;

    private onFinishOnClickListener onFinishOnClickListener;
    private onConfimOnClickListener onConfimOnClickListener;

    public MakeDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nick_name_dialog);
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        cancel_btn.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
        avoid_image.setOnClickListener(this);
    }

    public void setOnFinishOnClickListener(onFinishOnClickListener onFinishOnClickListener) {
        this.onFinishOnClickListener = onFinishOnClickListener;
    }

    public void setOnConfimOnClickListener(onConfimOnClickListener onConfimOnClickListener) {
        this.onConfimOnClickListener = onConfimOnClickListener;
    }

    public void Cancel() {
        dismiss();
    }

    public String getNickName() {
        return nick_name_edit.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                if (onFinishOnClickListener != null) {
                    onFinishOnClickListener.onfinish(v);
                }
                break;
            case R.id.confirm_btn:
                if (onConfimOnClickListener != null) {
                    onConfimOnClickListener.onconfim(v);
                }
                break;
            case R.id.avoid_image:
                nick_name_edit.setText("");
                break;
        }
    }

    public interface onFinishOnClickListener {
        void onfinish(View v);
    }

    public interface onConfimOnClickListener {
        void onconfim(View v);
    }
}