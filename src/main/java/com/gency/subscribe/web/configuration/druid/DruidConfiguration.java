package com.gency.subscribe.web.configuration.druid;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * 作者:唐鹏
 * 描述: 开启监控统计
 * 版本: version 1.0.0
 * 时间: 2017年7月13日 下午3:10:46
 */
@Configuration
public class DruidConfiguration {
	private static final Logger log = LoggerFactory.getLogger(DruidConfiguration.class);

	  @Bean
	  public ServletRegistrationBean druidServlet() {
	    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>初始化阿里巴巴数据源servlet<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
	    servletRegistrationBean.setServlet(new StatViewServlet());
	    servletRegistrationBean.addUrlMappings("/druid/*");
	    Map<String, String> initParameters = new HashMap<String, String>();
	    initParameters.put("loginUsername", "admin");// 用户名
	    initParameters.put("loginPassword", "admin");// 密码
	    initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
	    initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
	    //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
	    servletRegistrationBean.setInitParameters(initParameters);
	    return servletRegistrationBean;
	  }

	  @Bean
	  public FilterRegistrationBean filterRegistrationBean() {
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    filterRegistrationBean.setFilter(new WebStatFilter());
	    filterRegistrationBean.addUrlPatterns("/*");
	    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	    return filterRegistrationBean;
	  }
}
