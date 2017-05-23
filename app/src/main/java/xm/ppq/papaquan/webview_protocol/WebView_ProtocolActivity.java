package xm.ppq.papaquan.webview_protocol;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.Image;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by EdgeDi on 13:59.
 */

public class WebView_ProtocolActivity extends BaseActivity {

    /**
     * 红卡协议
     */
    private String Red_Html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\">\n" +
            "\t\t<meta name=\"format-detection\" content=\"telephone=no,email=no,adress=no\">\n" +
            "\t\t<title>红卡使用协议</title>\n" +
            "\t\t<style>\n" +
            "\t\t\t\thtml{color:#333;}\n" +
            "body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,p,blockquote,th,td,strong{padding:0;margin:0;font-family:Arial,'Microsoft Yahei';}\n" +
            "/*table{border-collapse:collapse;border-spacing:0;}*/\n" +
            "fieldset,img{border:0;}\n" +
            "a{text-decoration:none;color:#555;  outline:none;-webkit-tap-highlight-color:transparent;}/*此处待添加默认链接颜色*/\n" +
            "var,em,strong,i{font-style:normal;}\n" +
            "address,caption,cite,code,dfn,em,strong,th,var, optgroup{font-style:inherit;font-weight:inherit;}\n" +
            "del,ins{text-decoration:none;}\n" +
            "li{list-style:none;}\n" +
            "caption,th{text-align:left;}\n" +
            "h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal;}\n" +
            "q:before,q:after{content:'';}\n" +
            "abbr,acronym{border:0;font-variant:normal;}\n" +
            "sup{vertical-align:baseline;}\n" +
            "sub{vertical-align:baseline;}\n" +
            "legend{color:#000;}\n" +
            "input,button,textarea,select,optgroup,option{ font-size:inherit;font-style:inherit;font-weight:inherit;}\n" +
            "input,button,textarea,select{*font-size:100%;font: 14px / 1.5 \"微软雅黑\";}\n" +
            ".clearfix:after {content:\"\\200B\"; display:block; height:0; clear:both; }\n" +
            ".clearfix { *zoom:1; }\n" +
            "input[type=\"button\"], input[type=\"submit\"], input[type=\"reset\"] {-webkit-appearance: none;}\n" +
            "textarea{-webkit-appearance:none;resize: none;}\n" +
            "i{font-style: normal;font-weight: normal; cursor: pointer;}\n" +
            "html,body{\n" +
            "\tmax-width: 640px;\n" +
            "\tmargin: 0 auto;\n" +
            "\t-webkit-tap-highlight-color: rgba(255, 255, 255, 0);\n" +
            "\tfont: 14px / 1.5 \"微软雅黑\";\n" +
            "\t-webkit-overflow-scrolling: touch;\n" +
            "}\n" +
            "\t\t\t.registerhead1 {\n" +
            "\t\t\t\tposition: relative;\n" +
            "\t\t\t\theight: 45px;\n" +
            "\t\t\t\tline-height: 45px;\n" +
            "\t\t\t\tfont-size: 16px;\n" +
            "\t\t\t}\n" +
            "\t\t\tstrong{\n" +
            "\t\t\t\tfont-weight: bold;\n" +
            "\t\t\t}\n" +
            "\t\t\t.registerhead1 .back {\n" +
            "\t\t\t\tfont-size: 26px;\n" +
            "\t\t\t\tleft: 2%;\n" +
            "\t\t\t}\n" +
            "\t\t\t.bgf {\n" +
            "\t\t\t\tpadding: 0 12px;\n" +
            "\t\t\t\ttext-align: justify;\n" +
            "\t\t\t\tline-height: 23px;\n" +
            "\t\t\t}\n" +
            "\t\t</style>\n" +
            "\t</head>\n" +
            "\n" +
            "\t<body class=\"bgc\">\n" +
            "\t\n" +
            "\t\t<div class=\"bgf\">\n" +
            "\t\t\t<br />\n" +
            "\t\t\t<p>\n" +
            "    <span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"></span>\n" +
            "</p>\n" +
            "<p style=\"margin-bottom: 7px\">\n" +
            "    <strong><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span></strong><strong><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡</span></span></strong><strong><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">会员</span></span></strong><strong><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">使用协议</span></span></strong>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">在接受本协议之前，请您仔细阅读本协议的全部内容。如果您不同意本协议的任意内容，或者无法准确理解本协议任何条款的含义，请不要进行后续操作。</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">1、您所购买的是</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红卡会员</span></span><span style=\"font-family: 宋体;font-size: 16px\">(五折卡</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">与</span>VIP</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">升级版</span>)。有效期</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">：年卡会员</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">为</span>365天</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">；月卡会员为</span>30天；周卡会员为7天</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">，购卡</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">激活后</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">当日即生效。</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">2、</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红卡</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">是</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">啪啪圈本地社交生活平台为用户</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">精心打造</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">的具有各类优质特权（社交特权、抢购特权、</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">吃喝玩乐</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">、衣食住行优惠折扣特权等）</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">的电子虚拟卡。</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">2.1 持卡可以在签约商家享受每个商家</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">给出</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">的优惠</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">折扣</span></span><span style=\"font-family: 宋体;font-size: 16px\">(具体优惠形式以商家公布的优惠条款为准)；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">2.2 </span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">定位本地，</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">可以通过</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">啪啪圈</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">客户端或</span></span><span style=\"font-family: 宋体;font-size: 16px\">WAP端</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">购买激活</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">2.3 可以通过</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">啪啪圈本地服务商指定的地点</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">购买</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红卡</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">激活码</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">，然后进入平台刮卡激活</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">2.4 </span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡及</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">会员</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">激活码一经购买，不可退换；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">2.5 此卡激活后内置于</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">啪啪圈</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">手机客户端，方便携带；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">2.6 此卡购买激活后即生效可使用，不可挂失注销；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">3、使用</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡：</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">3.1 指定日期持卡前往</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡签约商家消费；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">3.2 结帐前向服务员出示</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡中的优惠码</span>(数字码或条形码)；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">3.3 服务员根据优惠条款结算应付金额后支付买单(</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">线上只完成核销，消费金额与商家直接结算</span></span><span style=\"font-family: 宋体;font-size: 16px\">)；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">3.4 使用</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡优惠后的价格是如何计算的？</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">计算公式：应付金额</span>=[(总价-非优惠项目价格)x优惠折扣]+非优惠项目价格；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">3.6 每张</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡在一个商家一天内只能使用一次；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">3.7 商家优惠条款可查看</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡中该商家的服务详情</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">3.8 社交、抢购等红卡会员享受优惠特权以平台展示详情为准；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">4、不可使用</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡享受优惠的情况：</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">4.1 优惠指定日期和时间段之外的时间；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">4.2 优惠指定日期已经在同一商家使用过</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">4.3 享受商家其他优惠时(如顾客自带酒水、商家自行推出的其他优惠活动如消费满xx元送xx、特价或其他形式的优惠活动，此时不可再重复下个享受</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡优惠</span>)；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">4.4 遇重大节假日(平安夜、圣诞节、2-14情人节、春节假期</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">等</span></span><span style=\"font-family: 宋体;font-size: 16px\">)；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">4.5 商家停业、装修等不可抗因素期间</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">；</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px;margin-left: 48px\">\n" +
            "    <span style=\"font-family: Symbol;font-size: 13px\">·&nbsp;</span><span style=\"font-family: 宋体;font-size: 16px\">4.6 抢购产品中未设置红卡特权的产品；</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">5、持卡人在签约商家消费时，具体优惠形式以</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡服务详情中的描述为准，请于消费结账前出示</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡中的优惠码。</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">6、每个商家接待能力不同并遵循先到先服务的排队原则，为保证您愉快的消费体验，建议消费前致电商户咨询排队情况，本卡不保证您在优惠日一定可以在商户处得到服务。</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">7、本地红卡特权为啪啪圈平台所在区县服务商经营和服务，使用红卡过程中有任何问题可直接向当地服务商进行反馈或投诉。</span>\n" +
            "</p>\n" +
            "<p style=\"margin-top: 7px;margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">8</span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">、</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">啪啪圈</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">在法律许可范围内对本协议及</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">红</span></span><span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">卡享有最终解释权。</span></span>\n" +
            "</p>\n" +
            "<p style=\"margin-bottom: 7px\">\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\"><span style=\"font-family:宋体\">全国免费服务热线：</span></span><span style=\"font-family: 宋体;font-size: 16px\">400-1613-880</span>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <span style=\"font-family: 宋体;font-size: 16px\">&nbsp;</span>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"></span><br/>\n" +
            "</p>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "\n" +
            "</html>";
    /**
     * 注册协议
     */
    private String Enroll_Html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\">\n" +
            "        <meta name=\"format-detection\" content=\"telephone=no,email=no,adress=no\">\n" +
            "        <title>用户协议</title>\n" +
            "        <link rel=\"stylesheet\" type=\"text/css\" href=\"/static/index/css/reset.css\" />\n" +
            "        <style>\n" +
            "        \t.registerhead1{\n" +
            "        \t\tposition: relative;\n" +
            "        \t\theight: 45px;\n" +
            "        \t\tline-height: 45px;\n" +
            "        \t\tfont-size: 16px;\n" +
            "        \t}\n" +
            "        \t.registerhead1 .back{\n" +
            "        \t\tfont-size: 26px;\n" +
            "        \t\tleft: 2%;\n" +
            "        \t}\n" +
            "        \th1{\n" +
            "\t        \tpadding: 8px 15px;\n" +
            "\t    \t\tfont-size: 18px;\n" +
            "        \t}\n" +
            "        \tsection{\n" +
            "        \t\tfont-size: 16px;\n" +
            "\t\t\t    line-height: 23px;\n" +
            "\t\t\t    padding: 5px 12px;\n" +
            "\t\t\t    text-align: justify;\n" +
            "        \t}\n" +
            "        \t.mentlist{\n" +
            "        \t\t    padding: 12px 10px;\n" +
            "\t\t\t\t    line-height: 28px;\n" +
            "\t\t\t\t    font-size: 14px;\n" +
            "\t\t\t\t    text-align: justify;\n" +
            "        \t}\n" +
            "        \t.mentlist h4{\n" +
            "        \t\tfont-size: 15px;\n" +
            "        \t}\n" +
            "        </style>\n" +
            "\t</head>\n" +
            "\t<body class=\"bgc\">\n" +
            "\t\t<div class=\"bgf\">\n" +
            "\t\t\t<h1>啪啪圈用户协议</h1>\n" +
            "\t\t\t<section>本《用户协议》是您(下称\"用户\")与厦门啪啪圈科技有限公司(下称“啪啪圈”)之间在使用啪啪圈出品的各类产品之前，注册用户(又名“帐号”，下统称“帐号”)和使用产品时签署的协议。</section>\n" +
            "\t\t\t<div class=\"mentlist\">\n" +
            "\t\t\t\t<h4>一、重要须知---在签署本协议之前，啪啪圈正式提醒用户：</h4>\n" +
            "　　\n" +
            "1.1 您应认真阅读(未成年人应当在监护人陪同下阅读)、充分理解本《用户协议》中各条款，特别是免除或者限制啪啪圈责任的免责条款，用户的权利限制条款，约定争议解决方式、司法管辖、法律适用的条款。</br>\n" +
            "　　\n" +
            "1.2 除非您接受本协议，否则用户无权也无必要继续接受啪啪圈的服务，可以退出本次注册。用户点击接受并继续使用啪啪圈的服务，视为用户已完全的接受本协议。</br>\n" +
            "　　\n" +
            "1.3 本协议在您开始使用啪啪圈的服务，并注册成为啪啪圈产品的用户时即产生法律效力，请您慎重考虑是否接受本协议，如不接受本协议的任一条款，请自动退出并不再接受啪啪圈的任何服务。</br>\n" +
            "　　\n" +
            "1.4 在您签署本协议之后，此文本可能因国家政策、产品以及履行本协议的环境发生变化而进行修改，我们会将修改后的协议发布在本网站上，若您对修改后的协议有异议的，请立即停止登录、使用啪啪圈产品及服务，若您登录或继续使用啪啪圈产品，视为认可修改后的协议。</br>\n" +
            "　　\n" +
            "<h4>二、关于“帐号”</h4>\n" +
            "　　\n" +
            "2.1啪啪圈在旗下业务平台（包括但不限于啪啪圈官网papaquan.com、啪啪圈app……等）提供用户注册通道，用户在认可并接受本协议之后，有权选择未被其他用户使用过的字母符号组合作为用户的帐号，并自行设置符合安全要求的密码。用户设置的帐号、密码是用户用以登录啪啪圈产品，接受啪啪圈服务的凭证。</br>\n" +
            "　　\n" +
            "2.2 用户在注册了啪啪圈帐号并不意味获得全部啪啪圈产品的授权，仅仅是取得了接受啪啪圈服务的身份，用户在登录相关网页、加载应用、下载安装软件时需要另行签署单个产品的授权协议。</br>\n" +
            "　　\n" +
            "2.3啪啪圈账户仅限于在啪啪圈网站上注册用户本人使用，禁止赠与、借用、租用、转让或售卖。如果啪啪圈发现或者有理由怀疑使用者并非帐号初始注册人，则有权在未经通知的情况下，暂停或终止向用户提供服务，并有权注销该帐号，而无需向该帐号使用人承担法律责任，由此带来的包括并不限于用户通讯中断、用户资料和信息等清空等损失由用户自行承担。</br>\n" +
            "　　\n" +
            "2.4 用户有责任维护个人帐号、密码的安全性与保密性，用户就其帐号及密码项下之一切活动负全部责任，包括用户数据的修改，发表的言论以及其他所有的损失。用户应重视啪啪圈帐号密码保护。用户如发现他人未经许可使用其帐号或发生其他任何安全漏洞问题时立即通知啪啪圈。如果用户在使用啪啪圈服务时违反上述规则而产生任何损失或损害，啪啪圈不承担任何责任。</br>\n" +
            "　　\n" +
            "2.5 用户帐号在丢失或遗忘密码后，可遵照啪啪圈的申诉途径及时申诉请求找回帐号。用户应提供能增加帐号安全性的个人密码保护资料。用户可以凭初始注册资料及个人密码保护资料填写申诉单向啪啪圈申请找回帐号，啪啪圈的密码找回机制仅负责识别申诉单上所填资料与系统记录资料的正确性，而无法识别申诉人是否系真正帐号有权使用人。对用户因被他人冒名申诉而致的任何损失，啪啪圈不承担任何责任，用户知晓帐号及密码保管责任在于用户，啪啪圈并无义务保证帐号丢失或遗忘密码后用户一定能通过申诉找回帐号。</br>\n" +
            "　　\n" +
            "2.6 用户应保证注册啪啪圈帐号时填写的身份信息的真实性，任何由于非法、不真实、不准确的用户信息所产生的责任由用户承担。用户应不断根据实际情况更新注册资料，符合及时、详尽、真实、准确的要求。所有原始键入的资料将引用用户的帐号注册资料。如果因用户的注册信息不真实而引起的问题，以及对问题发生所带来的后果，啪啪圈不负任何责任。如果用户提供的信息不准确、不真实、不合法或者啪啪圈有理由怀疑为错误、不实或不完整的资料或在个人资料中发布广告、不严肃内容及无关信息，啪啪圈有权暂停或终止向用户提供服务，注销该帐号，并拒绝用户现在和未来使用啪啪圈服务之全部或任何部分。因此产生的一切损失由用户自行承担。</br>\n" +
            "　　\n" +
            "2.7 除自行注册啪啪圈帐号外，用户未来也可选择使用已有的新浪微博、腾讯微博、QQ空间、人人网、开心网的帐号登录和使用啪啪圈的产品。用户可通过啪啪圈产品的帐号管理后台授权并登录啪啪圈的产品。</br>\n" +
            "　　\n" +
            "<h4>三、用户在使用啪啪圈时，应当遵守《中华人民共和国宪法》、《中华人民共和国刑法》、《中华人民共和国民法通则》、《中华人民共和国合同法》、《中华人民共和国著作权法》、《中华人民共和国电信条例》、《互联网信息服务管理办法》、《互联网电子公告服务管理规定》、《计算机信息网络国际互联网安全保护管理办法》等相关规定。用户不得利用啪啪圈服务产品从事违反法律法规、政策以及侵犯他人合法权益的行为，包括但不限于下列行为：</h4>\n" +
            "　　\n" +
            "3.1 利用啪啪圈服务产品发表、传送、传播、储存反对宪法所确定的基本原则的、危害国家安全、国家统一、社会稳定的、煽动民族仇恨、民族歧视、破坏民族团结的内容，或侮辱诽谤、色情、暴力、引起他人不安及任何违反国家法律法规政策的内容或者设置含有上述内容的网名、角色名。</br>\n" +
            "　　\n" +
            "3.2 利用啪啪圈服务发表、传送、传播、储存侵害他人知识产权、商业机密、肖像权、隐私权等合法权利或其他道德上令人反感的内容。</br>\n" +
            "　　\n" +
            "3.3 进行任何危害计算机网络安全的行为，包括但不限于：使用未经许可的数据或进入未经许可的服务器/帐户;未经允许进入公众计算机网络或者他人计算机系统并删除、修改、增加存储信息;未经许可，企图探查、扫描、测试本“软件”系统或网络的弱点或其它实施破坏网络安全的行为;企图干涉、破坏本“软件”系统或网站的正常运行，故意传播恶意程序或病毒以及其他破坏干扰正常网络信息服务的行为;伪造TCP/IP数据包名称或部分名称;自行或利用其他软件对啪啪圈提供产品进行反向破解等违法行为。</br>\n" +
            "　　\n" +
            "3.4 进行任何诸如发布广告、推广信息、销售商品的行为，或者进行任何非法的侵害啪啪圈利益的行为。</br>\n" +
            "　　\n" +
            "3.5 进行其他任何违法以及侵犯其他个人、公司、社会团体、组织的合法权益的行为或者法律、行政法规、规章、条例以及任何具有法律效力之规范所限制或禁止的其他行为。</br>\n" +
            "　　\n" +
            "在任何情况下，如果啪啪圈有理由认为用户的任何行为，包括但不限于用户的任何言论和其它行为违反或可能违反法律法规、国家政策以及本协议的任何规定，啪啪圈可在任何时候不经任何事先通知，即有权终止向用户提供服务。\n" +
            "　　\n" +
            "<h4>四、啪啪圈声明</h4>\n" +
            "　　\n" +
            "4.1 用户须知，在使用啪啪圈服务可能存在有来自任何他人的包括威胁性的、诽谤性的、令人反感的或非法的内容或行为或对他人权利的侵犯(包括知识产权)的匿名或冒名的信息的风险，用户须承担以上风险，啪啪圈对服务不作担保，不论是明确的或隐含的，包括所有有关信息真实性、适当性、适于某一特定用途、所有权和非侵权性的默示担保和条件，对因此导致任何因用户不正当或非法使用服务产生的直接、间接、偶然、特殊及后续的损害，啪啪圈不承担任何责任。</br>\n" +
            "　　\n" +
            "4.2 使用啪啪圈服务必须遵守国家有关法律和政策等，维护国家利益，保护国家安全，并遵守本协议，对于用户违法行为或违反本协议的使用(包括但不限于言论发表、传送等)而引起的一切责任，由用户承担全部责任。</br>\n" +
            "　　\n" +
            "4.3啪啪圈提供的所有信息、资讯、内容和服务均来自互联网，并不代表啪啪圈科技的观点，啪啪圈对其真实性、合法性概不负责，亦不承担任何法律责任。</br>\n" +
            "　　\n" +
            "4.4啪啪圈所提供的产品和服务也属于互联网范畴，也易受到各种安全问题的困扰，包括但不限于：</br>\n" +
            "　　\n" +
            "1)个人资料被不法分子利用，造成现实生活中的骚扰;</br>\n" +
            "　　\n" +
            "2)哄骗、破译密码;</br>\n" +
            "　　\n" +
            "3)下载安装的其它软件中含有“特洛伊木马”等病毒程序，威胁到个人计算机上信息和数据的安全，继而威胁对本服务的使用。 　</br>\n" +
            "　 　　\n" +
            "4）以及其他类网络安全困扰问题</br>\n" +
            "　　\n" +
            "对于发生上述情况的，用户应当自行承担责任。</br>\n" +
            "　　\n" +
            "4.5 用户须明白，啪啪圈为了整体运营的需要，有权在公告通知后，在不事先通知用户的情况下修改、中断、中止或终止服务，而无须向用户或第三方负责，啪啪圈不承担任何赔偿责任。</br>\n" +
            "　　\n" +
            "4.6 用户应理解，互联网技术存在不稳定性，可能导致政府管制、政策限制、病毒入侵、黑客攻击、服务器系统崩溃或者其他现今技术无法解决的风险发生。由以上原因可能导致啪啪圈科技服务中断或帐号信息损失，对此非人为因素引起的用户损失由用户自行承担责任。</br>\n" +
            "　　\n" +
            "<h4>五、知识产权</h4>\n" +
            "　　\n" +
            "5.1啪啪圈对其旗下运营的网页、应用、软件等产品和服务享有知识产权。受中国法律的保护。</br>\n" +
            "　　\n" +
            "5.2用户不得对啪啪圈服务涉及的相关网页、应用、软件等产品进行反向工程、反向汇编、反向编译等。</br>\n" +
            "　　\n" +
            "5.3 用户只能在本《用户协议》以及相应的授权许可协议授权的范围内使用啪啪圈知识产权，未经授权超范围使用的，构成对字节跳动的侵权。</br>\n" +
            "　　\n" +
            "5.4 用户在使用啪啪圈产品服务时发表上传的文字、图片、视频、软件以及表演等信息，用户的发表、上传行为是对啪啪圈服务平台的授权，为非独占性、永久性的授权，该授权可转授权。啪啪圈可将前述信息在啪啪圈科技旗下的所有服务平台上使用，可再次编辑后使用，也可以由啪啪圈授权给合作方使用。</br>\n" +
            "　　\n" +
            "5.5 用户应保证，在使用啪啪圈产品服务时上传的文字、图片、视频、软件以及表演等的信息不侵犯任何第三方知识产权，包括但不限于商标权、著作权等。若用户在使用啪啪圈产品服务时上传的文字、图片、视频、软件以及表演等的信息中侵犯第三方知识产权，啪啪圈有权移除该侵权产品，并对此不负任何责任。用户应当负责处理前述第三方的权利主张，承担由此产生的全部费用，包括但不限于侵权赔偿、律师费及其他合理费用，并保证啪啪圈不会因此而遭受任何损失。</br>\n" +
            "　　\n" +
            "5.6 任何单位或个人认为通过啪啪圈提供服务的内容可能涉嫌侵犯其知识产权或信息网络传播权，应该及时向啪啪圈提出书面权利通知投诉，并提供身份证明、权属证明及详细侵权情况证明。啪啪圈在收到上述法律文件后，将会依法尽快断开相关链接内容。啪啪圈提供投诉通道，全国服务热线400-1613-880。如投诉中未向啪啪圈科技提供合法有效的证明材料，啪啪圈有权不采取任何措施。</br>\n" +
            "　　\n" +
            "<h4>六、隐私保护</h4>\n" +
            "　　\n" +
            "啪啪圈非常重视用户的隐私权，用户在享受啪啪圈提供的服务时可能涉及用户的隐私，因此请用户仔细阅读本隐私保护条款。</br>\n" +
            "　　\n" +
            "6.1 请用户注意勿在使用啪啪圈服务中透露自己的各类财产帐户、银行卡、信用卡、第三方支付账户及对应密码等重要信息资料，否则由此带来的任何损失由用户自行承担。</br>\n" +
            "　　\n" +
            "6.2 用户的帐号、密码属于保密信息，啪啪圈会努力采取积极的措施保护用户帐号、密码的安全。</br>\n" +
            "　　\n" +
            "6.3 互联网的开放性以及技术更新速度快，因非啪啪圈可控制的因素导致用户信息泄漏的，啪啪圈不承担任何责任。</br>\n" +
            "　　\n" +
            "6.4 用户在使用啪啪圈服务时不应将自认为隐私的信息发表、上传至啪啪圈，也不应将该等信息通过啪啪圈的服务传播给其他人，由于用户的行为引起的隐私泄漏，由用户自行承担责任。</br>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "</html>\n";
    /**
     * 服务使用协议
     */
    private String Service_Html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"UTF-8\">\n" +
            "\t\t<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\">\n" +
            "\t\t<meta name=\"format-detection\" content=\"telephone=no,email=no,adress=no\">\n" +
            "\t\t<title>服务使用协议</title>\n" +
            "\t\t<style>\n" +
            "\t\t\t\thtml{color:#333;}\n" +
            "body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,p,blockquote,th,td,strong{padding:0;margin:0;font-family:Arial,'Microsoft Yahei';}\n" +
            "/*table{border-collapse:collapse;border-spacing:0;}*/\n" +
            "fieldset,img{border:0;}\n" +
            "a{text-decoration:none;color:#555;  outline:none;-webkit-tap-highlight-color:transparent;}/*此处待添加默认链接颜色*/\n" +
            "var,em,strong,i{font-style:normal;}\n" +
            "address,caption,cite,code,dfn,em,strong,th,var, optgroup{font-style:inherit;font-weight:inherit;}\n" +
            "del,ins{text-decoration:none;}\n" +
            "li{list-style:none;}\n" +
            "caption,th{text-align:left;}\n" +
            "h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal;}\n" +
            "q:before,q:after{content:'';}\n" +
            "abbr,acronym{border:0;font-variant:normal;}\n" +
            "sup{vertical-align:baseline;}\n" +
            "sub{vertical-align:baseline;}\n" +
            "legend{color:#000;}\n" +
            "input,button,textarea,select,optgroup,option{ font-size:inherit;font-style:inherit;font-weight:inherit;}\n" +
            "input,button,textarea,select{*font-size:100%;font: 14px / 1.5 \"微软雅黑\";}\n" +
            ".clearfix:after {content:\"\\200B\"; display:block; height:0; clear:both; }\n" +
            ".clearfix { *zoom:1; }\n" +
            "input[type=\"button\"], input[type=\"submit\"], input[type=\"reset\"] {-webkit-appearance: none;}\n" +
            "textarea{-webkit-appearance:none;resize: none;}\n" +
            "i{font-style: normal;font-weight: normal; cursor: pointer;}\n" +
            "html,body{\n" +
            "\tmax-width: 640px;\n" +
            "\tmargin: 0 auto;\n" +
            "\t-webkit-tap-highlight-color: rgba(255, 255, 255, 0);\n" +
            "\tfont: 14px / 1.5 \"微软雅黑\";\n" +
            "\t-webkit-overflow-scrolling: touch;\n" +
            "}\n" +
            "\t\t\t.registerhead1 {\n" +
            "\t\t\t\tposition: relative;\n" +
            "\t\t\t\theight: 45px;\n" +
            "\t\t\t\tline-height: 45px;\n" +
            "\t\t\t\tfont-size: 16px;\n" +
            "\t\t\t}\n" +
            "\t\t\t\n" +
            "\t\t\t.registerhead1 .back {\n" +
            "\t\t\t\tfont-size: 26px;\n" +
            "\t\t\t\tleft: 2%;\n" +
            "\t\t\t}\n" +
            "\t\t\t.bgf {\n" +
            "\t\t\t\tpadding: 0 12px;\n" +
            "\t\t\t\ttext-align: justify;\n" +
            "\t\t\t\tline-height: 23px;\n" +
            "\t\t\t}\n" +
            "\t\t</style>\n" +
            "\t</head>\n" +
            "\n" +
            "\t<body class=\"bgc\">\n" +
            "\t\t<div class=\"bgf\">\n" +
            "\t\t\t<br />\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<strong><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 18px;\"><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">啪啪圈平台行为规范：</span></span></strong>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">一</span>. <span style=\"font-size: 14px; font-family: 宋体;\">原则</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">1<span style=\"font-size: 14px; font-family: 宋体;\">、为维护啪啪圈平台健康生态秩序，更好地保障用户合法权益及良好的用户体验，厦门啪啪圈科技有限公司（以下简称“啪啪圈科技”）根据现行法律法规及《啪啪圈用户协议》，制定《啪啪圈平台行为规范》。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">2<span style=\"font-size: 14px; font-family: 宋体;\">、啪啪圈用户在啪啪圈平台的活动须遵守现行法律法规。啪啪圈科技将按照相关法律法规及用户协议规则，对违规行为进行相应的处理。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">3<span style=\"font-size: 14px; font-family: 宋体;\">、若你对本规范的理解和执行有任何疑惑或争议，可告知我们，我们将根据有关规则予以解释或处理。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">二、使用规范</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（一）聊天及资料内容行为规范</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>1<span style=\"font-size: 14px; font-family: 宋体;\">）发布色情及色情擦边内容的行为。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•散布淫秽、色情内容，包括但不限于招嫖、约炮等内容。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布带有色情暗示、挑逗性的音频、文字、动图、外部网站链接和二维码等内容的。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">违反以上行为的，将禁用帐号的部分功能，限制与其他人的互动注。屡犯者，视情节轻重，可能冻结帐号，不能再使用啪啪圈。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（注：与陌生人交流的功能包括但不限于：给陌生人打招呼，评论陌生人的动态，创建、加入群组，禁止发言、禁止打招呼、禁止评论他人留言、禁止使用话题、禁止加入聊天室、禁止加入和创建群组等限制部分使用功能的处罚，情节特别严重的还将受到帐号封禁直至注销的处罚。）</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>2<span style=\"font-size: 14px; font-family: 宋体;\">）在平台上发布任何广告相关信息的行为。例如：微商、招聘、信用卡、贷款、化妆品、数码产品、淘宝店铺、整容整形等广告。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">违反以上行为的，将删除资料广告信息。屡犯者，视情节轻重，禁用帐号的部分功能。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>3<span style=\"font-size: 14px; font-family: 宋体;\">）威胁、污蔑诽谤以及辱骂他人的行为。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>4<span style=\"font-size: 14px; font-family: 宋体;\">）盗用他人资料的行为。包括但不限于以下盗用行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•擅自发布、使用他人已经登记注册的企业名称或商标，侵犯他人企业名称专用权及商标专用权。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•擅自发布、使用他人名称、照片、身份证号码，侵害他人名誉权、肖像权等合法权利。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>5<span style=\"font-size: 14px; font-family: 宋体;\">）冒充啪啪圈官方帐号的行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布、使用、恶搞啪啪圈<span style=\"font-size: 14px; font-family: Calibri;\">LOGO</span><span style=\"font-size: 14px; font-family: 宋体;\">等已有知识产权内容相同、相似的字样，或者容易与目前已有啪啪圈产品设计主题、外观等相混淆的内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•恶意中伤、侮辱、调侃啪啪圈科技以及相关产品和技术。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上行为（</span>3<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">5</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">），将禁用帐号的部分功能，限制与其他人的互动注。屡犯者，视情节轻重，可能冻结帐号，不能再使用啪啪圈。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>6<span style=\"font-size: 14px; font-family: 宋体;\">）违反国家现行法律法规的行为。包括但不限于以下行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•反政治活动：反对宪法所确定的基本原则、危害国家安全、颠覆国家政权、煽动民族仇恨、宣扬邪教和封建迷信、非法集会等。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•买卖毒品、买卖假币、买卖枪支弹药以及管制刀具、买卖违禁药物、买卖假证等。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•以骗取钱财为目的的虚假社交行为，诈骗话费或产生非正常高消费行为（话费托、酒托、饭托等）</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布的信息含有聚众赌博、传授赌博（千术）技巧等内容。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•雇佣、引诱他人从事恐怖、暴力等活动</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>7<span style=\"font-size: 14px; font-family: 宋体;\">）发布血腥、暴力、恐怖、恶心等会令人感到不适内容的行为。包括但不限于以下内容：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•散播人或动物被杀、致残以及枪击、刺伤、拷打等受伤情形的真实画面。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•含有出现描绘暴力或虐待儿童等内容。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布替人复仇、收账、炫耀黑社会身份等具有黑社会性质的信息。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>8<span style=\"font-size: 14px; font-family: 宋体;\">）发布钓鱼网站链接、含木马病毒的网站链接和二维码等可能会对用户造成信息安全影响的内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>9<span style=\"font-size: 14px; font-family: 宋体;\">）使用外挂及其他恶意行为。包括但不限于以下行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•未经啪啪圈科技书面许可使用插件、外挂或其他第三方工具、服务接入啪啪圈以及相关系统。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•使用外挂、人工进行刷注册、刷曝光、刷关注、刷赞、刷招呼、刷动态、刷评论等影响啪啪圈正常运营的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•恶意攻击啪啪圈科技及相关系统的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•恶意收集啪啪圈系统或其他啪啪圈用户的个人信息的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•制作、发布与以上行为相关的方法、工具，或对此类方法、工具进行运营或传播的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>10<span style=\"font-size: 14px; font-family: 宋体;\">）利用红包进行赌博诈骗。包括但不限于以下行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">• 抽成：抽取群组红包手续费形式的赌博行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">• 红包返利：承诺收到红包后会多倍返还的欺诈行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">• 抢红包外挂：售卖“抢红包外挂”趁机诈骗钱财行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>11<span style=\"font-size: 14px; font-family: 宋体;\">）在平台上发布关于伪基站和改号软件的行为。包括但不限于以下行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">• 利用改号软件冒充他人诈骗的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">• 宣传伪基站<span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件的行为，如：介绍、宣传关于伪基站</span><span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件的信息；展示伪基站</span><span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件的使用效果；劝说他人使用伪基站</span><span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件等。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">• 售卖伪基站<span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件的行为，如：发布出售伪基站设备</span><span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件的信息；发布求购伪基站设备</span><span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件的信息；询问或发布伪基站设备</span><span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">改号软件的售卖途径等。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上行为（</span>6<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">11</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">），将禁用帐号的部分功能，限制与其他人的互动注。屡犯者，视情节轻重，可能冻结帐号，不能再使用啪啪圈。同时可能会将相关帐号移交司法机关。 </span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">若在使用中发现他人帐号存在违法信息，可拨打举报电话：</span>400-1613-880<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">进行反馈，核实后我们会及时处理。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（二）啪啪圈附近动态行为规范</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>1<span style=\"font-size: 14px; font-family: 宋体;\">）发布色情及色情擦边内容的行为。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•散布淫秽、色情内容，包括但不限于招嫖、约炮、色情图片等内容。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布带有色情暗示、挑逗和引起他人色情感受的音频、文字、动图、外部网站链接、二维码等内容的。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">违反以上行为的，将删除对应动态，禁用帐号的部分功能，限制与其他人的互动注。屡犯者，视情节轻重，可能冻结帐号，不能再使用啪啪圈。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（注：与陌生人交流的功能包括但不限于：给陌生人打招呼，评论陌生人的动态，创建、加入群组，禁止发言、禁止打招呼、禁止评论他人留言、禁止使用话题、禁止加入聊天室、禁止加入和创建群组等限制部分使用功能的处罚，情节特别严重的还将受到帐号封禁直至注销的处罚。）</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>2<span style=\"font-size: 14px; font-family: 宋体;\">）在平台上发布任何广告相关信息的行为，包括图片和文字。例如：赚钱的找我、加我有福利、进群有福利。具体形式包括但不限于：金融类（微商、信用卡、贷款、保险），医院类（化妆品、整容整形、成人用品），物品兜售（数码产品、淘宝店铺、），其他（招聘、各类兼职）等广告。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>3<span style=\"font-size: 14px; font-family: 宋体;\">）威胁、污蔑诽谤以及辱骂他人的行为。例如，使用他人图片，并在文字中进行攻击、指名道姓的辱骂（包括了姓名、家乡、头像、用户</span><span style=\"font-size: 14px; font-family: Calibri;\">ID</span><span style=\"font-size: 14px; font-family: 宋体;\">、手机号、联系方式等）。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上行为（</span>2<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">3</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）的，将删除对应动态。屡犯者，视情节轻重，禁用帐号的部分功能。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>4<span style=\"font-size: 14px; font-family: 宋体;\">）盗用他人资料的行为。包括但不限于以下盗用行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•擅自发布、使用他人已经登记注册的企业名称或商标，侵犯他人企业名称专用权及商标专用权。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•擅自发布、使用他人名称、照片、身份证号码，侵害他人名誉权、肖像权等合法权利。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>5<span style=\"font-size: 14px; font-family: 宋体;\">）冒充啪啪圈官方帐号的行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布、使用、恶搞啪啪圈<span style=\"font-size: 14px; font-family: Calibri;\">LOGO</span><span style=\"font-size: 14px; font-family: 宋体;\">等已有知识产权内容相同、相似的字样，或者容易与目前已有啪啪圈产品设计主题、外观等相混淆的内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•恶意中伤、侮辱、调侃啪啪圈科技以及相关产品和技术。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上行为（</span>4<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">5</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）的，将删除对应动态，禁用帐号的部分功能，限制与其他人的互动注。屡犯者，视情节轻重，可能冻结帐号，不能再使用啪啪圈。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>6<span style=\"font-size: 14px; font-family: 宋体;\">）违反国家现行法律法规的行为。包括但不限于以下行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•反政治活动：反对宪法所确定的基本原则、危害国家安全、颠覆国家政权、煽动民族仇恨、宣扬邪教和封建迷信、非法集会等。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•买卖毒品、野生保护动物、买卖假币、买卖枪支弹药以及管制刀具、买卖违禁药物、买卖假证等。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布代孕信息、传销信息、私彩信息等。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•以骗取钱财为目的的虚假社交行为，诈骗话费或产生非正常高消费行为。（话费托、酒托、饭托等）</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布的信息含有聚众赌博、传授赌博（千术）技巧等内容。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•雇佣、引诱他人从事恐怖、暴力等活动。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>7<span style=\"font-size: 14px; font-family: 宋体;\">）发布血腥、暴力、恐怖、恶心等会令人感到不适内容的行为。包括但不限于以下内容：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•散播人或动物被杀、致残以及枪击、刺伤、拷打等受伤情形的真实画面。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•含有出现描绘暴力或虐待儿童等内容。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•发布替人复仇、收账、炫耀黑社会身份等具有黑社会性质的信息。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>8<span style=\"font-size: 14px; font-family: 宋体;\">）发布钓鱼网站链接、含木马病毒的网站链接和二维码等可能会对用户造成信息安全影响的内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>9<span style=\"font-size: 14px; font-family: 宋体;\">）使用外挂及其他恶意行为。包括但不限于以下行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•未经啪啪圈科技书面许可使用插件、外挂或其他第三方工具、服务接入啪啪圈以及相关系统。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•使用外挂、人工进行刷注册、刷曝光、刷关注、刷赞、刷招呼、刷动态、刷评论等影响啪啪圈正常运营的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•恶意攻击啪啪圈科技及相关系统的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•恶意收集啪啪圈系统或其他啪啪圈用户的个人信息的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•制作、发布与以上行为相关的方法、工具，或对此类方法、工具进行运营或传播的行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>10<span style=\"font-size: 14px; font-family: 宋体;\">）利用红包进行赌博诈骗。包括但不限于以下行为：</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•抽成：抽取群组红包手续费形式的赌博行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•红包返利：承诺收到红包后会多倍返还的欺诈行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">•抢红包外挂：售卖“抢红包外挂”趁机诈骗钱财行为。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上（</span>6<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">10</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）行为的，将删除对应动态，禁用帐号的部分功能，限制与其他人的互动注。屡犯者，视情节轻重，可能冻结帐号，不能再使用啪啪圈。同时可能会将相关帐号移交司法机关。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（三）啪啪圈群组行为规范</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>1<span style=\"font-size: 14px; font-family: 宋体;\">）群组内发布招嫖、色情话术、性爱描述、性奴、约炮内容等违规色情信息，传播淫秽。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>2<span style=\"font-size: 14px; font-family: 宋体;\">）群组内发布色情（图片、音频、视频、链接、网站、二维码等）内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>3<span style=\"font-size: 14px; font-family: 宋体;\">）利用群组红包功能进行组织赌博、开发赌博游戏、参与赌博游戏（百家乐、六合彩、牛牛模式、时时彩、地雷模式、红包接龙等）内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>4<span style=\"font-size: 14px; font-family: 宋体;\">）群组内（讨论、研究、买卖、收售等）关于六合彩、百家乐、时时彩等违法彩票或私彩的内容，或者买卖千术器具，例如透明麻将、看牌眼睛、捕鱼机、老虎机等与赌博相关的器具信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>5<span style=\"font-size: 14px; font-family: 宋体;\">）群组内发布（讨论、批判、同情、宣传、恶搞等）任何涉及政治敏感事件、煽动民族仇恨、民族歧视、破坏国家宗教政策、宣扬邪教、发布煽动非法集会、结社、游行的内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>6<span style=\"font-size: 14px; font-family: 宋体;\">）群组内发布或讨论任何关于政治敏感信息、国家领导人的不实事件、恶搞内容及恶意信息等。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>7<span style=\"font-size: 14px; font-family: 宋体;\">）群组内发送有关于毒品的（工具、器皿、吸毒邀请、贩卖、运输、组织）等任何相关信息，例如买卖、吸食和宣传毒品的相关信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>8<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有内容明确涉及买卖（枪支、弹药、管制刀具、弓弩、冷兵器、气枪、仿真枪）等违法信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>9<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有内容涉及买卖象牙、熊掌、虎皮、犀牛角等野生保护动物相关的信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>10<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有涉黑内容，发布自己是杀手，教唆他人从事恐怖、暴力等犯罪活动的信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>11<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有涉及制售假币内容，明确发布售卖假币的信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>12<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有涉及代孕类的信息，发布招人代孕、捐卵、捐精、重金求子等相对比较明确的信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>13<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有涉及传销类的信息，发布含有入会、缴会费、拉人头赚钱等传销特点的信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>14<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有涉及制售假证的信息，发布、售卖或代办学历证、毕业证、驾驶证、身份证、学生证、军官证、军残证等必须本人办理或者考取的证件的信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>15<span style=\"font-size: 14px; font-family: 宋体;\">）群组内有信息涉及售卖违禁药物的，例如发布售卖迷奸水、乖乖水、听话水、催情水、金苍蝇等迷药类的信息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上行为（</span>1<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">15</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）的，群组可能会被解散，并且相关人员会被禁用帐号的部分功能，限制与其他人的互动注。屡犯者，视情节轻重，可能冻结帐号，不能再使用啪啪圈。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（注：与陌生人交流的功能包括但不限于：给陌生人打招呼，评论陌生人的动态，创建、加入群组，禁止发言、禁止打招呼、禁止评论他人留言、禁止使用话题、禁止加入聊天室、禁止加入和创建群组等限制部分使用功能的处罚，情节特别严重的还将受到帐号封禁直至注销的处罚。）</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（四）群成员禁止的违规行为</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>1<span style=\"font-size: 14px; font-family: 宋体;\">）群成员在群组内发布任何违反国家法律规定的信息（包括但不限于：淫秽色情、赌博诈骗、毒品枪支、其他违法买卖及违规广告等）。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 宋体;\">违反以上行为的，将禁用帐号的部分功能，限制与其他人的互动。视情节严重，屡犯者，可能会被冻结账号，届时将不能使用啪啪圈。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>2<span style=\"font-size: 14px; font-family: 宋体;\">）群成员在群内发布带有色情暗示、挑逗性的音频、文字、动图、外部网站链接和二维码等的内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">违反以上行为的，视情节严重，可能会被踢出群组，限制与其他人的互动。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>3<span style=\"font-size: 14px; font-family: 宋体;\">）群成员在群组内发布广告宣传、售卖等内容。包含医院类、金融相关、物品兜售类，以及各类文字、图片资料中某一项包含价格</span><span style=\"font-size: 14px; font-family: Calibri;\">/</span><span style=\"font-size: 14px; font-family: 宋体;\">联系方式。</span></span><span style=\"font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>4<span style=\"font-size: 14px; font-family: 宋体;\">）群成员在群组内发布了恐怖、血腥、恶心、自杀等引起他人不适的图片或辱骂歧视内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<br/>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上违规行为（</span>3<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">4</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">），将可能收到群主或者管理员的禁言处罚，届时将不能发送消息。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">（五）群视频行为规范：</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">严重违反啪啪圈群视频行为规范，涉及违规内容传播的行为，将会受到严厉的惩罚。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>1<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，内容出现关于政治敏感信息、国家领导人的不实事件、恶搞内容及恶意信息等。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>2<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，内容涉及违法国家法律法规的行为。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\"><br/></span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>3<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，有涉及毒品相关物品，讲解毒品制作过程等一切与毒品相关的内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>4<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，出现管制刀具、枪支弹药用做道具表演、买卖、宣传。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<br/>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>5<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，出现非正常博彩类：六合彩、赌马马票、网络直播百家乐等。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">违反以上违规行为（</span>1<span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">~</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">（</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: Calibri;\">5</span><span style=\"color: rgb(255, 0, 0); font-size: 14px; font-family: 宋体;\">）的，将可能受到永久冻结帐号处罚，群组将会被解散。出现严重直播违规行为，还有可能受到帐号功能受限的处罚，出现以下行为，帐号将会受到封禁限制的处罚。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>6<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，出现赌博相关内容，相关场景，赌博机器，（老虎机、捕鱼机）等。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>7<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中涉及黄色视频买卖、色情网盘买卖、将会给予功能受限处理。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>8<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中使用性爱相关用品或圆柱形自慰道具作为表演道具。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>9<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中出现色情互动内容，或极其暴露的直播表演。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>10<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，出现国外限制级影片，或成人视频内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>11<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，出现低俗游戏，性爱模仿，动作低俗等内容。</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span style=\"font-size: 14px; font-family: 宋体;\">（</span>12<span style=\"font-size: 14px; font-family: 宋体;\">）群视频过程中，出现打架斗殴、直播殴打、自残、砍人等内容</span></span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp;</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">三、动态文档</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<span style=\"font-size: 14px; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">本规范为动态文档，我们有权根据相关法律法规或产品运营的需要对其内容进行修改并更新，请您反复查看以便获得最新信息。本规范作为用户协议的补充，如果有冲突的地方以用户协议为准。</span>\n" +
            "\t\t\t</p>\n" +
            "\t\t\t<p>\n" +
            "\t\t\t\t<br/>\n" +
            "\t\t\t</p>\n" +
            "\t\t</div>\n" +
            "\t</body>\n" +
            "\n" +
            "</html>";

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.left_image)
    ImageView left_image;
    @BindView(R.id.center_text)
    TextView center_text;

    private int type;

    @Override
    protected int getLayout() {
        return R.layout.activity_web_view_protocol;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", -1);
        left_image.setImageResource(R.drawable.white_finish);
        if (type == 0) {//红卡
            center_text.setText("红卡使用协议");
            webView.loadDataWithBaseURL(null, Red_Html, "text/html", "UTF-8", null);
        } else if (type == 1) {//服务
            center_text.setText("啪啪圈服务使用协议");
            webView.loadDataWithBaseURL(null, Service_Html, "text/html", "UTF-8", null);
        } else if (type == 2) {//注册
            center_text.setText("啪啪圈用户协议");
            webView.loadDataWithBaseURL(null, Enroll_Html, "text/html", "UTF-8", null);
        }
    }

    @Override
    protected void setListener() {
        left_image.setOnClickListener(v -> finish());
    }
}
