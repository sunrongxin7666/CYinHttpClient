package com.CYinHttpClient.download;

/**
 * Created by sunrongxin on 2017/6/21.
 */

public class ThreadPoolTest {
	static class RunnableToStop implements Runnable{
		@Override
		public void run() {
			//是否被停止或者中断
			while (flag||!Thread.interrupted()){
				System.out.println("running");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
					//处理中断异常；
					System.out.println("Interrupt!");
					break;
					//return
				}
			}
			System.out.println("Stop!");
		}

		public void stop() {
			flag = false;
		}

		public void stopWithInterrupt(Thread thread){
			thread.interrupt();
			stop();
		}

		private volatile Boolean flag = true;
	}
	public static void main(String args[]) throws InterruptedException {
//		final LinkedBlockingDeque queue = new LinkedBlockingDeque<Runnable>(10);
//		ThreadPoolExecutor threadPoolExecutor =
//				//核心线程数：即初始生成的线程数，
//				new ThreadPoolExecutor(2, 4, 60, TimeUnit.MILLISECONDS, queue);
//
//		for (int i=0; i<20; i++){
//			final int index = i;
//			threadPoolExecutor.execute(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println("index："+index+" queue size ="+queue.size());
//				}
//			});
//		}
		RunnableToStop runnable = new RunnableToStop();

		Thread thread = new Thread(runnable);
		thread.start();
		Thread.sleep(1000);
		runnable.stopWithInterrupt(thread);
	}


}
