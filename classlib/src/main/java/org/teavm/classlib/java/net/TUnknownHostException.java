package org.teavm.classlib.java.net;

import java.io.IOException;

public class TUnknownHostException extends IOException {
        public TUnknownHostException(String message) {
            super(message);
        }
    
        public TUnknownHostException() { }
}