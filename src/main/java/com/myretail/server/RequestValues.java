package com.myretail.server;

/**
 * Simple class with a thread local for storing header information from http requests
 *
 * Created by MVW on 7/4/2018.
 */
public class RequestValues {

    /**
     * For each incoming request, we determine whether the caller is master of the universe
     */
    private static final ThreadLocal<Boolean> isMasterOfTheUniverseThreadLocal = new ThreadLocal<>();

    static void setMasterOfTheUniverse(boolean isMasterOfTheUniverse) {
        isMasterOfTheUniverseThreadLocal.set(isMasterOfTheUniverse);
    }

    static public boolean isMasterOfTheUniverse() {
        if (isMasterOfTheUniverseThreadLocal.get() == null) {
            isMasterOfTheUniverseThreadLocal.set(false);
        }
        return isMasterOfTheUniverseThreadLocal.get();
    }
}
