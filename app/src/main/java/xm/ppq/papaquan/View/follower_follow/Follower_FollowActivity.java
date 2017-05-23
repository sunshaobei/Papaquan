package xm.ppq.papaquan.View.follower_follow;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.FollowerAndFollowAdapter;
import xm.ppq.papaquan.Bean.FollowerandFollowBean;
import xm.ppq.papaquan.Presenter.follow_follower.Mutual_Follow_Follower;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.Tool.customview.PullUprefreListView;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;

/**
 * Created by 我的关注或者我的粉丝 on 2017/2/24.
 */

public class Follower_FollowActivity extends BaseActivity implements View.OnClickListener, Round_FerFow {

    @BindView(R.id.follower_follow_list)
    ListView follower_follow_list;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_text;
    @BindView(R.id.bar)
    LinearLayout statusBar;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;

    private Mutual_Follow_Follower mutual_follow_follower;
    private FollowerAndFollowAdapter adapter;
    private SharedPreferencesPotting potting;
    private EditText search_edit_ff;
    private View edit_hint;

    @Override
    protected int getLayout() {
        return R.layout.acrivity_follower_follow;
    }

    private String result = "";

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusBar);
        mutual_follow_follower = new Mutual_Follow_Follower(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        result = getData("type");
        if (result.equals("1")) {
            center_text.setText("我的关注");
        } else if (result.equals("2")) {
            center_text.setText("我的粉丝");
        } else if (result.equals("3")) {
            center_text.setText("我的屏蔽");
        }
        initListvew();
        mutual_follow_follower.start(page, "");

    }

    private int page;

    private void initListvew() {
        View head = getLayoutInflater().inflate(R.layout.item_listviewhead_follow, null);
        follower_follow_list.addHeaderView(head);
        search_edit_ff = (EditText) head.findViewById(R.id.search_edit_ff);
        edit_hint = head.findViewById(R.id.edit_hint);
        adapter = new FollowerAndFollowAdapter(this, datalist, R.layout.follower_follow_item, result);
        follower_follow_list.setAdapter(adapter);
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
                mutual_follow_follower.start(page, "");
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                mutual_follow_follower.start(page, "");
            }
        });

        follower_follow_list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(Follower_FollowActivity.this, Ta_SheetActivity.class);
            String himid = null;
            if (result.equals("2")) {
                himid = datalist.get(position - follower_follow_list.getHeaderViewsCount()).uidone;
            } else if (result.equals("3")) {
                himid = datalist.get(position - follower_follow_list.getHeaderViewsCount()).uidtwo;
            } else {
                himid = datalist.get(position - follower_follow_list.getHeaderViewsCount()).uidtwo;
            }
            intent.putExtra("Uuid", himid);
            startActivity(intent);
        });
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(this);
        search_edit_ff.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mutual_follow_follower.start(0, search_edit_ff.getText().toString());
                return true;
            }
            return false;
        });
        search_edit_ff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    edit_hint.setVisibility(View.VISIBLE);
                    datalist.clear();
                    datalist.addAll(datalist3);
                    adapter.notifyDataSetChanged();
                } else {
                    edit_hint.setVisibility(View.GONE);
                    new Thread(() -> {
                        datalist2.clear();
                        for (int i = 0; i < datalist3.size(); i++) {
                            String s1 = datalist3.get(i).nickname.toUpperCase();
                            String s2 = s.toString().toUpperCase();
                            if (s1.contains(s2)) {
                                datalist2.add(datalist3.get(i));
                            }
                        }
                        runOnUiThread(() -> {
                            datalist.clear();
                            datalist.addAll(datalist2);
                            adapter.notifyDataSetChanged();
                        });
                    }).start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_result:
                finish();
                break;
        }
    }

    private ArrayList<FollowerandFollowBean.Data> datalist = new ArrayList<>();
    private ArrayList<FollowerandFollowBean.Data> datalist2 = new ArrayList<>();
    private ArrayList<FollowerandFollowBean.Data> datalist3 = new ArrayList<>();

    @Override
    public String getType() {
        return result;
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }


    @Override
    public void setAdapter(List<FollowerandFollowBean.Data> datas) {
        if (page == 0) {
            this.datalist.clear();
        }
        this.datalist.addAll(datas);
        this.datalist3.clear();
        this.datalist3.addAll(datas);
        adapter.notifyDataSetChanged();
        xRefreshView.stopLoadMore();
        xRefreshView.stopRefresh();
    }

    @Override
    public void setG_Q_P(String itemUid, String type, String url) {
        mutual_follow_follower.Operate(itemUid, type, url);
    }

    @Override
    public void setSyntony(int type) {
        switch (type) {
            case 0:
                mutual_follow_follower.start(0, "");
                ToastShow("操作成功");
                break;
            case 1:
                ToastShow("重复操作");
                break;
            case 2:
                ToastShow("操作失败");
                break;
            case 3:
                ToastShow("数据错误，请重试");
                break;
        }
    }
}
