package xm.ppq.papaquan.Presenter.life.sller_oof;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.NewSellerOofBean;
import xm.ppq.papaquan.Bean.NewsBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.LifeHomeData;
import xm.ppq.papaquan.Bean.life.SeventhBean;
import xm.ppq.papaquan.Bean.life.TopSelleOofBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.selleroof.Round_SellerOof;

/**
 * Created by EdgeDi on 14:17.
 */

public class Mutual_Seller_Oof implements SellerOofPresenter {

    private Round_SellerOof round_sellerOof;

    public Mutual_Seller_Oof(Round_SellerOof round_sellerOof) {
        this.round_sellerOof = round_sellerOof;
    }

    /**
     * 请求商业主页轮播图
     */
    @Override
    public void getCarousel_Figure() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("citycode")
                .put_value(round_sellerOof.getCityCode());
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETAD, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    TopSelleOofBean topSelleOofBean = (TopSelleOofBean) JsonUtil.fromJson(s, TopSelleOofBean.class);
                    if (topSelleOofBean.getCode() == 0) {
                        round_sellerOof.setTop((ArrayList<TopSelleOofBean.DataBean>) topSelleOofBean.getData());
                    }
                }
            }
        });
    }

    /**
     * 商家最新入驻
     */
    @Override
    public void getNewEnter() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("citycode")
                .put_value(round_sellerOof.getCityCode());
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.NEWJOIN, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                NewSellerOofBean newSellerOofBean = (NewSellerOofBean) JsonUtil.fromJson(s, NewSellerOofBean.class);
                if (newSellerOofBean.getCode() == 0) {
                    round_sellerOof.setNesEnter((ArrayList<NewSellerOofBean.DataBean>) newSellerOofBean.getData());
                }
            }
        });
    }

    /**
     * 获取商家行业
     */
    @Override
    public void getTradeClassify() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("citycode", "type")
                .put_value(round_sellerOof.getCityCode(), "2");
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETINDUSTRY, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    TypeClassfiyBean typeClassfiyBean = (TypeClassfiyBean) JsonUtil.fromJson(s, TypeClassfiyBean.class);
                    if (typeClassfiyBean.getCode() == 0) {
                        round_sellerOof.setClassify(typeClassfiyBean.getData());
                    }
                }
            }
        });
    }

    @Override
    public void getSeventh(String type) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("citycode", "type", "page", "length")
                .put_value(round_sellerOof.getCityCode(), type, round_sellerOof.getPage(), 10);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPSORT, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    SeventhBean seventhBean = (SeventhBean) JsonUtil.fromJson(s, SeventhBean.class);
                    if (seventhBean.getCode() == 0) {
                        round_sellerOof.setSeventh((ArrayList<SeventhBean.DataBean>) seventhBean.getData());
                    }
                }
            }
        });
    }
}
