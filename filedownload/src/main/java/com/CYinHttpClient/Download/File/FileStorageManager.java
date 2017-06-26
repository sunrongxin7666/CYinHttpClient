package com.CYinHttpClient.Download.File;

import android.content.Context;
import android.os.Environment;

import com.CYinHttpClient.Download.Utils.Md5Utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by sunrongxin on 2017/6/15.
 */

public class FileStorageManager {
	private static final FileStorageManager sManager = new FileStorageManager();

	private Context mContext;

	public static FileStorageManager getInstance() {
		return sManager;
	}

	private FileStorageManager() {

	}

	public void init(Context context) {
		this.mContext = context;
	}


	public File getFileByName(String url) {

		File parent;

		// if SDCard here
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			parent = mContext.getExternalCacheDir();
		} else {
			parent = mContext.getCacheDir();
		}

		String fileName = Md5Utils.generateCode(url);

		File file = new File(parent, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{
			//file.delete();
		}

		return file;

	}
}
