package com.gency.subscribe.web.configuration.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.gency.subscribe.web.filter.shiro.AuthenticationFilter;
import com.gency.subscribe.web.filter.shiro.KickoutSessionControlFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
/**
 * 
 * 作者:唐鹏 描述: shiro(权限框架配置) 版本: version 1.0.0 时间: 2017年7月26日 下午3:50:54
 */
@Configuration
@EnableCaching
public class ShiroConfig {

	private static final Log log=LogFactory.getLog(ShiroConfig.class);
	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.password}")
	private String password;
	
	/**
	 *  
	 * 作者:唐鹏
	 * 描述:保证实现了Shiro内部lifecycle函数的bean执行
	 * 版本: version 1.0.0
	 * 时间: 2017年8月29日 上午11:41:49
	 * @return
	 * LifecycleBeanPostProcessor
	 */
	@Bean(name = "lifecycleBeanPostProcessor")  
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {  
        return new LifecycleBeanPostProcessor();  
    }  
	/**
	 * 
	 * 作者:唐鹏 描述: ShiroFilterFactoryBean 处理拦截资源文件问题。 Filter Chain定义说明
	 * 1、一个URL可以配置多个Filter 使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 版本: version 1.0.0 时间:
	 * 2017年7月26日 下午4:08:10
	 * 
	 * @param securityManager
	 * @return ShiroFilterFactoryBean
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		log.info("初始化shiro过滤器");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/system/admin/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		// 限制同一帐号同时在线的个数。
		filtersMap.put("kickout", kickoutSessionControlFilter());
		// 扩展shiro令牌
		filtersMap.put("authc", authenticationFilter());

		shiroFilterFactoryBean.setFilters(filtersMap);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 静态资源不用拦截
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/font/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/assets/**", "anon");
		// 验证码配置
		filterChainDefinitionMap.put("/system/admin/verificationcode", "anon");
		//释放数据源监控
		filterChainDefinitionMap.put("/druid", "anon");
		// 拦截路径配置
		filterChainDefinitionMap.put("/**", "authc,kickout");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	
	/*
     * ehcache 主要的管理器
     */
    @Bean
    public SpringCacheManagerWrapper  springCacheManagerWrapper(){
    	SpringCacheManagerWrapper cacheManagerWrapper =new SpringCacheManagerWrapper();
        return cacheManagerWrapper;
    }

    /*
     * ehcache 主要的管理器
     */
    @Bean(name = "cacheManager")
    public CacheManager  ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager (bean.getObject ());
    }

    /*
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource ("ehcache-shiro.xml"));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
    }
    @Bean
    public EhCacheManager getShiroEhcacheManager(EhCacheManagerFactoryBean bean){
    	
    	EhCacheManager cacheCacheManager =new EhCacheManager();
    	cacheCacheManager.setCacheManager(bean.getObject());
    	return cacheCacheManager;
    }
    
    
	/**
	 * 
	 * 作者:唐鹏 描述: shiro核心管理类配置 版本: version 1.0.0 时间: 2017年7月26日 下午4:07:48
	 * 
	 * @return SecurityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		log.info("初始化shiro安全管理容器");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(adminRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(getShiroEhcacheManager(ehCacheManagerFactoryBean()));
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
	/**
	 * 
	 * 作者:唐鹏 描述: 身份认证数据源填充，密码验证 版本: version 1.0.0 时间: 2017年7月26日 下午3:51:48
	 * 
	 * @return AdminRealm
	 */
	@Bean
	public AdminRealm adminRealm() {
		AdminRealm adminRealm = new AdminRealm();
	/*	adminRealm.setCachingEnabled(true);
		adminRealm.setAuthenticationCachingEnabled(true);
		adminRealm.setAuthenticationCacheName("authorizationCache");*/
		// 设置密码校验器
		adminRealm.setCredentialsMatcher(credentialsMatcher());
		return adminRealm;
	}

	/**
	 * 
	 * 作者:唐鹏 描述:密码校验器 版本: version 1.0.0 时间: 2017年7月31日 下午2:07:20
	 * 
	 * @return PasswordMatcher
	 */
	@Bean
	public CustomCredentialsMatcher credentialsMatcher() {
		CustomCredentialsMatcher credentialsMatcher = new CustomCredentialsMatcher();
		return credentialsMatcher;
	}

	/**
	 * 
	 * 作者:唐鹏 描述: 操作当前服务器会话(session)所有会话记录存储在redis中 版本: version 1.0.0 时间:
	 * 2017年7月26日 下午3:53:15
	 * 
	 * @return RedisSessionDAO
	 */
	@Bean
	public EnterpriseCacheSessionDAO cacheSessionDAO() {
		EnterpriseCacheSessionDAO cacheSessionDAO=new EnterpriseCacheSessionDAO();
		cacheSessionDAO.setCacheManager(getShiroEhcacheManager(ehCacheManagerFactoryBean()));
		cacheSessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
		return cacheSessionDAO;
	}

	/**
	 * 
	 * 作者:唐鹏 描述: 配置会话管理 版本: version 1.0.0 时间: 2017年7月26日 下午3:54:02
	 * 
	 * @return DefaultWebSessionManager
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(cacheSessionDAO());
		/* 设置session过期时间 */
		sessionManager.setGlobalSessionTimeout(43200000); // 12 小时
		return sessionManager;
	}

	/**
	 * 
	 * 作者:唐鹏 描述: 配置Cookie 版本: version 1.0.0 时间: 2017年7月26日 下午3:54:22
	 * 
	 * @return SimpleCookie
	 */
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(2592000);
		return simpleCookie;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizat(){
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager());
		return advisor;
	}
	
	
	/**
	 * 
	 * 作者:唐鹏 描述: 登录操作记住我功能实现 版本: version 1.0.0 时间: 2017年7月26日 下午3:54:40
	 * 
	 * @return CookieRememberMeManager
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}

	/**
	 * 
	 * 作者:唐鹏 描述: 限制同一账号登录同时登录人数控制 版本: version 1.0.0 时间: 2017年7月26日 下午3:55:02
	 * 
	 * @return KickoutSessionControlFilter
	 */
	public KickoutSessionControlFilter kickoutSessionControlFilter() {
		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
		/* 使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的 */
		/* 也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性 */
		kickoutSessionControlFilter.setCacheManager(getShiroEhcacheManager(ehCacheManagerFactoryBean()));
		/* 用于根据会话ID，获取会话进行踢出操作的； */
		kickoutSessionControlFilter.setSessionManager(sessionManager());
		/* 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。 */
		kickoutSessionControlFilter.setKickoutAfter(false);
		/* 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录； */
		kickoutSessionControlFilter.setMaxSession(1);
		/* 被踢出后重定向到的地址； */
		kickoutSessionControlFilter.setKickoutUrl("/kickout");
		return kickoutSessionControlFilter;
	}

	/**
	 * 
	 * 作者:唐鹏 描述: 第一次登录时会经过此过滤器目的为了拓展shiro token 中httpSession 和验证码参数
	 * 同时从新构建session对象防止Session Fixation攻击 版本: version 1.0.0 时间: 2017年7月26日
	 * 下午3:56:40
	 * 
	 * @return AuthenticationFilter
	 */
	public AuthenticationFilter authenticationFilter() {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		/* 设置超时处理页面地址 */
		authenticationFilter.setTime_out_show_url("/system/admin/login");
		return authenticationFilter;
	}
}
