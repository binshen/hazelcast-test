package org.test;

import com.hazelcast.map.MapInterceptor;

public class IMapInterceptor implements MapInterceptor {

    private static final long serialVersionUID = 3556808830046436753L;

    public Object interceptGet(Object o) {
        return null;
    }

    public void afterGet(Object o) {
        System.out.println(o);
    }

    public Object interceptPut(Object o, Object o1) {
        return null;
    }

    public void afterPut(Object o) {
        System.out.println(o);
    }

    public Object interceptRemove(Object o) {
        return null;
    }

    public void afterRemove(Object o) {
        System.out.println(o);
    }
}

