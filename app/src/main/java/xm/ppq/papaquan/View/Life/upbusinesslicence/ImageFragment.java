package xm.ppq.papaquan.View.Life.upbusinesslicence;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.SmoothImageView;
import xm.ppq.papaquan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    @BindView(R.id.image)
    SmoothImageView imageView;

    public ImageFragment() {
        // Required empty public constructor
    }

    ArrayList<String> piclist = new ArrayList<>();
    int position;

    public ImageFragment(ArrayList<String> list ,int position) {
        this.piclist.clear();
        this.piclist.addAll(list);
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this,view);
//        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
//        imageView.transformIn();
//        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
////		setContentView(imageView);
        Picasso.with(getActivity()).load("file://"+piclist.get(0)).into(imageView);
        return view;
    }

}
