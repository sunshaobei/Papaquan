package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/6.
 */

public class PapaTopicData {

    private List<TopicData> list;

    public List<TopicData> getList() {
        return list;
    }

    public void setList(List<TopicData> list) {
        this.list = list;
    }

    public static class TopicData {

        private int id;
        private String title;
        private String img;
        private int isadmin;

        public int getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(int isadmin) {
            this.isadmin = isadmin;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        private int top;
    }
}
