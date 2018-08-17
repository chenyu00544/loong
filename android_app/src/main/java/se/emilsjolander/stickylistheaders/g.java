package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.SectionIndexer;

/* compiled from: SectionIndexerAdapterWrapper */
class g extends a implements SectionIndexer {
    SectionIndexer b;

    g(Context context, h hVar) {
        super(context, hVar);
        this.b = (SectionIndexer) hVar;
    }

    public int getPositionForSection(int i) {
        return this.b.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return this.b.getSectionForPosition(i);
    }

    public Object[] getSections() {
        return this.b.getSections();
    }
}
