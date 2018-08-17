package org.apache.commons.lang3;

import ru.truba.touchgallery.BuildConfig;

public enum JavaVersion {
    JAVA_0_9(1.5f, "0.9"),
    JAVA_1_1(1.1f, BuildConfig.VERSION_NAME),
    JAVA_1_2(1.2f, "1.2"),
    JAVA_1_3(1.3f, "1.3"),
    JAVA_1_4(1.4f, "1.4"),
    JAVA_1_5(1.5f, "1.5"),
    JAVA_1_6(1.6f, "1.6"),
    JAVA_1_7(1.7f, "1.7"),
    JAVA_1_8(1.8f, "1.8");
    
    private float a;
    private String b;

    private JavaVersion(float f, String str) {
        this.a = f;
        this.b = str;
    }

    public boolean atLeast(JavaVersion javaVersion) {
        return this.a >= javaVersion.a;
    }

    static JavaVersion a(String str) {
        if ("0.9".equals(str)) {
            return JAVA_0_9;
        }
        if (BuildConfig.VERSION_NAME.equals(str)) {
            return JAVA_1_1;
        }
        if ("1.2".equals(str)) {
            return JAVA_1_2;
        }
        if ("1.3".equals(str)) {
            return JAVA_1_3;
        }
        if ("1.4".equals(str)) {
            return JAVA_1_4;
        }
        if ("1.5".equals(str)) {
            return JAVA_1_5;
        }
        if ("1.6".equals(str)) {
            return JAVA_1_6;
        }
        if ("1.7".equals(str)) {
            return JAVA_1_7;
        }
        if ("1.8".equals(str)) {
            return JAVA_1_8;
        }
        return null;
    }

    public String toString() {
        return this.b;
    }
}
