package com.vcvb.chenyu.shop.adapter.horizontallistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.overrideView.RoundImageView;

public class HorizontalListViewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ViewHolder vh = new ViewHolder();
    private Context mContext;

    public HorizontalListViewAdapter(Context context) {
        this.mContext = context;
//        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    private static class ViewHolder {
        private TextView title;
        private TextView info;
        private RoundImageView im;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.goods_evaluate_item, null);
            vh.im = (RoundImageView) convertView.findViewById(R.id.imageView3);
            vh.info = (TextView) convertView.findViewById(R.id.textView5);
            vh.title = (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(vh);
            System.out.println(position);
            final int pos = position;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(pos);
                }
            });
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.info.setText("00:00");
        vh.title.setText("XXXXXX");
        return convertView;
    }

}
