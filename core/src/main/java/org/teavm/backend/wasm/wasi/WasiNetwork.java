package org.teavm.backend.wasm.wasi;

public class WasiNetwork {

    private WasiSocket wasiSocket = new WasiSocket();
    private int socketFd;

    public WasiNetwork() {
    }

    /**
     * Opens a socket with specified address family and socket type.
     *
     * @param family   The address family (INET4 or INET6).
     * @param sockType The socket type (SOCKET_STREAM or SOCKET_DGRAM).
     * @return true if the socket was opened successfully, false otherwise.
     */
    public boolean openSocket(WasiSocket.WasiAddressFamily family, WasiSocket.WasiSockType sockType) {
        int[] fd = new int[1];
        int result = wasiSocket.sockOpen(0, family, sockType, fd);
        if (result == 0) {
            this.socketFd = fd[0];
            return true;
        }
        return false;
    }

    /**
     * Binds the socket to a local address and port.
     *
     * @param addr The local address to bind to.
     * @param port The local port to bind to.
     * @return true if binding was successful, false otherwise.
     */
    public boolean bind(String addr, short port) {
        WasiSocket.WasiAddr wasiAddr = createIp4Addr(addr, port);
        int result = wasiSocket.sockBind(socketFd, wasiAddr);
        return result == 0;
    }

    /**
     * Connects the socket to a remote address and port.
     *
     * @param addr The remote address to connect to.
     * @param port The remote port to connect to.
     * @return true if the connection was successful, false otherwise.
     */
    public boolean connect(String addr, short port) {
        WasiSocket.WasiAddr wasiAddr = createIp4Addr(addr, port);
        int result = wasiSocket.sockConnect(socketFd, wasiAddr);
        return result == 0;
    }

    /**
     * Sends data to a connected remote address.
     *
     * @param data The data to send.
     * @return Number of bytes sent, or -1 if an error occurred.
     */
    public int send(byte[] data) {
        int[] sentDataLen = new int[1];
        int result = wasiSocket.sockSendTo(socketFd, data, data.length, 0, null, sentDataLen);
        return result == 0 ? sentDataLen[0] : -1;
    }

    /**
     * Receives data from the connected socket.
     *
     * @param buffer The buffer to store received data.
     * @return Number of bytes received, or -1 if an error occurred.
     */
    public int receive(byte[] buffer) {
        int[] recvDataLen = new int[1];
        int result = wasiSocket.sockRecvFrom(socketFd, buffer, buffer.length, 0, null, recvDataLen);
        return result == 0 ? recvDataLen[0] : -1;
    }

    /**
     * Sets a socket option for reuse address.
     *
     * @param reuse True to enable address reuse, false to disable.
     * @return true if successful, false otherwise.
     */
    public boolean setReuseAddress(boolean reuse) {
        int result = wasiSocket.sockSetReuseAddr(socketFd, reuse);
        return result == 0;
    }

    /**
     * Closes the socket.
     */
    public void close() {
        wasiSocket.sockClose(socketFd);
    }

    /**
     * Creates a WasiAddr object for IPv4 addresses.
     *
     * @param ip   The IP address in string format.
     * @param port The port number.
     * @return A WasiAddr object.
     */
    private WasiSocket.WasiAddr createIp4Addr(String ip, short port) {
        String[] octets = ip.split("\\.");
        WasiSocket.WasiAddrIp4 ip4 = new WasiSocket.WasiAddrIp4();
        ip4.n0 = Byte.parseByte(octets[0]);
        ip4.n1 = Byte.parseByte(octets[1]);
        ip4.n2 = Byte.parseByte(octets[2]);
        ip4.n3 = Byte.parseByte(octets[3]);

        WasiSocket.WasiAddrIp4Port addrIp4Port = new WasiSocket.WasiAddrIp4Port();
        addrIp4Port.addr = ip4;
        addrIp4Port.port = port;

        WasiSocket.WasiAddr addr = new WasiSocket.WasiAddr();
        addr.kind = WasiSocket.WasiAddressFamily.INET4;
        addr.addr = addrIp4Port;
        return addr;
    }
}
