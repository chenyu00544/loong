package com.vcvb.chenyu.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.viewholder.BaseViewHolder;
import com.vcvb.chenyu.shop.javaBean.cart.CartListBean;

import java.util.List;

public class CartRecyclerViewAdapter extends BaseRecyclerAdapter<CartListBean> implements View
        .OnClickListener, View.OnLongClickListener, View.OnTouchListener{
    public CartRecyclerViewAdapter(Context context, List<CartListBean> list) {
        super(context, list);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= 0 && position < mList.size()) {
            switch (mList.get(position).getIsType()) {
                case 1:
                    return ITEM_VIEW_TYPE_NORMAL;
                case 2:
                    return ITEM_VIEW_TYPE_TITLE;
                case 3:
                    return ITEM_VIEW_TYPE_BUTTOM;
                case 4:
                    return ITEM_VIEW_TYPE_BUTTOM1;
                case 5:
                    return ITEM_VIEW_TYPE_BUTTOM2;
            }
        }
        return ITEM_VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public BaseViewHolder getHolder(View itemView, int viewType, AdapterView.OnItemClickListener
            listener) {
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        if(viewType == ITEM_VIEW_TYPE_NORMAL){
            itemView.setOnTouchListener(this);
        }
        return new OrderViewHolder(itemView, viewType, listener);
    }

    @Override
    public int getLayoutId(int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_NORMAL:
                return R.layout.cart_content_have_data_item;
            case ITEM_VIEW_TYPE_TITLE:
                return R.layout.cart_content_have_data_title_item;
            case ITEM_VIEW_TYPE_BUTTOM:
                return R.layout.cart_content_no_data;
//            case ITEM_VIEW_TYPE_BUTTOM1:
//                return R.layout.cart_content_have_data_buttom2_item;
//            case ITEM_VIEW_TYPE_BUTTOM2:
//                return R.layout.cart_content_have_data_buttom3_item;
            default:
                return R.layout.cart_content_have_data_item;
        }
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnRecyclerViewItemLongClickListener onItemLongClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener longListener) {
        onItemLongClickListener = longListener;
    }

    /**
     * item里面有多个控件可以点击
     */
    public enum ViewName {
        ITEM,
        ADD,
        SUB,
        CHECKBOX,
        FIND,
        COLLECTION,
        DELETE,
        TOBUY
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        if (mOnItemClickListener != null) {
            switch (view.getId()) {
                case R.id.imageView43:
                    mOnItemClickListener.onClick(view, ViewName.SUB, position);
                    break;
                case R.id.imageView44:
                    mOnItemClickListener.onClick(view, ViewName.ADD, position);
                    break;
                case R.id.checkBox2:
                    mOnItemClickListener.onClick(view, ViewName.CHECKBOX, position);
                    break;
                case R.id.checkBox3:
                    mOnItemClickListener.onClick(view, ViewName.CHECKBOX, position);
                    break;
                case R.id.textView109:
                    mOnItemClickListener.onClick(view, ViewName.FIND, position);
                    break;
                case R.id.textView110:
                    mOnItemClickListener.onClick(view, ViewName.COLLECTION, position);
                    break;
                case R.id.textView111:
                    mOnItemClickListener.onClick(view, ViewName.DELETE, position);
                    break;
                case R.id.textView82:
                    mOnItemClickListener.onClick(view, ViewName.TOBUY, position);
                    break;
                default:
                    mOnItemClickListener.onClick(view, ViewName.ITEM, position);
                    break;
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        return onItemLongClickListener != null && onItemLongClickListener.onLongClick
                (view, (int) view.getTag());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                view.setBackgroundColor(Color.parseColor("#EEEEEE"));
                break;
            case MotionEvent.ACTION_UP:
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case MotionEvent.ACTION_CANCEL:
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
        }
        return false;
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int position);
    }

    public interface OnRecyclerViewItemLongClickListener {
        boolean onLongClick(View view, int position);
    }

    public class OrderViewHolder extends BaseViewHolder<CartListBean> {

        public OrderViewHolder(View itemView, int viewType, AdapterView.OnItemClickListener
                listener) {
            super(itemView, viewType, listener);
            switch (viewType) {
                case ITEM_VIEW_TYPE_TITLE:
                    CheckBox checkbox2 = itemView.findViewById(R.id.checkBox2);
                    checkbox2.setOnClickListener(CartRecyclerViewAdapter.this);
                    break;
                case ITEM_VIEW_TYPE_BUTTOM:
                    TextView pay = itemView.findViewById(R.id.textView82);
                    pay.setOnClickListener(CartRecyclerViewAdapter.this);
                    break;
                case ITEM_VIEW_TYPE_BUTTOM1:
                    break;
                case ITEM_VIEW_TYPE_BUTTOM2:
                    break;
                default:
                    ImageView add = itemView.findViewById(R.id.imageView44);
                    add.setOnClickListener(CartRecyclerViewAdapter.this);
                    ImageView sub = itemView.findViewById(R.id.imageView43);
                    sub.setOnClickListener(CartRecyclerViewAdapter.this);
                    CheckBox checkbox3 = itemView.findViewById(R.id.checkBox3);
                    checkbox3.setOnClickListener(CartRecyclerViewAdapter.this);
                    break;
            }
        }

        @Override
        public void bindViewHolder(CartListBean bean, int position) {
            itemView.setTag(position);
            switch (CartRecyclerViewAdapter.this.getItemViewType(position)) {
                case ITEM_VIEW_TYPE_TITLE:
                    CheckBox checkbox2 = itemView.findViewById(R.id.checkBox2);
                    checkbox2.setTag(position);
                    checkbox2.setChecked(bean.getIsCheckAll());

                    TextView storeName = itemView.findViewById(R.id.textView95);
                    storeName.setText(bean.getStoreName());
                    break;
                case ITEM_VIEW_TYPE_BUTTOM:
                    TextView pay = itemView.findViewById(R.id.textView82);
                    pay.setTag(position);
                    break;
                case ITEM_VIEW_TYPE_BUTTOM1:
                    break;
                case ITEM_VIEW_TYPE_BUTTOM2:
                    break;
                default:
                    TextView goodsName = itemView.findViewById(R.id.textView85);
                    goodsName.setText(bean.getGoodsName());
                    TextView goodsNum = itemView.findViewById(R.id.textView90);
                    goodsNum.setText("" + bean.getGoodsNum());
                    CheckBox checkbox3 = itemView.findViewById(R.id.checkBox3);
                    checkbox3.setChecked(bean.getIsChecOnce());
                    break;
            }
        }
    }
}
