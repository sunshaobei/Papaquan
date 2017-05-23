package xm.ppq.papaquan.Tool;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 只会加载一个页面，每次都会再次加载
 * Created by fuzhaowei on 2016/11/11.
 */
public abstract class LoadingOneFragment extends Fragment {

    protected boolean isViewInitiated; //控件是否初始化完成
    protected boolean isVisibleToUser; //页面是否可见
    protected int pitch_on = Color.parseColor("#e60012");
    protected int pitch_off = Color.parseColor("#555555");

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {   //page 1等会此读数据  生命周期
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {   //page 0进入再此读数据
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    public abstract void loadData();

    public abstract void setListener();

    protected void prepareFetchData() {
        if (isVisibleToUser && isViewInitiated) {
            loadData();
            setListener();
        }
    }

    protected void Skip(Class cla) {
        Intent intent = new Intent(getActivity(), cla);
        getActivity().startActivity(intent);
    }

    protected void Skip(Class cla, String key, String value) {
        Intent intent = new Intent(getActivity(), cla);
        intent.putExtra(key, value);
        getActivity().startActivity(intent);
    }

    protected void TextView(int position, TextView... textViews) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == position) {
                textViews[i].setTextColor(pitch_on);
            } else {
                textViews[i].setTextColor(pitch_off);
            }
        }
    }

}
