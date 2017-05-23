package xm.ppq.papaquan.Bean.life;

/**
 * Created by EdgeDi on 19:42.
 */

public class PayBean {

    private String type;
    private String url;

    public PayBean(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}