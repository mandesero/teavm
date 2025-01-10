package org.teavm.classlib.java.net;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

import org.teavm.classlib.java.net.TInetAddress;
import org.teavm.classlib.java.net.TSocketAddress;
import org.teavm.runtime.fs.VirtualFileSystem;
import org.teavm.runtime.fs.VirtualFileSystemProvider;
import org.teavm.runtime.net.SockAddr;
import org.teavm.runtime.net.VirtualSocket;
import org.teavm.runtime.net.VirtualSocketProvider;

import static org.teavm.backend.wasm.wasi.Wasi.AF_INET;
import static org.teavm.backend.wasm.wasi.Wasi.AF_INET6;
import static org.teavm.backend.wasm.wasi.Wasi.AF_UNIX;
import static org.teavm.backend.wasm.wasi.Wasi.INET4;
import static org.teavm.backend.wasm.wasi.Wasi.INET6;
import static org.teavm.backend.wasm.wasi.Wasi.INET_UNSPEC;
import static org.teavm.backend.wasm.wasi.Wasi.SOCK_ANY;
import static org.teavm.backend.wasm.wasi.Wasi.SOCK_DGRAM;
import static org.teavm.backend.wasm.wasi.Wasi.SOCK_STREAM;

import org.teavm.backend.wasm.runtime.net.impl.*;

public class TSocket implements Closeable {
    private int fd;

    private static VirtualSocket vs() {
        return VirtualSocketProvider.getInstance();
    }


    public TSocket() {
        System.out.println("TSocket()");
        try {
            fd = vs().socket(AF_INET, SOCK_STREAM);
            System.out.println("FD:  " + fd);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public TSocket(TInetAddress address, int port) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TSocket(TInetAddress host, int port, boolean stream) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TSocket(TInetAddress address, int port, TInetAddress localAddr, int localPort) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TSocket(String host, int port) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TSocket(String host, int port, boolean stream) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TSocket(String host, int port, TInetAddress localAddr, int localPort) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void bind(TSocketAddress bindpoint) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void close() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void connect(TSocketAddress sa) {
        System.out.println("TSocket::connect()");
        try {
            SockAddrInet4 _sa = new SockAddrInet4("127.0.0.1:8081");
            vs().connect(fd, _sa);
            byte[] data = new byte[]{'h', 'i', '!'};
            vs().sendTo(fd, data, data.length, _sa);
            byte[] buf = new byte[1024];
            vs().recvFrom(fd, buf, buf.length, _sa);
            printByteArrayAsChars(buf);
            SockAddrInet4 t = (SockAddrInet4) vs().getSockName(fd);
            System.out.println(t);
            t = (SockAddrInet4) vs().getPeerName(fd);
            System.out.println(t);

            SockAddr[] adrs = vs().getAddrInfo("ya.ru", "https", INET4, SOCK_STREAM, 1);
            for (SockAddr elem: adrs) {
                System.out.println(elem);
            }
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }


    public static void printByteArrayAsChars(byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Data must not be null.");
        }

        for (byte b : data) {
            System.out.print((char) b);
        }
        System.out.println();
    }

    public void connect(TSocketAddress endpoint, int timeout) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TInetAddress getInetAddress() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public InputStream getInputStream() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean getKeepAlive() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TInetAddress getLocalAddress() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public int getLocalPort() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TSocketAddress getLocalSocketAddress() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean getOOBInline() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public OutputStream getOutputStream() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public int getPort() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public int getReceiveBufferSize() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public TSocketAddress getRemoteSocketAddress() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean getReuseAddress() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public int getSendBufferSize() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public int getSoLinger() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public int getSoTimeout() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean getTcpNoDelay() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public int getTrafficClass() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean isBound() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean isClosed() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean isConnected() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean isInputShutdown() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public boolean isOutputShutdown() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void sendUrgentData(int data) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setKeepAlive(boolean on) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setOOBInline(boolean on) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setReceiveBufferSize(int size) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setReuseAddress(boolean on) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setSendBufferSize(int size) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setSoLinger(boolean on, int linger) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setSoTimeout(int timeout) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setTcpNoDelay(boolean on) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void setTrafficClass(int tc) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void shutdownInput() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    public void shutdownOutput() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
    }
}
