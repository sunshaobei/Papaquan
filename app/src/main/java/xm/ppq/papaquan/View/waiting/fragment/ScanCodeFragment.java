package xm.ppq.papaquan.View.waiting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 扫码核销 on 2017/4/11.
 */

public class ScanCodeFragment extends LoadingOneFragment {

    private View view;
    private String image;
    private String num;

    @BindView(R.id.qr_code_image)
    ImageView qr_code_image;
    @BindView(R.id.qr_num_text)
    TextView qr_num_text;

    public ScanCodeFragment() {
    }

    public ScanCodeFragment(Object image, Object num) {
        this.image = (String) image;
        this.num = (String) num;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_scancode, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void loadData() {
        if (num != null) {
            qr_num_text.setText(num);
        }
        ImageLoading.common(getActivity(), BaseUrl.BITMAP + image, qr_code_image, R.mipmap.food);
    }

    @Override
    public void setListener() {

    }
}
