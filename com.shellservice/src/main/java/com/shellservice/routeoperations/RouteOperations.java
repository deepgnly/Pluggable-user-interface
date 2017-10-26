package com.shellservice.routeoperations;

import java.io.IOException;
import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RouteOperations {

	private DiscoveryClient discoveryClient;

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

	public boolean isAppContextRegistered(String context) {
		return true;
	}

	public boolean isModuleContextRegistered(String appContext) {
		return true;
	}

	public ResponseEntity<String> getResource(DiscoveryClient discoveryClient, String serviceName,String resourcePathOfService) throws RestClientException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		String resourceUrl=this.getServiceUrl(discoveryClient, serviceName)+resourcePathOfService;
		try {
			response = restTemplate.exchange(resourceUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response;
	}
	
	public String getServiceUrl(DiscoveryClient discoveryClient,String serviceName){
		
		List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
		ServiceInstance serviceInstance = instances.get(0);
		String baseUrl = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() ;
		return baseUrl;
	}
}
