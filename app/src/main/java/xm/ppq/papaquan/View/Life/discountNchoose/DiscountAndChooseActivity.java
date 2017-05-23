package xm.ppq.papaquan.View.Life.discountNchoose;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tencent.mapsdk.raster.model.LatLng;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Adapter.RadioAdapter;
import xm.ppq.papaquan.Bean.DisChBreak;
import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.Presenter.life.DiscountChoose.Mutual_DiscountChoose;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.discountNchoose.fragment.ChooseFragment;
import xm.ppq.papaquan.View.Life.discountNchoose.fragment.DiscountFragment;
import xm.ppq.papaquan.life.Tool.ChildPopupWindow;

/**
 * 折扣商家 精选商品
 */
public class DiscountAndChooseActivity extends BaseActivity implements ChangeText, Round_DiscountChoose {

    @BindView(R.id.finish_discount)
    ImageView finish_discount;
    @BindView(R.id.two_radio)
    RadioButton two_radio;
    @BindView(R.id.one_radio)
    RadioButton one_radio;
    @BindView(R.id.three_radio)
    RadioButton three_radio;
    @BindView(R.id.four_radio)
    RadioButton four_radio;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    @BindView(R.id.show_list)
    ListView show_list;
    @BindView(R.id.flow_layout)
    TagFlowLayout flow_layout;
    @BindView(R.id.frame_list)
    FrameLayout frame_list;
    @BindView(R.id.v)
    View v;
    @BindView(R.id.flow_linear)
    LinearLayout flow_linear;
    @BindView(R.id.resetting_btn)
    TextView resetting_btn;
    @BindView(R.id.ensure_btn)
    TextView ensure_btn;
    @BindView(R.id.discount_btn)
    RadioButton discount_btn;
    @BindView(R.id.choose_btn)
    RadioButton choose_btn;
    @BindView(R.id.title_radio)
    RadioGroup title_radio;

    @BindView(R.id.dc_view_pager)
    ViewPager dc_view_pager;

    private HomePagerAdapter homePagerAdapter;
    private ArrayList<Fragment> fragments;
    private ShareDialog shareDialog;

    private Mutual_DiscountChoose mutual_discountChoose;
    private RadioAdapter adapter;
    private SharedPreferencesPotting potting;
    private DiscountFragment discountFragment;
    private ChooseFragment chooseFragment;

    private String url = "index/coupon/index?city=" + BaseUrl.citycode;

