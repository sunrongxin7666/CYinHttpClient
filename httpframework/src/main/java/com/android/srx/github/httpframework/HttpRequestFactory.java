package com.android.srx.github.httpframework;

import com.android.srx.github.httpframework.http.HttpMethod;
import com.android.srx.github.httpframework.http.HttpRequest;

import java.io.IOException;
import java.net.URI;

/**
 * Created by sunrongxin on 2017/7/5.
 */
public interface HttpRequestFactory {

	HttpRequest createHttpRequest(URI uri, HttpMethod method) throws IOException;
}
