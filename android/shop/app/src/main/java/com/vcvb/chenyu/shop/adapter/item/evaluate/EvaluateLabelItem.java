package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaluateGroup;
import com.vcvb.chenyu.shop.javaBean.evaluate.Labels;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

public class EvaluateLabelItem extends BaseItem<EvaluateGroup> {
    public static final int TYPE = R.layout.evaluate_label_item;

    public EvaluateLabelItem(EvaluateGroup bean, Context c) {
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
        FlowLayout flowLayout = holder.get(R.id.label);
        flowLayout.setChildSpacing(8);
        flowLayout.setRowSpacing(8);
        flowLayout.setChildSpacingForLastRow(8);
        flowLayout.removeAllViews();
        Labels labels = (Labels) mData.getObjs().get(position);
        for (int i = 0; i < labels.getLabels().size(); i++) {
            TextView ftv = new TextView(context);
            int id = IdsUtils.generateViewId();
            ftv.setId(id);
            ftv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            ftv.setEllipsize(TextUtils.TruncateAt.END);
            ftv.setGravity(Gravity.CENTER_HORIZONTAL);
            ftv.setLines(1);
            ftv.setMaxEms(8);
            ftv.setText(labels.getLabels().get(i).getLabel_name());
            ftv.setPadding(ToolUtils.dip2px(context, 8), ToolUtils.dip2px(context, 4), ToolUtils
                    .dip2px(context, 8), ToolUtils.dip2px(context, 4));
            flowLayout.addView(ftv);
            if(labels.getLabels().get(i).isIs_select()){
                ftv.setBackgroundResource(R.drawable.shape_6_red);
                ftv.setTextColor(context.getResources().getColor(R.color.white));
            }else{
                ftv.setBackgroundResource(R.drawable.shape_6_gray_d);
                ftv.setTextColor(context.getResources().getColor(R.color.black));
            }
            posMap.put(ftv.getId(), i);
            ftv.setOnClickListener(listener);
        }
    }
}
