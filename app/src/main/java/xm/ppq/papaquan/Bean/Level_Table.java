package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/2/20.
 */

public class Level_Table {

    private String level;
    private int i;
    private String icon;
    private int resource;
    private boolean is_io;
    private boolean is_type;

    public boolean is_type() {
        return is_type;
    }

    public void setIs_type(boolean is_type) {
        this.is_type = is_type;
    }

    public boolean is_io() {
        return is_io;
    }

    public void setIs_io(boolean is_io) {
        this.is_io = is_io;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
