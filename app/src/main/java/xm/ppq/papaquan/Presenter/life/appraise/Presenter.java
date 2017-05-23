package xm.ppq.papaquan.Presenter.life.appraise;

import java.util.List;

/**
 * Created by sunshaobei on 2017/4/28.
 */

public interface Presenter {

    void appraise(int pid, String content, List<String> piclist);

    void Discount(int pid, String content, List<String> piclist);

    void Coupon(int pid, String content, List<String> piclist);

    void getTitle(String type, String url);
}
