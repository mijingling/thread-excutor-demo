/*
 * Copyright (c) 2014
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 이 클래스는...
 *
 * @author yoots
 * Created on 2014. 8. 20.
 */

public class Runner implements Runnable {

	private String threadName; // 작업명
	
	private Integer timeOut; // 작업 타임아웃 시간
	

	// 작업 객체를 생성함
	private RunnerExecutor executor = RunnerExecutor.getInstance();
	
	private List<CallableRunner> workList = new ArrayList<CallableRunner>();

	public Runner(String threadName, Integer timeOut) {
		this.threadName = threadName;
		this.timeOut = timeOut;
	}


	/**
	 * 작업을 강제 종료시킴
	 * @param pool
	 */
	public void shutdownAndAwaitTermination(ExecutorService pool) {

		pool.shutdown();
		
		try {
			
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if (!pool.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println(this.threadName + " did not terminate.");
			}
			
		} catch (InterruptedException ie) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
		
		System.err.println(this.threadName + " did terminate.");
	}
	
	/**
	 * 작업 실행 메소드
	 */
	public void run() {

		workList.add(new CallableRunner(this.threadName + " - 0"));
		workList.add(new CallableRunner(this.threadName + " - 1"));
		workList.add(new CallableRunner(this.threadName + " - 2"));

		try {
			
			// 작업 실행기에 타임아웃 제한 시간을 설정한 후 실행함
			//executor.invokeAll(workList, this.timeOut, TimeUnit.SECONDS);
			
			// 작업 실행함
			executor.invokeAll(workList);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			shutdownAndAwaitTermination(executor.getExecutor());
			
		} catch (NullPointerException e) {
			
			e.printStackTrace();
			shutdownAndAwaitTermination(executor.getExecutor());
			
		} catch (RejectedExecutionException e) {
			
			e.printStackTrace();
			shutdownAndAwaitTermination(executor.getExecutor());
			
		}

		// 작업 실행이 완료됨
		System.out.println(this.threadName + " did complete.");
		
		executor.shutdown();
	}

}