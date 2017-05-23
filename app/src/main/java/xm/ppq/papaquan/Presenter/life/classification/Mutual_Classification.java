package xm.ppq.papaquan.Presenter.life.classification;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.ClassificationBean;
import xm.ppq.papaquan.Bean.life.LinkHeadAdBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.classification.Round_Classification;

/**
 * Created by EdgeDi on 9:34.
 */

public class Mutual_Classification implements ClassificationPresenter {

    private Round_Classification round_classification;

    public Mutual_Classification(Round_Classification round_classification) {
        this.round_classification = round_classification;
    }

    /**
     * 头部广告
     */
    @Override
    public void getHeadAdvert() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("citycode")
                .put_value(round_classification.getCityCode());
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.TYPEAD, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    LinkHeadAdBean linkHeadAdBean = (LinkHeadAdBean) JsonUtil.fromJson(s, LinkHeadAdBean.class);
                    if (linkHeadAdBean.getCode() == 0) {
                        round_classification.setHead((ArrayList<LinkHeadAdBean.DataBean>) linkHeadAdBean.getData());
                    }
                }
            }
        });
    }

    /**
     * 获取数据
     */
    @Override
    public void getInfo() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("citycode", "type", "industry", "industrytwo", "lng", "lat", "page", "length")
                .put_value(round_classification.getCityCode(), round_classification.getType(),
                        round_classification.getIndustry(), round_classification.getIndustry_Two(),
                        round_classification.getLng(), round_classification.getLat(),
                        round_classification.getPage(), 10);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPTYPE, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    ClassificationBean classificationBean = (ClassificationBean) JsonUtil.fromJson(s, ClassificationBean.class);
                    if (classificationBean.getCode() == 0) {
                        round_classification.setBean((ArrayList<ClassificationBean.DataBean>) classificationBean.getData());
                    }
                }
            }
        });
    }
}