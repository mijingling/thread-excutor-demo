/*
 * Copyright (c) 2014
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.thread.pool;

import java.util.Date;

/**
 * 이 클래스는...
 *
 * @author yoots
 * Created on 2014. 8. 20.
 */

public class Worker implements Runnable{
	
	private String strData;
	
	public Worker(){}
	
	public Worker(String data){
		this.strData = data;
	}
	
	public void run(){
		
		try{
			
			Thread.sleep(1000);
			
			System.out.println("======================[ "+(new Date())+" / "+this.strData+" ]============================");
			
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
