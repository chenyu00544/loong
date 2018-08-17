package cn.itguy.zxingportrait.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.baidu.location.b.l;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: CameraConfigurationManager */
final class b {
    private final Context a;
    private Point b;
    private Point c;

    /* compiled from: CameraConfigurationManager */
    class b_1 implements Comparator<Size> {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((Size) obj, (Size) obj2);
        }

        public int a(Size size, Size size2) {
            int i = size.height * size.width;
            int i2 = size2.height * size2.width;
            if (i2 < i) {
                return -1;
            }
            if (i2 > i) {
                return 1;
            }
            return 0;
        }
    }

    b(Context context) {
        this.a = context;
    }

    void a(Camera camera) {
        Parameters parameters = camera.getParameters();
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        this.b = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        Log.i("CameraConfiguration", "Screen resolution: " + this.b);
        Point point = new Point();
        point.x = this.b.x;
        point.y = this.b.y;
        if (this.b.x < this.b.y) {
            point.x = this.b.y;
            point.y = this.b.x;
        }
        this.c = a(parameters, point);
        Log.i("CameraConfiguration", "Camera resolution: " + this.c);
    }

    void a(Camera camera, boolean z) {
        Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i("CameraConfiguration", "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w("CameraConfiguration", "In camera config safe mode -- most settings will not be honored");
        }
        a(parameters, PreferenceManager.getDefaultSharedPreferences(this.a), z);
        String a = a(parameters.getSupportedFocusModes(), "auto");
        if (!z && a == null) {
            a = a(parameters.getSupportedFocusModes(), "macro", "edof");
        }
        if (a != null) {
            parameters.setFocusMode(a);
        }
        parameters.setPreviewSize(this.c.x, this.c.y);
        camera.setDisplayOrientation(90);
        camera.setParameters(parameters);
    }

    Point a() {
        return this.c;
    }

    Point b() {
        return this.b;
    }

    boolean b(Camera camera) {
        if (camera == null || camera.getParameters() == null) {
            return false;
        }
        String flashMode = camera.getParameters().getFlashMode();
        if (flashMode == null) {
            return false;
        }
        if ("on".equals(flashMode) || "torch".equals(flashMode)) {
            return true;
        }
        return false;
    }

    void b(Camera camera, boolean z) {
        Parameters parameters = camera.getParameters();
        a(parameters, z, false);
        camera.setParameters(parameters);
    }

    private void a(Parameters parameters, SharedPreferences sharedPreferences, boolean z) {
        a(parameters, FrontLightMode.readPref(sharedPreferences) == FrontLightMode.ON, z);
    }

    private void a(Parameters parameters, boolean z, boolean z2) {
        String a;
        if (z) {
            a = a(parameters.getSupportedFlashModes(), "torch", "on");
        } else {
            a = a(parameters.getSupportedFlashModes(), l.cW);
        }
        if (a != null) {
            parameters.setFlashMode(a);
        }
    }

    private Point a(Parameters parameters, Point point) {
        Collection supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
            Size previewSize = parameters.getPreviewSize();
            return new Point(previewSize.width, previewSize.height);
        }
        List<Size> arrayList = new ArrayList(supportedPreviewSizes);
        Collections.sort(arrayList, new b_1(this));
        if (Log.isLoggable("CameraConfiguration", 4)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Size previewSize2 : arrayList) {
                stringBuilder.append(previewSize2.width).append('x').append(previewSize2.height).append(' ');
            }
            Log.i("CameraConfiguration", "Supported preview sizes: " + stringBuilder);
        }
        Point point2 = null;
        float f = ((float) point.x) / ((float) point.y);
        float f2 = Float.POSITIVE_INFINITY;
        for (Size previewSize22 : arrayList) {
            int i = previewSize22.width;
            int i2 = previewSize22.height;
            int i3 = i * i2;
            if (i3 >= 150400 && i3 <= 1024000) {
                int i4;
                Object obj = i < i2 ? 1 : null;
                if (obj != null) {
                    i4 = i2;
                } else {
                    i4 = i;
                }
                if (obj != null) {
                    i3 = i;
                } else {
                    i3 = i2;
                }
                if (i4 == point.x && i3 == point.y) {
                    point2 = new Point(i, i2);
                    Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + point2);
                    return point2;
                }
                Point point3;
                float abs = Math.abs((((float) i4) / ((float) i3)) - f);
                if (abs < f2) {
                    point3 = new Point(i, i2);
                } else {
                    abs = f2;
                    point3 = point2;
                }
                point2 = point3;
                f2 = abs;
            }
        }
        if (point2 == null) {
            previewSize22 = parameters.getPreviewSize();
            point2 = new Point(previewSize22.width, previewSize22.height);
            Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + point2);
        }
        Log.i("CameraConfiguration", "Found best approximate preview size: " + point2);
        return point2;
    }

    private static String a(Collection<String> collection, String... strArr) {
        Log.i("CameraConfiguration", "Supported values: " + collection);
        if (collection != null) {
            for (String str : strArr) {
                if (collection.contains(str)) {
                    break;
                }
            }
        }
        String str2 = null;
        Log.i("CameraConfiguration", "Settable value: " + str2);
        return str2;
    }
}
