package org.teavm.classlib.java.net;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

import org.teavm.classlib.java.net.TInetAddress;
import org.teavm.classlib.java.net.TSocketAddress;


public class TSocket implements Closeable {

    public TSocket() {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
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

    public void connect(TSocketAddress endpoint) {
        throw new UnsupportedOperationException("not supported for JS and non-WASI Wasm");
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
