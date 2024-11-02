package org.teavm.backend.wasm.wasi;

import org.teavm.interop.Import;

public class WasiSocket {

    // Enums for socket types
    public enum WasiSockType {
        SOCKET_ANY(-1),
        SOCKET_DGRAM(0),
        SOCKET_STREAM(1);

        private final int value;

        WasiSockType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum WasiAddressFamily {
        INET4(0),
        INET6(1),
        INET_UNSPEC(2);

        private final int value;

        WasiAddressFamily(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // Structures for IPv4 and IPv6 address
    public static class WasiAddrIp4 {
        public byte n0;
        public byte n1;
        public byte n2;
        public byte n3;
    }

    public static class WasiAddrIp6 {
        public short n0;
        public short n1;
        public short n2;
        public short n3;
        public short h0;
        public short h1;
        public short h2;
        public short h3;
    }

    public static class WasiAddrIp4Port {
        public WasiAddrIp4 addr;
        public short port;
    }

    public static class WasiAddrIp6Port {
        public WasiAddrIp6 addr;
        public short port;
    }

    public static class WasiAddr {
        public WasiAddressFamily kind;
        public Object addr;
    }

    // Import WASI functions
    @Import(name = "sock_accept", module = "wasi_snapshot_preview1")
    public native int sockAccept(int fd, int flags, int fdNew);

    @Import(name = "sock_addr_local", module = "wasi_snapshot_preview1")
    public native int sockAddrLocal(int fd, WasiAddr addr);

    @Import(name = "sock_addr_remote", module = "wasi_snapshot_preview1")
    public native int sockAddrRemote(int fd, WasiAddr addr);

    @Import(name = "sock_addr_resolve", module = "wasi_snapshot_preview1")
    public native int sockAddrResolve(String host, String service, WasiAddrInfoHints hints, WasiAddrInfo[] addrInfo, int addrInfoSize, int[] maxInfoSize);

    @Import(name = "sock_bind", module = "wasi_snapshot_preview1")
    public native int sockBind(int fd, WasiAddr addr);

    @Import(name = "sock_send_to", module = "wasi_snapshot_preview1")
    public native int sockSendTo(int fd, byte[] data, int dataLen, int flags, WasiAddr destAddr, int[] sentDataLen);

    @Import(name = "sock_recv_from", module = "wasi_snapshot_preview1")
    public native int sockRecvFrom(int fd, byte[] data, int dataLen, int flags, WasiAddr srcAddr, int[] recvDataLen);

    @Import(name = "sock_open", module = "wasi_snapshot_preview1")
    public native int sockOpen(int fd, WasiAddressFamily af, WasiSockType sockType, int[] sockfd);

    @Import(name = "sock_set_reuse_addr", module = "wasi_snapshot_preview1")
    public native int sockSetReuseAddr(int fd, boolean reuse);

    @Import(name = "sock_get_reuse_addr", module = "wasi_snapshot_preview1")
    public native int sockGetReuseAddr(int fd, boolean[] reuse);

    @Import(name = "sock_close", module = "wasi_snapshot_preview1")
    public native int sockClose(int fd);

    @Import(name = "sock_connect", module = "wasi_snapshot_preview1")
    public native int sockConnect(int fd, WasiAddr addr);

    @Import(name = "sock_listen", module = "wasi_snapshot_preview1")
    public native int sockListen(int fd, int backlog);

    // Helper structures
    public static class WasiAddrInfo {
        public WasiAddr addr;
        public WasiSockType type;
    }

    public static class WasiAddrInfoHints {
        public WasiSockType type;
        public WasiAddressFamily family;
        public byte hintsEnabled;
    }
}
