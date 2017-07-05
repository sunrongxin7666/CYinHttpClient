package com.android.srx.github.httpframework.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by sunrongxin on 2017/7/5.
 */

public interface HttpRequest extends Header {

	HttpMethod getMethod();

	URI getUri();

	OutputStream getBody();

	HttpResponse execute() throws IOException;

}
