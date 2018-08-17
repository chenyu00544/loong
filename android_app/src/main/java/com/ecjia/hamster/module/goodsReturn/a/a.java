package com.ecjia.hamster.module.goodsReturn.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ecjia.a.q;
import com.ecjia.hamster.activity.ECJiaFullScreenViewPagerActivity;
import com.ecmoban.android.missmall.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ECJiaAddImageAdapter */
public class a extends Adapter<b> {
    public List<String> a;
    int b;
    boolean c = true;
    a d;
    private Context e;

    /* compiled from: ECJiaAddImageAdapter */
    public interface a {
        void a(String str, int i);
    }

    /* compiled from: ECJiaAddImageAdapter */
    public static class b extends ViewHolder {
        private ImageView a;
        private ImageView b;

        public b(View view) {
            super(view);
            this.a = (ImageView) view.findViewById(R.id.image);
            this.b = (ImageView) view.findViewById(R.id.delete);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a(Context context, List<String> list, int i) {
        this.e = context;
        if (context instanceof a) {
            this.d = (a) context;
        }
        this.a = list;
        this.b = i;
    }

    public a(Context context, List<String> list, int i, boolean z) {
        this.e = context;
        if (context instanceof a) {
            this.d = (a) context;
        }
        this.a = list;
        this.b = i;
        this.c = z;
    }

    public b a(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_image, viewGroup, false);
        inflate.getLayoutParams().height = this.b;
        inflate.getLayoutParams().width = this.b;
        return new b(inflate);
    }

    public void a(b bVar, final int i) {
        if (this.c) {
            q.a("路径 " + ((String) this.a.get(i)));
            ImageLoader.getInstance().clearDiskCache();
            ImageLoader.getInstance().displayImage("file://" + ((String) this.a.get(i)), bVar.a);
            bVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.d != null) {
                        this.b.d.a((String) this.b.a.get(i), i);
                    }
                }
            });
            bVar.b.setVisibility(0);
        } else {
            bVar.b.setVisibility(8);
            ImageLoader.getInstance().displayImage((String) this.a.get(i), bVar.a);
        }
        bVar.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (!this.b.c) {
                    Intent intent = new Intent(this.b.e, ECJiaFullScreenViewPagerActivity.class);
                    intent.putExtra("position", i);
                    Serializable arrayList = new ArrayList();
                    int min = Math.min(5, this.b.a.size());
                    for (int i = 0; i < min; i++) {
                        arrayList.add(this.b.a.get(i));
                    }
                    intent.putExtra("size", min);
                    intent.putExtra("pictures", arrayList);
                    this.b.e.startActivity(intent);
                    ((Activity) this.b.e).overridePendingTransition(R.anim.my_scale_action, R.anim.my_scale_action);
                }
            }
        });
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return this.a.size();
    }
}
