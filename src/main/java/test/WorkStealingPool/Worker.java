package test.WorkStealingPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Worker {
	private static ExecutorService executorService;
	
	public enum PoolType {
		WorkStealing,
		FixedThread
	}
	
	public Worker() {
		this(PoolType.WorkStealing);
	}
	
	public Worker(PoolType type) {
		 if (type == PoolType.WorkStealing)
			 executorService = Executors.newWorkStealingPool(4);
		 else
			 executorService = Executors.newFixedThreadPool(4);
	}
	
	public static Future<String> addWork(Callable<String> item) throws Exception {
		return executorService.submit(item);
	}
	
	public void generateWork() {
		List<Callable<String>> items = new ArrayList<>(); 
		for (int i=0; i<10; i++) {
			items.add(new WorkItem("message" + i, i, 100));
		}
		try {
			System.out.println("about to invoke");
			List<Future<String>> results = executorService.invokeAll(items);
			for (Future<String> future : results) {
				String result = future.get();
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
