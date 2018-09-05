package com.vcvb.chenyu.shop.adapter.itemclick;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CYCItemClickSupport {
    private static final int KEY = 0x99999999;
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnChildItemClickListener mOnChildItemClickListener;
    private OnChildItemClickListener1 mOnChildItemClickListener1;
    private OnChildItemClickListener2 mOnChildItemClickListener2;
    private OnChildItemClickListener3 mOnChildItemClickListener3;
    private OnChildItemClickListener4 mOnChildItemClickListener4;
    private OnChildItemClickListener5 mOnChildItemClickListener5;
    private OnChildItemClickListener6 mOnChildItemClickListener6;

    private static int clildId = -1;
    private static int clildId1 = -1;
    private static int clildId2 = -1;
    private static int clildId3 = -1;
    private static int clildId4 = -1;
    private static int clildId5 = -1;
    private static int clildId6 = -1;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecyclerView, v, holder.getAdapterPosition());
            }
        }
    };

    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
            return false;
        }
    };

    private View.OnClickListener mOnChildClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };

    private View.OnClickListener mOnChildClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener1 != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener1.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };

    private View.OnClickListener mOnChildClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener2 != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener2.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };
    private View.OnClickListener mOnChildClickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener3 != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener3.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };
    private View.OnClickListener mOnChildClickListener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener4 != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener4.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };
    private View.OnClickListener mOnChildClickListener5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener5 != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener5.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };
    private View.OnClickListener mOnChildClickListener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnChildItemClickListener6 != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.findContainingViewHolder(v);
                mOnChildItemClickListener6.onChildItemClicked(mRecyclerView, v, holder
                        .getAdapterPosition());
            }
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener mAttachListener = new RecyclerView
            .OnChildAttachStateChangeListener() {

        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnChildItemClickListener != null) {
                View v = view.findViewById(clildId);
                if (v != null && v.hasOnClickListeners() == false) {
                    System.out.println(clildId);
                    v.setOnClickListener(mOnChildClickListener);
                }
            }
            if (mOnChildItemClickListener1 != null) {
                View v = view.findViewById(clildId1);
                if (v != null && v.hasOnClickListeners() == false) {
                    System.out.println(clildId1);
                    v.setOnClickListener(mOnChildClickListener1);
                }
            }
            if (mOnChildItemClickListener2 != null) {
                View v = view.findViewById(clildId2);
                if (v != null && v.hasOnClickListeners() == false) {
                    System.out.println(clildId2);
                    v.setOnClickListener(mOnChildClickListener2);
                }
            }
            if (mOnChildItemClickListener3 != null) {
                View v = view.findViewById(clildId3);
                if (v != null && v.hasOnClickListeners() == false) {
                    System.out.println(clildId3);
                    v.setOnClickListener(mOnChildClickListener3);
                }
            }
            if (mOnChildItemClickListener4 != null) {
                View v = view.findViewById(clildId4);
                if (v != null && v.hasOnClickListeners() == false) {
                    System.out.println(clildId4);
                    v.setOnClickListener(mOnChildClickListener4);
                }
            }
            if (mOnChildItemClickListener5 != null) {
                View v = view.findViewById(clildId5);
                if (v != null && v.hasOnClickListeners() == false) {
                    System.out.println(clildId5);
                    v.setOnClickListener(mOnChildClickListener5);
                }
            }
            if (mOnChildItemClickListener6 != null) {
                View v = view.findViewById(clildId6);
                if (v != null && v.hasOnClickListeners() == false) {
                    System.out.println(clildId6);
                    v.setOnClickListener(mOnChildClickListener6);
                }
            }

            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };

    /**
     * ItemClickSupport的私有构造方法
     */
    private CYCItemClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(KEY, this);
        // 为RecyclerView设置OnChildAttachStateChangeListener事件监听
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    /**
     * 为RecyclerView设置ItemClickSupport
     */
    public static CYCItemClickSupport addTo(RecyclerView view) {
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    public static CYCItemClickSupport BuildTo(RecyclerView view, int id) {
        clildId = id;
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    public static CYCItemClickSupport BuildTo1(RecyclerView view, int id) {
        clildId1 = id;
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    public static CYCItemClickSupport BuildTo2(RecyclerView view, int id) {
        clildId2 = id;
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    public static CYCItemClickSupport BuildTo3(RecyclerView view, int id) {
        clildId3 = id;
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    public static CYCItemClickSupport BuildTo4(RecyclerView view, int id) {
        clildId4 = id;
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    public static CYCItemClickSupport BuildTo5(RecyclerView view, int id) {
        clildId5 = id;
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    public static CYCItemClickSupport BuildTo6(RecyclerView view, int id) {
        clildId6 = id;
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support == null) {
            support = new CYCItemClickSupport(view);
        }
        return support;
    }

    /**
     * 为RecyclerView移除ItemClickSupport
     */
    public static CYCItemClickSupport removeFrom(RecyclerView view) {
        CYCItemClickSupport support = (CYCItemClickSupport) view.getTag(KEY);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    /**
     * 为RecyclerView设置点击事件监听
     */
    public CYCItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    /**
     * 为RecyclerView设置长按事件监听
     */
    public CYCItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    /**
     * 为子View设置点击事件监听
     */
    public CYCItemClickSupport setOnChildClickListener(OnChildItemClickListener listener) {
        mOnChildItemClickListener = listener;
        return this;
    }

    public CYCItemClickSupport setOnChildClickListener1(OnChildItemClickListener1 listener) {
        mOnChildItemClickListener1 = listener;
        return this;
    }

    public CYCItemClickSupport setOnChildClickListener2(OnChildItemClickListener2 listener) {
        mOnChildItemClickListener2 = listener;
        return this;
    }

    public CYCItemClickSupport setOnChildClickListener3(OnChildItemClickListener3 listener) {
        mOnChildItemClickListener3 = listener;
        return this;
    }

    public CYCItemClickSupport setOnChildClickListener4(OnChildItemClickListener4 listener) {
        mOnChildItemClickListener4 = listener;
        return this;
    }

    public CYCItemClickSupport setOnChildClickListener5(OnChildItemClickListener5 listener) {
        mOnChildItemClickListener5 = listener;
        return this;
    }

    public CYCItemClickSupport setOnChildClickListener6(OnChildItemClickListener6 listener) {
        mOnChildItemClickListener6 = listener;
        return this;
    }

    /**
     * 为RecyclerView移除OnChildAttachStateChangeListener事件监听
     */
    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(KEY, null);
    }

    /**
     * RecyclerView的点击事件监听接口
     */
    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    /**
     * RecyclerView的长按事件监听接口
     */
    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, View itemView, int position);
    }

    public interface OnChildItemClickListener {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    public interface OnChildItemClickListener1 {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    public interface OnChildItemClickListener2 {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    public interface OnChildItemClickListener3 {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    public interface OnChildItemClickListener4 {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    public interface OnChildItemClickListener5 {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }

    public interface OnChildItemClickListener6 {
        void onChildItemClicked(RecyclerView recyclerView, View itemView, int position);
    }
}
