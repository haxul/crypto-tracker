package com.tcrypto.annotaitions;

import com.tcrypto.controllers.Coinmarketcap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnotaitionProcessor {
    public static void main(String[] args) throws NoSuchMethodException {
        inspectService(Coinmarketcap.class);
    }

    static void inspectService(Class<?> service) {
        Method[] methods = service.getMethods();
        System.out.println(methods[0].getParameters()[0]);
    }
}
