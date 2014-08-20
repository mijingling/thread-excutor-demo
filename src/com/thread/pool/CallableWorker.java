/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.thread.pool;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 이 클래스는...
 *
 * @author yoots
 * Created on 2014. 8. 20.
 */

public class CallableWorker  implements Callable<Integer> {

	private String strData;      // worker data
    
    public CallableWorker(String threadName)
    {
        this.strData = threadName;
    }
 
    /**
     * 작업을 수행함
     */
    public Integer call() throws Exception {
         
        Integer result = 1;
         
        for (int i=0 ; i < 20 ; i++)
        {
            try {
                
            	Thread.sleep(500);
                
            	System.out.println("======================[ "+(new Date())+" / "+this.strData+" ]============================");
                
            } catch (InterruptedException e) {
                e.printStackTrace();
                result = 0;
                return result;
            }
        }
        
        return result;
    }
}
