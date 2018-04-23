package com.gency.subscribe.web.configuration.mybatis;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
/**
 * 
 * 作者:唐鹏
 * 描述: mybatis配置
 * 版本: version 1.0.0
 * 时间: 2017年7月13日 下午5:04:09
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer{
	
	 private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);
	@Resource(name = "dataSource")
    DataSource dataSource;
	
	  @Bean(name = "sqlSessionFactory")
	    public SqlSessionFactory sqlSessionFactoryBean() {

	        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	        bean.setDataSource(dataSource);
	        //分页插件,插件无非是设置mybatis的拦截器
	        PageInterceptor pageHelper = new PageInterceptor();
	        Properties properties = new Properties();
	        properties.setProperty("reasonable", "true");
	        properties.setProperty("supportMethodsArguments", "true");
	        properties.setProperty("returnPageInfo", "check");
	        properties.setProperty("params", "count=countSql");
	        pageHelper.setProperties(properties);

	        //添加插件
	        bean.setPlugins(new Interceptor[]{pageHelper});

	        //添加XML目录
	        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        try {
	            //设置xml扫描路径
	            bean.setMapperLocations(resolver.getResources("classpath:mapper/*/*.xml"));
	            return bean.getObject();
	        } catch (Exception e) {
	        	 logger.error("mybatis连接工厂配置初始化失败",e);
	            throw new RuntimeException("mybatis连接工厂配置初始化失败",e);
	        }
	       
	    }

	    /**
	     * 用于实际查询的sql工具,传统dao开发形式可以使用这个,基于mapper代理则不需要注入
	     * @param sqlSessionFactory
	     * @return
	     */
	    @Bean(name = "sqlSessionTemplate")
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }
	    /**
	     * 事务管理,具体使用在service层加入@Transactional注解
	     */
	    @Bean(name = "transactionManager")
	    @Override
	    public PlatformTransactionManager annotationDrivenTransactionManager() {
	        return new DataSourceTransactionManager(dataSource);
	    }
}
