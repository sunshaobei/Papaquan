package xm.ppq.papaquan.Presenter.life.record;

import android.util.Log;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.RecordBean;
import xm.ppq.papaquan.Bean.life.RecordListBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.take_notes.fragment.Round_Record;

/**
 * Created by EdgeDi on 19:03.
 */

public class Mutual_Record implements RecordPresenter {

    private Round_Record record;

    public Mutual_Record(Round_Record record) {
        this.record = record;
    }

    @Override
    public void getInfo() {
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.USETITLE, "{\"sid\": " + record.getSid() + ",\"type\": \"" + record.getType() + "\"}", new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    RecordBean recordBean = (RecordBean) JsonUtil.fromJson(s, RecordBean.class);
                    if (recordBean.getCode() == 0) {
                        record.setTopBean(recordBean.getData());
                    }
                }
            }
        });
    }

    @Override
    public void DisposeString() {
        String start = record.getStartTime();
        String end = record.getEndTime();
        String s_year = start.substring(0, 4);
        String s_moon = start.substring(5, 7);
        String s_day = start.substring(8, 10);
        String e_year = end.substring(0, 4);
        String e_moon = end.substring(5, 7);
        String e_day = end.substring(8, 10);
        record.setTime(s_moon + "-" + s_day + "至" + e_moon + "-" + e_day);
        record.setStartTime(s_year + "-" + s_moon);
        record.setEndTime(e_year + "-" + e_moon);
    }

    @Override
    public void TakeInfo(String clerk, String product_id) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "sid", "type", "page", "length", "starttime", "endtime", "clerk", "product_id")
                .put_value(record.getUid(), record.getToken(), 1, record.getSid(), record.getType(), 0, 10, record.getStartTime(), record.getEndTime(), clerk, product_id);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SRERCORD, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    RecordListBean recordListBean = (RecordListBean) JsonUtil.fromJson(s, RecordListBean.class);
                    if (recordListBean.getCode() == 0) {
                        record.setListData((ArrayList<RecordListBean.DataBean>) recordListBean.getData());
                        record.setMoney("核销金额:" + recordListBean.getOther().getCountmoney() + "元");
                    }
                }
            }
        });
    }
}
