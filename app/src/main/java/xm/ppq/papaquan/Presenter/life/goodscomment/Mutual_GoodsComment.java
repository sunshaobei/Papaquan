package xm.ppq.papaquan.Presenter.life.goodscomment;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.JudgePanicBuyBean;
import xm.ppq.papaquan.Bean.life.JudgeRebateBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.goodscommet.Round_GoodsComment;

/**
 * Created by EdgeDi on 15:12.
 */

public class Mutual_GoodsComment implements GoodsCommentPresenter {

    private Round_GoodsComment round_goodsComment;

    public Mutual_GoodsComment(Round_GoodsComment round_goodsComment) {
        this.round_goodsComment = round_goodsComment;
    }

    /**
     * 精选
     */
    @Override
    public void getCoupon() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("cid", "haspic", "page", "length")
                .put_value(round_goodsComment.getPid(), round_goodsComment.getType(), round_goodsComment.getPage(), 10);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(round_goodsComment.getUrl(), jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    JudgeRebateBean bean = (JudgeRebateBean) JsonUtil.fromJson(s, JudgeRebateBean.class);
                    if (bean.getCode() == 0) {
                        round_goodsComment.setRebate(bean);
                    }
                }
            }
        });
    }

    /**
     * 折扣
     */
    @Override
    public void getRebate() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("sid", "haspic", "page", "length")
                .put_value(round_goodsComment.getPid(), round_goodsComment.getType(), round_goodsComment.getPage(), 10);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(round_goodsComment.getUrl(), jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    JudgeRebateBean bean = (JudgeRebateBean) JsonUtil.fromJson(s, JudgeRebateBean.class);
                    if (bean.getCode() == 0) {
                        round_goodsComment.setRebate(bean);
                    }
                }
            }
        });
    }

    /**
     * 抢购
     */
    @Override
    public void getPanicBuy() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid", round_goodsComment.getPid())
                    .put("type", round_goodsComment.getType())
                    .put("page", round_goodsComment.getPage())
                    .put("length", 10)
                    .put("token", round_goodsComment.getToken())
                    .put("tokentype", 1)
                    .put("uid", round_goodsComment.getUid());
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(round_goodsComment.getUrl(), jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        JudgePanicBuyBean judgePanicBuyBean = (JudgePanicBuyBean) JsonUtil.fromJson(s, JudgePanicBuyBean.class);
                        if (judgePanicBuyBean.getCode() == 0) {
                            round_goodsComment.setPanicBuy(judgePanicBuyBean.getData());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}