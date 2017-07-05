package com.android.srx.github.httpframework;

import com.android.srx.github.httpframework.http.HttpMethod;
import com.android.srx.github.httpframework.http.HttpRequest;
import com.android.srx.github.httpframework.utils.Utills;

import java.io.IOException;
import java.net.URI;

/**
 * Created by sunrongxin on 2017/7/5.
 */
public class HttpRequestProvider {

	private static boolean OKHTTP_REQUEST = Utills.isExist("okhttp3.OkHttpClient", HttpRequestProvider.class.getClassLoader());

	private HttpRequestFactory mHttpRequestFactory;

	public HttpRequestProvider() {
		if (OKHTTP_REQUEST) {
			mHttpRequestFactory = new OkHttpRequestFactory();
		} else {
			mHttpRequestFactory = new OriginHttpRequestFactory();
		}
	}

	public HttpRequest getHttpRequest(URI uri, HttpMethod httpMethod) throws IOException {
		return mHttpRequestFactory.createHttpRequest(uri, httpMethod);
	}

	public HttpRequestFactory getHttpRequestFactory() {
		return mHttpRequestFactory;
	}

	public void setHttpRequestFactory(HttpRequestFactory httpRequestFactory) {
		mHttpRequestFactory = httpRequestFactory;
	}
}
