package com.nineoldandroids.a;

/* compiled from: FloatEvaluator */
public class d implements l<Number> {
    public Float a(float f, Number number, Number number2) {
        float floatValue = number.floatValue();
        return Float.valueOf(floatValue + ((number2.floatValue() - floatValue) * f));
    }
}
