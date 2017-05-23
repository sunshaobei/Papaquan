package xm.ppq.papaquan.View.main.frame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.lib_sunshaobei2017.widget.pagerslidingtabstrip.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Adapter.PapanewsFragmentAdapter;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Bean.PapaTopicData;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.search.Mutual_Search;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageUtil;
import xm.ppq.papaquan.Tool.PapaPopupWindow;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.Tool.customview.ZoomOutPageTransformer;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.Tool.typewriting.TypewritingUtil;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.alltopic.AlltopicActivity;
import xm.ppq.papaquan.View.city_search.CitySearchActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.View.topic_deail.Topic_DetailActivity;

/**
 * Created by Administrator on 2017/2/17.
 */

public class PapaFragLayout extends Fragment implements ViewPager.OnPageChangeListener, Round_papa_news, UpMoreInterface, PapanewsInterface {

    @BindView(R.id.select_papa)
    ImageView select_papa;
    @BindView(R.id.papa_three_page)
    ViewPager papa_three_page;
    @BindView(R.id.address_place)
    ImageView address_place;
    @BindView(R.id.papa_fragment_bar)
    LinearLayout bar;
    @BindView(R.id.indicator)
    Indicator indicator;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.searchview)
    View searchview;
    @BindView(R.id.search_edit)
    EditText search_edit;
    @BindView(R.id.dismiss)
    View dismiss;
    @BindView(R.id.hideedit_hint)
    View hideedit_hint;
    @BindView(R.id.hidepullup)
    View hidepullup;
    @BindView(R.id.hideswipe_layout)
    SwipeRefreshLayout hideswipe_layout;
    @BindView(R.id.hiderecycleview)
    RecyclerView hiderecycleview;
    @BindView(R.id.hidetext)
    View hidetext;
    @BindView(R.id.moretopic)
    TextView moretopic;
    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tabStrip;
    @BindView(R.id.headicon1)
    ImageView headicon1;
    @BindView(R.id.topic1title)
    TextView topic1title;
    @BindView(R.id.topic1)
    RelativeLayout topic1;
    @BindView(R.id.headicon2)
    ImageView headicon2;
    @BindView(R.id.topic2title)
    TextView topic2title;
    @BindView(R.id.topic2)
    RelativeLayout topic2;
    @BindView(R.id.headicon3)
    ImageView headicon3;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.collapsin)
    CollapsingToolbarLayout collapsin;

    private PapaPopupWindow papaPopupWindow;
    private HomePagerAdapter adapter;
    private List<Fragment> list;
    private View view;
    private MainActivity context;
    private SharedPreferencesPotting my_login;
    private Mutual_Search mutual_search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_papa, container, false);
            ButterKnife.bind(this, view);
            context = (MainActivity) getActivity();
            context.initStatusBar(bar);
            my_login = new SharedPreferencesPotting(getActivity(), "my_login");
            mutual_search = new Mutual_Search(this);
            initHideView();
            loadData();
            setListener();
        }
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return view;
    }

    public void loadData() {
        papaPopupWindow = new PapaPopupWindow(getContext(), select_papa);
        String[] str = {"同城", "关注", "我的"};
        if (list == null) {
            list = new ArrayList<>();
            list.add(new PapaNewsFragLayout());
            list.add(new PapaFollowFragLayout());
            list.add(new PapaMineFragLayout());
        }
        if (adapter == null) {
            adapter = new HomePagerAdapter(getChildFragmentManager());
            adapter.setFragments(list);
            adapter.setPagerTitle(str);
            papa_three_page.setAdapter(adapter);
            papa_three_page.setPageTransformer(true, new ZoomOutPageTransformer());
            tabStrip.setTextColor(Color.BLACK);
            tabStrip.setTextSize(14);
            tabStrip.setViewPager(papa_three_page);
        } else {
            adapter.setFragments(list);
            adapter.notifyDataSetChanged();
        }
        String cityname = my_login.getItemString("cityname");
        if (!TextUtils.isEmpty(cityname))
            location.setText(cityname);
        indicator.setOffwidth(-30);
        Drawable drawable = getResources().getDrawable(R.drawable.topic_item);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        headicon3.setImageBitmap(ImageUtil.getRoundedCornerBitmap(bitmap, 20));
        headicon2.setImageBitmap(ImageUtil.getRoundedCornerBitmap(bitmap, 20));
        headicon1.setImageBitmap(ImageUtil.getRoundedCornerBitmap(bitmap, 20));
    }

    public void setListener() {
        papa_three_page.addOnPageChangeListener(this);
        moretopic.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AlltopicActivity.class));
        });
    }

    @OnClick({R.id.select_papa, R.id.address_place, R.id.location})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_papa:
                papaPopupWindow.Show(searchview);
                break;
            case R.id.address_place:
                Intent intent = new Intent(getActivity(), CitySearchActivity.class);
                intent.putExtra("type", "定位");
                startActivityForResult(intent, IntentCode.RequestCode.TOLOCATION);
                break;
            case R.id.location:
                Intent intent1 = new Intent(getActivity(), CitySearchActivity.class);
                intent1.putExtra("type", "定位");
                startActivityForResult(intent1, IntentCode.RequestCode.TOLOCATION);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        indicator.onMove(positionOffset, position);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING://正在滑动
                break;
            case ViewPager.SCROLL_STATE_IDLE://空闲状态
                break;
            case ViewPager.SCROLL_STATE_SETTLING://自动沉降，松手后
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.CITYBACKTOHOME) {
            if (!my_login.getItemString("cityname").isEmpty())
                location.setText(my_login.getItemString("citycityname"));
            EventBus.getDefault().post("刷新");
        }
    }

    public static int WAIT = 0;

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventBusEventSynEvent(String s) {
        switch (s) {
            case "moveTofirst":
                WAIT = 1;
                papa_three_page.setCurrentItem(0);
                EventBus.getDefault().post("刷新");
                break;
            case "dismiss":
                dismissSearchview();
                break;
            case "刷新":
                location.setText(my_login.getItemString("cityname"));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostArrive(List<PapaTopicData.TopicData> data) {
        if (data != null) {
            my_login.setItemInt("AdminId", data.get(0).getIsadmin())
                    .build();
            for (int i = 0; i < data.size(); i++) {
                ImageView imageView = null;
                int id = data.get(i).getId();
                if (i == 0) {
                    Glide.with(getActivity()).load(BaseUrl.BITMAP + data.get(i).getImg()).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Bitmap roundedCornerBitmap = ImageUtil.getRoundedCornerBitmap(resource, 20);
                            headicon1.setImageBitmap(roundedCornerBitmap);
                        }
                    });
                    topic1title.setText(data.get(i).getTitle());
                    topic1.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), Topic_DetailActivity.class);
                        intent.putExtra("hotid", id + "");
                        startActivity(intent);
                    });
                } else {
                    Glide.with(getActivity()).load(BaseUrl.BITMAP + data.get(i).getImg()).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Bitmap roundedCornerBitmap = ImageUtil.getRoundedCornerBitmap(resource, 10);
                            headicon2.setImageBitmap(roundedCornerBitmap);
                        }
                    });
                    topic2title.setText(data.get(i).getTitle());
                    topic2.setOnClickListener(v -> {
                        Intent intent = new Intent(getActivity(), Topic_DetailActivity.class);
                        intent.putExtra("hotid", id + "");
                        startActivity(intent);
                    });
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 隐藏的searchtopic视图
     */
    private void initHideView() {
        hiderecycleview.setLayoutManager(new LinearLayoutManager(context));
        papanewsFragmentAdapter = new PapanewsFragmentAdapter(getActivity(), datas, this, this, search_edit);
        hiderecycleview.setAdapter(papanewsFragmentAdapter);
        hideswipe_layout.setColorSchemeResources(android.R.color.holo_blue_light);
        inithidelistener();
    }

    private PapanewsFragmentAdapter papanewsFragmentAdapter;
    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();
    private boolean candismiss = true;
    private int page = 1;
    private boolean isfreshing = false;
    private boolean isvisible = false;

    private void inithidelistener() {
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    candismiss = true;
                    hideedit_hint.setVisibility(View.VISIBLE);
                    hideswipe_layout.setVisibility(View.GONE);
                    hiderecycleview.setVisibility(View.GONE);
                    hidetext.setVisibility(View.GONE);
                    dismiss.setBackground(new ColorDrawable(Color.parseColor("#66000000")));
                } else {
                    hideswipe_layout.setVisibility(View.VISIBLE);
                    candismiss = false;
                    hideedit_hint.setVisibility(View.GONE);
                    hidetext.setVisibility(View.VISIBLE);
                    dismiss.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
                    hiderecycleview.setVisibility(View.VISIBLE);
                    page = 1;
                    hideswipe_layout.setRefreshing(true);
                    mutual_search.SearchNews(s.toString(), 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        dismiss.setOnClickListener(v1 -> {
            if (candismiss) {
                dismissSearchview();
            }
        });
        hiderecycleview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (papanewsFragmentAdapter.getView() != null) {
                    papanewsFragmentAdapter.hide();
                }
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && isvisible)
                    EmotionPopupWindow.HideKeyboard(search_edit);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        if (datas.size() != 0) {
                            if (!isfreshing && datas.size() >= 10) {
                                mutual_search.SearchNews(search_edit.getText().toString(), page, 1);
                                hidepullup.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        hidetext.setOnClickListener(v12 -> {
            search_edit.setText("");
            hideedit_hint.setVisibility(View.VISIBLE);
            hiderecycleview.setVisibility(View.GONE);
            hidetext.setVisibility(View.GONE);
            dismiss.setBackground(new ColorDrawable(Color.parseColor("#66000000")));
        });
        hideswipe_layout.setOnRefreshListener(() -> {
            page = 1;
            isfreshing = true;
            mutual_search.SearchNews(search_edit.getText().toString(), 0, 0);
        });
        new TypewritingUtil(getActivity()).addOnSoftKeyBoardVisibleListener(context, (visible, keyboardHeight) -> isvisible = visible);
    }

    private void dismissSearchview() {
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


    @Override
    public String getTid() {
        return null;
    }

    @Override
    public String getCityCode() {
        return my_login.getItemString("citycode");
    }

    @Override
    public String getToken() {
        return my_login.getItemString("token");
    }

    @Override
    public int getUid() {
        return my_login.getItemInt("uid");
    }

    @Override
    public void setList(List<ShowNewsBigBean.Data> list) {
        hideswipe_layout.setRefreshing(false);
        isfreshing = false;
        datas.clear();
        datas.addAll(list);
        papanewsFragmentAdapter.notifyDataSetChanged();

    }

    @Override
    public void setRefreshList(List<ShowNewsBigBean.Data> list) {
        hideswipe_layout.setRefreshing(false);
        hidepullup.setVisibility(View.GONE);
        isfreshing = false;
        page++;
        datas.addAll(list);
        papanewsFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showInfo(String s) {

    }

    @Override
    public void startMutual() {
        mutual_search.SearchNews(search_edit.getText().toString(), 0, 0);
    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(select_papa);
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {

    }

    @Override
    public void ShowShare(View view) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}