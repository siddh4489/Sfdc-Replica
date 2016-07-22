package com.espl.zero.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages={"com.espl.zero"}, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = { "com.espl.zero.web.*" }))
@PropertySource(value = {"classpath:config/application.properties"})
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public JavaMailSenderImpl javaMailSenderImpl(){
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(env.getProperty("smtp.host"));
		mailSenderImpl.setPort(env.getProperty("smtp.port",Integer.class));
		mailSenderImpl.setHost(env.getProperty("smtp.protocol"));
		mailSenderImpl.setHost(env.getProperty("smtp.username"));
		mailSenderImpl.setHost(env.getProperty("smtp.password"));
		mailSenderImpl.setJavaMailProperties(javaMailProperties);
		
		return mailSenderImpl;
	}
	
	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager();
	}
	
	
}
