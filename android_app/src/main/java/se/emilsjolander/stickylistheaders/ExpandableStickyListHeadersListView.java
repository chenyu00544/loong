package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

public class ExpandableStickyListHeadersListView extends StickyListHeadersListView {
    public static final int ANIMATION_COLLAPSE = 1;
    public static final int ANIMATION_EXPAND = 0;
    a mDefaultAnimExecutor = new ExpandableStickyListHeadersListView_1(this);
    e mExpandableStickyListHeadersAdapter;

    public interface a {
        void a(View view, int i);
    }

    class ExpandableStickyListHeadersListView_1 implements a {
        final /* synthetic */ ExpandableStickyListHeadersListView a;

        ExpandableStickyListHeadersListView_1(ExpandableStickyListHeadersListView expandableStickyListHeadersListView) {
            this.a = expandableStickyListHeadersListView;
        }

        public void a(View view, int i) {
            if (i == 0) {
                view.setVisibility(0);
            } else if (i == 1) {
                view.setVisibility(8);
            }
        }
    }

    public ExpandableStickyListHeadersListView(Context context) {
        super(context);
    }

    public ExpandableStickyListHeadersListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ExpandableStickyListHeadersListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public e getAdapter() {
        return this.mExpandableStickyListHeadersAdapter;
    }

    public void setAdapter(h hVar) {
        this.mExpandableStickyListHeadersAdapter = new e(hVar);
        super.setAdapter(this.mExpandableStickyListHeadersAdapter);
    }

    public View findViewByItemId(long j) {
        return this.mExpandableStickyListHeadersAdapter.e(j);
    }

    public long findItemIdByView(View view) {
        return this.mExpandableStickyListHeadersAdapter.a(view);
    }

    public void expand(long j) {
        if (this.mExpandableStickyListHeadersAdapter.b(j)) {
            this.mExpandableStickyListHeadersAdapter.c(j);
            List<View> a = this.mExpandableStickyListHeadersAdapter.a(j);
            if (a != null) {
                for (View animateView : a) {
                    animateView(animateView, 0);
                }
            }
        }
    }

    public void collapse(long j) {
        if (!this.mExpandableStickyListHeadersAdapter.b(j)) {
            this.mExpandableStickyListHeadersAdapter.d(j);
            List<View> a = this.mExpandableStickyListHeadersAdapter.a(j);
            if (a != null) {
                for (View animateView : a) {
                    animateView(animateView, 1);
                }
            }
        }
    }

    public boolean isHeaderCollapsed(long j) {
        return this.mExpandableStickyListHeadersAdapter.b(j);
    }

    public void setAnimExecutor(a aVar) {
        this.mDefaultAnimExecutor = aVar;
    }

    private void animateView(View view, int i) {
        if (i != 0 || view.getVisibility() != 0) {
            if ((1 != i || view.getVisibility() == 0) && this.mDefaultAnimExecutor != null) {
                this.mDefaultAnimExecutor.a(view, i);
            }
        }
    }
}
