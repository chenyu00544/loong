package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;

import java.util.HashMap;
import java.util.Locale;

public class EvaluateStarItem extends BaseItem<HashMap<String, Integer>> {
    public static final int TYPE = R.layout.evaluate_star_item;
    public OnClickListener onClickListener;

    public EvaluateStarItem(HashMap<String, Integer> bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public CYCBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CYCBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(CYCBaseViewHolder holder, int position) {
        ImageView iv1 = holder.get(R.id.imageView137);
        iv1.setOnClickListener(listener);
        ImageView iv2 = holder.get(R.id.imageView138);
        iv2.setOnClickListener(listener);
        ImageView iv3 = holder.get(R.id.imageView139);
        iv3.setOnClickListener(listener);
        ImageView iv4 = holder.get(R.id.imageView140);
        iv4.setOnClickListener(listener);
        ImageView iv5 = holder.get(R.id.imageView141);
        iv5.setOnClickListener(listener);
        ImageView iv6 = holder.get(R.id.imageView142);
        iv6.setOnClickListener(listener);
        ImageView iv7 = holder.get(R.id.imageView143);
        iv7.setOnClickListener(listener);
        ImageView iv8 = holder.get(R.id.imageView144);
        iv8.setOnClickListener(listener);
        ImageView iv9 = holder.get(R.id.imageView145);
        iv9.setOnClickListener(listener);
        ImageView iv10 = holder.get(R.id.imageView146);
        iv10.setOnClickListener(listener);
        ImageView iv11 = holder.get(R.id.imageView147);
        iv11.setOnClickListener(listener);
        ImageView iv12 = holder.get(R.id.imageView148);
        iv12.setOnClickListener(listener);
        ImageView iv13 = holder.get(R.id.imageView149);
        iv13.setOnClickListener(listener);
        ImageView iv14 = holder.get(R.id.imageView150);
        iv14.setOnClickListener(listener);
        ImageView iv15 = holder.get(R.id.imageView151);
        iv15.setOnClickListener(listener);

        TextView tv1 = holder.get(R.id.textView282);
        TextView tv2 = holder.get(R.id.textView283);
        TextView tv3 = holder.get(R.id.textView284);

        TextView ctv1 = holder.get(R.id.textView285);
        TextView ctv2 = holder.get(R.id.textView286);
        TextView ctv3 = holder.get(R.id.textView287);

        String str = "%d.0";
        tv1.setText(String.format(Locale.CANADA, str, mData.get("goods_rank")));
        tv2.setText(String.format(Locale.CANADA, str, mData.get("service_rank")));
        tv3.setText(String.format(Locale.CANADA, str, mData.get("shipping_rank")));

        if (mData.get("goods_rank") == 0) {
            iv1.setImageResource(R.drawable.icon_star);
            iv2.setImageResource(R.drawable.icon_star);
            iv3.setImageResource(R.drawable.icon_star);
            iv4.setImageResource(R.drawable.icon_star);
            iv5.setImageResource(R.drawable.icon_star);
            ctv1.setText(R.string.bad_comment);
        } else if (mData.get("goods_rank") == 1) {
            iv1.setImageResource(R.drawable.icon_star_active);
            iv2.setImageResource(R.drawable.icon_star);
            iv3.setImageResource(R.drawable.icon_star);
            iv4.setImageResource(R.drawable.icon_star);
            iv5.setImageResource(R.drawable.icon_star);
            ctv1.setText(R.string.bad_comment);
        } else if (mData.get("goods_rank") == 2) {
            iv1.setImageResource(R.drawable.icon_star_active);
            iv2.setImageResource(R.drawable.icon_star_active);
            iv3.setImageResource(R.drawable.icon_star);
            iv4.setImageResource(R.drawable.icon_star);
            iv5.setImageResource(R.drawable.icon_star);
            ctv1.setText(R.string.middle_comment);
        } else if (mData.get("goods_rank") == 3) {
            iv1.setImageResource(R.drawable.icon_star_active);
            iv2.setImageResource(R.drawable.icon_star_active);
            iv3.setImageResource(R.drawable.icon_star_active);
            iv4.setImageResource(R.drawable.icon_star);
            iv5.setImageResource(R.drawable.icon_star);
            ctv1.setText(R.string.middle_comment);
        } else if (mData.get("goods_rank") == 4) {
            iv1.setImageResource(R.drawable.icon_star_active);
            iv2.setImageResource(R.drawable.icon_star_active);
            iv3.setImageResource(R.drawable.icon_star_active);
            iv4.setImageResource(R.drawable.icon_star_active);
            iv5.setImageResource(R.drawable.icon_star);
            ctv1.setText(R.string.good_comment);
        } else if (mData.get("goods_rank") == 5) {
            iv1.setImageResource(R.drawable.icon_star_active);
            iv2.setImageResource(R.drawable.icon_star_active);
            iv3.setImageResource(R.drawable.icon_star_active);
            iv4.setImageResource(R.drawable.icon_star_active);
            iv5.setImageResource(R.drawable.icon_star_active);
            ctv1.setText(R.string.good_comment);
        }

        if (mData.get("service_rank") == 0) {
            iv6.setImageResource(R.drawable.icon_star);
            iv7.setImageResource(R.drawable.icon_star);
            iv8.setImageResource(R.drawable.icon_star);
            iv9.setImageResource(R.drawable.icon_star);
            iv10.setImageResource(R.drawable.icon_star);
            ctv2.setText(R.string.bad_comment);
        } else if (mData.get("service_rank") == 1) {
            iv6.setImageResource(R.drawable.icon_star_active);
            iv7.setImageResource(R.drawable.icon_star);
            iv8.setImageResource(R.drawable.icon_star);
            iv9.setImageResource(R.drawable.icon_star);
            iv10.setImageResource(R.drawable.icon_star);
            ctv2.setText(R.string.bad_comment);
        } else if (mData.get("service_rank") == 2) {
            iv6.setImageResource(R.drawable.icon_star_active);
            iv7.setImageResource(R.drawable.icon_star_active);
            iv8.setImageResource(R.drawable.icon_star);
            iv9.setImageResource(R.drawable.icon_star);
            iv10.setImageResource(R.drawable.icon_star);
            ctv2.setText(R.string.middle_comment);
        } else if (mData.get("service_rank") == 3) {
            iv6.setImageResource(R.drawable.icon_star_active);
            iv7.setImageResource(R.drawable.icon_star_active);
            iv8.setImageResource(R.drawable.icon_star_active);
            iv9.setImageResource(R.drawable.icon_star);
            iv10.setImageResource(R.drawable.icon_star);
            ctv2.setText(R.string.middle_comment);
        } else if (mData.get("service_rank") == 4) {
            iv6.setImageResource(R.drawable.icon_star_active);
            iv7.setImageResource(R.drawable.icon_star_active);
            iv8.setImageResource(R.drawable.icon_star_active);
            iv9.setImageResource(R.drawable.icon_star_active);
            iv10.setImageResource(R.drawable.icon_star);
            ctv2.setText(R.string.good_comment);
        } else if (mData.get("service_rank") == 5) {
            iv6.setImageResource(R.drawable.icon_star_active);
            iv7.setImageResource(R.drawable.icon_star_active);
            iv8.setImageResource(R.drawable.icon_star_active);
            iv9.setImageResource(R.drawable.icon_star_active);
            iv10.setImageResource(R.drawable.icon_star_active);
            ctv2.setText(R.string.good_comment);
        }
        if (mData.get("shipping_rank") == 0) {
            iv11.setImageResource(R.drawable.icon_star);
            iv12.setImageResource(R.drawable.icon_star);
            iv13.setImageResource(R.drawable.icon_star);
            iv14.setImageResource(R.drawable.icon_star);
            iv15.setImageResource(R.drawable.icon_star);
            ctv3.setText(R.string.bad_comment);
        } else if (mData.get("shipping_rank") == 1) {
            iv11.setImageResource(R.drawable.icon_star_active);
            iv12.setImageResource(R.drawable.icon_star);
            iv13.setImageResource(R.drawable.icon_star);
            iv14.setImageResource(R.drawable.icon_star);
            iv15.setImageResource(R.drawable.icon_star);
            ctv3.setText(R.string.bad_comment);
        } else if (mData.get("shipping_rank") == 2) {
            iv11.setImageResource(R.drawable.icon_star_active);
            iv12.setImageResource(R.drawable.icon_star_active);
            iv13.setImageResource(R.drawable.icon_star);
            iv14.setImageResource(R.drawable.icon_star);
            iv15.setImageResource(R.drawable.icon_star);
            ctv3.setText(R.string.middle_comment);
        } else if (mData.get("shipping_rank") == 3) {
            iv11.setImageResource(R.drawable.icon_star_active);
            iv12.setImageResource(R.drawable.icon_star_active);
            iv13.setImageResource(R.drawable.icon_star_active);
            iv14.setImageResource(R.drawable.icon_star);
            iv15.setImageResource(R.drawable.icon_star);
            ctv3.setText(R.string.middle_comment);
        } else if (mData.get("shipping_rank") == 4) {
            iv11.setImageResource(R.drawable.icon_star_active);
            iv12.setImageResource(R.drawable.icon_star_active);
            iv13.setImageResource(R.drawable.icon_star_active);
            iv14.setImageResource(R.drawable.icon_star_active);
            iv15.setImageResource(R.drawable.icon_star);
            ctv3.setText(R.string.good_comment);
        } else if (mData.get("shipping_rank") == 5) {
            iv11.setImageResource(R.drawable.icon_star_active);
            iv12.setImageResource(R.drawable.icon_star_active);
            iv13.setImageResource(R.drawable.icon_star_active);
            iv14.setImageResource(R.drawable.icon_star_active);
            iv15.setImageResource(R.drawable.icon_star_active);
            ctv3.setText(R.string.good_comment);
        }
    }

    public interface OnClickListener {
        void onClicked(View view, HashMap<String, Integer> mp);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            HashMap<String, Integer> mp = mData;
            switch (view.getId()) {
                case R.id.imageView137:
                    mp.put("goods_rank", 1);
                    break;
                case R.id.imageView138:
                    mp.put("goods_rank", 2);
                    break;
                case R.id.imageView139:
                    mp.put("goods_rank", 3);
                    break;
                case R.id.imageView140:
                    mp.put("goods_rank", 4);
                    break;
                case R.id.imageView141:
                    mp.put("goods_rank", 5);
                    break;
                case R.id.imageView142:
                    mp.put("service_rank", 1);
                    break;
                case R.id.imageView143:
                    mp.put("service_rank", 2);
                    break;
                case R.id.imageView144:
                    mp.put("service_rank", 3);
                    break;
                case R.id.imageView145:
                    mp.put("service_rank", 4);
                    break;
                case R.id.imageView146:
                    mp.put("service_rank", 5);
                    break;
                case R.id.imageView147:
                    mp.put("shipping_rank", 1);
                    break;
                case R.id.imageView148:
                    mp.put("shipping_rank", 2);
                    break;
                case R.id.imageView149:
                    mp.put("shipping_rank", 3);
                    break;
                case R.id.imageView150:
                    mp.put("shipping_rank", 4);
                    break;
                case R.id.imageView151:
                    mp.put("shipping_rank", 5);
                    break;
            }
            if (onClickListener != null) {
                onClickListener.onClicked(view, mp);
            }
        }
    };
}
