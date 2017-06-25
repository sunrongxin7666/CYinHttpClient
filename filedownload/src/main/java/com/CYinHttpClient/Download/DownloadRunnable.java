package com.CYinHttpClient.Download;

import com.CYinHttpClient.Download.File.FileStorageManager;
import com.CYinHttpClient.Download.Http.DownloadCallback;
import com.CYinHttpClient.Download.Http.HttpManager;
import com.CYinHttpClient.Download.Utils.Logger;
import com.CYinHttpClient.Download.db.DownloadHelper;
import com.android.srx.github.dbgenerator.DownloadEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * Created by sunrongxin on 2017/6/19.
 */

public class DownloadRunnable implements Runnable {
	private long mStart;
	private long mEnd;
	private String mUrl;
	private DownloadCallback mCallback;
	private DownloadEntity mEntity;
	public DownloadRunnable(long start, long end, String url, DownloadCallback callback) {
		mStart = start;
		mEnd = end;
		mUrl = url;
		mCallback = callback;
	}

	public DownloadRunnable(long start, long end, String url, DownloadCallback callback, DownloadEntity entity) {
		mStart = start;
		mEnd = end;
		mUrl = url;
		mCallback = callback;
		mEntity = entity;
	}

	@Override
	public void run() {
		Response response = HttpManager.getInstance().syncRequestByRange(mUrl, mStart, mEnd);
		if (response == null && mCallback != null) {
			mCallback.fail(HttpManager.NETWORK_ERROR_CODE, "网络出问题了");
		}
		File file = FileStorageManager.getInstance().getFileByName(mUrl);

		long finshProgress = mEntity.getProgress_position() == 0 ? 0 : mEntity.getProgress_position();
		long progress = 0;
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
			//很重要的；偏移量
			randomAccessFile.seek(mStart);
			byte[] buffer = new byte[1024 * 500];
			int len;
			InputStream inStream = response.body().byteStream();
			while ((len = inStream.read(buffer, 0, buffer.length)) != -1) {
				randomAccessFile.write(buffer, 0, len);
				progress += len;
				mEntity.setProgress_position(progress);
				Logger.debug("nate", "progress  ----->" + progress);
			}

			mEntity.setProgress_position(mEntity.getProgress_position() + finshProgress);
			randomAccessFile.close();
			mCallback.success(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			long id = DownloadHelper.getInstance().insert(mEntity);
			mEntity.setId(id);
		}

	}
}
