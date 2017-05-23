package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.FollowerandFollowBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.follower_follow.Round_FerFow;

/**
 * Created by Administrator on 2017/2/24.
 */

public class FollowerAndFollowAdapter extends ConcreteAdapter<FollowerandFollowBean.Data> {

    private String type;
    private Round_FerFow round_ferFow;

    public FollowerAndFollowAdapter(Context context, List<FollowerandFollowBean.Data> list, int itemLayout, String type) {
        super(context, list, itemLayout);
        this.type = type;
        round_ferFow = (Round_FerFow) getContext();
    }

    @Override
    protected void convert(ViewHolder viewHolder, final FollowerandFollowBean.Data item, int position) {
        viewHolder.setText(item.nickname, R.id.ff_nick_center)
                .setText(item.signature, R.id.ff_sign_center);
        ImageLoading.common(getContext(), item.headurl, viewHolder.getView(R.id.ff_icon_left), R.drawable.default_icon);
        switch (type) {
            case "1"://关注
                item.type = "关注";
                if (item.eachother.equals("1")) {
                    item.operate = "取消关注";
                    viewHolder.setResources(R.drawable.mutual_follow, R.id.ff_icon_right);
                } else {
                    item.operate = "取消关注";
                    viewHolder.setResources(R.drawable.already_concern, R.id.ff_icon_right);
                }
                break;
            case "2"://粉丝
                item.type = "粉丝";
                if (item.eachother.equals("1")) {
                    item.operate = "取消关注";
                    viewHolder.setResources(R.drawable.mutual_follow, R.id.ff_icon_right);
                } else {
                    item.operate = "关注";
                    viewHolder.setResources(R.drawable.follow_ta, R.id.ff_icon_right);
                }
                break;
            case "3"://屏蔽
                item.operate = "取消屏蔽";
                viewHolder.setResources(R.drawable.cancel_mask, R.id.ff_icon_right);
                break;
        }
        viewHolder.setOnClickListener(v -> {
            if (item.operate.equals("关注")) {
                if (item.type.equals("关注")) {
                    round_ferFow.setG_Q_P(item.uidone, "1", BaseUrl.DOSUB);
                } else if (item.type.equals("粉丝")) {
                    round_ferFow.setG_Q_P(item.uidone, "1", BaseUrl.DOSUB);
                }
            } else if (item.operate.equals("取消关注")) {
                if (item.type.equals("关注")) {
                    round_ferFow.setG_Q_P(item.uidtwo, "1", BaseUrl.DELSUB);
                } else if (item.type.equals("粉丝")) {
                    round_ferFow.setG_Q_P(item.uidone, "1", BaseUrl.DELSUB);
                }
            } else if (item.operate.equals("取消屏蔽")) {
                round_ferFow.setG_Q_P(item.uidtwo, "2", BaseUrl.DELSUB);
            }
        }, R.id.ff_icon_right);
    }
}