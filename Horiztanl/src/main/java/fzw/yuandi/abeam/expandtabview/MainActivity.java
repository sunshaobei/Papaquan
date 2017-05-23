package fzw.yuandi.abeam.expandtabview;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import fzw.yuandi.abeam.view.ChildView;
import fzw.yuandi.abeam.view.ExpandTabView;
import fzw.yuandi.qr_code.R;

public class MainActivity extends Activity {

    private ExpandTabView expandTabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ChildView viewLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVaule();
        initListener();

    }

    private void initView() {

        //初始化控件
        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);
        viewLeft = new ChildView(this);

    }

    private void initVaule() {

        mViewArray.add(viewLeft);

        //设置顶部数据信息
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("全部");
        expandTabView.setValue(mTextArray, mViewArray);
        expandTabView.setTitle(viewLeft.getShowText(), 0);

    }

    private void initListener() {


        viewLeft.setOnSelectListener(new ChildView.OnSelectListener() {

            @Override
            public void getValue(String showText) {

                onRefresh(viewLeft, showText);

            }
        });


    }

    //视图被点击后刷新数据
    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        Toast.makeText(MainActivity.this, showText, Toast.LENGTH_SHORT).show();

    }

    //获取当前的view
    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandTabView.onPressBack()) {
            finish();
        }

    }

}
