package xm.ppq.papaquan.Tool;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xm.ppq.papaquan.R;

import static xm.ppq.papaquan.Tool.IMinit.ConversationOperation.text;

/**
 * Created by sunshaobei on 2017/3/29.
 */

public class ConversationDialog extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_conversationdialog, null);
        TextView setop = (TextView) view.findViewById(R.id.settop);
        setop.setText(text);
        setop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectListener.onSettop();
            }
        });
        View delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(v -> {
            onSelectListener.onDelete();
        });
        View close = view.findViewById(R.id.close);
        close.setOnClickListener(v -> {
            dismiss();
        });
        return view;
    }

    public interface OnSelectListener {
        void onDelete();

        void onSettop();
    }

    private OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener o) {
        this.onSelectListener = o;
    }

}
