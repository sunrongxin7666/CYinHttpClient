package com.android.srx.github.cyinhttpclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.CYinHttpClient.Download.File.FileStorageManager;
import com.CYinHttpClient.Download.Utils.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		File file = FileStorageManager.getInstance().getFileByName("fileStoragetest");
		Logger.debug("SRX",file.getAbsolutePath());
	}
}
