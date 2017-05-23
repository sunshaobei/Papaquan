package xm.ppq.papaquan.Presenter.trendtopic;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/22.
 */

public interface TrendPresenter {

    void start();

    void Criticism_num(int type, String tid,int pager);

    void Follow_Finish(String url, JSONObject jsonObject, String result);

    void Love(String url, JSONObject jsonObject);

    String getNickname();

    String getId();

    String getUid();

    String getUrl();
}