package com.android.srx.github.httpframework;

import com.android.srx.github.httpframework.http.HttpHeader;
import com.android.srx.github.httpframework.http.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class BufferHttpRequest extends AbstractHttpRequest {

	private ByteArrayOutputStream mByteArray = new ByteArrayOutputStream();

	protected OutputStream getBodyOutputStream() {
		return mByteArray;
	}

	protected HttpResponse executeInternal(HttpHeader header) throws IOException {
		byte[] data = mByteArray.toByteArray();
		return executeInternal(header, data);
	}

	protected abstract HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException;
}
