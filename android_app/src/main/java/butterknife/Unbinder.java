package butterknife;

public interface Unbinder {
    public static final Unbinder EMPTY = new Unbinder_1();

    static class Unbinder_1 implements Unbinder {
        Unbinder_1() {
        }

        public void unbind() {
        }
    }

    void unbind();
}
