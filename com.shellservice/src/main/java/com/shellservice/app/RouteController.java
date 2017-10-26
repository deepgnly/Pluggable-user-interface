package com.shellservice.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.shellservice.routeoperations.RouteOperations;
import com.util.transferObjects.Contribution;

@RestController
public class RouteController {

	public static final String PLAIN_RESPONSE_TYPE = "text/plain";
	public static final String JSON_RESPONSE_TYPE = "application/json";
	public Map<String, Contribution> contributionMap = new HashMap<String, Contribution>();
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/static/**", method = RequestMethod.GET)
	public ResponseEntity<byte[]> redirectEverythingOtherThanContributed(HttpServletRequest request,
			RouteOperations routeOps) {

		String totalUrl = request.getRequestURI();
		String[] splittedUrl = totalUrl.split("/");
		if (splittedUrl.length > 3) {
			String serviceName = splittedUrl[2];
			String constantUrl = "/" + splittedUrl[1] + "/" + splittedUrl[2];
			String[] resourceUrl = totalUrl.split(constantUrl);
			try {
				ResponseEntity<String> responseFromService = routeOps.getResource(discoveryClient, serviceName,
						resourceUrl[1]);

				return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
						.body(responseFromService.getBody().getBytes());

			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ResponseEntity.badRequest().contentType(MediaType.ALL).body(null);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = PLAIN_RESPONSE_TYPE)
	public String findRoot() {
		return "This is root!!";
	}

	@RequestMapping(value = "/sparta", method = RequestMethod.GET, produces = PLAIN_RESPONSE_TYPE)
	public String sparta() {

		return "This is Sparta!!";
	}

	@RequestMapping(value = "/getAllTabContribution", method = RequestMethod.GET, produces = JSON_RESPONSE_TYPE)
	@ResponseBody
	public Map<String, Contribution> getAllTabContribution() {

		return contributionMap;
	}

	@RequestMapping(value = "/registerContribution", method = RequestMethod.POST, produces = JSON_RESPONSE_TYPE)
	public BodyBuilder registerContribution(@RequestBody Contribution contributionObj) {
		contributionMap.put(contributionObj.getServiceName(), contributionObj);
		System.out.println("RECEIVED");
		return ResponseEntity.ok();
	}

}
