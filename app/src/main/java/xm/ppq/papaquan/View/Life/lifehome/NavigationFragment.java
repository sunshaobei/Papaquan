package xm.ppq.papaquan.View.Life.lifehome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.life.LifeHomeData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {

    @BindView(R.id.gridview)
    GridView gridView;

    private List<LifeHomeData.DataBean.NavigationIconBean> list;
    private ArrayList<LifeHomeData.DataBean.NavigationIconBean> list1 = new ArrayList<>();
    private int position;
    private View view;
    private NavigationAdapter navigationAdapter;

    public NavigationFragment() {
        // Required empty public constructor
    }

    public NavigationFragment(List<LifeHomeData.DataBean.NavigationIconBean> list, int position) {
        this.list = list;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_navigation, container, false);
            ButterKnife.bind(this, view);

            if (list != null) {
                navigationAdapter = new NavigationAdapter(getActivity(), list1, R.layout.item_lifehomegd);
                gridView.setAdapter(navigationAdapter);
                gridView.setFocusable(false);
                gridView.setOnItemClickListener((parent, view1, position1, id) -> {
                    if (list.get(position1).getImg() != null) {
                        LifeHomeData.DataBean.NavigationIconBean navigationIconBean = list1.get(position1);
                        if (navigationIconBean.getLink_type().equals("0")) {//内链
                            int i = navigationIconBean.getLink_val() == null ? 0 : Integer.valueOf(navigationIconBean.getLink_val());
                            LinkSkip.Link(getActivity(), i, navigationIconBean.getSingleid());
                        } else {
                            LinkSkip.Go2Chrome(getActivity(), navigationIconBean.getLink());
                        }
                    }
                });
            }
        }
        if (list != null) {
            list1.clear();
            for (int i = 0; i < list.size(); i++) {
                int i1 = 10 * position;
                if (i >= i1 && i < i1 + 10)
                    list1.add(list.get(i));
            }
            navigationAdapter.notifyDataSetChanged();
        }
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        if (list != null) {
            list1.clear();
            for (int i = 0; i < list.size(); i++) {
                int i1 = 10 * position;
                if (i >= i1 && i < i1 + 10)
                    list1.add(list.get(i));
            }
            navigationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
