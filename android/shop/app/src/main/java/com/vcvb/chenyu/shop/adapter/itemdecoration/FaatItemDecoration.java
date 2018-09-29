package com.vcvb.chenyu.shop.adapter.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vcvb.chenyu.shop.javaBean.faat.Banner;
import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;

import java.util.List;

public class FaatItemDecoration extends RecyclerView.ItemDecoration {

    List<Faat> faats;
    Context context;

    public FaatItemDecoration(List<Faat> faats, Context context) {
        this.faats = faats;
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int p = 0;
        int pos = 0;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            for (int i = 0; i < faats.size(); i++) {
                Faat faat = faats.get(i);
                if (faats.get(i).getHeader() != null) {
                    if (position == p) {
                        outRect.set(0, 0, 0, 0);
                        break;
                    }
                    p += 1;
                    pos = p;
                }
                if (faats.get(i).getObjs() != null) {
                    for (int j = 0; j < faats.get(i).getObjs().size(); j++){
                        if(position == p){
                            if (faats.get(i).getObjs().get(position - pos) instanceof Goods) {
                                outRect.set(5, 5, 5, 5);
                                break;
                            } else if (faats.get(i).getObjs().get(position - pos) instanceof Banner) {
                                outRect.set(0, 0, 0, 0);
                                break;
                            }
                        }
                        p+=1;
                    }
                }
                if (faats.get(i).getFooter() != null) {
                    if (position == p) {
                        outRect.set(0, 0, 0, 0);
                        break;
                    }
                    p += 1;
                }
                pos = p;
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.set(0, 0, 0, 0);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
