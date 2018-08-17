package kankan.wheel.widget;

/* compiled from: ItemsRange */
public class a {
    private int a;
    private int b;

    public a() {
        this(0, 0);
    }

    public a(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return (a() + c()) - 1;
    }

    public int c() {
        return this.b;
    }

    public boolean a(int i) {
        return i >= a() && i <= b();
    }
}
