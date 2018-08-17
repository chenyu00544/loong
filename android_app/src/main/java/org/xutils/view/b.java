package org.xutils.view;

/* compiled from: ViewInfo */
final class b {
    public int a;
    public int b;

    b() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a != bVar.a) {
            return false;
        }
        if (this.b != bVar.b) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.a * 31) + this.b;
    }
}
