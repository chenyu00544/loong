package anet.channel.strategy;

import java.io.File;
import java.util.Comparator;

/* compiled from: Taobao */
final class o implements Comparator<File> {
    o() {
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }

    public int a(File file, File file2) {
        return (int) (file2.lastModified() - file.lastModified());
    }
}
