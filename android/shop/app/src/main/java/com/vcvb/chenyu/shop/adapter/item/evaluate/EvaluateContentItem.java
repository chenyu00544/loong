package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.BaseItem;
import com.vcvb.chenyu.shop.adapter.base.CYCBaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.order.OrderDetail;

public class EvaluateContentItem extends BaseItem<OrderDetail> {
    public static final int TYPE = R.layout.evaluate_content_item;
    private OnClickListener onClickListener;

    public EvaluateContentItem(OrderDetail bean, Context c) {
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
        final EditText et = holder.get(R.id.editText24);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (onClickListener != null) {
                    onClickListener.onClicked(et, et.getText().toString());
                }
            }
        });
    }

    public interface OnClickListener {
        void onClicked(View view, String info);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }
}
