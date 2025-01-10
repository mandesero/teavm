package org.teavm.backend.wasm.runtime.net;

import java.net.InetAddress;
import java.net.SocketException;

import org.teavm.runtime.net.SockAddr;
import org.teavm.runtime.net.VirtualSocket;

import org.teavm.backend.wasm.wasi.Wasi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.teavm.interop.Address;

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

public class WasiVirtualSocket implements VirtualSocket {

        private static final int MAX_RESOLVED_ADDRESSES = 16;
        private static final int ADDR_SIZE = 22;
        private static final int BUFFER_SIZE = 8;
        private static final int IPV4_ADDR_SIZE = 4;
        private static final int IPV6_ADDR_SIZE = 8;
        private static final int ADDR_INFO_BUFFER_SIZE = AddrInfo.getBufferSize();
        private static final int ADDR_INFO_ADDR_BUFFER_SIZE = AddrInfo.getAddrBufferSize();
        public static final int HINTS_ENABLED = 1;
        public static final int HINTS_DISABLED = 2;


        @Override
        public int socket(int proto, int sotype) throws SocketException {
            System.out.println("WasiVirtualSocket::socket()");
    
            validateProto(proto);
            validateSotype(sotype);
    
            int[] newFd = new int[1];
            int errno = Wasi.sockOpen(0, proto, sotype, Address.ofData(newFd));
            if (errno != 0) {
                throw new SocketException("socket: " + errno);
            }
            return newFd[0];
        }

        @Override
        public void connect(int fd, SockAddr sa) throws SocketException {
            System.out.println("WasiVirtualSocket::connect()");

            try {
                Address rawAddr = sa.sockAddr();
                int errno = Wasi.sockConnect(fd, rawAddr);
                if (errno != 0) {
                    throw new SocketException("сonnect: " + errno);
                }
            } catch (Exception e) {
                throw new SocketException("Failed to get SockAddr: " + e.getMessage());
            }
        }

        @Override
        public void bind(int fd, SockAddr sa) throws SocketException {
            System.out.println("WasiVirtualSocket::bind()");

            try {
                Address rawAddr = sa.sockAddr();
                int errno = Wasi.sockBind(fd, rawAddr);
                if (errno != 0) {
                    throw new SocketException("bind: " + errno);
                }
            } catch (Exception e) {
                throw new SocketException("Failed to get SockAddr: " + e.getMessage());
            }
        }
        
        @Override
        public void listen(int fd, int backlog) throws SocketException {
            System.out.println("WasiVirtualSocket::listen()");
            int errno = Wasi.sockListen(fd, backlog);
            if (errno != 0) {
                throw new SocketException("listen: " + errno);
            }
        }

        @Override
        public int sendTo(int fd, byte[] buf, int len, SockAddr sa) throws SocketException {
            Address rawAddr;

            try {
                rawAddr = sa.sockAddr();
            } catch (Exception e) {
                throw new SocketException("Failed to get SockAddr: " + e.getMessage());
            }

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            buffer.order(ByteOrder.nativeOrder());
            buffer.putInt((int) Address.ofData(buf).toLong());
            buffer.putInt(len);

            int[] dataLen = new int[1];
            int errno = Wasi.sockSendTo(fd, Address.ofData(buffer.array()), 1, 0, rawAddr, Address.ofData(dataLen));

            if (errno != 0) {
                throw new SocketException("send_to: " + errno);
            }
            return dataLen[0];
        }

        @Override
        public int recvFrom(int fd, byte[] buf, int len, SockAddr sa) throws SocketException {
            byte[] addr = new byte[ADDR_SIZE];
            Address rawAddr = Address.ofData(addr);

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            buffer.order(ByteOrder.nativeOrder());
            buffer.putInt((int) Address.ofData(buf).toLong());
            buffer.putInt(len);

            int[] dataLen = new int[1];

            int errno = Wasi.sockRecvFrom(fd, Address.ofData(buffer.array()), 1, 0, rawAddr, Address.ofData(dataLen));
            if (errno != 0) {
                throw new SocketException("recv_from: " + errno);
            }
            return dataLen[0];
        }

        @Override
        public void shutdown(int fd, int how) throws SocketException {
            int errno = Wasi.sockShutdown(fd, how);
            if (errno != 0) {
                throw new SocketException("shutdown: " + errno);
            }
        }

        @Override
        public int accept(int fd, int flags) throws SocketException {
            int[] newFd = new int[1];
            int errno = Wasi.sockAccept(fd, flags, Address.ofData(newFd));
            if (errno != 0) {
                throw new SocketException("accept: " + errno);
            }
            return newFd[0];
        }

        @Override
        public SockAddr getSockName(int fd) throws SocketException {
            byte[] addr = new byte[ADDR_SIZE];
            int errno = Wasi.sockAddrLocal(fd, Address.ofData(addr));
            if (errno != 0) {
                throw new SocketException("get_sock_name: " + errno);
            }
            return parseSockAddr(addr);
        }

