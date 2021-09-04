package com.liawan.config;

import com.liawan.config.thymeleaf.dialect.ThSysDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Thymeleaf配置
 * 
 * @author :
 * @createDate : 2018年12月4日
 * 
 */
@Configuration
public class ThymeleafDialectConfig {
	@Bean
	public ThSysDialect thSysDialect() {
		return new ThSysDialect();
	}
}
