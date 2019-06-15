package test.WorkStealingPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WorkItem implements Callable<String> {

	private String message;
	private int millisecondsDelay;
	private int id;

	public WorkItem(String message, int id, int delay) {
		this.message = message;
		this.millisecondsDelay = delay;
		this.id = id;
	}

	public String call() throws Exception {
		System.out.println("running: " + message);
		TimeUnit.MILLISECONDS.sleep(millisecondsDelay);
		
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i=0; i<10; i++) {
			futures.add(Worker.addWork(new WorkItem(message + " " + i, id+i, 100)));
		}
		for (Future<String> future : futures) {
			System.out.println("child: " + future.get());
		}
		
		return message;
	}

}
