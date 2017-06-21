package com.CYinHttpClient.download;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunrongxin on 2017/6/21.
 */

public class ThreadPoolTest {
	public static void main(String args[]){
		final ArrayBlockingQueue queue = new ArrayBlockingQueue<Runnable>(10);
		ThreadPoolExecutor threadPoolExecutor =
				//核心线程数：即初始生成的线程数，
				new ThreadPoolExecutor(2, 4, 60, TimeUnit.MILLISECONDS, queue);

		for (int i=0; i<20; i++){
			final int index = i;
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("index："+index+" queue size ="+queue.size());
				}
			});
		}
	}
}
