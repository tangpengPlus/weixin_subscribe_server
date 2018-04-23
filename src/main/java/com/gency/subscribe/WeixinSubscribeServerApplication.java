package com.gency.subscribe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.gency.subscribe.dao")
@EnableCaching
public class WeixinSubscribeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeixinSubscribeServerApplication.class, args);
	}
}
