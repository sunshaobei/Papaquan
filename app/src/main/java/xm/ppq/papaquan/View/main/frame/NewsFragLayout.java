package xm.ppq.papaquan.View.main.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Model.News.Dispose_News;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.login.LoginActivity;
import xm.ppq.papaquan.View.main.MainActivity;


/**
 * Created by Administrator on 2017/2/17.
 */

public class NewsFragLayout extends Fragment implements Round_news {

    @BindView(R.id.news_bar)
    LinearLayout news_bar;
    @BindView(R.id.aite_mine_lin)
    View view1;
    @BindView(R.id.discuss_mine_lin)
    View view2;
    @BindView(R.id.money_mine_lin)
    View view3;

    private View view;
    private SharedPreferencesPotting potting;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frament_news, null);
            ButterKnife.bind(this, view);
            MainActivity context = (MainActivity) getContext();
            context.initStatusBar(news_bar);
            loadData();
            potting = new SharedPreferencesPotting(getContext(), "my_login");
        }
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
            if (potting.getItemInt("uid") != 0) {
                EventBus.getDefault().post("fresh");
                if (fragment == null){
                    IMUtils.setUserId("u_" + potting.getItemInt("uid"));
                    fragment = IMUtils.IMfragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.imfragment, fragment);
                    fragmentTransaction.commitAllowingStateLoss();
                    IMUtils.login();
                }
            }
            if (potting.getItemInt("uid") ==0)
            {
                if (fragment!=null)
                {
                    EventBus.getDefault().post("unregist");
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.remove(fragment);
                    EventBus.getDefault().unregister(fragment);
                    fragment = null;
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
    }

    public void loadData() {
        view1.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1));
        view2.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1));
        view3.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            fragment = IMUtils.IMfragment();
//            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.imfragment, fragment);
//            fragmentTransaction.commit();
//            IMUtils.login();
//        }
    }

    @Override
    public void setData(int actcount, int messageCount, int rewardCount) {

    }

}
