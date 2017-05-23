package xm.ppq.papaquan.View.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.SplashActivity;

/**
 * Created by EdgeDi on 17:00.
 */

public class ItemFragment extends Fragment {

    private View view;

    @BindView(R.id.item_image)
    ImageView item_image;
    @BindView(R.id.get_into)
    TextView get_into;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_item, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int type = getArguments().getInt("num");
        switch (type) {
            case 0:
                item_image.setImageResource(R.mipmap.page_1);
                get_into.setVisibility(View.GONE);
                break;
            case 1:
                item_image.setImageResource(R.mipmap.page_2);
                get_into.setVisibility(View.GONE);
                break;
            case 2:
                item_image.setImageResource(R.mipmap.page_3);
                get_into.setVisibility(View.VISIBLE);
                break;
        }
        get_into.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SplashActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }
}