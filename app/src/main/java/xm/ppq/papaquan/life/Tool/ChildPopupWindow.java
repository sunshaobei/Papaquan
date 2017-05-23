package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import xm.ppq.papaquan.Adapter.RadioAdapter;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.Life.discountNchoose.ChangeText;

/**
 * Created by EdgeDi on 14:29.
 */

public class ChildPopupWindow extends PopupWindowPotting {

    private ListView popup;
    private RadioAdapter adapter;
    private ArrayList<LevelBean> levelBeen;
    private ChangeText changeText;
    private View v;

    public ChildPopupWindow(Activity activity) {
        super(activity);
        this.changeText = (ChangeText) activity;
    }

    public ArrayList<LevelBean> getLevelBeen() {
        return levelBeen;
    }

    public void setLevelBeen(ArrayList<LevelBean> levelBeen) {
        this.levelBeen = levelBeen;
        if (adapter == null) {
            adapter = new RadioAdapter(levelBeen, getActivity(), R.layout.level_item_pop);
            popup.setAdapter(adapter);
        } else {
            adapter.setList(levelBeen);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.popupwindow_list;
    }

    @Override
    protected void initUI() {
        popup = Bind(R.id.popup);
        v = Bind(R.id.v);
    }

    @Override
    protected void setListener() {
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Hide();
                }
            });
        }
        if (popup != null) {
            popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (levelBeen != null) {
                        for (int i = 0; i < levelBeen.size(); i++) {
                            if (i == position) {
                                levelBeen.get(i).setCheck(true);
                            } else {
                                levelBeen.get(i).setCheck(false);
                                changeText.setText(0, levelBeen.get(position).getResult(), levelBeen.get(position).getId());
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    public void ShowLeft(View view) {
        getPopupWindow().showAsDropDown(view);
    }
}