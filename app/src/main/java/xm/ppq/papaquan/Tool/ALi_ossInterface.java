package xm.ppq.papaquan.Tool;

/**
 * Created by sunshaobei on 2017/3/14.
 */

public interface ALi_ossInterface {
    void upImageSuccess(String url);

    void upImageError(String s);

    void upVideoSuccess(String s);

    void upProgress(int progress);

    void setVideoPic(String url);
}
