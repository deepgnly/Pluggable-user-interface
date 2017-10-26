package com.londonTab.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import com.londonTab.util.LondonTabContributionUtil;

@SpringBootApplication
@EnableDiscoveryClient
public class LondonTabContributionApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("spring.application.name", LondonTabContributionUtil.SERVICE_NAME);
		ConfigurableApplicationContext context = SpringApplication.run(LondonTabContributionApp.class, args);

		context.getBean(Init.class).afterPropertiesSet();
	}
	
	
	

}
