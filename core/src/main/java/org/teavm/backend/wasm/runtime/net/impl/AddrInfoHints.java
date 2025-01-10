package org.teavm.backend.wasm.runtime.net.impl;

import org.teavm.interop.Address;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AddrInfoHints {
    private static final int BUFFER_SIZE = 12;
    private final int type;
    private final int family;
    private final int hintsEnabled;
    private final ByteBuffer buffer;

    public AddrInfoHints(int type, int family, int hintsEnabled) {
        this.type = type;
        this.family = family;
        this.hintsEnabled = hintsEnabled;
        this.buffer = createBuffer(type, family, hintsEnabled);
    }

    private static ByteBuffer createBuffer(int type, int family, int hintsEnabled) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.putInt(type);
        buffer.putInt(family);
        buffer.putInt(hintsEnabled);
        return buffer;
    }

    public int getType() {
        return type;
    }

    public int getFamily() {
        return family;
    }

    public int getHintsEnabled() {
        return hintsEnabled;
    }

    public Address getAddress() {
        return Address.ofData(buffer.array());
    }

    @Override
    public String toString() {
        return String.format(
                "AddrInfoHints{type=%d, family=%d, hintsEnabled=%d}", type, family, hintsEnabled);
    }
}
