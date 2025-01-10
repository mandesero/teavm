package org.teavm.classlib.java.net;

import java.io.Serializable;
import java.util.Arrays;

public class TInetAddress implements Serializable {

    static final int IPv4 = 1;
    static final int IPv6 = 2;

    private String hostName;
    private byte[] address;

    private TInetAddress(String hostName, byte[] address) {
        this.hostName = hostName;
        this.address = address;
    }

    public boolean equals(Object obj) {
        return false;
    }

    public byte[] getAddress() {
        return address;
    }

    public static TInetAddress[] getAllByName(String host) {
        return new TInetAddress[]{getByName(host)};
    }

    public static TInetAddress getByAddress(byte[] addr) {
        return new TInetAddress(null, addr);
    }

    public static TInetAddress getByAddress(String host, byte[] addr) {
        return new TInetAddress(host, addr);
    }

    public static TInetAddress getByName(String host) {
        throw new UnsupportedOperationException();
    }

    public String getCanonicalHostName() {
        throw new UnsupportedOperationException();
    }

    public String getHostAddress() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length; i++) {
            sb.append(address[i] & 0xFF);
            if (i < address.length - 1) {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    public String getHostName() {
        return hostName != null ? hostName : "localhost";
    }

    public static TInetAddress getLocalHost() {
        return new TInetAddress("localhost", new byte[]{127, 0, 0, 1});
    }

    public static TInetAddress getLoopbackAddress() {
        return getLocalHost();
    }

    public int hashCode() {
        return Arrays.hashCode(address) + (hostName != null ? hostName.hashCode() : 0);
    }

    public boolean isAnyLocalAddress() {
        return address.length == 4 && address[0] == 0 && address[1] == 0 && address[2] == 0 && address[3] == 0;
    }

    public boolean isLinkLocalAddress() {
        return address.length == 4 && (address[0] & 0xFF) == 169 && (address[1] & 0xFF) == 254;
    }

    public boolean isLoopbackAddress() {
        return address.length == 4 && address[0] == 127;
    }

    public boolean isMCGlobal() {
        return isMulticastAddress() && (address[1] & 0xFF) >= 224 && (address[1] & 0xFF) <= 239;
    }

    public boolean isMCLinkLocal() {
        return isMulticastAddress() && (address[1] & 0xFF) == 224;
    }

    public boolean isMCNodeLocal() {
        return isMulticastAddress() && (address[1] & 0xFF) == 225;
    }

    public boolean isMCOrgLocal() {
        return isMulticastAddress() && (address[1] & 0xFF) == 239;
    }

    public boolean isMCSiteLocal() {
        return isMulticastAddress() && (address[1] & 0xFF) == 234;
    }

    public boolean isMulticastAddress() {
        return (address[0] & 0xF0) == 0xE0;
    }

    public boolean isReachable(int timeout) {
        throw new UnsupportedOperationException();
    }

    public boolean isSiteLocalAddress() {
        return address.length == 4 && (address[0] & 0xFF) == 10;
    }

    public String toString() {
        return (hostName != null ? hostName : "") + "/" + getHostAddress();
    }
}
