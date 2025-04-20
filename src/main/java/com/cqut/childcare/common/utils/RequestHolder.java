package com.cqut.childcare.common.utils;


import com.cqut.childcare.common.domain.dto.RequestInfo;

public class RequestHolder {

    private static final ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        threadLocal.set(requestInfo);
    }

    public static RequestInfo get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
