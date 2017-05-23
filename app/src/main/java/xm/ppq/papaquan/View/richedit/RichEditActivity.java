package xm.ppq.papaquan.View.richedit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.app.SunActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.richeditor.RichEditor;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.ShopIntrodutionData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity;

import static xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity.INTENT_MAX_NUM;


public class RichEditActivity extends SunActivity implements ALi_ossInterface {

    private String sid;
    private ALioss aLioss;
    private SharedPreferencesPotting my_login;


    @BindView(R.id.action_redo)
    ImageButton action_redo;
    @BindView(R.id.action_bold)
    ImageButton action_bold;
    @BindView(R.id.action_italic)
    ImageButton action_italic;
    @BindView(R.id.action_strikethrough)
    ImageButton action_strikethrough;
    @BindView(R.id.action_underline)
    ImageButton action_underline;
    @BindView(R.id.action_align_left)
    ImageButton action_align_left;
    @BindView(R.id.action_align_center)
    ImageButton action_align_center;
    @BindView(R.id.action_align_right)
    ImageButton action_align_right;
    @BindView(R.id.action_insert_bullets)
    ImageButton action_insert_bullets;
    @BindView(R.id.action_insert_numbers)
    ImageButton action_insert_numbers;
    @BindView(R.id.upcontent)
    TextView upcontent;
    @BindView(R.id.editor)
    RichEditor mEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richedit);
        ButterKnife.bind(this);
        my_login = new SharedPreferencesPotting(this, "my_login");
        sid = getIntent().getStringExtra("sid");
        initStatusBar((LinearLayout) findViewById(R.id.bar));
        aLioss = new ALioss(this);
        initlistener();
        getData();
    }

    private void getData() {
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETINTRODUCTION, "{\"sid\":" + sid + "}", new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {
                if (JsonUtil.getString(o,"code").equals("0")) {
                    ShopIntrodutionData data = (ShopIntrodutionData) JsonUtil.fromJson(o, ShopIntrodutionData.class);
                    runOnUiThread(() -> {
                        mEditor.setHtml(data.getData().getContent());
                    });
                }
        }
        });
    }


    //提交简介
    public void upCoontent(String content)
    {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid",sid)
                    .put("uid",my_login.getItemInt("uid"))
                    .put("token",my_login.getItemString("token"))
                    .put("tokentype",1)
                    .put("content",content);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.EDITINTRODUCTION, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o,"code").equals("0"))
                    {
                        ShortToast("修改成功");
                        finish();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





    private  boolean isbold = false;
    private  boolean isitalic = false;
    private boolean isstrike = false;
    private boolean isunderline = false;
    private boolean isalileft = false;
    private boolean isaligncenter = false;
    private boolean isalignright = false;
    private boolean isbullets = false;
    private boolean isnumbers = false;


    private void initlistener() {

        upcontent.setOnClickListener(v->{
            upCoontent(mEditor.getHtml());
        });

        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("编辑商户简介");
        //mEditor.setInputEnabled(false);

        mEditor.setOnTextChangeListener(text -> {

        });

        findViewById(R.id.action_undo).setOnClickListener(v -> mEditor.undo());

        action_redo.setOnClickListener(v -> mEditor.redo());




        action_bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });

        action_italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(v -> mEditor.setSubscript());
        findViewById(R.id.action_subscript).setVisibility(View.GONE);

        findViewById(R.id.action_superscript).setOnClickListener(v -> mEditor.setSuperscript());
        findViewById(R.id.action_superscript).setVisibility(View.GONE);

        action_strikethrough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        action_underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(v -> mEditor.setHeading(1));
        findViewById(R.id.action_heading1).setVisibility(View.GONE);

        findViewById(R.id.action_heading2).setOnClickListener(v -> mEditor.setHeading(2));
        findViewById(R.id.action_heading2).setVisibility(View.GONE);

        findViewById(R.id.action_heading3).setOnClickListener(v -> mEditor.setHeading(3));
        findViewById(R.id.action_heading3).setVisibility(View.GONE);

        findViewById(R.id.action_heading4).setOnClickListener(v -> mEditor.setHeading(4));
        findViewById(R.id.action_heading4).setVisibility(View.GONE);

        findViewById(R.id.action_heading5).setOnClickListener(v -> mEditor.setHeading(5));
        findViewById(R.id.action_heading5).setVisibility(View.GONE);

        findViewById(R.id.action_heading6).setOnClickListener(v -> mEditor.setHeading(6));
        findViewById(R.id.action_heading6).setVisibility(View.GONE);

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override
            public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });
