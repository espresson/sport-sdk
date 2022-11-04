package com.udisk.lib;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by junx on 2017/9/8.
 */

public final class IOCloseUtils {

    private IOCloseUtils() {
    }

    @Deprecated
    public static final void closeStream(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (null != closeable) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    closeable = null;
                }
            }
        }
    }

    public static final void onlyCloseStream(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (null != closeable) {
                try {
                    closeable.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
