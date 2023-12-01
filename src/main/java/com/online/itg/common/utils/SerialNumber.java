package com.online.itg.common.utils;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: SerialNumber.java
 *
 * @Description: 流水生成工具
 *
 * @author GERRARD
 *
 * @date 2015年1月27日下午3:27:09
 * 
 */
public class SerialNumber {

    /**
     * 生成业务流水号
     * 系统标识（sysFlg.length位）+时间（14位，年月日时分秒）+系统流水（randomCount位）
     * 
     * @param sysFlg		系统标识
     * @param randomCount	随机数位数
     * @return
     */
    public static synchronized String createSerial()
    {

        safeSleep(1);
        SimpleDateFormat sdft = new SimpleDateFormat("yyyyMMddhhmmss");
		Date nowdate = new Date();
		String str = sdft.format(nowdate);
		 DecimalFormat df=new DecimalFormat("0000");
		 
		 int serial = SerialNumberHolder.getInstance().getCounter_integer().incrementAndGet();
	     String str2=df.format(serial);
	     System.out.println("返回的流水为:"+ str+str2);
	     return str+str2;
     
    }
    
    public static void safeSleep(long millis)
    {

        try
        {
            Thread.sleep(millis);

        } catch (InterruptedException e)
        {
        	e.printStackTrace();
        }
    }

}
