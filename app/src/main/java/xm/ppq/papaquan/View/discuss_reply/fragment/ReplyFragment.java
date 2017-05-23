package xm.ppq.papaquan.View.discuss_reply.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.ReplyAdapter;
import xm.ppq.papaquan.Bean.NewsReplyBean;
import xm.ppq.papaquan.Presenter.discuss.Mutual_Disreply;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.View.trendtopicdetail.TrendTopicDetailActivity;

/**
 * Created by 回复 on 2017/3/2.
 */

public class ReplyFragment extends LoadingOneFragment implements Round_Reply {

    @BindView(R.id.reply_list)
    ListView reply_list;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.backtotop)
    View backtotop;

    private ReplyAdapter adapter;
    private Mutual_Disreply mutual_disreply;
    private ArrayList<NewsReplyBean.Data> datas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reply, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void loadData() {
        adapter = new ReplyAdapter(getActivity(), datas, R.layout.reply_item);
        reply_list.setAdapter(adapter);
        mutual_disreply = new Mutual_Disreply(getContext(), this);
        mutual_disreply.getData(0);
    }

    @Override
    public void setListener() {
        reply_list.setOnItemClickListener(((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), TrendTopicDetailActivity.class);
            intent.putExtra("tid", datas.get(position).tid);
            getActivity().startActivity(intent);
        }));


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
                page = 0;
                mutual_disreply.getData(page);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                mutual_disreply.getData(page);
            }
        });
        backtotop.setOnClickListener(v -> {
            reply_list.smoothScrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });

        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem>=3)
                {
                    backtotop.setVisibility(View.VISIBLE);
                }
                else {
                    backtotop.setVisibility(View.GONE);
                }

            }
        });

    }
    int page;

    @Override
    public void setData(List<NewsReplyBean.Data> s) {
        if (page ==0)
        datas.clear();
        datas.addAll(s);
        adapter.notifyDataSetChanged();
        xRefreshView.stopLoadMore();
        xRefreshView.stopRefresh();
    }

    @Override
    public void setError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

}