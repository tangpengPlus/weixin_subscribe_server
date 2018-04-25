package com.gency.subscribe.core.util.base;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * 
 * 标题:LazyService/com.lazyservice.util/LazyServiceNumberUtil.java 
 * @author GENCY
 * 创建时间:2015年11月20日下午2:26:27
 * 说明:系统编码处理
 * 备注:
 *
 */
public class LazyServiceNumberUtil {
	
	/**
	 * 产生随机的四位整形数字
	 * 作者:GENCY
	 * 创建时间:2015年11月20日 下午2:27:56
	 * 返回: String
	 * 所属类:LazyServiceNumberUtil
	 * @return
	 * TODO
	 */
	public static String productNumber(){
		Random ran=new Random();
		int r=0;
		m1:while(true){
		    int n=ran.nextInt(10000);
		    r=n;
		    int[] bs=new int[5];
		    for(int i=0;i<bs.length;i++){
		        bs[i]=n%10;
		        n/=10;
		    }
		    Arrays.sort(bs);
		    for(int i=1;i<bs.length;i++){
		        if(bs[i-1]==bs[i]){
		            continue m1;
		        }
		    }
		    break;
		}
		String checknumber=String.valueOf(r);
		return checknumber;
	}
	/**
	 * 生成随机不重复的名称
	 * 作者:GENCY
	 * 创建时间:2015年11月20日 下午2:30:16
	 * 返回: String
	 * 所属类:LazyServiceNumberUtil
	 * @param fileName
	 * @return
	 * @throws Exception
	 * TODO
	 */
	  public static String productUUidName(String fileName)throws Exception{
	    	//获取文件的后缀名
	    	String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
	        UUID uuid = UUID.randomUUID();
	        String fileNames=uuid+"."+fileType;
	        return fileNames;
	    	
	    }
	  /**
	   * 生成随机长度的整形数字
	   * 作者:GENCY
	   * 创建时间:2015年11月20日 下午2:32:57
	   * 返回: String
	   * 所属类:LazyServiceNumberUtil
	   * @param lenth
	   * @return
	   * TODO
	   */
	  public static String randNum(int lenth){
			double d=new Random().nextDouble();
			String s=String.valueOf(d);
			int len=s.length()>2+lenth?lenth:s.length();
			return s.substring(2,2+len);
		}
	  /**
	   * 生成随机网页名称
	   * 作者:GENCY
	   * 创建时间:2015年11月20日 下午2:50:02
	   * 返回: String
	   * 所属类:LazyServiceNumberUtil
	   * @return
	   * @throws Exception
	   * TODO
	   */
	  public static String productUUidName()throws Exception{
		 	//获取文件的后缀名
		     UUID uuid = UUID.randomUUID();
		     String fileNames=uuid+".html";
		     return fileNames;
		 	
		 }
}
