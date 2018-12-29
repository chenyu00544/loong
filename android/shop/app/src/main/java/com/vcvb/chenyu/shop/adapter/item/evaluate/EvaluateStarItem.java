package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaluateGroup;
import com.vcvb.chenyu.shop.javaBean.evaluate.Stars;

import java.util.Locale;

public class EvaluateStarItem extends BaseItem<EvaluateGroup> {
    public static final int TYPE = R.layout.evaluate_star_item;

    public EvaluateStarItem(EvaluateGroup bean, Context c) {
        super(bean, c);
    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(TYPE, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int groupPosition, int position) {
        ImageView iv1 = holder.get(R.id.imageView137);
        groupMap.put(iv1.getId(), groupPosition);
        posMap.put(iv1.getId(), 1);
        iv1.setOnClickListener(listener);
        ImageView iv2 = holder.get(R.id.imageView138);
        groupMap.put(iv2.getId(), groupPosition);
        posMap.put(iv2.getId(), 2);
        iv2.setOnClickListener(listener);
        ImageView iv3 = holder.get(R.id.imageView139);
        groupMap.put(iv3.getId(), groupPosition);
        posMap.put(iv3.getId(), 3);
        iv3.setOnClickListener(listener);
        ImageView iv4 = holder.get(R.id.imageView140);
        groupMap.put(iv4.getId(), groupPosition);
        posMap.put(iv4.getId(), 4);
        iv4.setOnClickListener(listener);
        ImageView iv5 = holder.get(R.id.imageView141);
        groupMap.put(iv5.getId(), groupPosition);
        posMap.put(iv5.getId(), 5);
        iv5.setOnClickListener(listener);
        ImageView iv6 = holder.get(R.id.imageView142);
        groupMap.put(iv6.getId(), groupPosition);
        posMap.put(iv6.getId(), 6);
        iv6.setOnClickListener(listener);
        ImageView iv7 = holder.get(R.id.imageView143);
        groupMap.put(iv7.getId(), groupPosition);
        posMap.put(iv7.getId(), 7);
        iv7.setOnClickListener(listener);
        ImageView iv8 = holder.get(R.id.imageView144);
        groupMap.put(iv8.getId(), groupPosition);
        posMap.put(iv8.getId(), 8);
        iv8.setOnClickListener(listener);
        ImageView iv9 = holder.get(R.id.imageView145);
        groupMap.put(iv9.getId(), groupPosition);
        posMap.put(iv9.getId(), 9);
        iv9.setOnClickListener(listener);
        ImageView iv10 = holder.get(R.id.imageView146);
        groupMap.put(iv10.getId(), groupPosition);
        posMap.put(iv10.getId(), 10);
        iv10.setOnClickListener(listener);
        ImageView iv11 = holder.get(R.id.imageView147);
        groupMap.put(iv11.getId(), groupPosition);
        posMap.put(iv11.getId(), 11);
        iv11.setOnClickListener(listener);
        ImageView iv12 = holder.get(R.id.imageView148);
        groupMap.put(iv12.getId(), groupPosition);
        posMap.put(iv12.getId(), 12);
        iv12.setOnClickListener(listener);
        ImageView iv13 = holder.get(R.id.imageView149);
        groupMap.put(iv13.getId(), groupPosition);
        posMap.put(iv13.getId(), 13);
        iv13.setOnClickListener(listener);
        ImageView iv14 = holder.get(R.id.imageView150);
        groupMap.put(iv14.getId(), groupPosition);
        posMap.put(iv14.getId(), 14);
        iv14.setOnClickListener(listener);
        ImageView iv15 = holder.get(R.id.imageView151);
        groupMap.put(iv15.getId(), groupPosition);
        posMap.put(iv15.getId(), 15);
        iv15.setOnClickListener(listener);

        TextView tv1 = holder.get(R.id.textView282);
        TextView tv2 = holder.get(R.id.textView283);
        TextView tv3 = holder.get(R.id.textView284);

        TextView ctv1 = holder.get(R.id.textView285);
        TextView ctv2 = holder.get(R.id.textView286);
        TextView ctv3 = holder.get(R.id.textView287);

        String str = "%d.0";
        Stars stars = (Stars) mData.getObjs().get(position);
        for (int i = 0; i < stars.getStars().size(); i++) {
            switch (i) {
                case 0:
                    tv1.setText(String.format(Locale.CANADA, str, stars.getStars().get(i)
                            .getValue()));
                    if (stars.getStars().get(i).getValue() == 0) {
                        iv1.setImageResource(R.drawable.icon_star);
                        iv2.setImageResource(R.drawable.icon_star);
                        iv3.setImageResource(R.drawable.icon_star);
                        iv4.setImageResource(R.drawable.icon_star);
                        iv5.setImageResource(R.drawable.icon_star);
                        ctv1.setText(R.string.bad_comment);
                    } else if (stars.getStars().get(i).getValue() == 1) {
                        iv1.setImageResource(R.drawable.icon_star_active);
                        iv2.setImageResource(R.drawable.icon_star);
                        iv3.setImageResource(R.drawable.icon_star);
                        iv4.setImageResource(R.drawable.icon_star);
                        iv5.setImageResource(R.drawable.icon_star);
                        ctv1.setText(R.string.bad_comment);
                    } else if (stars.getStars().get(i).getValue() == 2) {
                        iv1.setImageResource(R.drawable.icon_star_active);
                        iv2.setImageResource(R.drawable.icon_star_active);
                        iv3.setImageResource(R.drawable.icon_star);
                        iv4.setImageResource(R.drawable.icon_star);
                        iv5.setImageResource(R.drawable.icon_star);
                        ctv1.setText(R.string.middle_comment);
                    } else if (stars.getStars().get(i).getValue() == 3) {
                        iv1.setImageResource(R.drawable.icon_star_active);
                        iv2.setImageResource(R.drawable.icon_star_active);
                        iv3.setImageResource(R.drawable.icon_star_active);
                        iv4.setImageResource(R.drawable.icon_star);
                        iv5.setImageResource(R.drawable.icon_star);
                        ctv1.setText(R.string.middle_comment);
                    } else if (stars.getStars().get(i).getValue() == 4) {
                        iv1.setImageResource(R.drawable.icon_star_active);
                        iv2.setImageResource(R.drawable.icon_star_active);
                        iv3.setImageResource(R.drawable.icon_star_active);
                        iv4.setImageResource(R.drawable.icon_star_active);
                        iv5.setImageResource(R.drawable.icon_star);
                        ctv1.setText(R.string.good_comment);
                    } else if (stars.getStars().get(i).getValue() == 5) {
                        iv1.setImageResource(R.drawable.icon_star_active);
                        iv2.setImageResource(R.drawable.icon_star_active);
                        iv3.setImageResource(R.drawable.icon_star_active);
                        iv4.setImageResource(R.drawable.icon_star_active);
                        iv5.setImageResource(R.drawable.icon_star_active);
                        ctv1.setText(R.string.good_comment);
                    }
                    break;
                case 1:
                    tv2.setText(String.format(Locale.CANADA, str, stars.getStars().get(i)
                            .getValue()));
                    if (stars.getStars().get(i).getValue() == 0) {
                        iv6.setImageResource(R.drawable.icon_star);
                        iv7.setImageResource(R.drawable.icon_star);
                        iv8.setImageResource(R.drawable.icon_star);
                        iv9.setImageResource(R.drawable.icon_star);
                        iv10.setImageResource(R.drawable.icon_star);
                        ctv2.setText(R.string.bad_comment);
                    } else if (stars.getStars().get(i).getValue() == 1) {
                        iv6.setImageResource(R.drawable.icon_star_active);
                        iv7.setImageResource(R.drawable.icon_star);
                        iv8.setImageResource(R.drawable.icon_star);
                        iv9.setImageResource(R.drawable.icon_star);
                        iv10.setImageResource(R.drawable.icon_star);
                        ctv2.setText(R.string.bad_comment);
                    } else if (stars.getStars().get(i).getValue() == 2) {
                        iv6.setImageResource(R.drawable.icon_star_active);
                        iv7.setImageResource(R.drawable.icon_star_active);
                        iv8.setImageResource(R.drawable.icon_star);
                        iv9.setImageResource(R.drawable.icon_star);
                        iv10.setImageResource(R.drawable.icon_star);
                        ctv2.setText(R.string.middle_comment);
                    } else if (stars.getStars().get(i).getValue() == 3) {
                        iv6.setImageResource(R.drawable.icon_star_active);
                        iv7.setImageResource(R.drawable.icon_star_active);
                        iv8.setImageResource(R.drawable.icon_star_active);
                        iv9.setImageResource(R.drawable.icon_star);
                        iv10.setImageResource(R.drawable.icon_star);
                        ctv2.setText(R.string.middle_comment);
                    } else if (stars.getStars().get(i).getValue() == 4) {
                        iv6.setImageResource(R.drawable.icon_star_active);
                        iv7.setImageResource(R.drawable.icon_star_active);
                        iv8.setImageResource(R.drawable.icon_star_active);
                        iv9.setImageResource(R.drawable.icon_star_active);
                        iv10.setImageResource(R.drawable.icon_star);
                        ctv2.setText(R.string.good_comment);
                    } else if (stars.getStars().get(i).getValue() == 5) {
                        iv6.setImageResource(R.drawable.icon_star_active);
                        iv7.setImageResource(R.drawable.icon_star_active);
                        iv8.setImageResource(R.drawable.icon_star_active);
                        iv9.setImageResource(R.drawable.icon_star_active);
                        iv10.setImageResource(R.drawable.icon_star_active);
                        ctv2.setText(R.string.good_comment);
                    }
                    break;
                case 2:
                    tv3.setText(String.format(Locale.CANADA, str, stars.getStars().get(i)
                            .getValue()));
                    if (stars.getStars().get(i).getValue() == 0) {
                        iv11.setImageResource(R.drawable.icon_star);
                        iv12.setImageResource(R.drawable.icon_star);
                        iv13.setImageResource(R.drawable.icon_star);
                        iv14.setImageResource(R.drawable.icon_star);
                        iv15.setImageResource(R.drawable.icon_star);
                        ctv3.setText(R.string.bad_comment);
                    } else if (stars.getStars().get(i).getValue() == 1) {
                        iv11.setImageResource(R.drawable.icon_star_active);
                        iv12.setImageResource(R.drawable.icon_star);
                        iv13.setImageResource(R.drawable.icon_star);
                        iv14.setImageResource(R.drawable.icon_star);
                        iv15.setImageResource(R.drawable.icon_star);
                        ctv3.setText(R.string.bad_comment);
                    } else if (stars.getStars().get(i).getValue() == 2) {
                        iv11.setImageResource(R.drawable.icon_star_active);
                        iv12.setImageResource(R.drawable.icon_star_active);
                        iv13.setImageResource(R.drawable.icon_star);
                        iv14.setImageResource(R.drawable.icon_star);
                        iv15.setImageResource(R.drawable.icon_star);
                        ctv3.setText(R.string.middle_comment);
                    } else if (stars.getStars().get(i).getValue() == 3) {
                        iv11.setImageResource(R.drawable.icon_star_active);
                        iv12.setImageResource(R.drawable.icon_star_active);
                        iv13.setImageResource(R.drawable.icon_star_active);
                        iv14.setImageResource(R.drawable.icon_star);
                        iv15.setImageResource(R.drawable.icon_star);
                        ctv3.setText(R.string.middle_comment);
                    } else if (stars.getStars().get(i).getValue() == 4) {
                        iv11.setImageResource(R.drawable.icon_star_active);
                        iv12.setImageResource(R.drawable.icon_star_active);
                        iv13.setImageResource(R.drawable.icon_star_active);
                        iv14.setImageResource(R.drawable.icon_star_active);
                        iv15.setImageResource(R.drawable.icon_star);
                        ctv3.setText(R.string.good_comment);
                    } else if (stars.getStars().get(i).getValue() == 5) {
                        iv11.setImageResource(R.drawable.icon_star_active);
                        iv12.setImageResource(R.drawable.icon_star_active);
                        iv13.setImageResource(R.drawable.icon_star_active);
                        iv14.setImageResource(R.drawable.icon_star_active);
                        iv15.setImageResource(R.drawable.icon_star_active);
                        ctv3.setText(R.string.good_comment);
                    }
                    break;
            }
        }
    }
}
