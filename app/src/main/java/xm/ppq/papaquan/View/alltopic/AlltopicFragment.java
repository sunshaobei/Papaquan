package xm.ppq.papaquan.View.alltopic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.PapaTopicBean;
import xm.ppq.papaquan.Presenter.papatopic.Mutual_Papatopic;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.Tool.customview.Xffoot;
import xm.ppq.papaquan.View.main.frame.Round_PapaTopic;
import xm.ppq.papaquan.View.topic_deail.Topic_DetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlltopicFragment extends Fragment implements Round_PapaTopic {

    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.backtotop)
    View backtotop;

    private View view;
    private AlltopicListAdapter alltopicListAdapter;

    public AlltopicFragment() {
        // Required empty public constructor
    }

    private int position;

    public AlltopicFragment(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_alltopic, container, false);
            ButterKnife.bind(this, view);
            initListener();
            initData();
        }
        ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private String str;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        if (s.startsWith(position+""))
        {
            s.replaceFirst(position+"","");
            this.str = s;
            mutual_papatopic.getData(pager, position + 1, str);
        }
    }

    private void initListener() {
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoLoadMore(true);
        xRefreshView.setCustomHeaderView(new CustomHeader(getActivity(), 300));
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(getActivity()));
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setScrollBackDuration(300);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                pager = 0;
                mutual_papatopic.getData(pager, position + 1, str);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pager++;
                mutual_papatopic.getData(pager, position + 1, str);
            }
        });
        backtotop.setOnClickListener(v -> {
            listView.smoothScrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });

        listView.setOnItemClickListener((parent, view1, position1, id) -> {
            Intent intent = new Intent(getActivity(), Topic_DetailActivity.class);
            intent.putExtra("hotid", list.get(position1).getId());
            startActivity(intent);
        });
    }

    private int pager;
    private SharedPreferencesPotting potting;
    private Mutual_Papatopic mutual_papatopic;

    private void initData() {
        alltopicListAdapter = new AlltopicListAdapter(getActivity(), list, R.layout.item_alltopiclistview);
        listView.setAdapter(alltopicListAdapter);
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        pager = 0;
        mutual_papatopic = new Mutual_Papatopic(this);
        mutual_papatopic.getData(pager, position + 1, str);
    }

    private ArrayList<PapaTopicBean.DataBean> list = new ArrayList<>();

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public void setPapaTopicList(List<PapaTopicBean.DataBean> datalist) {
        if (pager == 0) {
            list.clear();
        }
        list.addAll(datalist);
        alltopicListAdapter.notifyDataSetChanged();
        xRefreshView.stopLoadMore();
        xRefreshView.stopRefresh();
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }
}
