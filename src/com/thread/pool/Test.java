/*
 * Copyright (c) 2014 
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.thread.pool;

/**
 * 이 클래스는...
 *
 * @author yoots
 * Created on 2014. 8. 20.
 */

public class Test {
	
	public static void main(String[] s){
		
		ThreadExecutor executor = ThreadExecutor.getInstance();
		
		 try
	        {
	            // 서비스 풀 객체에 작업 실행 객체를 추가함
	            executor.execute(new Runner("Worker1", 60));
	            executor.execute(new Runner("Worker2", 80));
	            executor.execute(new Runner("Worker3", 70));
	            executor.execute(new Runner("Worker4", 50));
	        	
//	        	for(int i = 0 ; i < 100 ; i++)
//	        		executor.execute("thread worker >>>>> 1");
//	        	
//	        	for(int i = 0 ; i < 100 ; i++)
//	        		executor.execute("thread worker >>>>> 2");
//	        	
//	        	for(int i = 0 ; i < 100 ; i++)
//	        		executor.execute("thread worker >>>>> 4");
//	        	
//	        	for(int i = 0 ; i < 100 ; i++)
//	        		executor.execute("thread worker >>>>> 3");
//	        	
//	        	for(int i = 0 ; i < 100 ; i++)
//	        		executor.execute("thread worker >>>>> 6");
//	        	
//	        	for(int i = 0 ; i < 100 ; i++)
//	        		executor.execute("thread worker >>>>> 5");
	             
	        } catch (Exception e) {
	            executor.shutdown();
	        }
	        
	        executor.shutdown();
	        
	        System.out.println(" ThreadExecutor end. ");
	}

}
