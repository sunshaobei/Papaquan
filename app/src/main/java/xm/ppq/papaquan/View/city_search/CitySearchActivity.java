package xm.ppq.papaquan.View.city_search;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tencent.map.geolocation.TencentLocation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.CitySearchAdapter;
import xm.ppq.papaquan.Bean.CityBean;
import xm.ppq.papaquan.Bean.CityBean2;
import xm.ppq.papaquan.Bean.CityInfo;
import xm.ppq.papaquan.Bean.CitySearchData;
import xm.ppq.papaquan.Presenter.citysearch.Mutual_CitySearch;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.CitySearchDialog;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.RequestpermissionUtil;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.Tool.shownews.NoScrollGridView;
import xm.ppq.papaquan.View.BaseActivity;

public class CitySearchActivity extends BaseActivity implements Round_CitySearch {

    @BindView(R.id.city_toolbar)
    Toolbar cityToolbar;
    @BindView(R.id.city_listview)
    ListView cityListview;

    private View headview;
    private TextView location;
    private Mutual_CitySearch mutual_citySearch;
    private SharedPreferencesPotting potting;
    private CitySearchAdapter citySearchAdapter;
    private EditText city_search_edit;
    private int action;
    private Intent intent;
    private TextView notice;
    private View nowlocation;
    private NoScrollGridView nowgridview;
    private TextView nowlocation1;
    private boolean nowgridviewisshow = false;
    private View dwlocation;
    private ImageView more;
    private String citycode;
    private View sameprovince;
    private String cityname;
    private List list = new ArrayList<>();
    private List listgrid1 = new ArrayList<>();
    private int type;
    private int clickcount;
    private List list1;
    private List list2;
    private CitySearchAdapter gridAdapter1;
    private List beforesearchlist;
    private List searchlist1;
    private List searchlist2;
    private TextView cityserch_edit_hint;
    private String code2 = "";
    private String code1 = "";
    private View editview;

