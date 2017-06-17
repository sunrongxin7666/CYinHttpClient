package com.CYinHttpClient.Download.Http;

import java.io.File;

/**
 * Created by sunrongxin on 2017/6/17.
 */

public interface DownloadCallback {
	void success(File file);

	void fail(int errorCode, String errorMessage);

	void progress(int progress);
}
