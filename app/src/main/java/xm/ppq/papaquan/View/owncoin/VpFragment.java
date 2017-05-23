package xm.ppq.papaquan.View.owncoin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by sunshaobei on 2017/5/2.
 */

public class VpFragment extends Fragment {
    private int position;

    public VpFragment() {
    }

    private ArrayList<String> imagelist = new ArrayList<>();

    public VpFragment(List<String> list, int position) {
        this.position = position;
        imagelist.clear();
        imagelist.addAll(list);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getActivity()).load(BaseUrl.BITMAP + imagelist.get(position)).into(imageView);
        return imageView;
    }
}
