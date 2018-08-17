package com.baidu.location.e;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.location.b.f;

public class l implements SensorEventListener, f {
    private static l ie;
    private SensorManager h9;
    private boolean ia = false;
    private boolean ib;
    private float ic;
    private float[] id;
    private double ig = Double.MIN_VALUE;
    private float[] ih;
    private boolean ii = false;

    public static l cf() {
        if (ie == null) {
            ie = new l();
        }
        return ie;
    }

    public void case(boolean z) {
        this.ib = z;
    }

    public boolean cb() {
        return this.ia;
    }

    public boolean cc() {
        return this.ib;
    }

    public synchronized void cd() {
        if (!this.ii) {
            if (this.h9 == null) {
                this.h9 = (SensorManager) com.baidu.location.f.getServiceContext().getSystemService("sensor");
            }
            if (this.h9 != null) {
                Sensor defaultSensor = this.h9.getDefaultSensor(11);
                if (defaultSensor != null && this.ib) {
                    this.h9.registerListener(this, defaultSensor, 3);
                }
                defaultSensor = this.h9.getDefaultSensor(6);
                if (defaultSensor != null && this.ia) {
                    this.h9.registerListener(this, defaultSensor, 3);
                }
            }
            this.ii = true;
        }
    }

    public double ce() {
        return this.ig;
    }

    public synchronized void cg() {
        if (this.ii) {
            if (this.h9 != null) {
                this.h9.unregisterListener(this);
                this.h9 = null;
            }
            this.ii = false;
        }
    }

    public float ch() {
        return this.ic;
    }

    public void char(boolean z) {
        this.ia = z;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 6:
                this.id = (float[]) sensorEvent.values.clone();
                this.ig = (double) SensorManager.getAltitude(1013.25f, this.id[0]);
                return;
            case 11:
                this.ih = (float[]) sensorEvent.values.clone();
                if (this.ih != null) {
                    float[] fArr = new float[9];
                    SensorManager.getRotationMatrixFromVector(fArr, this.ih);
                    float[] fArr2 = new float[3];
                    SensorManager.getOrientation(fArr, fArr2);
                    this.ic = (float) Math.toDegrees((double) fArr2[0]);
                    this.ic = (float) Math.floor(this.ic >= 0.0f ? (double) this.ic : (double) (this.ic + 360.0f));
                    return;
                }
                return;
            default:
                return;
        }
    }
}
