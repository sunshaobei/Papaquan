package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity;

/**
 * Created by sunshaobei on 2017/3/16.
 */

public class DynamciGridAdapter extends BaseAdapter {

    private ArrayList<String> allSelectedPicture;
    private Activity context;
    private GridView gridView;
    private RelativeLayout chooselayout;
    private Uri uri;
    private LayoutInflater layoutInflater;
    private TextView tvsend;

    public DynamciGridAdapter(ArrayList<String> allSelectedPicture, Activity context, GridView gridView, RelativeLayout chooselayout, TextView tvsend) {
        this.tvsend = tvsend;
        this.allSelectedPicture = allSelectedPicture;
        this.context = context;
        this.gridView = gridView;
        this.chooselayout = chooselayout;
        layoutInflater = context.getLayoutInflater();
    }
    public DynamciGridAdapter(ArrayList<String> allSelectedPicture, Activity context, GridView gridView, RelativeLayout chooselayout) {
        this.allSelectedPicture = allSelectedPicture;
        this.context = context;
        this.gridView = gridView;
        this.chooselayout = chooselayout;
        layoutInflater = context.getLayoutInflater();
    }


    @Override
    public int getCount() {
        return allSelectedPicture.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_dynamic_girdview, null);
            holder.image = (ImageView) convertView.findViewById(R.id.item_dynamic_image);
            holder.btn = (ImageView) convertView.findViewById(R.id.item_dynagrid_delete);
            holder.addpic = (LinearLayout) convertView.findViewById(R.id.sm_ll_addpic);
            holder.tv = (TextView) convertView.findViewById(R.id.addpic);
            holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == allSelectedPicture.size()) {
            holder.addpic.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.GONE);
            holder.btn.setVisibility(View.GONE);
            holder.tv.setText(position + "/" + 9);
            holder.addpic.setOnClickListener(v -> selectClick());
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
                holder.addpic.setVisibility(View.GONE);
            }
        } else if (allSelectedPicture.size() == 0) {
            gridView.setVisibility(View.GONE);
            chooselayout.setVisibility(View.VISIBLE);
        } else {
            holder.addpic.setVisibility(View.GONE);
            holder.image.setVisibility(View.VISIBLE);
            holder.btn.setVisibility(View.VISIBLE);
            ImageLoading.common(context, "file://" + allSelectedPicture.get(position), holder.image, R.drawable.white);
            holder.image.setOnClickListener(v -> {
                uri = Uri.parse("file://" + allSelectedPicture.get(position));
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setDataAndType(uri, "image/jpeg");
                context.startActivity(intent1);
            });
            holder.btn.setOnClickListener(v -> {
                //点击后移除图片
                allSelectedPicture.remove(position);
                //更新UI
                notifyDataSetChanged();
                if (allSelectedPicture.size() == 0) {
                    if (tvsend!=null)
                    {
                        tvsend.setTextColor(Color.parseColor("#ffffff"));
                        tvsend.setBackground(new ColorDrawable(Color.parseColor("#e60012")));
                    }
                    gridView.setVisibility(View.GONE);
                    chooselayout.setVisibility(View.VISIBLE);
                }
            });

        }
        return convertView;
    }

    public class ViewHolder {
        public ImageView image;
        public ImageView btn;
        public LinearLayout addpic;
        public TextView tv;
    }

    private void selectClick() {
        Intent intent = new Intent();
        intent.setClass(context, SelectPictureActivity.class);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("allSelectedPicture", allSelectedPicture);
        intent.putExtras(bundle);

        if (allSelectedPicture.size() < 9) {
            context.startActivityForResult(intent, IntentCode.RequestCode.TOPIC);
        }
    }
}
