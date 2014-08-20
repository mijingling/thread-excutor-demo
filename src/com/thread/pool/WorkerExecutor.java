/*
 * Copyright (c) 2014
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.thread.pool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 이 클래스는 싱글톤으로 작성.
 *
 * @author yoots
 * Created on 2014. 8. 20.
 */

public class WorkerExecutor {
	
	private static final int MAX_POOL = 5;
	
	//싱글톤 인스턴스
	private volatile static WorkerExecutor instance ;
	
	//ThreadPool Size 를 지정한다.
    private volatile static ExecutorService executor = Executors.newFixedThreadPool(MAX_POOL);
	
	/**
	 * 생성자...
	 */
	private WorkerExecutor(){}

	/**
	 * <pre>
	 * 객체 전달...
	 * @return
	 * </pre>
	 */
	public static WorkerExecutor getInstance(){ 
		
		if(instance == null){
			
			synchronized(WorkerExecutor.class) {
				
				if (instance == null) {
                	instance = new WorkerExecutor();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 
	 *
	 * @return
	 */
	public ExecutorService getExecutor(){
		
		return this.executor;
	}

	/**
	 * @param timeout
	 * @param unit
	 * @return
	 * @throws InterruptedException
	 * @see java.util.concurrent.ExecutorService#awaitTermination(long, java.util.concurrent.TimeUnit)
	 */
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return executor.awaitTermination(timeout, unit);
	}

	/**
	 * 
	 *
	 * @param request
	 */
	public void execute(String request) {
		this.execute(new Worker(request));
	}
	
	/**
	 * @param command
	 * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
	 */
	public void execute(Runnable command) {
		
		System.out.println("WorkerExecutor is execute.");
		
		executor.execute(command);
	}

	/**
	 * @return
	 * @see java.util.concurrent.ExecutorService#isShutdown()
	 */
	public boolean isShutdown() {
		return executor.isShutdown();
	}

	/**
	 * @return
	 * @see java.util.concurrent.ExecutorService#isTerminated()
	 */
	public boolean isTerminated() {
		return executor.isTerminated();
	}

	/**
	 * 
	 * @see java.util.concurrent.ExecutorService#shutdown()
	 */
	public void shutdown() {
		executor.shutdown();
	}

	/**
	 * @return
	 * @see java.util.concurrent.ExecutorService#shutdownNow()
	 */
	public List<Runnable> shutdownNow() {
		return executor.shutdownNow();
	}

	/**
	 * @param task
	 * @return
	 * @see java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)
	 */
	public <T> Future<T> submit(Callable<T> task) {
		return executor.submit(task);
	}

	/**
	 * @param task
	 * @param result
	 * @return
	 * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable, java.lang.Object)
	 */
	public <T> Future<T> submit(Runnable task, T result) {
		return executor.submit(task, result);
	}

	/**
	 * @param task
	 * @return
	 * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable)
	 */
	public Future<?> submit(Runnable task) {
		return executor.submit(task);
	}

	/**
	 * @param tasks
	 * @param timeout
	 * @param unit
	 * @return
	 * @throws InterruptedException
	 * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection, long, java.util.concurrent.TimeUnit)
	 */
	public <T> List<Future<T>> invokeAll(
			Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		return executor.invokeAll(tasks, timeout, unit);
	}

	/**
	 * @param tasks
	 * @return
	 * @throws InterruptedException
	 * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection)
	 */
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
			throws InterruptedException {
		
		System.out.println("WorkerExecutor is execute. >>  " + tasks.size());
		
		return executor.invokeAll(tasks);
	}

	/**
	 * @param tasks
	 * @param timeout
	 * @param unit
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 * @see java.util.concurrent.ExecutorService#invokeAny(java.util.Collection, long, java.util.concurrent.TimeUnit)
	 */
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
			long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return executor.invokeAny(tasks, timeout, unit);
	}

	/**
	 * @param tasks
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @see java.util.concurrent.ExecutorService#invokeAny(java.util.Collection)
	 */
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
			throws InterruptedException, ExecutionException {
		return executor.invokeAny(tasks);
	}
	
	
}
