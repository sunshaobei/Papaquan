package xm.ppq.papaquan.Tool.typewriting;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.R;

import static android.view.Gravity.CENTER;

/**
 * Created by Administrator on 2017/3/3.
 */

public class EmotionGridView {

    private Context context;
    private EditText content_edit;
    private List<GridView> grids = new ArrayList<>();
    private int height;
    private LookPagerAdapter pageradapter;
    private ViewPager viewpager;

    public EmotionGridView(Context context, EditText content_edit, int height, LookPagerAdapter pageradapter, ViewPager viewpager) {
        this.context = context;
        this.content_edit = content_edit;
        this.height = height;
        this.pageradapter = pageradapter;
        this.viewpager = viewpager;
    }

    private GridView CreateGridView(final List<String> list) {
        GridView gridview = new GridView(context);
        gridview.setNumColumns(7);
        gridview.setGravity(CENTER);
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridview.setBackgroundColor(context.getResources().getColor(R.color.white));
        gridview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        gridview.setAdapter(new GridAdapter(context, list, R.layout.grid_item));
        gridview.setOnItemClickListener((parent, view, position, id) -> {
            if (list.get(position).equals("[删除]")) {
                content_edit.dispatchKeyEvent(new KeyEvent(
                        KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            } else {
                result = content_edit.getText().toString() + list.get(position);
                content_edit.setText(SpanStringUtils.getEmotionContent(context, result));
            }
        });
        return gridview;
    }

    private String result;

    public void a1(ViewGroup.LayoutParams layoutParams) {
        List<String> list = new ArrayList<>();
        for (String icon_key : EmotionUtils.getMap(EmotionUtils.MAP_KEY).keySet()) {
            list.add(icon_key);
            if (list.size() == 20) {
                GridView gv = CreateGridView(list);
                list = new ArrayList<>();
                grids.add(gv);
            }
        }
        if (list.size() > 0) {
            GridView gv = CreateGridView(list);
            grids.add(gv);
        }
        pageradapter.setList(grids);
        viewpager.setLayoutParams(layoutParams);
        viewpager.setAdapter(pageradapter);
    }

}
