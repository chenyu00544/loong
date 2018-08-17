package org.xutils.http.body;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.http.ProgressHandler;

public class MultipartBody implements ProgressBody {
    private static byte[] a = "--------7da3d81520810".getBytes();
    private static byte[] b = "\r\n".getBytes();
    private static byte[] c = "--".getBytes();
    private byte[] d;
    private String e;
    private String f = "UTF-8";
    private List<KeyValue> g;
    private long h = 0;
    private long i = 0;
    private ProgressHandler j;

    private class a extends OutputStream {
        final AtomicLong a = new AtomicLong(0);
        final /* synthetic */ MultipartBody b;

        public a(MultipartBody multipartBody) {
            this.b = multipartBody;
        }

        public void a(File file) {
            if (this.a.get() != -1) {
                this.a.addAndGet(file.length());
            }
        }

        public void a(InputStream inputStream) {
            if (this.a.get() != -1) {
                long inputStreamLength = InputStreamBody.getInputStreamLength(inputStream);
                if (inputStreamLength > 0) {
                    this.a.addAndGet(inputStreamLength);
                } else {
                    this.a.set(-1);
                }
            }
        }

        public void write(int i) throws IOException {
            if (this.a.get() != -1) {
                this.a.incrementAndGet();
            }
        }

        public void write(byte[] bArr) throws IOException {
            if (this.a.get() != -1) {
                this.a.addAndGet((long) bArr.length);
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.a.get() != -1) {
                this.a.addAndGet((long) i2);
            }
        }
    }

    public MultipartBody(List<KeyValue> list, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
        }
        this.g = list;
        a();
        OutputStream aVar = new a(this);
        try {
            writeTo(aVar);
            this.h = aVar.a.get();
        } catch (IOException e) {
            this.h = -1;
        }
    }

    public void setProgressHandler(ProgressHandler progressHandler) {
        this.j = progressHandler;
    }

    private void a() {
        String toHexString = Double.toHexString(Math.random() * 65535.0d);
        this.d = toHexString.getBytes();
        this.e = "multipart/form-data; boundary=" + new String(a) + toHexString;
    }

    public long getContentLength() {
        return this.h;
    }

    public void setContentType(String str) {
        this.e = "multipart/" + str + this.e.substring(this.e.indexOf(";"));
    }

    public String getContentType() {
        return this.e;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        if (this.j == null || this.j.updateProgress(this.h, this.i, true)) {
            for (KeyValue keyValue : this.g) {
                String str = keyValue.key;
                Object obj = keyValue.value;
                if (!(TextUtils.isEmpty(str) || obj == null)) {
                    a(outputStream, str, obj);
                }
            }
            a(outputStream, c, a, this.d, c);
            outputStream.flush();
            if (this.j != null) {
                this.j.updateProgress(this.h, this.h, true);
                return;
            }
            return;
        }
        throw new CancelledException("upload stopped!");
    }

    private void a(OutputStream outputStream, String str, Object obj) throws IOException {
        String fileName;
        Object obj2;
        String contentType;
        a(outputStream, c, a, this.d);
        String str2 = "";
        if (obj instanceof BodyItemWrapper) {
            BodyItemWrapper bodyItemWrapper = (BodyItemWrapper) obj;
            Object value = bodyItemWrapper.getValue();
            fileName = bodyItemWrapper.getFileName();
            obj2 = value;
            contentType = bodyItemWrapper.getContentType();
        } else {
            contentType = null;
            fileName = str2;
            obj2 = obj;
        }
        if (obj2 instanceof File) {
            File file = (File) obj2;
            if (TextUtils.isEmpty(fileName)) {
                fileName = file.getName();
            }
            if (TextUtils.isEmpty(contentType)) {
                contentType = FileBody.getFileContentType(file);
            }
            a(outputStream, a(str, fileName, this.f));
            a(outputStream, a(obj2, contentType, this.f));
            a(outputStream, new byte[0][]);
            a(outputStream, file);
            a(outputStream, new byte[0][]);
            return;
        }
        a(outputStream, a(str, fileName, this.f));
        a(outputStream, a(obj2, contentType, this.f));
        a(outputStream, new byte[0][]);
        if (obj2 instanceof InputStream) {
            a(outputStream, (InputStream) obj2);
            a(outputStream, new byte[0][]);
            return;
        }
        byte[] bArr;
        if (obj2 instanceof byte[]) {
            bArr = (byte[]) obj2;
        } else {
            bArr = String.valueOf(obj2).getBytes(this.f);
        }
        a(outputStream, bArr);
        this.i = ((long) bArr.length) + this.i;
        if (this.j != null && !this.j.updateProgress(this.h, this.i, false)) {
            throw new CancelledException("upload stopped!");
        }
    }

    private void a(OutputStream outputStream, byte[]... bArr) throws IOException {
        if (bArr != null) {
            for (byte[] write : bArr) {
                outputStream.write(write);
            }
        }
        outputStream.write(b);
    }

    private void a(OutputStream outputStream, File file) throws IOException {
        if (outputStream instanceof a) {
            ((a) outputStream).a(file);
        } else {
            a(outputStream, new FileInputStream(file));
        }
    }

    private void a(OutputStream outputStream, InputStream inputStream) throws IOException {
        if (outputStream instanceof a) {
            ((a) outputStream).a(inputStream);
            return;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                this.i += (long) read;
                if (this.j != null && !this.j.updateProgress(this.h, this.i, false)) {
                    throw new CancelledException("upload stopped!");
                }
            }
        } finally {
            IOUtil.closeQuietly((Closeable) inputStream);
        }
    }

    private static byte[] a(String str, String str2, String str3) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder("Content-Disposition: form-data");
        stringBuilder.append("; name=\"").append(str.replace("\"", "\\\"")).append("\"");
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append("; filename=\"").append(str2.replace("\"", "\\\"")).append("\"");
        }
        return stringBuilder.toString().getBytes(str3);
    }

    private static byte[] a(Object obj, String str, String str2) throws UnsupportedEncodingException {
        String replaceFirst;
        StringBuilder stringBuilder = new StringBuilder("Content-Type: ");
        if (!TextUtils.isEmpty(str)) {
            replaceFirst = str.replaceFirst("\\/jpg$", "/jpeg");
        } else if (obj instanceof String) {
            replaceFirst = "text/plain; charset=" + str2;
        } else {
            replaceFirst = "application/octet-stream";
        }
        stringBuilder.append(replaceFirst);
        return stringBuilder.toString().getBytes(str2);
    }
}