    @Override
    protected int getLayout() {
        return R.layout.activity_discount_and_choose;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.setStatus("精折", url, BaseUrl.Image_url);
        if (fragments == null) {
            fragments = new ArrayList<>();
            discountFragment = new DiscountFragment();
            chooseFragment = new ChooseFragment();
            fragments.add(discountFragment);
            fragments.add(chooseFragment);
        }
        if (homePagerAdapter == null) {
            homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
            homePagerAdapter.setFragments(fragments);
            dc_view_pager.setAdapter(homePagerAdapter);
        } else {
            homePagerAdapter.setFragments(fragments);
            homePagerAdapter.notifyDataSetChanged();
        }
        childPopupWindow = new ChildPopupWindow(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_discountChoose = new Mutual_DiscountChoose(this, getActivity());
        mutual_discountChoose.StartInitTitle();
        mutual_discountChoose.setOnFlowOItemListener(list1 -> stringSparseArray = list1);
    }

    private SparseArray<String> stringSparseArray;

    @Override
    protected void setListener() {
        dc_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        choose_btn.setChecked(false);
                        discount_btn.setChecked(true);
                        break;
                    case 1:
                        choose_btn.setChecked(true);
                        discount_btn.setChecked(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        finish_discount.setOnClickListener(v -> finish());
        title_radio.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.discount_btn:
                    if (dc_view_pager.getCurrentItem() != 0)
                        dc_view_pager.setCurrentItem(0);
                    break;
                case R.id.choose_btn:
                    if (dc_view_pager.getCurrentItem() != 1)
                        dc_view_pager.setCurrentItem(1);
                    break;
            }
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("area", two_id);
                jsonObject.put("industry_two", one_id);
                if (three_id.equals("latlng")) {
                    if (getLatLng() != null) {
                        jsonObject.put("lng", getLatLng().getLongitude())
                                .put("lat", getLatLng().getLatitude());
                    }
                } else {
                    jsonObject.put(three_id, 1);
                }
                EventBus.getDefault().post(new DisChBreak(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private String one_id;
    private String two_id;
    private String three_id = "latlng";

    @Override
    public void setText(int position, String result, String id) {
        switch (position) {
            case 0:
                one_id = id;
                one_radio.setText(result);
                break;
            case 1:
                two_id = id;
                two_radio.setText(result);
                break;
            case 2:
                three_id = id;
                three_radio.setText(result);
                break;
            case 3:
                four_radio.setText(result);
                break;
        }
        if (position != 3) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("area", two_id);
                jsonObject.put("industry_two", one_id);
                if (three_id.equals("latlng")) {
                    if (getLatLng() != null) {
                        jsonObject.put("lng", getLatLng().getLongitude())
                                .put("lat", getLatLng().getLatitude());
                    }
                } else {
                    jsonObject.put(three_id, 1);
                }
                EventBus.getDefault().post(new DisChBreak(jsonObject));
                radio_group.clearCheck();
                childPopupWindow.Hide();
                frame_list.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public TagFlowLayout getFlowLayout() {
        return flow_layout;
    }

    private LatLng latLng;

    @Override
    public LatLng getLatLng() {
        if (latLng != null) {
            return latLng;
        } else {
            return null;
        }
    }

    private ArrayList<LevelBean> list;
    private int item;
    private ChildPopupWindow childPopupWindow;

    @Override
    public void setListes(ArrayList<LevelBean> l1, ArrayList<LevelBean> l2, ArrayList<LevelBean> l3, ArrayList<LevelBean> l4) {
        try {
            double log = 0;
            if (potting.getItemString("lng") != null && !potting.getItemString("lng").equals(""))
                log = Double.valueOf(potting.getItemString("lng"));
            double lat = 0;
            if (potting.getItemString("lng") != null && !potting.getItemString("lng").equals(""))
                lat = Double.valueOf(potting.getItemString("lat"));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("lng", log)
                    .put("lat", lat);
            discountFragment.Break(new DisChBreak(jsonObject));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resetting_btn.setOnClickListener(v -> {
            mutual_discountChoose.ResrttingItem4();
            mutual_discountChoose.Cancel();
        });
        ensure_btn.setOnClickListener(v -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("area", two_id);
                jsonObject.put("industry_two", one_id);
                if (three_id.equals("latlng")) {
                    if (getLatLng() != null) {
                        jsonObject.put("lng", getLatLng().getLongitude())
                                .put("lat", getLatLng().getLatitude());
                    }
                } else {
                    jsonObject.put(three_id, 1);
                }
                if (stringSparseArray != null) {
                    for (int i = 0; i < stringSparseArray.size(); i++) {
                        switch (i) {
                            case 0:
                                jsonObject.put("car", 1);
                                break;
                            case 1:
                                jsonObject.put("room", 1);
                                break;
                            case 2:
                                jsonObject.put("wifi", 1);
                                break;
                            case 3:
                                jsonObject.put("air", 1);
                                break;
                        }
                    }
                }
                EventBus.getDefault().post(new DisChBreak(jsonObject));
                radio_group.clearCheck();
                frame_list.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        radio_group.setOnCheckedChangeListener((group, checkedId) -> {
            frame_list.setVisibility(View.VISIBLE);
            switch (checkedId) {
                case R.id.one_radio:
                    list = l1;
                    item = 0;
                    break;
                case R.id.two_radio:
                    list = l2;
                    item = 1;
                    break;
                case R.id.three_radio:
                    list = l3;
                    item = 2;
                    break;
                case R.id.four_radio:
                    list = l4;
                    item = 3;
                    break;
            }
            if (item != 3) {
                if (list != null) {
                    if (adapter == null) {
                        adapter = new RadioAdapter(list, this, R.layout.level_item);
                        show_list.setAdapter(adapter);
                    } else {
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
                    flow_linear.setVisibility(View.GONE);
                    show_list.setVisibility(View.VISIBLE);
                    if (list.get(0).getChild() != null)
                        select(0);
                }
            } else {
                flow_linear.setVisibility(View.VISIBLE);
                show_list.setVisibility(View.GONE);
            }
        });
        show_list.setOnItemClickListener((parent, view, position, id) -> {
            if (list != null) {
                if (list.get(position).getChild() != null) {
                    select(position);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == position) {
                            list.get(position).setCheck(true);
                        } else {
                            list.get(i).setCheck(false);
                        }
                    }
                    setText(item, list.get(position).getResult(), list.get(position).getId());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        frame_list.setOnClickListener(v -> {
            radio_group.clearCheck();
            frame_list.setVisibility(View.GONE);
            childPopupWindow.Hide();
        });
    }

    @Override
    public void setDiscountBean(ArrayList<DiscountBean.DataBean> dataBeen, LatLng start) {

//            DCAdapter = new DiscountAdapter(this, dataBeen, R.layout.discountchoose_item);
    }

    @Override
    public void setChooseBean(ArrayList<ChooseBean.DataBean> dataBeen, LatLng start) {

    }

    private void select(int position) {
        if (list.get(position).getChild().size() > 0) {
            list.get(position).setCheck(false);
            adapter.setType(10);
            adapter.notifyDataSetChanged();
            childPopupWindow.setLevelBeen(list.get(position).getChild());
            childPopupWindow.ShowLeft(v);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (flow_linear.getVisibility() == View.VISIBLE) {
                radio_group.clearCheck();
                flow_linear.setVisibility(View.GONE);
                frame_list.setVisibility(View.GONE);
                return true;
            }
            if (frame_list.getVisibility() == View.VISIBLE) {
                radio_group.clearCheck();
                childPopupWindow.Hide();
                frame_list.setVisibility(View.GONE);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void go_home(View v) {
        finish();
    }

    public void share(View v) {
        shareDialog.Show(v);
    }
}