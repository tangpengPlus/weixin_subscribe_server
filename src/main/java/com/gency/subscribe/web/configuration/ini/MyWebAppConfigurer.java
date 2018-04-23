package com.gency.subscribe.web.configuration.ini;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gency.subscribe.web.interceptor.system.PaginationInterceptor;
import com.gency.subscribe.web.interceptor.system.SystemLogRecordInterceptor;
/**
 * 
 * 作者:唐鹏
 * 描述: Spring Mvc 基础配置
 * 版本: version 1.0.0
 * 时间: 2017年8月29日 下午2:31:00
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
	private static final Log log=LogFactory.getLog(MyWebAppConfigurer.class);
		
	  @Bean
	  public SystemLogRecordInterceptor systemLogRecordInterceptor() {
	    return new SystemLogRecordInterceptor();
	  }
	
		/**
		 * 拦截器配置
		 */
	 	@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	 		log.info(">>>>>>>>>>>>>>>>>>>>>>>初始化系统拦截器<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	        //分页处理拦截器
	        registry.addInterceptor(new PaginationInterceptor())
	        .addPathPatterns("/**")
	        .excludePathPatterns("/system/admin/login")
	        .excludePathPatterns("/system/admin/verificationcode")
	        .excludePathPatterns("/system/admin/loginout")
	        .excludePathPatterns("/css/**").excludePathPatterns("/js/**")
	        .excludePathPatterns("/img/**").excludePathPatterns("/font/**")
	        .excludePathPatterns("/fonts/**").excludePathPatterns("/assets/**");
	        //日志处理拦截器
	        registry.addInterceptor(systemLogRecordInterceptor())
	        .addPathPatterns("/**")
	        .excludePathPatterns("/system/admin/login")
	        .excludePathPatterns("/system/admin/verificationcode")
	        .excludePathPatterns("/system/admin/loginout")
	        .excludePathPatterns("/css/**").excludePathPatterns("/js/**")
	        .excludePathPatterns("/img/**").excludePathPatterns("/font/**")
	        .excludePathPatterns("/fonts/**").excludePathPatterns("/assets/**");
	        super.addInterceptors(registry);
	    }
}
