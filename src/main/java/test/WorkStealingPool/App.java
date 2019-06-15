package test.WorkStealingPool;

import java.util.concurrent.TimeUnit;

import test.WorkStealingPool.Worker.PoolType;

public class App {
	
	private static void startTerminator() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(10);
					System.out.println("terminating");
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "Terminate").start();
	}
	
	public static void main(String[] args) {
		//stop program running forever
		startTerminator();

		// switch between fixed pool and work stealing
		//new Worker(PoolType.FixedThread).generateWork();
		new Worker(PoolType.WorkStealing).generateWork();
	}
}
