package de.greenrobot.event;

/* compiled from: Subscription */
final class k {
    final Object a;
    final i b;

    k(Object obj, i iVar) {
        this.a = obj;
        this.b = iVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (this.a == kVar.a && this.b.equals(kVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.d.hashCode();
    }
}
