package org.teavm.runtime.net;

import java.net.SocketException;
import java.util.List;

import org.teavm.backend.wasm.wasi.Wasi;
import org.teavm.runtime.net.VirtualSocket;

public class VirtualSocketImpl implements VirtualSocket {

    @Override
    public int socket(int proto, int sotype) throws SocketException {
        throw new SocketException("socket: not supported for JS and non-WASI Wasm");
    }

    @Override
    public void connect(int fd, SockAddr sa) throws SocketException {
        throw new SocketException("connect: not supported for JS and non-WASI Wasm");
    }

    @Override
    public void bind(int fd, SockAddr sa) throws SocketException {
        throw new SocketException("bind: not supported for JS and non-WASI Wasm");
    }

    @Override
    public void listen(int fd, int backlog) throws SocketException {
        throw new SocketException("listen: not supported for JS and non-WASI Wasm");
    }

    @Override
    public int sendTo(int fd, byte[] buf, int len, SockAddr sa) throws SocketException {
        throw new SocketException("send_to: not supported for JS and non-WASI Wasm");
    }

    @Override
    public int recvFrom(int fd, byte[] buf, int len, SockAddr sa) throws SocketException {
        throw new SocketException("recv_from: not supported for JS and non-WASI Wasm");
    }

    @Override
    public void shutdown(int fd, int how) throws SocketException {
        throw new SocketException("shutdown: not supported for JS and non-WASI Wasm");
    }

    @Override
    public int accept(int fd, int flags) throws SocketException {
        throw new SocketException("accept: not supported for JS and non-WASI Wasm");
    }

    @Override
    public SockAddr getSockName(int fd) throws SocketException {
        throw new SocketException("get_sock_name: not supported for JS and non-WASI Wasm");
    }

    @Override
    public SockAddr getPeerName(int fd) throws SocketException {
        throw new SocketException("get_peer_name: not supported for JS and non-WASI Wasm");
    }

    @Override
    public int sockoptSetBroadcast(int fd, int value) throws SocketException {
        throw new SocketException("sockopt_set_broadcast: not supported for JS and non-WASI Wasm");
    }

    @Override
    public int sockoptSetReuseAddr(int fd, int value) throws SocketException {
        throw new SocketException("sockopt_set_reuse_addr: not supported for JS and non-WASI Wasm");
    }

    @Override
    public SockAddr[] getAddrInfo(String name, String service, int proto, int sotype, int hints_enabled) throws SocketException {
        throw new SocketException("get_addr_info: not supported for JS and non-WASI Wasm");
    }
}
