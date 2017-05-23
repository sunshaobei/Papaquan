package xm.ppq.papaquan.View.discuss_reply.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.DiscussReplyAdapter;
import xm.ppq.papaquan.Bean.DisComment;
import xm.ppq.papaquan.Presenter.discuss.Mutual_Discomment;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;
import xm.ppq.papaquan.View.trendtopicdetail.TrendTopicDetailActivity;

/**
 * Created by 评论 on 2017/3/2.
 */

public class DiscussFragment extends LoadingOneFragment implements Round_Discuss {

    @BindView(R.id.discuss_list)
    ListView discuss_list;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.backtotop)
    View backtotop;

    private ArrayList<DisComment.DataBean> discommentList = new ArrayList<>();
    private DiscussReplyAdapter discussReplyAdapter;
    private Mutual_Discomment mutual_discomment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discuss, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void loadData() {
        mutual_discomment = new Mutual_Discomment(getActivity(), this);
        discussReplyAdapter = new DiscussReplyAdapter(getActivity(), discommentList, R.layout.discuss_item);
        discuss_list.setAdapter(discussReplyAdapter);
        mutual_discomment.getData(0);
    }

    @Override
    public void setListener() {
        discuss_list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), TrendTopicDetailActivity.class);
            intent.putExtra("tid", discommentList.get(position).getTid()+"");
            getActivity().startActivity(intent);
        });

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
                mutual_discomment.getData(page);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                mutual_discomment.getData(page);
            }
        });
        backtotop.setOnClickListener(v -> {
            discuss_list.smoothScrollToPosition(0);
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
    public void setData(List<DisComment.DataBean> list) {
        if (page == 0)
        discommentList.clear();
        discommentList.addAll(list);
        discussReplyAdapter.notifyDataSetChanged();
        xRefreshView.stopLoadMore();
        xRefreshView.stopRefresh();
    }
    @Override
    public void setError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void replySuccess(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
