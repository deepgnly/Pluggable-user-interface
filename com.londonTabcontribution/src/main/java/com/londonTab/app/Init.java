package com.londonTab.app;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.londonTab.util.LondonTabContributionUtil;
import com.util.transferObjects.Contribution;

@Component
@ComponentScan
public class Init {

	@Autowired
	private DiscoveryClient discoveryClient;


	public void afterPropertiesSet() {
		Contribution contributionObj=new Contribution();
		// populates the movie cache upon initialization...
		contributionObj.setComponentId(LondonTabContributionUtil.COMPONENT_ID);
		contributionObj.setServiceName(LondonTabContributionUtil.SERVICE_NAME);
		contributionObj.setComponentOrder(LondonTabContributionUtil.COMPONENT_ORDER);
		contributionObj.setTabName(LondonTabContributionUtil.COMPONENT_TAB_NAME);
		contributionObj.setTabResource(LondonTabContributionUtil.COMPONENT_TAB_RESOURCE_FILE_LOCATION);

		RestTemplate rt = new RestTemplate();
		rt.getMessageConverters().add(new StringHttpMessageConverter());

		String resourceUrl = this.getServiceUrl(discoveryClient, LondonTabContributionUtil.PARENT_SERVICE_NAME)
				+ LondonTabContributionUtil.PARENT_COMPONENT_REGISTER_API;

		try {
			rt.postForObject(resourceUrl, contributionObj, Contribution.class, String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public String getServiceUrl(DiscoveryClient discoveryClient, String serviceName) {

		List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
		ServiceInstance serviceInstance = instances.get(0);
		String baseUrl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
		return baseUrl;
	}

}
