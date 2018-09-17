package com.vcvb.chenyu.shop.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.javaBean.goods.GoodsAttrs;
import com.vcvb.chenyu.shop.tools.IdsUtils;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("ValidFragment")
public class GoodsAttrDialog extends DialogFragment {
    Window window;
    View v;
    Context context;
    String tag;
    OnClickListener onClickListener;
    HashMap<String, Object> outAttr = new HashMap<>();
    List<GoodsAttrs> attrs;
    public TextView buy;
    public TextView add;

    @SuppressLint("ValidFragment")
    public GoodsAttrDialog(ArrayList<GoodsAttrs> gattrs) {
        attrs = gattrs;
        for (int i = 0; i < gattrs.size(); i++) {
            outAttr.put(i + "", gattrs.get(i).getAttrs().get(0).getAttrId());
            attrs.get(i).getAttrs().get(0).setIsSelect(true);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        outAttr.put("num", 1);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dialog_goods_attr, container);
        window = getDialog().getWindow();
        ConstraintLayout cly = (ConstraintLayout) v;
        ConstraintSet set = new ConstraintSet();
        set.clone(cly);
        buy = v.findViewById(R.id.textView177);
        buy.setOnClickListener(listener);
        add = v.findViewById(R.id.textView178);
        add.setOnClickListener(listener);
        if (tag == "Buy") {
            set.constrainHeight(buy.getId(), ToolUtils.dip2px(context, 50));
            set.constrainHeight(add.getId(), 0);
        } else if (tag == "AddCart") {
            set.constrainHeight(buy.getId(), 0);
            set.constrainHeight(add.getId(), ToolUtils.dip2px(context, 50));
        }

        ImageView numAdd = v.findViewById(R.id.imageView85);
        numAdd.setOnClickListener(listener1);
        ImageView numSub = v.findViewById(R.id.imageView84);
        numSub.setOnClickListener(listener1);

        FlowLayout fl1 = v.findViewById(R.id.flowLayout);
        fl1.removeAllViews();
        fl1.setChildSpacing(16);
        fl1.setRowSpacing(16);
        fl1.setChildSpacingForLastRow(16);
        FlowLayout fl2 = v.findViewById(R.id.flowLayout1);
        fl2.removeAllViews();
        fl2.setChildSpacing(16);
        fl2.setRowSpacing(16);
        fl2.setChildSpacingForLastRow(16);
        FlowLayout fl3 = v.findViewById(R.id.flowLayout2);
        fl3.removeAllViews();
        fl3.setChildSpacing(16);
        fl3.setRowSpacing(16);
        fl3.setChildSpacingForLastRow(16);
        FlowLayout fl4 = v.findViewById(R.id.flowLayout3);
        fl4.removeAllViews();
        fl4.setChildSpacing(16);
        fl4.setRowSpacing(16);
        fl4.setChildSpacingForLastRow(16);
        for (int i = 0; i < attrs.size(); i++) {
            switch (i) {
                case 0:
                    TextView tv1 = v.findViewById(R.id.textView179);
                    tv1.setText(attrs.get(i).getAttrName());
                    break;
                case 1:
                    TextView tv2 = v.findViewById(R.id.textView182);
                    tv2.setText(attrs.get(i).getAttrName());
                    break;
                case 2:
                    TextView tv3 = v.findViewById(R.id.textView183);
                    tv3.setText(attrs.get(i).getAttrName());
                    break;
                case 3:
                    TextView tv4 = v.findViewById(R.id.textView184);
                    tv4.setText(attrs.get(i).getAttrName());
                    break;
            }
            for (int j = 0; j < attrs.get(i).getAttrs().size(); j++) {
                TextView tv1 = new TextView(context);
                tv1.setTag(attrs.get(i).getAttrs().get(j).getAttrId());
                tv1.setText(attrs.get(i).getAttrs().get(j).getAttrName());
                int id = IdsUtils.generateViewId();
                attrs.get(i).getAttrs().get(j).setButtonId(id);
                tv1.setId(id);
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv1.setEllipsize(TextUtils.TruncateAt.END);
                tv1.setGravity(Gravity.CENTER_HORIZONTAL);
                tv1.setLines(1);
                tv1.setMaxEms(8);
                tv1.setPadding(ToolUtils.dip2px(context, 8), ToolUtils.dip2px(context, 8),
                        ToolUtils.dip2px(context, 8), ToolUtils.dip2px(context, 8));
                if (j == 0) {
                    tv1.setBackgroundResource(R.drawable.shape_6_red);
                    tv1.setTextColor(context.getResources().getColor(R.color.white));
                } else {
                    tv1.setBackgroundResource(R.drawable.shape_6_grad_d);
                    tv1.setTextColor(context.getResources().getColor(R.color.black));
                }
                tv1.setOnClickListener(listener1);
                switch (i) {
                    case 0:
                        fl1.addView(tv1);
                        break;
                    case 1:
                        fl2.addView(tv1);
                        set.connect(fl2.getId(), ConstraintSet.TOP, fl1.getId(), ConstraintSet
                                .BOTTOM, ToolUtils.dip2px(context, 8));
                        break;
                    case 2:
                        fl3.addView(tv1);
                        set.connect(fl3.getId(), ConstraintSet.TOP, fl2.getId(), ConstraintSet
                                .BOTTOM, ToolUtils.dip2px(context, 8));
                        break;
                    case 3:
                        fl4.addView(tv1);
                        set.connect(fl4.getId(), ConstraintSet.TOP, fl3.getId(), ConstraintSet
                                .BOTTOM, ToolUtils.dip2px(context, 8));
                        break;
                }
            }

        }

        set.applyTo(cly);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager windowManager = window.getWindowManager();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        Display display = windowManager.getDefaultDisplay();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.BottomShow);
        layoutParams.width = display.getWidth();
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        this.tag = tag;
    }

    public interface OnClickListener {
        void onClicked(View view, HashMap<String, Object> attr);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClicked(view, outAttr);
            }
        }
    };

    View.OnClickListener listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int num = 0;
            switch (view.getId()) {
                case R.id.imageView85:
                    if (outAttr.get("num") != null) {
                        num = (int) outAttr.get("num") + 1;
                        outAttr.put("num", num);
                    } else {
                        num = 1;
                        outAttr.put("num", 1);
                    }
                    ((TextView) v.findViewById(R.id.textView181)).setText(num + "");
                    break;
                case R.id.imageView84:
                    if (outAttr.get("num") != null && (int) outAttr.get("num") > 1) {
                        num = (int) outAttr.get("num") - 1;
                        outAttr.put("num", num);
                    } else {
                        num = 1;
                        outAttr.put("num", 1);
                    }
                    ((TextView) v.findViewById(R.id.textView181)).setText(num + "");
                    break;
                default:
                    System.out.println(view.getId());
                    for (int i = 0; i < attrs.size(); i++) {
                        for (int j = 0; j < attrs.get(i).getAttrs().size(); j++) {
                            TextView tv = v.findViewById(attrs.get(i).getAttrs().get(j)
                                    .getButtonId());
                            if (attrs.get(i).getAttrs().get(j).getButtonId() == view.getId()) {
                                attrs.get(i).getAttrs().get(j).setIsSelect(true);
                                outAttr.put(i + "", attrs.get(i).getAttrs().get(j).getAttrId());
                                tv.setTextColor(context.getResources().getColor(R.color.white));
                                tv.setBackgroundResource(R.drawable.shape_6_red);
                            } else {
                                if (attrs.get(i).getAttrs().get(j).getIsSelect() == true) {
                                    tv.setTextColor(context.getResources().getColor(R.color.white));
                                    tv.setBackgroundResource(R.drawable.shape_6_red);
                                } else {
                                    tv.setTextColor(context.getResources().getColor(R.color.black));
                                    tv.setBackgroundResource(R.drawable.shape_6_grad_d);
                                }
                            }
                        }
                    }
                    System.out.println(outAttr);
                    break;
            }
        }
    };
}
