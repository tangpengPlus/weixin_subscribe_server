package com.gency.subscribe.web.configuration.druid;

import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
/**
 * 
 * 作者:唐鹏
 * 描述: 阿里数据源配置项读取
 * 版本: version 1.0.0
 * 时间: 2017年7月13日 下午4:23:08
 */
@Configuration
public class DruidDBConfig {
	 private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);  
	 
	 	@Value("${spring.datasource.url}")  
	    private String dbUrl;  
	      
	    @Value("${spring.datasource.username}")  
	    private String username;  
	      
	    @Value("${spring.datasource.password}")  
	    private String password;  
	      
	    @Value("${spring.datasource.driver-class-name}")  
	    private String driverClassName;  
	    /**
	     * druid初始化
	     * @return
	     * @throws SQLException
	     */
	    @Primary //默认数据源
	    @Bean(name = "dataSource",destroyMethod = "close")
	    public DruidDataSource Construction() throws SQLException {
	        DruidDataSource dataSource = new DruidDataSource();
	        dataSource.setUrl(dbUrl);
	        dataSource.setUsername(username);
	        dataSource.setPassword(password);
	        dataSource.setDriverClassName(driverClassName);
	        //配置最大连接
	        dataSource.setMaxActive(100);
	        //配置初始连接
	        dataSource.setInitialSize(1);
	        //配置最小连接
	        dataSource.setMinIdle(1);
	        //连接等待超时时间
	        dataSource.setMaxWait(60000);
	        //间隔多久进行检测,关闭空闲连接
	        dataSource.setTimeBetweenEvictionRunsMillis(60000);
	        //一个连接最小生存时间
	        dataSource.setMinEvictableIdleTimeMillis(300000);
	        //用来检测是否有效的sql
	        dataSource.setValidationQuery("select 'x'");
	        dataSource.setTestWhileIdle(true);
	        dataSource.setTestOnBorrow(false);
	        dataSource.setTestOnReturn(false);
	        //打开PSCache,并指定每个连接的PSCache大小
	        dataSource.setPoolPreparedStatements(true);
	        dataSource.setMaxOpenPreparedStatements(20);
	        //配置sql监控的filter
	        dataSource.setFilters("stat,wall,log4j");
	        try {
	            dataSource.init();
	        } catch (SQLException e) {
	            throw new RuntimeException("数据源初始化异常");
	        }
	        logger.info("数据源初始化成功:"+new Date());
	        return dataSource;
	    }
}
