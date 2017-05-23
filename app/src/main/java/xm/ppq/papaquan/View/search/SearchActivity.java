package xm.ppq.papaquan.View.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import xm.ppq.papaquan.Adapter.PapanewsFragmentAdapter;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.search.Mutual_Search;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.main.frame.Round_papa_news;

/**
 * Created by Administrator on 2017/3/9.
 */

public class SearchActivity extends BaseActivity implements Round_papa_news, UpMoreInterface {

    @BindView(R.id.search_record)
    RecyclerView search_record;
    @BindView(R.id.search_edit)
    EditText search_edit;
    @BindView(R.id.search_text)
    TextView search_text;

    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();
    private PapanewsFragmentAdapter adapter;
    private SharedPreferencesPotting potting;
    private Mutual_Search mutual_search;

    @Override
    protected int getLayout() {
        return R.layout.activity_search_home;
    }

    private int page = 0;

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        search_record.setLayoutManager(new LinearLayoutManager(this));
        mutual_search = new Mutual_Search(this);
    }

    @Override
    protected void setListener() {
        search_text.setOnClickListener(v -> {
            if (search_edit.getText().toString().equals("")) {
                finish();
            } else {
                search_edit.setText("");
                page = 0;
                setRefreshList(datas);
            }
        });
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {
                    search_text.setText("删除");
                } else {
                    search_text.setText("取消");
                }
                page = 0;
                mutual_search.SearchNews(s.toString(), page, 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        search_edit.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId == 0 || actionId == 3) && event != null) {
                //点击搜索要做的操作
                page = 0;
                mutual_search.SearchNews(search_edit.getText().toString(), page, 0);
            }
            return false;
        });
        search_record.addOnScrollListener(onScrollListener);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//            int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
//            if (lastVisibleItemPosition > 3) {
//                backtotop.setVisibility(View.VISIBLE);
//            } else {
//                backtotop.setVisibility(View.GONE);
//            }
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (lastVisibleItem == (totalItemCount - 1)) {
                    if (datas.size() != 0) {
                        page++;
                        mutual_search.SearchNews(search_edit.getText().toString(), page, 1);
                    }
                }
            }
        }
    };

    @Override
    public String getTid() {
        return null;
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public void setList(List<ShowNewsBigBean.Data> list) {
        datas.addAll(list);
        if (adapter == null) {
            adapter = new PapanewsFragmentAdapter(SearchActivity.this, datas, this, this);
            search_record.setAdapter(adapter);
        } else {
            adapter.setDataList(datas);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setRefreshList(List<ShowNewsBigBean.Data> list) {
        datas.addAll(list);
        if (adapter == null) {
            adapter = new PapanewsFragmentAdapter(SearchActivity.this, datas, this, this);
            search_record.setAdapter(adapter);
        } else {
            adapter.setDataList(datas);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showInfo(String s) {

    }

    @Override
    public void startMutual() {

    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(search_edit);
    }
}
