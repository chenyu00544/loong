package com.vcvb.chenyu.shop.adapter.item.evaluate;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.b.BaseItem;
import com.vcvb.chenyu.shop.javaBean.evaluate.Content;
import com.vcvb.chenyu.shop.javaBean.evaluate.EvaluateGroup;

public class EvaluateContentItem extends BaseItem<EvaluateGroup> {
    public static final int TYPE = R.layout.evaluate_info_item;
    private OnClickListener onClickListener;

    public EvaluateContentItem(EvaluateGroup bean, Context c) {
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
        final EditText et = holder.get(R.id.editText24);
        et.setOnKeyListener(onKeyListener);
        groupMap.put(et.getId(), groupPosition);
        Content content = (Content) mData.getObjs().get(position);
        et.setText(content.getContent());
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
                    onClickListener.onClicked(et, groupMap.get(et.getId()), et.getText().toString());
                }
            }
        });
    }

    public interface OnClickListener {
        void onClicked(View view, int group, String info);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == KeyEvent.KEYCODE_ENTER) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService
                        (Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                    }
                }
                return true;
            }
            return false;
        }
    };
}
