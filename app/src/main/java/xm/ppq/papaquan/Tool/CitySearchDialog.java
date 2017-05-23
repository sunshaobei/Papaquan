package xm.ppq.papaquan.Tool;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.city_search.CitySearchActivity;

/**
 * Created by sunshaobei on 2017/3/18.
 */

public class CitySearchDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.requestFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_fragment, null);
        TextView dialog_info = (TextView) view.findViewById(R.id.dialog_info);
        TextView cancel = (TextView) view.findViewById(R.id.dialog_cancel);
        TextView sure = (TextView) view.findViewById(R.id.dialog_sure);
        String tag = getTag();
        dialog_info.setText(tag);
        cancel.setOnClickListener(v -> dismiss());
        sure.setOnClickListener(v -> onSureClickListener.onSureClickListener());
        return view;
    }

    public interface OnSureClickListener {
        void onSureClickListener();
    }

    private OnSureClickListener onSureClickListener;

    public void setOnSureClickListener(OnSureClickListener l) {
        this.onSureClickListener = l;
    }
}
