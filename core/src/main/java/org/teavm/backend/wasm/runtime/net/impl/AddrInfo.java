package org.teavm.backend.wasm.runtime.net.impl;

import org.teavm.interop.Address;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AddrInfo {
    private static final int BUFFER_SIZE = 28;
    private static final int ADDR_BUF_SIZE = 18;
    private final int sockKind;
    private final byte[] addrBuf;
    private final int sockType;
    private final ByteBuffer buffer;

    public AddrInfo() {
        this(0, new byte[ADDR_BUF_SIZE], 0);
    }

    public AddrInfo(int sockKind, byte[] addrBuf, int sockType) {
        if (addrBuf == null || addrBuf.length != ADDR_BUF_SIZE) {
            throw new IllegalArgumentException(
                    "addrBuf must be exactly " + ADDR_BUF_SIZE + " bytes long.");
        }
        this.sockKind = sockKind;
        this.addrBuf = addrBuf.clone();
        this.sockType = sockType;
        this.buffer = createBuffer(sockKind, addrBuf, sockType);
    }

    private static ByteBuffer createBuffer(int sockKind, byte[] addrBuf, int sockType) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.putInt(sockKind);
        buffer.put(addrBuf);
        buffer.putInt(sockType);
        return buffer;
    }

    public static int getBufferSize() {
        return BUFFER_SIZE;
    }

    public static int getAddrBufferSize() {
        return ADDR_BUF_SIZE;
    }

    public int getSockKind() {
        return sockKind;
    }

    public byte[] getAddrBuf() {
        return addrBuf.clone();
    }

    public int getSockType() {
        return sockType;
    }

    public Address getAddress() {
        return Address.ofData(buffer.array());
    }

    @Override
    public String toString() {
        StringBuilder addrBufString = new StringBuilder();
        for (byte b : addrBuf) {
            addrBufString.append(String.format("%02X", b)).append(" ");
        }
        return String.format(
                "AddrInfo{sockKind=%d, addrBuf=[%s], sockType=%d}",
                sockKind, addrBufString.toString().trim(), sockType);
    }
}
