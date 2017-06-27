package com.android.srx.github.cyinhttpclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.CYinHttpClient.Download.DownloadManager;
import com.CYinHttpClient.Download.File.FileStorageManager;
import com.CYinHttpClient.Download.Http.DownloadCallback;
import com.CYinHttpClient.Download.Utils.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {
	private ImageView mImageView;
	private ProgressBar mProgress;

	private volatile Integer count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.image_view);
		mProgress = (ProgressBar) findViewById(R.id.progress);

		//File file = FileStorageManager.getInstance().getFileByName("fileStoragetest");
		//Logger.debug("SRX",file.getAbsolutePath());
		//final String url = "http://szimg.mukewang.com/5763765d0001352105400300-360-202.jpg";
		final String url = "http://shouji.360tpcdn.com/160901/84c090897cbf0158b498da0f42f73308/com.icoolme.android.weather_2016090200.apk";
		//FileStorageManager.getInstance().deleteByName(url);
		DownloadManager.getInstance().download(url, new DownloadCallback() {
            @Override
            public void success(File file) {
	            synchronized (count) {
		            if (count < 1) {
			            count++;
			            return;
		            }
	            }
	            installApk(file);
//	            final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//	            runOnUiThread(new Runnable() {
//		            @Override
//		            public void run() {
//			            mImageView.setImageBitmap(bitmap);
//		            }
//	            });
	            Logger.debug("nate", "success " + file.getAbsoluteFile());

            }

            @Override
            public void fail(int errorCode, String errorMessage) {
                Logger.debug("nate", "fail " + errorCode + "  " + errorMessage);
            }

            @Override
            public void progress(int progress) {
                Logger.debug("nate", "progress    " + progress);
                mProgress.setProgress(progress);

            }
        });
	}
	private void installApk(File file) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://" + file.getAbsoluteFile().toString()), "application/vnd.android.package-archive");
		MainActivity.this.startActivity(intent);
	}
}
