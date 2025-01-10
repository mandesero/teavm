package org.teavm.runtime.net;


public final class VirtualSocketProvider {
    private static VirtualSocket instance;

    private VirtualSocketProvider() {
    }

    public static VirtualSocket getInstance() {
        if (instance == null) {
            instance = create();
        }
        return instance;
    }

    private static VirtualSocket create() {
        return new VirtualSocketImpl();
    }

    public static void setInstance(VirtualSocket instance) {
        VirtualSocketProvider.instance = instance;
    }
}
