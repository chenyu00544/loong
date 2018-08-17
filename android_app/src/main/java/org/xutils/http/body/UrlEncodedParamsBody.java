package org.xutils.http.body;

import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.xutils.common.util.KeyValue;

public class UrlEncodedParamsBody implements RequestBody {
    private byte[] a;
    private String b = "UTF-8";

    public UrlEncodedParamsBody(List<KeyValue> list, String str) throws IOException {
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            for (KeyValue keyValue : list) {
                Object obj = keyValue.key;
                String valueStr = keyValue.getValueStr();
                if (!(TextUtils.isEmpty(obj) || valueStr == null)) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(Uri.encode(obj, this.b)).append("=").append(Uri.encode(valueStr, this.b));
                }
            }
        }
        this.a = stringBuilder.toString().getBytes(this.b);
    }

    public long getContentLength() {
        return (long) this.a.length;
    }

    public void setContentType(String str) {
    }

    public String getContentType() {
        return "application/x-www-form-urlencoded;charset=" + this.b;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.a);
        outputStream.flush();
    }
}
