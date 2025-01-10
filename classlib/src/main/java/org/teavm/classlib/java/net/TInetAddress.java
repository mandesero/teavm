package org.teavm.classlib.java.net;

import java.io.Serializable;
import java.util.Arrays;
import java.net.URI;

import org.teavm.runtime.net.VirtualSocket;
import org.teavm.runtime.net.VirtualSocketProvider;

public class TInetAddress {
    private final byte[] address;
    private final String hostname;

    private static VirtualSocket vs() {
        return VirtualSocketProvider.getInstance();
    }

    private TInetAddress(byte[] address, String hostname) {
        this.address = address;
        this.hostname = hostname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TInetAddress that = (TInetAddress) obj;
        return Arrays.equals(address, that.address) && (hostname == that.hostname);
    }

    public byte[] getAddress() {
        return address.clone();
    }

    public static TInetAddress[] getAllByName(String host) throws TUnknownHostException {
        // URI u = new URI(uri);

        // String _host = u.getHost();
        // if (_host == null) {
        //     throw new UnknownHostException("No host found in URI: " + _uri);
        // }
        // String protocol = u.getProtocol();
        // int port = u.getPort();
        // String service = null;
        // if (port != -1) {
        //     service = port; 
        // } else if (!protocol.isEmpty()) {
        //     service = protocol;
        // }
        
        // TInetAddress[] addresses = vs().getaddrinfo(host, service);

        // if (addresses.length == 0) {
        //     throw new TUnknownHostException("Host not found: " + host);
        // }
        // return addresses;
        return null;
    }

    public static TInetAddress getByAddress(byte[] addr) throws TUnknownHostException {
        if (addr.length != 4) { // IPv4 only for simplicity
            throw new TUnknownHostException("Invalid address length: " + addr.length);
        }
        return new TInetAddress(addr, null);
    }

    public static TInetAddress getByAddress(String host, byte[] addr) throws TUnknownHostException {
        if (addr.length != 4) {
            throw new TUnknownHostException("Invalid address length: " + addr.length);
        }
        return new TInetAddress(addr, host);
    }

    public static TInetAddress getByName(String host) throws TUnknownHostException {
        TInetAddress[] addresses = getAllByName(host);
        return addresses[0];
    }

    public String getCanonicalHostName() {
        return hostname != null ? hostname : getHostAddress();
    }

    public String getHostAddress() {
        StringBuilder sb = new StringBuilder();
        for (byte b : address) {
            sb.append(b & 0xFF).append('.');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public String getHostName() {
        return hostname != null ? hostname : getHostAddress();
    }

    public static TInetAddress getLocalHost() throws TUnknownHostException {
        return new TInetAddress(new byte[]{127, 0, 0, 1}, "localhost");
    }

    public static TInetAddress getLoopbackAddress() {
        return new TInetAddress(new byte[]{127, 0, 0, 1}, "localhost");
    }

    public boolean isAnyLocalAddress() {
        return Arrays.equals(address, new byte[]{0, 0, 0, 0});
    }

    public boolean isLinkLocalAddress() {
        return (address[0] & 0xFF) == 169 && (address[1] & 0xFF) == 254;
    }

    public boolean isLoopbackAddress() {
        return address[0] == 127;
    }

    public boolean isMulticastAddress() {
        return (address[0] & 0xF0) == 0xE0;
    }

    public boolean isReachable(int timeout) {
        // Placeholder implementation
        return true;
    }

    public boolean isReachable(TNetworkInterface netif, int ttl, int timeout) {
        // Placeholder implementation
        return true;
    }

    public boolean isSiteLocalAddress() {
        return (address[0] & 0xFF) == 192 && (address[1] & 0xFF) == 168;
    }

    @Override
    public String toString() {
        return (hostname != null ? hostname : "") + "/" + getHostAddress();
    }
}
