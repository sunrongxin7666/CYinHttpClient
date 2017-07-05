package com.android.srx.github.httpframework;

import com.android.srx.github.httpframework.http.HttpMethod;
import com.android.srx.github.httpframework.http.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * Created by sunrongxin on 2017/7/5.
 */

public class OriginHttpRequestFactory implements HttpRequestFactory {

	private HttpURLConnection mConnection;

	public OriginHttpRequestFactory() {

	}

	public void setReadTimeOut(int readTimeOut) {
		mConnection.setReadTimeout(readTimeOut);
	}

	public void setConnectionTimeOut(int connectionTimeOut) {
		mConnection.setConnectTimeout(connectionTimeOut);
	}

	@Override
	public HttpRequest createHttpRequest(URI uri, HttpMethod method) throws IOException {
		mConnection = (HttpURLConnection) uri.toURL().openConnection();
		return new OriginHttpRequest(mConnection, method, uri.toString());
	}
}
