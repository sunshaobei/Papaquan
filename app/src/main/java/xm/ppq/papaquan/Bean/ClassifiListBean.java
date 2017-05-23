package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ClassifiListBean {

    private String content;
    private boolean isSelect;

    public ClassifiListBean(String content, boolean isSelect) {
        this.content = content;
        this.isSelect = isSelect;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}