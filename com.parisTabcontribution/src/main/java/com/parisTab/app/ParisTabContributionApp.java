package com.parisTab.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import com.parisTab.util.ParisTabContributionUtil;

@SpringBootApplication
@EnableDiscoveryClient
public class ParisTabContributionApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("spring.application.name", ParisTabContributionUtil.SERVICE_NAME);
		ConfigurableApplicationContext context = SpringApplication.run(ParisTabContributionApp.class, args);

		context.getBean(Init.class).afterPropertiesSet();
	}
	
	
	

}
