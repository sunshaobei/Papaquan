package xm.ppq.papaquan.View.pic;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/6.
 */

public class PicFragment extends Fragment {

    private View view;
    private KeepPicTure keeppiocture;

    @BindView(R.id.photo_view)
    PhotoView photo_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_pic, container, false);
            ButterKnife.bind(this, view);
            keeppiocture = (KeepPicTure) getActivity();
        }
        loadData();
        return view;
    }

    private Bitmap bitmap;

    public void loadData() {
        Bundle bundle = getArguments();
        Glide.with(getContext()).load(bundle.getString("url")).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                EventBus.getDefault().post("hide");
                bitmap = resource;
                photo_view.setImageBitmap(resource);
            }
        });
        photo_view.setOnPhotoTapListener((view1, x, y) -> {
            EventBus.getDefault().post("quit");
        });
        photo_view.setOnLongClickListener(v -> {
            if (bitmap != null) {
                if (keeppiocture != null) {
                    keeppiocture.SnackShow("是否保存图片", bitmap);
                }
            }
            return false;
        });
    }
}