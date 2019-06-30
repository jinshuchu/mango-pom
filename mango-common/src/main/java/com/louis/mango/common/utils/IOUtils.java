package com.louis.mango.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Description:
 * @Author: created by wangkaishuang on 2019-06-23
 */
public class IOUtils {

	/**
	 * 关闭对象，连接
	 * @param closeable
	 */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}
