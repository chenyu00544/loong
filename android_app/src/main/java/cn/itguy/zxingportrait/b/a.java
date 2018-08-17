package cn.itguy.zxingportrait.b;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import cn.itguy.zxingportrait.camera.FrontLightMode;
import cn.itguy.zxingportrait.camera.c;

/* compiled from: AmbientLightManager */
public final class a implements SensorEventListener {
    private final Context a;
    private c b;
    private Sensor c;

    public a(Context context) {
        this.a = context;
    }

    public void a(c cVar) {
        this.b = cVar;
        if (FrontLightMode.readPref(PreferenceManager.getDefaultSharedPreferences(this.a)) == FrontLightMode.AUTO) {
            SensorManager sensorManager = (SensorManager) this.a.getSystemService("sensor");
            this.c = sensorManager.getDefaultSensor(5);
            if (this.c != null) {
                sensorManager.registerListener(this, this.c, 3);
            }
        }
    }

    public void a() {
        if (this.c != null) {
            ((SensorManager) this.a.getSystemService("sensor")).unregisterListener(this);
            this.b = null;
            this.c = null;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        if (this.b == null) {
            return;
        }
        if (f <= 45.0f) {
            this.b.a(true);
        } else if (f >= 450.0f) {
            this.b.a(false);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
