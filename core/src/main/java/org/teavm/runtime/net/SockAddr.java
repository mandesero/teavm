package org.teavm.runtime.net;

import org.teavm.interop.Address; 
import java.net.SocketException;

public interface SockAddr {

        Address sockAddr() throws SocketException;

        int sockPort();

        int sockFamily();

}