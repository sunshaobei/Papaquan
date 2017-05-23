package xm.ppq.papaquan.View.Life.classification;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myview.mybanner.Mybanner;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ClassifiListAdapter;
import xm.ppq.papaquan.Adapter.ContentClassifyAdapter;
import xm.ppq.papaquan.Bean.ClassifiListBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.ClassificationBean;
import xm.ppq.papaquan.Bean.life.LinkHeadAdBean;
import xm.ppq.papaquan.Bean.life.SearchSellerData;
import xm.ppq.papaquan.Presenter.life.classification.Mutual_Classification;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.allsortofseller.AllSortOfSellerActivity;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.selleroof.SearchAdapter;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by 商家分类 on 2017/4/13.
 */

public class ClassificationActivity extends BaseActivity implements Round_Classification {

    @BindView(R.id.list_classify)
    ListView list_classify;
    @BindView(R.id.content_classify)
    ListView content_classify;
    @BindView(R.id.in_great)
    TextView in_great;
    @BindView(R.id.in_news)
    TextView in_news;
    @BindView(R.id.in_about)
    TextView in_about;
    @BindView(R.id.banner)
    Mybanner banner;

    //搜索
    @BindView(R.id.faceview)
    View searchview;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.search_edit)
    EditText search_edit;
    @BindView(R.id.hideedit_hint)
    View hideedit_hint;
    @BindView(R.id.dismiss)
    View dismiss;
    @BindView(R.id.hidetext)
    TextView hidetext;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.backtotop)
    View backtotop;

    private ClassifiListAdapter adapter;
    private List<ClassifiListBean> classifiListBeen;
    private ContentClassifyAdapter classifyAdapter;
    private TypeClassfiyBean.DataBean dataBean;
    private SharedPreferencesPotting potting;
    private Mutual_Classification mutual_classification;
    private SearchAdapter searchAdapter;
    private String s;
    private ArrayList<SearchSellerData.DataBean> searchlist = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_classification;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        dataBean = (TypeClassfiyBean.DataBean) getIntent().getSerializableExtra("bean");
        classifiListBeen = new ArrayList<>();
        if (dataBean != null) {
            mutual_classification = new Mutual_Classification(this);
            classifiListBeen.add(new ClassifiListBean("全部分类", true));
            for (TypeClassfiyBean.DataBean.ChildrenBean childrenBean : dataBean.getChildren()) {
                classifiListBeen.add(new ClassifiListBean(childrenBean.getName(), true));
            }
            handler.obtainMessage(4001).sendToTarget();
            mutual_classification.getInfo();
            mutual_classification.getHeadAdvert();
        }

        //search
        searchAdapter = new SearchAdapter(this, searchlist, R.layout.paihang_item);
        listview.setAdapter(searchAdapter);
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
                searchSellerData(s);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                searchSellerData(s);
            }
        });
        backtotop.setOnClickListener(v -> {
            listview.smoothScrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });

        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem>=3)
                    backtotop.setVisibility(View.VISIBLE);
                else backtotop.setVisibility(View.GONE);

            }
        });
    }

    private boolean isshow = false;

    private void dismissSearchview() {
        isshow = false;
        EventBus.getDefault().post("dismisstrue");
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(searchview, "alpha", 1, 0.5f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(searchview, "translationY", 0, -3000);
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(alpha).with(translationY);//两个动画同时开始
        animatorSet.start();
        EmotionPopupWindow.HideKeyboard(search_edit);
    }

    public void searchSellerData(String s) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", potting.getItemString("citycode"))
                    .put("page", page)
                    .put("length", 10)
                    .put("search", s);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("shop/search", jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        SearchSellerData data = (SearchSellerData) JsonUtil.fromJson(o, SearchSellerData.class);
                        if (data != null && data.getData().size() > 0) {
                            //TODO
                            if (page == 0)
                                searchlist.clear();
                            searchlist.addAll(data.getData());
                            searchAdapter.notifyDataSetChanged();
                            xRefreshView.stopLoadMore();
                            xRefreshView.stopRefresh();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 4001) {
                if (adapter == null) {
                    adapter = new ClassifiListAdapter(ClassificationActivity.this, classifiListBeen, R.layout.classify_item);
                    list_classify.setAdapter(adapter);
                } else {
                    adapter.setList(classifiListBeen);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    @Override
    protected void setListener() {
        list_classify.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(this, AllSortOfSellerActivity.class);
                startActivityForResult(intent, 0x98);
            } else {
                two_id = String.valueOf(dataBean.getChildren().get(position - 1).getId());
                for (int i = 0; i < classifiListBeen.size(); i++) {
                    if (position == i) {
                        classifiListBeen.get(i).setSelect(false);
                    } else {
                        classifiListBeen.get(i).setSelect(true);
                    }
                    mutual_classification.getInfo();
                    handler.obtainMessage(4001).sendToTarget();
                }
            }
        });

        //搜索
        search.setOnClickListener(v -> {
            isshow = true;
            searchview.setVisibility(View.VISIBLE);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator alpha = ObjectAnimator.ofFloat(searchview, "alpha", 0, 0.5f, 0.7f, 1f);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(searchview, "translationY", -3000, 0);
            animatorSet.setDuration(300);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.play(alpha).with(translationY);//两个动画同时开始
            animatorSet.start();
        });
        dismiss.setOnClickListener(v -> {
            dismissSearchview();
        });
        hidetext.setOnClickListener(v -> {
            search_edit.setText("");
            hideedit_hint.setVisibility(View.VISIBLE);
            hidetext.setVisibility(View.GONE);
        });

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    xRefreshView.setVisibility(View.VISIBLE);
                    hidetext.setVisibility(View.VISIBLE);
                    hideedit_hint.setVisibility(View.GONE);
                    ClassificationActivity.this.s = s.toString();
                    searchSellerData(s.toString());
                } else {
                    xRefreshView.setVisibility(View.GONE);
                    hideedit_hint.setVisibility(View.VISIBLE);
                    searchSellerData("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listview.setOnItemClickListener((parent, view2, position, id) -> {
            Intent intent1 = new Intent(ClassificationActivity.this, Merchant_HomepageActivity.class);
            intent1.putExtra("sid", String.valueOf(searchlist.get(position).getId()));
            startActivity(intent1);
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isshow) {
                dismissSearchview();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0x56) {
            dataBean = (TypeClassfiyBean.DataBean) data.getSerializableExtra("bean");
            classifiListBeen = new ArrayList<>();
            if (dataBean != null) {
                mutual_classification = new Mutual_Classification(this);
                classifiListBeen.add(new ClassifiListBean("全部分类", true));
                for (TypeClassfiyBean.DataBean.ChildrenBean childrenBean : dataBean.getChildren()) {
                    classifiListBeen.add(new ClassifiListBean(childrenBean.getName(), true));
                }
                handler.obtainMessage(4001).sendToTarget();
                mutual_classification.getInfo();
                mutual_classification.getHeadAdvert();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String two_id = "0";
    private String type = "1";
    private int page = 0;

    @OnClick({R.id.in_great, R.id.in_news, R.id.in_about})
    public void Select(View view) {
        int position = 0;
        switch (view.getId()) {
            case R.id.in_great:
                type = "1";
                position = 0;
                break;
            case R.id.in_news:
                type = "2";
                position = 1;
                break;
            case R.id.in_about:
                type = "3";
                position = 2;
                break;
        }
        mutual_classification.getInfo();
        TextSelect(position, in_great, in_news, in_about);
    }

    @Override
    public String getLat() {
        return potting.getItemString("lat");
    }

    @Override
    public String getLng() {
        return potting.getItemString("lng");
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getIndustry() {
        return String.valueOf(dataBean.getId());
    }

    @Override
    public String getIndustry_Two() {
        return two_id;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setBean(ArrayList<ClassificationBean.DataBean> dataBeen) {
        if (classifyAdapter == null) {
            classifyAdapter = new ContentClassifyAdapter(this, dataBeen, R.layout.content_classify_item);
            content_classify.setAdapter(classifyAdapter);
            content_classify.setOnItemClickListener((parent, view, position, id) -> {
                Skip(Merchant_HomepageActivity.class, "sid", dataBeen.get(position).getId());
            });
        } else {
            classifyAdapter.setList(dataBeen);
            classifyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setHead(ArrayList<LinkHeadAdBean.DataBean> dataBeen) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < dataBeen.size(); i++) {
            list.add(BaseUrl.BITMAP + dataBeen.get(i).getImg());
        }
        banner.setBanner(getSupportFragmentManager(), list);
        banner.setOnItemClickListener(position -> {
            int link_type = dataBeen.get(position).getLink_type();
            String link = dataBeen.get(position).getLink();
            int link_val = dataBeen.get(position).getLink_val();
            if (link_type == 0) {
                LinkSkip.Link(this, link_val, dataBeen.get(position).getSingleid());
            } else {
                LinkSkip.Go2Chrome(getActivity(), link);
            }
        });
    }

    @OnClick(R.id.finish)
    public void Finish() {
        finish();
    }
}