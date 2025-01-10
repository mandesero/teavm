package org.teavm.classlib.java.net;

import java.io.Serializable;

import org.teavm.classlib.java.net.TInetAddress;
import org.teavm.classlib.java.net.TSocketAddress;

public class TInetSocketAddress extends TSocketAddress implements Serializable {
    private TInetAddress address;
    private String hostname;
    private int port;
    private boolean unresolved;

    public TInetSocketAddress(TInetAddress addr, int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port out of range: " + port);
        }
        this.address = addr;
        this.port = port;
        this.unresolved = (addr == null);
    }

    public TInetSocketAddress(int port) {
        this((TInetAddress) null, port);
    }

    public TInetSocketAddress(String hostname, int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port out of range: " + port);
        }
        this.hostname = hostname;
        this.port = port;
        try {
            this.address = TInetAddress.getByName(hostname);
            this.unresolved = false;
        } catch (TUnknownHostException e) {
            this.address = null;
            this.unresolved = true;
        }
    }

    public static TInetSocketAddress createUnresolved(String host, int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port out of range: " + port);
        }
        TInetSocketAddress socketAddress = new TInetSocketAddress(host, port);
        socketAddress.unresolved = true;
        return socketAddress;
    }

    public TInetAddress getAddress() {
        return address;
    }

    public String getHostName() {
        return hostname;
    }

    public String getHostString() {
        if (hostname != null) {
            return hostname;
        }
        return address != null ? address.getHostAddress() : null;
    }

    public int getPort() {
        return port;
    }

    public boolean isUnresolved() {
        return unresolved;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (hostname != null) {
            sb.append(hostname);
        } else if (address != null) {
            sb.append(address.getHostAddress());
        } else {
            sb.append("<unresolved>");
        }
        sb.append(":").append(port);
        return sb.toString();
    }
}