        @Override
        public SockAddr getPeerName(int fd) throws SocketException {
            byte[] addr = new byte[ADDR_SIZE];
            int errno = Wasi.sockAddrRemote(fd, Address.ofData(addr));
            if (errno != 0) {
                throw new SocketException("get_peer_name: " + errno);
            }
            return parseSockAddr(addr);
        }

        @Override
        public int sockoptSetBroadcast(int fd, int value) throws SocketException {
            int errno = Wasi.sockSetBroadcast(fd, value);
            if (errno != 0) {
                throw new SocketException("sockopt_set_broadcast: " + errno);
            }
            return 0;
        }

        @Override
        public int sockoptSetReuseAddr(int fd, int value) throws SocketException {
            int errno = Wasi.sockSetReuseAddr(fd, value);
            if (errno != 0) {
                throw new SocketException("sockopt_set_reuse_addr: " + errno);
            }
            return 0;
        }
        
        public SockAddr[] getAddrInfo(String name, String service, int proto, int sotype, int hintsEnabled) throws SocketException {
            byte[] nameNT = toByteArrayWithNullTerminator(name);
            byte[] serviceNT = toByteArrayWithNullTerminator(service);

            if (hintsEnabled == HINTS_ENABLED) {
                validateProto(proto);
                validateSotype(sotype);
            }

            AddrInfoHints hints = new AddrInfoHints(sotype, proto, hintsEnabled);
            System.out.println(hints);
            byte[] resultBuffer = new byte[ADDR_INFO_BUFFER_SIZE * MAX_RESOLVED_ADDRESSES];
            int[] resolvedCount = new int[1];

            int errno = Wasi.sockAddrResolve(
                Address.ofData(nameNT),
                Address.ofData(serviceNT),
                hints.getAddress(),
                Address.ofData(resultBuffer),
                resultBuffer.length,
                Address.ofData(resolvedCount)
            );

            if (errno != 0) {
                throw new SocketException("get_addr_info: " + errno);
            }

            SockAddr[] addresses = new SockAddr[resolvedCount[0]];

            for (int i = 0; i < resolvedCount[0]; i++) {
                ByteBuffer buffer = ByteBuffer.wrap(resultBuffer, i * ADDR_INFO_BUFFER_SIZE, ADDR_INFO_BUFFER_SIZE).order(ByteOrder.nativeOrder());
            
                int sockKind = buffer.getInt();
                byte[] addrBuf = new byte[ADDR_INFO_ADDR_BUFFER_SIZE];
                buffer.get(addrBuf);
                int sockType = buffer.getInt();

                AddrInfo addrInfo = new AddrInfo(sockKind, addrBuf, sockType);
                addresses[i] = parseSockAddr(addrInfoToRaw(addrInfo));
            }

            return addresses;
        }
































        // ============================== Helpers ==============================


        private void validateProto(int proto) throws SocketException {
            if (!isValidProto(proto)) {
                throw new SocketException("Invalid protocol type: " + proto);
            }
        }
    
        private void validateSotype(int sotype) throws SocketException {
            if (!isValidSotype(sotype)) {
                throw new SocketException("Invalid socket type: " + sotype);
            }
        }

        private boolean isValidProto(int proto) {
            return proto == AF_INET || proto == AF_INET6 || proto == AF_UNIX;
        }
    
        private boolean isValidSotype(int sotype) {
            return sotype == SOCK_ANY || sotype == SOCK_DGRAM || sotype == SOCK_STREAM;
        }

        public static byte[] toByteArrayWithNullTerminator(String input) {
            if (input == null) {
                return new byte[]{0};
            }
    
            byte[] originalBytes = input.getBytes();
            byte[] result = new byte[originalBytes.length + 1];
            System.arraycopy(originalBytes, 0, result, 0, originalBytes.length);
            result[result.length - 1] = 0;
            return result;
        }

        private static SockAddr parseSockAddr(byte[] data) throws SocketException {
            if (data.length != ADDR_SIZE) {
                throw new SocketException("Expected " + ADDR_SIZE + " bytes of data, but got " + data.length);
            }

            ByteBuffer buffer = ByteBuffer.wrap(data).order(ByteOrder.nativeOrder());
            int kind = buffer.getInt();

            if (kind == AF_INET) {
                byte[] addr = new byte[IPV4_ADDR_SIZE];
                buffer.get(addr);
                short port = buffer.getShort();
                return new SockAddrInet4(addr, port);
            } else if (kind == AF_INET6) {
                short[] addr = new short[IPV6_ADDR_SIZE];
                for (int i = 0; i < IPV6_ADDR_SIZE; i++) {
                    addr[i] = buffer.getShort();
                }
                short port = buffer.getShort();
                return new SockAddrInet6(addr, port);
            } else {
                throw new SocketException("Unknown address family: " + kind);
            }
        }

        private byte[] addrInfoToRaw(AddrInfo addrInfo) {
            ByteBuffer buffer = ByteBuffer.allocate(ADDR_SIZE).order(ByteOrder.nativeOrder());
            buffer.putInt(addrInfo.getSockKind());
            buffer.put(addrInfo.getAddrBuf());
            return buffer.array();
        }

}