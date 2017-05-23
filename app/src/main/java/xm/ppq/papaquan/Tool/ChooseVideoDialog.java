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

/**
 * Created by sunshaobei on 2017/3/27.
 */

public class ChooseVideoDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.requestFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.item_choosevideodialog, null);
        TextView localvideo = (TextView) view.findViewById(R.id.local);
        TextView takevideo = (TextView) view.findViewById(R.id.take);
        takevideo.setOnClickListener(v -> {
            takelistener.onChooseTakeVideoListener();
            dismiss();
        });
        localvideo.setOnClickListener(v -> {
            listener.onChooseLocalListener();
            dismiss();
        });
        return view;

    }

    private ChooseLocalListener listener;

    public interface ChooseLocalListener {
        void onChooseLocalListener();
    }

    public void setOnChooseLocalListener(ChooseLocalListener l) {
        this.listener = l;
    }

    public ChooseTakeVideoListener takelistener;

    public interface ChooseTakeVideoListener {
        void onChooseTakeVideoListener();
    }

    public void setOnChooseTakeVideoListener(ChooseTakeVideoListener l) {
        this.takelistener = l;
    }
}
