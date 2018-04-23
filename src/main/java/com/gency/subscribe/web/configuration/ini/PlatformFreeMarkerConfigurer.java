package com.gency.subscribe.web.configuration.ini;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.Configuration;
@Component
public class PlatformFreeMarkerConfigurer implements  InitializingBean {
	
	@Autowired
	private Configuration configuration;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		configuration.setSharedVariable("shiro", new ShiroTags());
	}

}
