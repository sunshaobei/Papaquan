package xm.ppq.papaquan.Presenter.life.judge_of;

import android.util.Log;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.JudgeOfBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.judge_of.Round_JudgeOf;

/**
 * Created by EdgeDi on 11:19.
 */

public class Mutual_JudgeOf implements JudgeOfPresenter {

    private Round_JudgeOf round_judgeOf;

    private String start_time = "0";
    private String end_time = "0";

    public Mutual_JudgeOf(Round_JudgeOf round_judgeOf) {
        this.round_judgeOf = round_judgeOf;
    }

    @Override
    public void getInfo() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("sid", "uid", "token", "tokentype", "page", "length", "starttime", "endtime", "type")
                .put_value(round_judgeOf.getSid(), round_judgeOf.getUid(), round_judgeOf.getToken(), 1, round_judgeOf.getPage(), 10, start_time, end_time, round_judgeOf.getType());
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPCOMMENT, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    Log.e("s", s);
                    JudgeOfBean judgeOfBean = (JudgeOfBean) JsonUtil.fromJson(s, JudgeOfBean.class);
                    if (judgeOfBean.getCode() == 0) {
                        round_judgeOf.setBean((ArrayList<JudgeOfBean.DataBean>) judgeOfBean.getData());
                    }
                }
            }
        });
    }

    @Override
    public void DisposeString() {
        String start = round_judgeOf.getStart();
        String end = round_judgeOf.getEnd();
        String s_year = start.substring(0, 4);
        String s_moon = start.substring(5, 7);
        String s_day = start.substring(8, 10);
        String e_year = end.substring(0, 4);
        String e_moon = end.substring(5, 7);
        String e_day = end.substring(8, 10);
        round_judgeOf.setTime(s_moon + "-" + s_day + "至" + e_moon + "-" + e_day);
        start_time = s_year + "-" + s_moon;
        end_time = e_year + "-" + e_moon;
        getInfo();
    }
}
