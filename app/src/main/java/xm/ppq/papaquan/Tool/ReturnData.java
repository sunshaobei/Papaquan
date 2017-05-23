package xm.ppq.papaquan.Tool;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface ReturnData<T> {

    void Success(T result);

    void Lose(String result);
}