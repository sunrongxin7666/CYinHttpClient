package com.android.srx.github.cyinhttpclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.CYinHttpClient.Download.DownloadManager;
import com.CYinHttpClient.Download.File.FileStorageManager;
import com.CYinHttpClient.Download.Http.DownloadCallback;
import com.CYinHttpClient.Download.Utils.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.image_view);

		File file = FileStorageManager.getInstance().getFileByName("fileStoragetest");
		Logger.debug("SRX",file.getAbsolutePath());
		final String url = "http://szimg.mukewang.com/5763765d0001352105400300-360-202.jpg";

		DownloadManager.getInstance().download(url, new DownloadCallback() {
            @Override
            public void success(File file) {
	            final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
	            runOnUiThread(new Runnable() {
		            @Override
		            public void run() {
			            mImageView.setImageBitmap(bitmap);
		            }
	            });
	            Logger.debug("nate", "success " + file.getAbsoluteFile());

            }

            @Override
            public void fail(int errorCode, String errorMessage) {
                Logger.debug("nate", "fail " + errorCode + "  " + errorMessage);
            }

            @Override
            public void progress(int progress) {
                Logger.debug("nate", "progress    " + progress);
                //mProgress.setProgress(progress);

            }
        });
	}
}
