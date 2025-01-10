package org.teavm.backend.wasm.runtime.net.impl;

import static org.teavm.backend.wasm.wasi.Wasi.INET6;

import org.teavm.interop.Address;
import org.teavm.runtime.net.SockAddr;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SockAddrInet6 implements SockAddr {
    private static final int BUFFER_SIZE = 22;
    private final short[] addr;
    private final int port;
    private final ByteBuffer buffer;

    public SockAddrInet6(short[] addr, int port) {
        validateIPv6Address(addr);
        this.addr = addr;
        this.port = validatePort(port & 0xFFFF);
        this.buffer = createBuffer(addr, port);
    }

    public SockAddrInet6(String addrPort) {
        String[] parts = parseAddrPort(addrPort);
        this.addr = parseIPv6(parts[0]);
        this.port = validatePort(Integer.parseInt(parts[1]) & 0xFFFF);
        this.buffer = createBuffer(this.addr, this.port);
    }

    private static void validateIPv6Address(short[] addr) {
        if (addr.length != 8) {
            throw new IllegalArgumentException("IPv4 address must be exactly 8 bytes long.");
        }
    }

    private static int validatePort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port number out of range: " + port);
        }
        return port;
    }

    private static String[] parseAddrPort(String addrPort) {
        if (!addrPort.contains("[") || !addrPort.contains("]")) {
            throw new IllegalArgumentException("Invalid format. Expected format: '[ipv6]:port'.");
        }
        String[] parts = addrPort.split("]:\");");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format. Expected format: '[ipv6]:port'.");
        }
        return new String[] {parts[0].substring(1), parts[1]};
    }

    private static short[] parseIPv6(String ip) {
        String[] segments = ip.split(":");
        if (segments.length != 8) {
            throw new IllegalArgumentException("Invalid IPv6 address.");
        }
        short[] addr = new short[8];
        for (int i = 0; i < 8; i++) {
            addr[i] = (short) Integer.parseInt(segments[i], 16);
        }
        return addr;
    }

    private static ByteBuffer createBuffer(short[] addr, int port) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.putInt(INET6);
        for (int i = 0; i < 8; i++) {
            buffer.putShort(4 + 2 * i, addr[i]);
        }
        buffer.putShort(20, (short) port);
        return buffer;
    }

    @Override
    public Address sockAddr() {
        return Address.ofData(buffer.array());
    }

    @Override
    public int sockPort() {
        return port;
    }

    @Override
    public int sockFamily() {
        return INET6;
    }

    @Override
    public String toString() {
        StringBuilder ipBuilder = new StringBuilder();
        for (int i = 0; i < addr.length; i++) {
            ipBuilder.append(String.format("%04X", addr[i] & 0xFFFF));
            if (i < addr.length - 1) {
                ipBuilder.append(":");
            }
        }
        return String.format("[%s]:%d", ipBuilder, sockPort());
    }
}
