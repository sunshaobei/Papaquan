package xm.ppq.papaquan.View.my_topic;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.my_topic.Mutual_MyTopic;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DateUtil;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.main.frame.Round_papa_news;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyTopicActivity extends BaseActivity implements Round_papa_news, Round_MyTopic, UpMoreInterface {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.bar)
    LinearLayout statusbar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.year)
    TextView titleyear;

    private SharedPreferencesPotting potting;
    private Mutual_MyTopic mutual_myTopic;
    private MydynamicListviewAdapter mydynamicListviewAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_topic;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusbar);
        center_result.setText("我的动态");
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_myTopic = new Mutual_MyTopic(this);
        int[] dateFromDate = DateUtil.getDateFromDate(System.currentTimeMillis());
        titleyear.setText(dateFromDate[0] + "年");
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = 0 ;
        listview.smoothScrollToPosition(0);
        mutual_myTopic.start(page);
    }

    private int page = 0;

    @Override
    protected void setListener() {
        mydynamicListviewAdapter = new MydynamicListviewAdapter(this, datas);
        listview.setAdapter(mydynamicListviewAdapter);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setCustomHeaderView(new CustomHeader(this, 300));
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(this));
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setScrollBackDuration(300);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 0;
                mutual_myTopic.start(page);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                mutual_myTopic.start(page);
            }
        });
        finish_result.setOnClickListener(v -> finish());
        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    int[] dateFromString = DateUtil.getDateFromDate(Long.valueOf(datas.get(firstVisibleItem - 1).createtime+"000"));
                    int year = dateFromString[0];
                    int systemyear = Integer.valueOf(titleyear.getText().toString().replace("年", ""));
                    if (year != systemyear) {
                        titleyear.setText(year + "年");
                    }
                }else {
                    int[] dateFromDate = DateUtil.getDateFromDate(System.currentTimeMillis());
                    titleyear.setText(dateFromDate[0] + "年");
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentCode.RequestCode.TODYNAMIC) {
            page = 0;
            mutual_myTopic.start(page);
        }
    }

    @Override
    public String getTid() {
        return null;
    }

    @Override
    public String getCityCode() {
        return null;
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }


    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();

    @Override
    public void setList(ArrayList<ShowNewsBigBean.Data> list) {
        if (page == 0) {
            datas.clear();
        }
        datas.addAll(list);
        mydynamicListviewAdapter.notifyDataSetChanged();
        xRefreshView.stopRefresh();
        xRefreshView.stopLoadMore();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
           if (JCVideoPlayer.backPress())
           {
               JCVideoPlayer.releaseAllVideos();
               return true;
           }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public void setList(List<ShowNewsBigBean.Data> list) {

    }

    @Override
    public void setRefreshList(List<ShowNewsBigBean.Data> list) {

    }

    @Override
    public void showInfo(String s) {
    }

    @Override
    public void startMutual() {
        mutual_myTopic.start(1);
    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(center_result);
    }
}