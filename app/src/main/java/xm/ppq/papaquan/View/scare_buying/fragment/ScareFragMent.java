package xm.ppq.papaquan.View.scare_buying.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ScareFAdapter;
import xm.ppq.papaquan.Bean.life.MycouponData;
import xm.ppq.papaquan.Bean.life.ScareFBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.Tool.customview.Xffoot;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/4/10.
 */

public class ScareFragMent extends Fragment {

    @BindView(R.id.scare_f_list)
    ListView scare_f_list;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;

    private View view;
    private ArrayList<ScareFBean.DataBean> dataBeen = new ArrayList<>();
    private ArrayList<MycouponData.DataBean> couponList = new ArrayList<>();
    private String ss;
    private int type;
    private ScareFAdapter adapter;
    private SharedPreferencesPotting potting;
    private int pager;
    private MycouponAdapter mycouponAdapter;
    private String type1;

    public ScareFragMent() {
    }

    private int action;

    public ScareFragMent(int a) {
        this.action = a;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_scare, container, false);
            ButterKnife.bind(this, view);
        }
        loadData();
        setListener();
        return view;
    }

    private void initView() {
        xRefreshView.setPullLoadEnable(true);
        //设置在上拉加载被禁用的情况下，是否允许界面被上拉
//		xRefreshView.setMoveFootWhenDisablePullLoadMore(false);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoLoadMore(true);
        xRefreshView.setCustomHeaderView(new CustomHeader(getActivity(), 300));
        xRefreshView.setCustomFooterView(new Xffoot(getActivity()));
        xRefreshView.setMoveForHorizontal(true);
//		xRefreshView.setPinnedContent(true);
        //设置当非RecyclerView上拉加载完成以后的回弹时间
        xRefreshView.setScrollBackDuration(300);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                pager = 0;
                getData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pager++;
                getData();
            }
        });
        potting = new SharedPreferencesPotting(getActivity(), "my_login");
        if (action == 1) {
            adapter = new ScareFAdapter(getContext(), dataBeen, R.layout.scare_item_f);
            scare_f_list.setAdapter(adapter);
        } else {
            mycouponAdapter = new MycouponAdapter(getActivity(), couponList, R.layout.scare_item_f);
            mycouponAdapter.setType(type);
            scare_f_list.setAdapter(mycouponAdapter);
        }
        getData();
    }

    private void getData() {
        String path = "";
        if (action == 1) {
            path = BaseUrl.MYPAIC;
        } else {
            path = BaseUrl.MYCOUPON;
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            if (action == 1) {
                jsonObject.put("type", type1);
            } else {
                jsonObject.put("type", type);
            }
            jsonObject.put("page", pager)
                    .put("length", 10);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(path, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (action == 1) {
                            ScareFBean scareFBean = (ScareFBean) JsonUtil.fromJson(s, ScareFBean.class);
                            if (scareFBean.getCode() == 0) {
                                List<ScareFBean.DataBean> data = scareFBean.getData();
                                if (pager == 0) {
                                    dataBeen.clear();
                                }
                                dataBeen.addAll(data);
                                adapter.notifyDataSetChanged();
                                xRefreshView.stopRefresh();
                                xRefreshView.stopLoadMore();
                            }
                        } else {
                            MycouponData data = (MycouponData) JsonUtil.fromJson(s, MycouponData.class);
                            List<MycouponData.DataBean> list = data.getData();
                            if (pager == 0)
                                couponList.clear();
                            couponList.addAll(list);
                            mycouponAdapter.notifyDataSetChanged();
                            xRefreshView.stopRefresh();
                            xRefreshView.stopLoadMore();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        type = getArguments().getInt("type");
        type1 = getArguments().getString("type1", "all");
        switch (getArguments().getString("number")) {
            case "全部抢购":
                ss = "已完成";
                break;
            case "待付款":
                ss = "去付款";
                break;
            case "待使用":
                ss = "去使用";
                break;
            case "待评论":
                ss = "去评论";
                break;
            case "退款":
                ss = "去退款";
                break;
        }
        initView();
    }

    public void setListener() {
//        scare_f_list.setOnItemClickListener((parent, view1, position, id) -> {
//            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
//            startActivityForResult(intent, IntentCode.RequestCode.TOPRODUCTDETAIL);
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOLIFE) {
            getActivity().setResult(IntentCode.ResultCode.BACKTOLIFE);
            getActivity().finish();
        }
    }
}