    @Override
    protected int getLayout() {
        return R.layout.activity_city_search;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initData() {
        RequestpermissionUtil.requestGPSPermissions(this, null);
        ButterKnife.bind(this);
        intent = getIntent();
        action = intent.getIntExtra("action", 0);
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_citySearch = new Mutual_CitySearch(this);
        initToolbar();
        initListView();
        if (action == 1) {
            dwlocation.setVisibility(View.GONE);
            nowlocation.setVisibility(View.GONE);
            editview.setVisibility(View.GONE);
        }
        initheadData();
    }

    private void initheadData() {
        String code = potting.getItemString("citycode");
        if (!code.equals("") && code != null)
            mutual_citySearch.getCityData(3, code);
        else nowlocation1.setText("当前: 未选择");

    }


    boolean gridclick1 = true;

    private void initListView() {
        headview = getLayoutInflater().inflate(R.layout.citylistview_head, null);
        editview = headview.findViewById(R.id.editview);
        location = (TextView) headview.findViewById(R.id.location);
        notice = (TextView) headview.findViewById(R.id.notice);
        city_search_edit = (EditText) headview.findViewById(R.id.city_search_edit);
        sameprovince = headview.findViewById(R.id.sameprovince);
        nowlocation = headview.findViewById(R.id.nowlocation);
        nowgridview = (NoScrollGridView) headview.findViewById(R.id.citysearch_gridview1);

        nowgridview.setOnItemClickListener((parent, view, position, id) -> {
            if (gridclick1) {
                CityInfo.DataBean.OtherCityBean otherCityBean = ((List<CityInfo.DataBean.OtherCityBean>) listgrid1).get(position);
                String code = otherCityBean.getCode() + "";
                if (otherCityBean.getChildren() != 0) {
                    gridclick1 = false;
                    mutual_citySearch.getCityData(7, code);
                } else {
                    if (otherCityBean.getPointcode() == 0) {
                        citycode = code;
                        cityname = otherCityBean.getFullname();
                        potting.setItemString("citycode", citycode).build();
                        potting.setItemString("cityname", cityname).build();
                        setResult(IntentCode.ResultCode.CITYBACKTOHOME);
                        finish();
                    } else {
                        mutual_citySearch.getCityData(6, otherCityBean.getPointcode() + "");
                    }
                }
            } else {
                CityBean2.DataBean dataBean = ((List<CityBean2.DataBean>) listgrid1).get(position);
                if (dataBean.getChildren() == 0) {
                    if (dataBean.getPointcode() == 0) {
                        citycode = dataBean.getCode() + "";
                        cityname = dataBean.getFullname();
                        potting.setItemString("citycode", citycode).build();
                        potting.setItemString("cityname", cityname).build();
                        setResult(IntentCode.ResultCode.CITYBACKTOHOME);
                        finish();
                    } else {
                        mutual_citySearch.getCityData(6, dataBean.getPointcode() + "");
                    }
                } else {
                    mutual_citySearch.getCityData(7, dataBean.getCode() + "");
                }
            }
        });

        nowlocation1 = (TextView) headview.findViewById(R.id.location1);
        nowlocation1.setOnClickListener(v -> {
            gridclick1 = true;
            mutual_citySearch.getCityData(3, potting.getItemString("citycode"));
        });
        dwlocation = headview.findViewById(R.id.dwlocation);
        more = (ImageView) headview.findViewById(R.id.more);
        View choosexq1 = headview.findViewById(R.id.choosexq1);
        choosexq1.setOnClickListener(v -> {
            if (nowgridviewisshow) {
                nowgridview.setVisibility(View.GONE);
                more.setImageResource(R.drawable.point_bottom);
                nowgridviewisshow = false;
            } else {
                more.setImageResource(R.drawable.point_up);
                nowgridview.setVisibility(View.VISIBLE);
                nowgridviewisshow = true;
            }
        });
        city_search_edit = (EditText) headview.findViewById(R.id.city_search_edit);
        cityserch_edit_hint = (TextView) headview.findViewById(R.id.cityserch_edit_hint);
        city_search_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    if (isfirstsearch) {
                        isfirstsearch = false;
                        beforesearchtype = type;
                        beforesearchlist = list;
                    }
                    cityserch_edit_hint.setVisibility(View.GONE);
                    nowlocation.setVisibility(View.GONE);
                    dwlocation.setVisibility(View.GONE);
                    mutual_citySearch.getCityData(4, s.toString());
                } else {
                    cityserch_edit_hint.setVisibility(View.VISIBLE);
                    if (action != 1) {
                        nowlocation.setVisibility(View.VISIBLE);
                        dwlocation.setVisibility(View.VISIBLE);
                    }
                    searchclick = 0;
                    setList(beforesearchlist, beforesearchtype);
                    beforesearchlist = null;
                    isfirstsearch = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        cityListview.addHeaderView(headview);
        this.citySearchAdapter = new CitySearchAdapter(this, list, R.layout.item_citylist);
        cityListview.setAdapter(this.citySearchAdapter);
        mutual_citySearch.getCityData(1, "");
        cityListview.setOnItemClickListener((parent, view, position, id) -> {
            switch (type) {
                case 1://CityBean
                    clickcount++;
                    code1 = ((List<CityBean.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getCode() + "";
                    mutual_citySearch.getCityData(2, ((List<CityBean.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getCode() + "");
                    list1 = list;
                    break;
                case 2://CityBean2
                    int children = ((List<CityBean2.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getChildren();
                    if (children > 0) {
                        if (searchclick == 0) {
                            clickcount++;
                            code2 = ((List<CityBean2.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getCode() + "";
                            list2 = list;
                        } else {
                            searchclick++;
                            searchlist2 = list;
                        }
                        mutual_citySearch.getCityData(2, ((List<CityBean2.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getCode() + "");
                    } else {
                        //TODO
                        if (((List<CityBean2.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getPointcode() == 0) {
                            citycode = ((List<CityBean2.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getCode() + "";
                            cityname = ((List<CityBean2.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getFullname();
                            if (action == 1) {
                                intent.putExtra("code1", code1);
                                intent.putExtra("code2", code2);
                                intent.putExtra("code3", citycode);
                                setResult(IntentCode.ResultCode.BACKTOMAKEADDRESS, intent);
                            } else {
                                potting.setItemString("citycode", citycode).build();
                                potting.setItemString("cityname", cityname).build();
                                setResult(IntentCode.ResultCode.CITYBACKTOHOME);
                            }
                            finish();
                        } else {
                            mutual_citySearch.getCityData(6, ((List<CityBean2.DataBean>) list).get(position - cityListview.getHeaderViewsCount()).getPointcode() + "");
                        }
                    }
                    break;
                case 4://search
                    if (searchclick == 0) {
                        CitySearchData.DataBean dataBean = ((List<CitySearchData.DataBean>) list).get(position - cityListview.getHeaderViewsCount());
                        int children1 = dataBean.getChildren();
                        if (children1 != 0) {
                            searchclick++;
                            searchlist1 = list;
                            mutual_citySearch.getCityData(2, dataBean.getCode() + "");

                        } else {
                            if (dataBean.getPointcode() == 0) {
                                citycode = dataBean.getCode() + "";
                                cityname = dataBean.getFullname();
                                potting.setItemString("citycode", citycode).build();
                                potting.setItemString("cityname", cityname).build();
                                setResult(IntentCode.ResultCode.CITYBACKTOHOME);
                                finish();
                            } else {
                                mutual_citySearch.getCityData(6, dataBean.getPointcode() + "");
                            }
                        }
                    }
                    break;
            }
        });
    }

    boolean isfirstsearch = true;
    int searchclick;
    int beforesearchtype;


    private void initToolbar() {
        cityToolbar.setTitle("");
        setSupportActionBar(cityToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void setListener() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            if (searchclick == 0) {
                if (beforesearchlist != null) {
                    setList(beforesearchlist, beforesearchtype);
                    city_search_edit.setText("");
                    nowlocation.setVisibility(View.VISIBLE);
                    cityserch_edit_hint.setVisibility(View.VISIBLE);
                    dwlocation.setVisibility(View.VISIBLE);
                    isfirstsearch = true;
                    beforesearchlist = null;
                } else {
                    if (clickcount > 0) {
                        switch (clickcount) {
                            case 1:
                                code1 = "";
                                setList(list1, 1);
                                break;
                            case 2:
                                code2 = "";
                                setList(list2, 2);
                                break;
                        }
                        clickcount--;
                        return true;
                    } else {
                        finish();
                    }
                }
            } else {
                switch (searchclick) {
                    case 1:
                        setList(searchlist1, 4);
                        break;
                    case 2:
                        setList(searchlist2, 2);
                        break;
                }
                searchclick--;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void setonLocationError(String s) {
        notice.setVisibility(View.VISIBLE);
        notice.setText(s);
        location.setVisibility(View.GONE);
    }

    @Override
    public void onLocationSuccess(String s, String ccode) {
        notice.setVisibility(View.GONE);
        location.setVisibility(View.VISIBLE);
        ToastShow("定位成功");
        location.setText(s);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkPotting.getInstance("http://app.papaquan.com/index.php/index/Citys/getcityinfo").AskOne("", "{\"citycode\": \"" + ccode + "\"}", new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String o) {
                        if (JsonUtil.getString(o, "code").equals("0")) {
                            CityInfo cityinfo = (CityInfo) JsonUtil.fromJson(o, CityInfo.class);
                            int pointcode = cityinfo.getData().getPointcode();
                            if (pointcode == 0) {
                                citycode = cityinfo.getData().getCode() + "";
                                cityname = cityinfo.getData().getFullname();
                                if (action == 1) {
                                    String info = "";
                                    intent.putExtra("city", info);
                                    setResult(IntentCode.ResultCode.BACKTOMAKEADDRESS, intent);
                                } else {
                                    potting.setItemString("citycode", citycode).build();
                                    potting.setItemString("cityname", cityname).build();
                                    setResult(IntentCode.ResultCode.CITYBACKTOHOME);
                                }
                                finish();
                            } else {
                                mutual_citySearch.getCityData(6, cityinfo.getData().getPointcode() + "");
                            }
                        }
                    }
                });
            }
        });
    }

    public void setnowLocationList(CityInfo cityInfo) {
        if (!nowgridviewisshow) {
            more.setImageResource(R.drawable.point_up);
            nowgridview.setVisibility(View.VISIBLE);
            nowgridviewisshow = true;
        }
        nowlocation1.setText("当前: " + cityInfo.getData().getFullname());
        CityInfo.DataBean data = cityInfo.getData();
        List<CityInfo.DataBean.OtherCityBean> otherCity = data.getOtherCity();
        listgrid1 = otherCity;
        if (gridAdapter1 == null) {
            gridAdapter1 = new CitySearchAdapter(this, listgrid1, R.layout.item_citygrid);
            gridAdapter1.setType(3);
            nowgridview.setAdapter(gridAdapter1);
        } else {
            gridAdapter1.setType(3);
            gridAdapter1.setList(listgrid1);
            gridAdapter1.notifyDataSetChanged();
        }
    }

    @Override
    public void setnowLocationList(List<CityBean2.DataBean> data) {
        listgrid1 = data;
        gridAdapter1.setType(2);
        gridAdapter1.setList(listgrid1);
        gridAdapter1.notifyDataSetChanged();
    }

    @Override
    public void setExit(CityInfo cityInfo) {
        cityname = cityInfo.getData().getFullname();
        citycode = cityInfo.getData().getCode() + "";
        if (action == 1) {
            intent.putExtra("code1", code1);
            intent.putExtra("code2", code2);
            intent.putExtra("code3", citycode);
            setResult(IntentCode.ResultCode.BACKTOMAKEADDRESS, intent);
        } else {
            potting.setItemString("citycode", citycode).build();
            potting.setItemString("cityname", cityname).build();
            setResult(IntentCode.ResultCode.CITYBACKTOHOME);
        }
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            if (searchclick == 0) {
                if (beforesearchlist != null) {
                    city_search_edit.setText("");
                    nowlocation.setVisibility(View.VISIBLE);
                    cityserch_edit_hint.setVisibility(View.VISIBLE);
                    dwlocation.setVisibility(View.VISIBLE);
                    isfirstsearch = true;
                    setList(beforesearchlist, beforesearchtype);
                    beforesearchlist = null;
                    return true;
                } else {
                    if (clickcount > 0) {
                        switch (clickcount) {
                            case 1:
                                code1 = "";
                                setList(list1, 1);
                                break;
                            case 2:
                                code2 = "";
                                setList(list2, 2);
                                break;
                        }
                        clickcount--;
                        return true;
                    }
                }
            } else {
                switch (searchclick) {
                    case 1:
                        setList(searchlist1, 4);
                        break;
                    case 2:
                        setList(searchlist2, 2);
                        break;
                }
                searchclick--;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void setList(List list, int type) {
        this.list = list;
        this.type = type;
        citySearchAdapter.setList(list);
        citySearchAdapter.setType(type);
        citySearchAdapter.notifyDataSetChanged();
    }


}