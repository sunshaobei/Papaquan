package xm.ppq.papaquan.Presenter.life.DiscountChoose;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;

import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Adapter.FlowTabAdapter;
import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.Bean.SureBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.discountNchoose.Round_DiscountChoose;

/**
 * Created by EdgeDi on 19:37.
 */

public class Mutual_DiscountChoose implements DiscountChoosePresenter {

    private Round_DiscountChoose round_discountChoose;
    private Activity activity;

    private ArrayList<TypeClassfiyBean.DataBean> dataBeen;
    private ArrayList<LevelBean> item1;
    private ArrayList<LevelBean> item2;
    private ArrayList<LevelBean> item3;
    private ArrayList<LevelBean> item4;
    private TagFlowLayout flowLayout;
    private final SharedPreferencesPotting my_login;

    public Mutual_DiscountChoose(Round_DiscountChoose round_discountChoose, Activity activity) {
        this.round_discountChoose = round_discountChoose;
        this.activity = activity;
        my_login = new SharedPreferencesPotting(activity, "my_login");
    }

    /**
     * 开始初始化筛选条件并请求行业数据
     */
    @Override
    public void StartInitTitle() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", round_discountChoose.getCityCode())
                    .put("type", 2);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETINDUSTRY, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBeen != null) {
                        item1 = new ArrayList<>();
                        LevelBean levelBean;
                        for (int i = 0; i < dataBeen.size(); i++) {
                            levelBean = new LevelBean();
                            levelBean.setResult(dataBeen.get(i).getName());
                            levelBean.setId(String.valueOf(dataBeen.get(i).getId()));
                            if (i == 0) {
                                levelBean.setCheck(true);
                            } else {
                                levelBean.setCheck(false);
                            }
                            if (dataBeen.get(i).getChildren().size() > 0) {
                                ArrayList<LevelBean> levelBeen = new ArrayList<>();
                                for (int i1 = 0; i1 < dataBeen.get(i).getChildren().size(); i1++) {
                                    levelBeen.add(new LevelBean(dataBeen.get(i).getChildren().get(i1).getName(), false, String.valueOf(dataBeen.get(i).getChildren().get(i1).getId())));
                                }
                                levelBean.setChild(levelBeen);
                                levelBeen = null;
                            }
                            item1.add(levelBean);
                            levelBean = null;
                        }
                        StartArea();
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        TypeClassfiyBean classfiyBean = (TypeClassfiyBean) JsonUtil.fromJson(s, TypeClassfiyBean.class);
                        if (classfiyBean.getCode() == 0) {
                            dataBeen = classfiyBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求商品数据
     */
    @Override
    public void StartWares(String url, int page) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", round_discountChoose.getCityCode())
                    .put("page", page)
                    .put("length", 10)
                    .put("lat", my_login.getItemString("lat"))
                    .put("lng", my_login.getItemString("lng"));
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(url, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (url.equals(BaseUrl.REBATELIST)) {
                            DiscountBean chooseBean = (DiscountBean) JsonUtil.fromJson(s, DiscountBean.class);
                            if (chooseBean.getCode() == 0) {
                                dcdatabean = chooseBean.getData();
                                if (dcdatabean != null) {
                                    round_discountChoose.setDiscountBean(dcdatabean, round_discountChoose.getLatLng());
                                }
                            }
                        } else if (url.equals(BaseUrl.COUPONLIST)) {
                            ChooseBean chooseBean = (ChooseBean) JsonUtil.fromJson(s, ChooseBean.class);
                            if (chooseBean.getCode() == 0) {
                                chdatabean = chooseBean.getData();
                                if (chooseBean != null) {
                                    round_discountChoose.setChooseBean(chdatabean, round_discountChoose.getLatLng());
                                }
                            }
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ResrttingItem4() {
        if (item4 != null) {
            for (LevelBean levelBean : item4) {
                levelBean.setCheck(false);
            }
            tabAdapter.notifyDataChanged();
        }
    }

    @Override
    public void NotLifeGetInfo(String url, JSONObject jsonObject, int page) {
        try {
            jsonObject.put("citycode", round_discountChoose.getCityCode())
                    .put("page", page)
                    .put("length", 10);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(url, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (url.equals(BaseUrl.REBATELIST)) {
                            DiscountBean chooseBean = (DiscountBean) JsonUtil.fromJson(s, DiscountBean.class);
                            if (chooseBean.getCode() == 0) {
                                dcdatabean = chooseBean.getData();
                                if (dcdatabean != null) {
                                    round_discountChoose.setDiscountBean(dcdatabean, round_discountChoose.getLatLng());
                                }
                            }
                        } else if (url.equals(BaseUrl.COUPONLIST)) {
                            ChooseBean chooseBean = (ChooseBean) JsonUtil.fromJson(s, ChooseBean.class);
                            if (chooseBean.getCode() == 0) {
                                chdatabean = chooseBean.getData();
                                if (chooseBean != null) {
                                    round_discountChoose.setChooseBean(chdatabean, round_discountChoose.getLatLng());
                                }
                            }
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private OnFlowOItemListener onFlowOItemListener;

    public OnFlowOItemListener getOnFlowOItemListener() {
        return onFlowOItemListener;
    }

    public void setOnFlowOItemListener(OnFlowOItemListener onFlowOItemListener) {
        this.onFlowOItemListener = onFlowOItemListener;
    }

    private ArrayList<DiscountBean.DataBean> dcdatabean;
    private ArrayList<ChooseBean.DataBean> chdatabean;
    private ArrayList<SureBean.DataBean> dataBeanArrayList;
    private FlowTabAdapter tabAdapter;

    /**
     * 请求商圈数据
     */
    private void StartArea() {
        flowLayout = round_discountChoose.getFlowLayout();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", round_discountChoose.getCityCode());
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.AREA, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBeanArrayList != null) {
                        item2 = new ArrayList<>();
                        for (int i = 0; i < dataBeanArrayList.size(); i++) {
                            item2.add(new LevelBean(dataBeanArrayList.get(i).getName(), false, String.valueOf(dataBeanArrayList.get(i).getId())));
                        }
                        item3 = new ArrayList<>();
                        item3.add(new LevelBean("附近优先", true, "latlng"));
                        item3.add(new LevelBean("折扣最低", false, "discount_low"));
                        item3.add(new LevelBean("销量优先", false, "discount_sale"));
                        item3.add(new LevelBean("好评最多", false, "discount_appraise"));
                        item4 = new ArrayList<>();
                        item4.add(new LevelBean("停车位", false, ""));
                        item4.add(new LevelBean("包厢", false, ""));
                        item4.add(new LevelBean("Wi-Fi", false, ""));
                        item4.add(new LevelBean("空调", false, ""));
                        if (tabAdapter == null) {
                            tabAdapter = new FlowTabAdapter(item4, activity, R.layout.flow_layout_item);
                            flowLayout.setAdapter(tabAdapter);
                        } else {
                            tabAdapter.setList(item4);
                            tabAdapter.notifyDataChanged();
                        }
                        flowLayout.setOnTagClickListener((view, position, parent) -> {
                            item4.get(position).setCheck(true);
                            list.put(position, item4.get(position).getResult());
                            if (onFlowOItemListener != null) {
                                onFlowOItemListener.onFlow(list);
                            }
                            tabAdapter.notifyDataChanged();
                            return false;
                        });
                        round_discountChoose.setListes(item1, item2, item3, item4);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        SureBean sureBean = (SureBean) JsonUtil.fromJson(s, SureBean.class);
                        if (sureBean.getCode() == 0) {
                            dataBeanArrayList = sureBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Cancel() {
        list = new SparseArray<>();
        if (onFlowOItemListener != null) {
            onFlowOItemListener.onFlow(list);
        }
    }

    private SparseArray<String> list = new SparseArray<>();

    public interface OnFlowOItemListener {
        void onFlow(SparseArray<String> list);
    }
}