//        findViewById(R.id.action_txt_color).setVisibility(View.GONE);

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override
            public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });
        findViewById(R.id.action_bg_color).setVisibility(View.GONE);

        findViewById(R.id.action_indent).setOnClickListener(v -> mEditor.setIndent());
        findViewById(R.id.action_indent).setVisibility(View.GONE);

        findViewById(R.id.action_outdent).setOnClickListener(v -> mEditor.setOutdent());
        findViewById(R.id.action_outdent).setVisibility(View.GONE);

        action_align_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        action_align_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        action_align_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_blockquote).setOnClickListener(v -> mEditor.setBlockquote());
        findViewById(R.id.action_blockquote).setVisibility(View.GONE);

        action_insert_bullets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBullets();
            }
        });

        action_insert_numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setNumbers();
            }
        });


        //TODO 添加图片
        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RichEditActivity.this, SelectPictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("allSelectedPicture", allSelectedPicture);
                intent.putExtra(INTENT_MAX_NUM,9);
                intent.putExtras(bundle);
                startActivityForResult(intent, IntentCode.RequestCode.TOPIC);
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(v -> mEditor.insertLink("https://github.com/wasabeef", "wasabeef"));
        findViewById(R.id.action_insert_link).setVisibility(View.GONE);


        findViewById(R.id.action_insert_checkbox).setOnClickListener(v -> mEditor.insertTodo());
    }

    private ArrayList<String> allSelectedPicture = new ArrayList<>();
    private ArrayList<String> selectedPicture = new ArrayList<>();

    public void finish(View v) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentCode.RequestCode.TOPIC) {
            selectedPicture = (ArrayList) data.getSerializableExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
            if (selectedPicture.size() != 0) {
                for (String str : selectedPicture) {
                    if (!allSelectedPicture.contains(str)) {
                        allSelectedPicture.add(str);
                    }
                    if (allSelectedPicture.size()>0)
                    {
                        String s = OwnUtil.compressImage(allSelectedPicture.get(0), allSelectedPicture.get(0) + "compress", 30);
                        aLioss.init(s, "pid.jpg", 1);
                        allSelectedPicture.clear();
                    }
                    if (allSelectedPicture.size() > 0) {
                        String s = OwnUtil.compressImage(allSelectedPicture.get(0), allSelectedPicture.get(0) + "compress", 30);
                        aLioss.init(s, "pid.jpg", 1);
                        allSelectedPicture.clear();
                    }
                }
                if (allSelectedPicture.size()>0)
                {
                    new Thread(()->{
                        for (int i = 0; i < allSelectedPicture.size(); i++) {
                            String s = OwnUtil.compressImage(allSelectedPicture.get(i), allSelectedPicture.get(i) + "compress", 30);
                            aLioss.init(s, "pid"+i+".jpg", 1);
                        }
                    }).start();
                }
            }
        }
    }


    private int i ;
    @Override
    public void upImageSuccess(String url) {
        i++;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEditor.insertImage(BaseUrl.BITMAP+url+"/200x200",url);
            }
        });
        if (i==allSelectedPicture.size()){
            selectedPicture.clear();
            allSelectedPicture.clear();
        }
//
////        mEditor.insertImage(BaseUrl.BITMAP+url,url);
//        mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
//                        "dachshund");
////        mEditor.insertImage(BaseUrl.BITMAP+url,url);
//        mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
//                "dachshund");
    }

    @Override
    public void upImageError(String s) {

    }

    @Override
    public void upVideoSuccess(String s) {

    }

    @Override
    public void upProgress(int progress) {

    }

    @Override
    public void setVideoPic(String url) {

    }
}
