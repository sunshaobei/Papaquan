package xm.ppq.papaquan.Tool.IMinit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import xm.ppq.papaquan.Model.News.Dispose_News;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SearchcontactPopupwindow;
import xm.ppq.papaquan.View.discuss_reply.Discuss_ReplyActivity;
import xm.ppq.papaquan.View.main.frame.Round_news;
import xm.ppq.papaquan.View.news_mine_money.Mine_MoneyActivity;

/**
 * Created by sunshaobei on 2017/3/24.
 */

public class ConversationUI extends IMConversationListUI implements Round_news {

    private TextView at_count;
    private TextView pl_count;
    private TextView ds_count;
    private View at_image;
    private View pl_image;
    private View ds_image;
    private Dispose_News dispose_news;
    private View view;

    public ConversationUI(Pointcut pointcut) {
        super(pointcut);
    }

    @Override
    public View getCustomConversationListTitle(final Fragment fragment,
                                               final Context context, LayoutInflater inflater) {
        //TODO 重要：必须以该形式初始化customView---［inflate(R.layout.**, new RelativeLayout(context),false)］------，以让inflater知道父布局的类型，否则布局xml**中定义的高度和宽度无效，均被默认的wrap_content替代
        RelativeLayout customView = (RelativeLayout) inflater
                .inflate(R.layout.demo_custom_conversation_title_bar, new RelativeLayout(context), false);
        return customView;
    }

    @Override
    public View getCustomSearchView(Fragment fragment, View.OnClickListener onClickListener) {
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
        if (view==null)
        {
            Context context = fragment.getContext();
            view = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_news_listhead, null);
            View searchview = view.findViewById(R.id.searchview);
            View search = view.findViewById(R.id.search);
            search.setOnClickListener(v -> {
                SearchcontactPopupwindow searchcontactPopupwindow = new SearchcontactPopupwindow(fragment.getActivity(), search);
                searchcontactPopupwindow.show();
                searchview.setVisibility(View.GONE);
                searchcontactPopupwindow.getDefault().setOnDismissListener(() -> searchview.setVisibility(View.VISIBLE));
            });
            at_image = view.findViewById(R.id.at_image);
            pl_image = view.findViewById(R.id.pl_image);
            ds_image = view.findViewById(R.id.ds_image);
            at_count = (TextView) view.findViewById(R.id.at_count);
            pl_count = (TextView) view.findViewById(R.id.pl_count);
            ds_count = (TextView) view.findViewById(R.id.ds_count);
            LinearLayout aite_mine_lin = (LinearLayout) view.findViewById(R.id.aite_mine_lin);
            LinearLayout discuss_mine_lin = (LinearLayout) view.findViewById(R.id.discuss_mine_lin);
            LinearLayout money_mine_lin = (LinearLayout) view.findViewById(R.id.money_mine_lin);
            aite_mine_lin.setOnClickListener(v -> {
                Intent intent = new Intent(context, Mine_MoneyActivity.class);
                intent.putExtra("mom", "@我的");
                context.startActivity(intent);
            });
            discuss_mine_lin.setOnClickListener(v -> {
                Intent intent = new Intent(context,
                        Discuss_ReplyActivity.class);
                context.startActivity(intent);
            });
            money_mine_lin.setOnClickListener(v -> {
                Intent intent = new Intent(context, Mine_MoneyActivity.class);
                intent.putExtra("mom", "打赏");
                context.startActivity(intent);
            });
            dispose_news = new Dispose_News(this, context);
            dispose_news.getData();
        }
        return view;
    }

    @Subscribe
    public void onEvnent(String s)
    {
        if (s.equals("fresh"))
        dispose_news.getData();
        if (s.equals("unregist"))
            EventBus.getDefault().unregister(this);
    }


    @Override
    public boolean enableSearchConversations(Fragment fragment) {
        return true;
    }

    @Override
    public boolean needHideTitleView(Fragment fragment) {
        return false;
    }

    @Override
    public boolean getPullToRefreshEnabled() {
        return true;
    }


    @Override
    public void setData(int actcount, int messageCount, int rewardCount) {
        at_count.setText(String.valueOf(actcount));
        pl_count.setText(String.valueOf(messageCount));
        ds_count.setText(String.valueOf(rewardCount));
        if (actcount == 0)
            at_image.setVisibility(View.GONE);
        else at_image.setVisibility(View.VISIBLE);
        if (messageCount == 0)
            pl_image.setVisibility(View.GONE);
        else pl_image.setVisibility(View.VISIBLE);
        if (rewardCount == 0)
            ds_image.setVisibility(View.GONE);
        else ds_image.setVisibility(View.VISIBLE);
    }

    /**
     * 返回自定义置顶回话的背景色(16进制字符串形式)
     *
     * @return
     */
    @Override
    public String getCustomTopConversationColor() {
        return "#ffffff";
    }
}
