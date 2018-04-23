package com.gency.subscribe.core.util.base;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * 普通工具
 * 标题:LazyService/com.lazyservice.util/LazyServiceEncrptUtil.java 
 * @author GENCY
 * 创建时间:2015年11月20日下午2:34:16
 * 说明:
 * 备注:
 *
 */
public class BaseUtil {

	public static final String PHONE_REGEX="1[345689][0-9]{9}";
	public static PrintWriter out;
	/**
	 * 验证是否为手机号码
	 * 作者:GENCY
	 * 创建时间:2015年11月20日 下午2:35:37
	 * 返回: boolean
	 * 所属类:LazyServiceEncrptUtil
	 * @param phoneNum
	 * @return
	 * TODO
	 */
	public static boolean checkPhone(String phoneNum) {
		return Pattern.matches(PHONE_REGEX, phoneNum);
	}
	/**
	 * 验证是否为Email
	 * 作者:GENCY
	 * 创建时间:2015年11月20日 下午2:36:10
	 * 返回: boolean
	 * 所属类:LazyServiceEncrptUtil
	 * @param email
	 * @return
	 * TODO
	 */
	public static boolean checkEmail(String email) {
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
		return Pattern.matches(regex, email);
	}
	/**
	 * 将long类型数据转为int类型
	 * 作者:GENCY
	 * 创建时间:2015年11月20日 下午3:28:41
	 * 返回: Integer
	 * 所属类:LazyServiceEncrptUtil
	 * @param parm
	 * @return
	 * TODO
	 */
	public static Integer changeLongToInt(Long parm){
		String s=parm.toString();
		return Integer.valueOf(s);
	}
	/**
	 * 获取客户端IP
	 * 作者:GENCY
	 * 创建时间:2015年11月25日 下午4:56:26
	 * 返回: String
	 * 所属类:LazyServiceEncrptUtil
	 * @param request
	 * @return
	 * @throws Exception
	 * TODO
	 */
	public static String getLoginIP(HttpServletRequest request)throws Exception{
		
		 String ip = request.getHeader("x-forwarded-for");
	     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("Proxy-Client-IP");
	     }
	     if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("WL-Proxy-Client-IP");
	     }
	     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getRemoteAddr();
	     }
	      return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	/**
	 * 去掉字符串的大写
	 * 作者:GENCY
	 * 创建时间:2015年11月26日 上午10:05:18
	 * 返回: String
	 * 所属类:LazyServiceEncrptUtil
	 * @param inStr
	 * @return
	 * TODO
	 */
	public static String changeString(String inStr){
		  StringBuffer outStr = new StringBuffer();
		  if(inStr!=null){
			  for (int i = 0; i < inStr.length(); i++) {
		            char c = inStr.charAt(i);
		            if(65<=c&&c<=91){
		                outStr.append(Character.toLowerCase(c));
		            }else{
		                outStr.append(inStr.charAt(i));
		            }
		        } 
		  }
		return outStr.toString();
	}
	/**
	 * 获取错误消息
	 * 作者:GENCY
	 * 创建时间:2015年11月30日 上午9:33:52
	 * 返回: String
	 * 所属类:LazyServiceEncrptUtil
	 * @param errors
	 * @return
	 * @throws Exception
	 * TODO
	 */
	public static String getErrorInfo(Errors errors)throws Exception{
		StringBuffer sb=new StringBuffer();
		List<FieldError> list=errors.getFieldErrors();
		for (int i = 0; i < list.size(); i++) {
			if(i+1==list.size()){
				sb.append(list.get(i).getDefaultMessage());
			}else{
				sb.append(list.get(i).getDefaultMessage()+",");
			}		
		}
		return sb.toString();	
	}
	/**
	 * 判断两个double类型的大小
	 * 作者:GENCY
	 * 创建时间:2015年12月1日 下午4:38:22
	 * 返回: boolean
	 * 所属类:LazyServiceEncrptUtil
	 * @param parm1
	 * @param parm2
	 * @return
	 * @throws Exception
	 * TODO
	 */
	public static boolean juedgeDoubleNumber(double parm1,double parm2)throws Exception{
		BigDecimal data1 = new BigDecimal(parm1);  
	    BigDecimal data2 = new BigDecimal(parm2);  
	    if(data1.compareTo(data2)<0){
	    	return false;
	    }
	    return true;
	}
	
	/**
	 * 截取字符串
	 * 作者:GENCY
	 * 创建时间:2015年12月8日 上午11:18:09
	 * 返回: String
	 * 所属类:LazyServiceEncrptUtil
	 * @param str
	 * @return
	 * @throws Exception
	 * TODO
	 */
	public static String getStr(String str)throws Exception{
		return str.substring(str.length()-2, str.length());
	}
	/**
	 * 判断是否为金额格式
	 * 作者:GENCY
	 * 创建时间:2015年12月12日 下午12:32:22
	 * 返回: boolean
	 * 所属类:LazyServiceEncrptUtil
	 * @param str
	 * @return
	 * TODO
	 */
	public static boolean isNumber(String str) 
    { 
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后一位的数字的正则表达式
        java.util.regex.Matcher match=pattern.matcher(str); 
        if(match.matches()==false) 
        { 
           return false; 
        } 
        else 
        { 
           return true; 
        } 
    }
	/**
	 * 获取星期
	 * 作者:GENCY
	 * 创建时间:2015年12月14日 下午2:05:58
	 * 返回: String
	 * 所属类:LazyServiceEncrptUtil
	 * @param index
	 * @return
	 * @throws Exception
	 * TODO
	 */
	public static String getWeek(Integer index)throws Exception{
		String weekInfo="";
		switch (index) {
		case 1:
			weekInfo= "星期天";
			break;
		case 2:
			weekInfo="星期一";
			break;
		case 3:
			weekInfo="星期二";
			break;
		case 4:
			weekInfo="星期三";
			break;
		case 5:
			weekInfo="星期四";
			break;
		case 6:
			weekInfo="星期五";
			break;
		case 7:
			weekInfo="星期六";
			break;
		default:
			break;
		}
		return weekInfo;
	}
	/**
	 * 根据状态生成请求名
	 * 作者:GENCY
	 * 创建时间:2015年12月14日 下午7:48:36
	 * 返回: String
	 * 所属类:LazyServiceEncrptUtil
	 * @param type
	 * @return
	 * @throws Exception
	 * TODO
	 */
	public static String getRequestType(Integer type)throws Exception{
		String requestTypeName="";
		switch (type) {
		case 1:
			requestTypeName="按小时分组";
			break;
		case 2:
			requestTypeName="按天分组";
			break;
		case 3:
			requestTypeName="按周分组";
			break;
		case 4:
			requestTypeName="按月分组";
			break;
		case 5:
			requestTypeName="按年分组";
			break;
		default:
			break;
		}
		return requestTypeName;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取http 请求参数
	 * 版本: version 1.0.0
	 * 时间: 2017年8月29日 下午2:25:18
	 * @param request
	 * @return
	 * String
	 */
	public static String getHttpRequestParm(HttpServletRequest request){
		StringBuffer sb=new StringBuffer();
		 Enumeration paramNames = request.getParameterNames();  
		 while (paramNames.hasMoreElements()) {  
		      String paramName = (String) paramNames.nextElement();  
		  
		      String[] paramValues = request.getParameterValues(paramName);  
		      if (paramValues.length == 1) {  
		        String paramValue = paramValues[0];  
		        if (paramValue.length() != 0) { 
		        	if(!paramName.equals("curPage")){
		        		sb.append(paramName + "=" + paramValue+"&");
		        	}
		        }  
		      }  
		    }  
		 return sb.toString();
	}
}
