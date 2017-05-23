package xm.ppq.papaquan.Tool.typewriting;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/2/27.
 */

public class EmotionUtils {

    public static final int MAP_KEY = 0x00001;
    public static final int ALL_MAP_KEY = 0x00002;

    public static Map<String, Integer> EMOTION_MAP;
    public static Map<String, Integer> EMOTION_ALL_MAP;

    static {
        EMOTION_MAP = new HashMap<>();
        EMOTION_ALL_MAP = new HashMap<>();

        EMOTION_MAP.put("[奋斗]", R.drawable.look_0);
        EMOTION_MAP.put("[委屈]", R.drawable.look_1);
        EMOTION_MAP.put("[尴尬]", R.drawable.look_2);
        EMOTION_MAP.put("[得意]", R.drawable.look_3);
        EMOTION_MAP.put("[拜拜]", R.drawable.look_4);
        EMOTION_MAP.put("[傲慢]", R.drawable.look_5);
        EMOTION_MAP.put("[快哭了]", R.drawable.look_6);
        EMOTION_MAP.put("[惊讶]", R.drawable.look_7);
        EMOTION_MAP.put("[憨笑]", R.drawable.look_8);
        EMOTION_MAP.put("[折磨]", R.drawable.look_9);
        EMOTION_MAP.put("[抠鼻]", R.drawable.look_10);
        EMOTION_MAP.put("[拜拜]", R.drawable.look_11);
        EMOTION_MAP.put("[撇嘴]", R.drawable.look_12);
        EMOTION_MAP.put("[擦汗]", R.drawable.look_13);
        EMOTION_MAP.put("[敲打]", R.drawable.look_14);
        EMOTION_MAP.put("[晕]", R.drawable.look_15);
        EMOTION_MAP.put("[冷汗]", R.drawable.look_16);
        EMOTION_MAP.put("[流汗]", R.drawable.look_17);
        EMOTION_MAP.put("[疑问]", R.drawable.look_18);
        EMOTION_MAP.put("[白眼]", R.drawable.look_19);
        EMOTION_MAP.put("[睡]", R.drawable.look_20);
        EMOTION_MAP.put("[糗]", R.drawable.look_21);
        EMOTION_MAP.put("[色]", R.drawable.look_22);
        EMOTION_MAP.put("[衰]", R.drawable.look_23);
        EMOTION_MAP.put("[调皮]", R.drawable.look_24);
        EMOTION_MAP.put("[贪吃]", R.drawable.look_25);
        EMOTION_MAP.put("[鄙视]", R.drawable.look_26);
        EMOTION_MAP.put("[发呆]", R.drawable.look_27);
        EMOTION_MAP.put("[酷]", R.drawable.look_28);
        EMOTION_MAP.put("[闭嘴]", R.drawable.look_29);
        EMOTION_MAP.put("[阴险]", R.drawable.look_30);
        EMOTION_MAP.put("[难过]", R.drawable.look_31);
        EMOTION_MAP.put("[骂]", R.drawable.look_32);
        EMOTION_MAP.put("[鼓掌]", R.drawable.look_33);
        EMOTION_MAP.put("[呲牙]", R.drawable.look_34);
        EMOTION_MAP.put("[可怜]", R.drawable.look_35);
        EMOTION_MAP.put("[可爱]", R.drawable.look_36);
        EMOTION_MAP.put("[吐]", R.drawable.look_37);
        EMOTION_MAP.put("[吓]", R.drawable.look_38);
        EMOTION_MAP.put("[哈欠]", R.drawable.look_39);
        EMOTION_MAP.put("[亲亲]", R.drawable.look_40);
        EMOTION_MAP.put("[偷笑]", R.drawable.look_41);
        EMOTION_MAP.put("[嘘]", R.drawable.look_42);
        EMOTION_MAP.put("[困]", R.drawable.look_43);
        EMOTION_MAP.put("[坏笑]", R.drawable.look_44);
        EMOTION_MAP.put("[大兵]", R.drawable.look_45);
        EMOTION_MAP.put("[大哭]", R.drawable.look_46);
        EMOTION_MAP.put("[乒乓]", R.drawable.look_47);
        EMOTION_MAP.put("[凋谢]", R.drawable.look_48);
        EMOTION_MAP.put("[刀]", R.drawable.look_49);
        EMOTION_MAP.put("[发财]", R.drawable.look_50);
        EMOTION_MAP.put("[咖啡]", R.drawable.look_51);
        EMOTION_MAP.put("[啤酒]", R.drawable.look_52);
        EMOTION_MAP.put("[喜]", R.drawable.look_53);
        EMOTION_MAP.put("[喜欢]", R.drawable.look_54);
        EMOTION_MAP.put("[大便]", R.drawable.look_55);
        EMOTION_MAP.put("[太阳]", R.drawable.look_56);
        EMOTION_MAP.put("[奶瓶]", R.drawable.look_57);
        EMOTION_MAP.put("[帅]", R.drawable.look_58);
        EMOTION_MAP.put("[庆祝]", R.drawable.look_59);
        EMOTION_MAP.put("[心碎]", R.drawable.look_60);
        EMOTION_MAP.put("[怒]", R.drawable.look_61);
        EMOTION_MAP.put("[抱抱]", R.drawable.look_62);
        EMOTION_MAP.put("[握手]", R.drawable.look_63);
        EMOTION_MAP.put("[晚安]", R.drawable.look_64);
        EMOTION_MAP.put("[棒棒糖]", R.drawable.look_65);
        EMOTION_MAP.put("[气球]", R.drawable.look_66);
        EMOTION_MAP.put("[汽车]", R.drawable.look_67);
        EMOTION_MAP.put("[沙发]", R.drawable.look_68);
        EMOTION_MAP.put("[灯笼]", R.drawable.look_69);
        EMOTION_MAP.put("[炸弹]", R.drawable.look_70);
        EMOTION_MAP.put("[猪头]", R.drawable.look_71);
        EMOTION_MAP.put("[瓢虫]", R.drawable.look_72);
        EMOTION_MAP.put("[示爱]", R.drawable.look_73);
        EMOTION_MAP.put("[礼物]", R.drawable.look_74);
        EMOTION_MAP.put("[篮球]", R.drawable.look_75);
        EMOTION_MAP.put("[米饭]", R.drawable.look_76);
        EMOTION_MAP.put("[纸巾]", R.drawable.look_77);
        EMOTION_MAP.put("[药]", R.drawable.look_79);
        EMOTION_MAP.put("[花]", R.drawable.look_78);
        EMOTION_MAP.put("[菜刀]", R.drawable.look_80);
        EMOTION_MAP.put("[蛋糕]", R.drawable.look_81);
        EMOTION_MAP.put("[蜡烛]", R.drawable.look_82);
        EMOTION_MAP.put("[西瓜]", R.drawable.look_83);
        EMOTION_MAP.put("[购物]", R.drawable.look_84);
        EMOTION_MAP.put("[足球]", R.drawable.look_85);
        EMOTION_MAP.put("[钞票]", R.drawable.look_86);
        EMOTION_MAP.put("[钻戒]", R.drawable.look_87);
        EMOTION_MAP.put("[闪电]", R.drawable.look_88);
        EMOTION_MAP.put("[雨伞]", R.drawable.look_89);
        EMOTION_MAP.put("[青蛙]", R.drawable.look_90);
        EMOTION_MAP.put("[面条]", R.drawable.look_91);
        EMOTION_MAP.put("[鞭炮]", R.drawable.look_92);
        EMOTION_MAP.put("[风车]", R.drawable.look_93);
        EMOTION_MAP.put("[骷髅]", R.drawable.look_94);
        EMOTION_MAP.put("[麦克风]", R.drawable.look_95);
        EMOTION_MAP.put("[520]", R.drawable.look_96);
        EMOTION_MAP.put("[NO]", R.drawable.look_97);
        EMOTION_MAP.put("[OK]", R.drawable.look_98);
        EMOTION_MAP.put("[加油]", R.drawable.look_99);
        EMOTION_MAP.put("[勾引]", R.drawable.look_100);
        EMOTION_MAP.put("[差劲]", R.drawable.look_101);
        EMOTION_MAP.put("[弱]", R.drawable.look_102);
        EMOTION_MAP.put("[抱拳]", R.drawable.look_103);
        EMOTION_MAP.put("[棒]", R.drawable.look_104);
        EMOTION_MAP.put("[胜利]", R.drawable.look_105);

        EMOTION_ALL_MAP.putAll(EMOTION_MAP);
        EMOTION_ALL_MAP.put("[删除]", R.drawable.look_106);
    }

    public static Map<String, Integer> getMap(int key) {
        Map<String, Integer> map = null;
        switch (key) {
            case MAP_KEY:
                map = EMOTION_MAP;
                break;
            case ALL_MAP_KEY:
                map = EMOTION_ALL_MAP;
                break;
        }
        return map;
    }

}