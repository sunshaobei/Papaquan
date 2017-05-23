package xm.ppq.papaquan.Presenter.follow_follower;

/**
 * Created by Administrator on 2017/3/14.
 */

public interface Follow_Follower_Presenter {

    void start(int page,String search);

    void Operate(String itemUid, String type, String url);
}
