package com.CYinHttpClient.Download;

import com.CYinHttpClient.Download.Http.DownloadCallback;
import com.CYinHttpClient.Download.Http.HttpManager;
import com.CYinHttpClient.Download.Utils.Logger;
import com.CYinHttpClient.Download.db.DownloadHelper;
import com.android.srx.github.dbgenerator.DownloadEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by sunrongxin on 2017/6/19.
 */

public class DownloadManager {
	private static final int MAX_THREAD = 2  ;
	private static final String TAG = "SRX" ;
	private static DownloadManager sDownloadManage = new DownloadManager();
	private HashSet<DownloadTask> mHashSet = new HashSet<>();
	//线程池
	private static ThreadPoolExecutor sThreadPool =
			new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 60, TimeUnit.MILLISECONDS,
					                      new LinkedBlockingDeque<Runnable>(),
					                      new ThreadFactory() {
		private AtomicInteger mInteger = new AtomicInteger(1);

		@Override
		public Thread newThread(Runnable runnable) {
			//对线程统一编号管理
			Thread thread = new Thread(runnable, "download thread #" + mInteger.getAndIncrement());
			return thread;
		}
	});
	private List<DownloadEntity> mCache;
	private long mLength;

	public static DownloadManager getInstance(){
		return sDownloadManage;
	}

	private DownloadManager(){
	}

	public void finsh(DownloadTask task){
		mHashSet.remove(task);
	}
	public void download(final String url, final DownloadCallback callback){
		final DownloadTask downloadTask = new DownloadTask(url, callback);
		if(mHashSet.contains(downloadTask)){
			callback.fail(HttpManager.TASK_RUNNING_ERROR_CODE,"Task is running");
		}
		mHashSet.add(downloadTask);

		mCache = DownloadHelper.getInstance().getAll(url);
		if(mCache == null || mCache.size()==0){
			HttpManager.getInstance().asyncRequest(url, new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					callback.fail(HttpManager.NETWORK_ERROR_CODE,"网络错误");
					finsh(downloadTask);
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if(!response.isSuccessful()&&callback!=null){
						callback.fail(HttpManager.NETWORK_ERROR_CODE,"网络错误");
						return;
					}
					long length = response.body().contentLength();
					Logger.debug(TAG,"Contentlength："+length);
					if(length == -1){
						callback.fail(HttpManager.CONTENT_LENGTH_ERROR_CODE,"无法获取网络长度");
					}
					processDownload(url,length,callback, mCache);
					finsh(downloadTask);
				}
			});
		}
		else { // cache is here
			for (int i = 0; i < mCache.size(); i++) {
				DownloadEntity entity = mCache.get(i);
				if (i == mCache.size() - 1) {
					mLength = entity.getEnd_position() + 1;
				}
				long startSize = entity.getStart_position() + entity.getProgress_position();
				long endSize = entity.getEnd_position();
				sThreadPool.execute(new DownloadRunnable(startSize, endSize, url, callback, entity));
			}
		}

	}

	private void processDownload(String url, long length, DownloadCallback callback, List<DownloadEntity> cache) {

		long threadDownloadSize = length / MAX_THREAD;
		if(cache == null || cache.size()==0){
			cache  = new ArrayList<>();
		}

		for (int i = 0; i < MAX_THREAD ; i++) {
			processByOneThread(url, length, callback, threadDownloadSize, i);
		}
	}

	private void processByOneThread(String url, long length, DownloadCallback callback, long threadDownloadSize, int i) {

		long startSize = i * threadDownloadSize;
		long endSize = 0;
		if (endSize == MAX_THREAD - 1) {
			endSize = length - 1;
		} else {
			endSize = (i + 1) * threadDownloadSize - 1;
		}
		DownloadEntity entity = new DownloadEntity(i,startSize,endSize,0,url,i+1);
		sThreadPool.execute(new DownloadRunnable(startSize,endSize,url,callback,entity));
	}

}
