package org.xutils.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ParameterizedTypeUtil {
    private ParameterizedTypeUtil() {
    }

    public static Type getParameterizedType(Type type, Class<?> cls, int i) {
        TypeVariable[] typeParameters;
        Type[] typeArr;
        Class<?> cls2;
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class<?> cls3 = (Class) parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            typeParameters = cls3.getTypeParameters();
            typeArr = actualTypeArguments;
            cls2 = cls3;
        } else {
            typeParameters = null;
            typeArr = null;
            cls2 = (Class) type;
        }
        if (cls != cls2) {
            Type[] genericInterfaces = cls2.getGenericInterfaces();
            if (genericInterfaces != null) {
                for (Type type2 : genericInterfaces) {
                    if ((type2 instanceof ParameterizedType) && cls.isAssignableFrom((Class) ((ParameterizedType) type2).getRawType())) {
                        try {
                            return a(getParameterizedType(type2, cls, i), typeParameters, typeArr);
                        } catch (Throwable th) {
                        }
                    }
                }
            }
            Class superclass = cls2.getSuperclass();
            if (superclass != null && cls.isAssignableFrom(superclass)) {
                return a(getParameterizedType(cls2.getGenericSuperclass(), cls, i), typeParameters, typeArr);
            }
            throw new IllegalArgumentException("FindGenericType:" + type + ", declaredClass: " + cls + ", index: " + i);
        } else if (typeArr != null) {
            return typeArr[i];
        } else {
            return Object.class;
        }
    }

    private static Type a(Type type, TypeVariable<?>[] typeVariableArr, Type[] typeArr) {
        if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            String name = typeVariable.getName();
            if (typeArr == null) {
                return typeVariable;
            }
            for (int i = 0; i < typeVariableArr.length; i++) {
                if (name.equals(typeVariableArr[i].getName())) {
                    return typeArr[i];
                }
            }
            return typeVariable;
        } else if (!(type instanceof GenericArrayType)) {
            return type;
        } else {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (genericComponentType instanceof Class) {
                return Array.newInstance((Class) genericComponentType, 0).getClass();
            }
            return type;
        }
    }
}
