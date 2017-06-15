package com.android.srx.github.cyinhttpclient;

import android.app.Application;

import com.CYinHttpClient.Download.File.FileStorageManager;

/**
 * Created by sunrongxin on 2017/6/15.
 */

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		FileStorageManager.getInstance().init(this);
	}
}
