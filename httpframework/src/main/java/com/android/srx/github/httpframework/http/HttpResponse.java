package com.android.srx.github.httpframework.http;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sunrongxin on 2017/7/5.
 */

public interface HttpResponse extends Header, Closeable {

	HttpStatus getStatus();

	String getStatusMsg();

	InputStream getBody() throws IOException;

	void close();

	long getContentLength();

}
