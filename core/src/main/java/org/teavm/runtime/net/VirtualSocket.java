package org.teavm.runtime.net;

import java.net.SocketException;
import java.util.List;

public interface VirtualSocket {

        int socket(int proto, int sotype) throws SocketException;

        void connect(int fd, SockAddr sa) throws SocketException;

        void bind(int fd, SockAddr sa) throws SocketException;

        void listen(int fd, int backlog) throws SocketException;

        int sendTo(int fd, byte[] buf, int len, SockAddr sa) throws SocketException;
        
        int recvFrom(int fd, byte[] buf, int len, SockAddr sa) throws SocketException;

        void shutdown(int fd, int how) throws SocketException;

        int accept(int fd, int flags) throws SocketException;

        SockAddr getSockName(int fd) throws SocketException;
        
        SockAddr getPeerName(int fd) throws SocketException;
        
        int sockoptSetBroadcast(int fd, int value) throws SocketException;

        int sockoptSetReuseAddr(int fd, int value) throws SocketException;

        SockAddr[] getAddrInfo(String name, String service, int proto, int sotype, int hints_enabled) throws SocketException;
}