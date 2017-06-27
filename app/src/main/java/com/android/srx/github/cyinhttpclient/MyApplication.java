package com.android.srx.github.cyinhttpclient;

import android.app.Application;

import com.CYinHttpClient.Download.DownloadConfig;
import com.CYinHttpClient.Download.DownloadManager;
import com.CYinHttpClient.Download.File.FileStorageManager;
import com.CYinHttpClient.Download.Http.HttpManager;
import com.CYinHttpClient.Download.db.DownloadHelper;
import com.facebook.stetho.Stetho;

/**
 * Created by Sun Rx on 2017/6/15.
 */

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		FileStorageManager.getInstance().init(this);
		HttpManager.getInstance().init(this);

		DownloadConfig config = new DownloadConfig.Builder()
				                        .setCoreThreadSize(2)
				                        .setMaxThreadSize(4)
				                        .setLocalProgressThreadSize(1)
				                        .builder();
		DownloadManager.getInstance().init(config);

		DownloadHelper.getInstance().init(this);
		Stetho.initializeWithDefaults(this);
		//LeakCanary.install(this);
	}
}
