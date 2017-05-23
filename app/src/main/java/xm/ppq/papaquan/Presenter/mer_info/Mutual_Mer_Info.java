package xm.ppq.papaquan.Presenter.mer_info;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.MerInfoBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.mer_info.Round_Mer_Info;

/**
 * Created by Administrator on 2017/4/20.
 */

public class Mutual_Mer_Info implements MerInfoPresenter {

    private Round_Mer_Info round_mer_info;

    public Mutual_Mer_Info(Round_Mer_Info round_mer_info) {
        this.round_mer_info = round_mer_info;
    }

    private ArrayList<TypeClassfiyBean.DataBean> dataBeen;

    /**
     * 请求行业分类
     */
    @Override
    public void startclassify() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", round_mer_info.getCityCode())
                    .put("type", 2);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETINDUSTRY, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBeen != null) {
                        round_mer_info.setTypeClassify(dataBeen);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        TypeClassfiyBean typeClassfiyBean = (TypeClassfiyBean) JsonUtil.fromJson(s, TypeClassfiyBean.class);
                        if (typeClassfiyBean.getCode() == 0) {
                            dataBeen = typeClassfiyBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getShopInfo() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("sid", "uid", "token", "tokentype").put_value(round_mer_info.getSid(), round_mer_info.getUid(), round_mer_info.getToken(), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETSHOPS, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    MerInfoBean merInfoBean = (MerInfoBean) JsonUtil.fromJson(s, MerInfoBean.class);
                    if (merInfoBean.getCode() == 0) {
                        round_mer_info.setBean(merInfoBean.getData());
                    }
                }
            }
        });
    }

    /**
     * 提交商户申请
     */
    @Override
    public void startApply() {
        if (round_mer_info.getName() != null) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("citycode", round_mer_info.getCityCode())
                        .put("uid", round_mer_info.getUid())
                        .put("token", round_mer_info.getToken())
                        .put("tokentype", 1)
                        .put("token", round_mer_info.getToken())
                        .put("id", round_mer_info.getId())
                        .put("name", round_mer_info.getName())
                        .putOpt("logo", round_mer_info.getPhoto());
                if (round_mer_info.getForSure() != null) {
                    if (round_mer_info.getForSure().getAddress() != null) {
                        jsonObject.put("address", round_mer_info.getForSure().getAddress());//地址
                    }
                    if (round_mer_info.getForSure().getLatLng() != null) {
                        jsonObject.put("lat", round_mer_info.getForSure().getLatLng().getLatitude())//纬度
                                .put("lng", round_mer_info.getForSure().getLatLng().getLongitude());//经度
                    }
                    if (round_mer_info.getForSure().getArea() != null) {
                        jsonObject.put("area", round_mer_info.getForSure().getArea());//商圈id
                    }
                } else {
                    round_mer_info.ToastShow("请检查地址/商圈是否填写正确");
                    return;
                }
                jsonObject.put("tel", round_mer_info.getTel())
                        .put("shoptype", round_mer_info.getShopType())
                        .put("industry", round_mer_info.getIndOne())
                        .put("industry_two", round_mer_info.getIndTwo())
                        .put("consume", round_mer_info.getOther().getMoney())
                        .put("wifi", round_mer_info.getOther().getWifi())
                        .put("car", round_mer_info.getOther().getCar())
                        .put("air", round_mer_info.getOther().getConditioner())
                        .put("room", round_mer_info.getOther().getBox())
                        .put("business_hours", round_mer_info.getOther().getTime())
                        .put("business_license", round_mer_info.getPhoto2())
                        .put("business_certificate", round_mer_info.getPhoto3())
                        .put("linkname", round_mer_info.getPhotoWoman())
                        .put("linkphone", round_mer_info.getPhtooNumber())
                        .put("tokentype", 1);
                OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.PUTSHOP, jsonObject.toString(), new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("0")) {
                            round_mer_info.SuccesShow();
                        } else {
                            round_mer_info.ErrorShow();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
