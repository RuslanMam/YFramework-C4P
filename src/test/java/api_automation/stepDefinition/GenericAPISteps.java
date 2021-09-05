package api_automation.stepDefinition;


import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.util.List;

import api_automation.RequestBuilder.TokenRequestBuilder;
import api_automation.utils.FunctionLibrary;
import com.jayway.jsonpath.JsonPath;


import io.cucumber.java.en.*;

import io.restassured.response.Response;

public class GenericAPISteps extends FunctionLibrary {
	private String token;
	private Response getResponse;
	
	
	
	@Given("User get token for {string} account")
	public void user_get_token_for_account(String account) {
		String userName="";
		String password="";
		// read username and password from properties file
		if(account.equals("company")) {
			userName=property.getProperty("CompanyUserID");
			password=property.getProperty("CompanyPassword");
			
		}else if(account.equals("employee")) {
			userName=property.getProperty("EmployeeUserID");
			password=property.getProperty("EmployeePassword");
			
		}else if(account.equals("self-employed")) {
			userName=property.getProperty("SelfEmployedUserID");
			password=property.getProperty("SelfEmployedPassword");
			
		}
		
		// create token request body;
		TokenRequestBuilder payload=new TokenRequestBuilder();
			payload.setUsernameOrEmailAddress(userName);
			payload.setPassword(password);
		// convert payload obj to String	
		   String requestData=convertObjectToString(payload);
		
		 //submit request to token API  
		   Response response = submitTokenAPIRequest(requestData);
           response.prettyPrint();
           
           // retrieve token from response
             token=JsonPath.read(response.asString(), "$.result.accessToken");
  
	   
	}

	@When("User submits GET request to {string}")
	public void user_submits_GET_request_to(String url) {
		getResponse=submitGETRequest(token,url);
		getResponse.prettyPrint();
	}

	
	@When("User validates if the status code is {int}")
	public void user_validates_if_the_status_code_is(int statusCode) {
	    int actualStatusCode=getResponse.getStatusCode();
	    System.out.println(actualStatusCode);
	    assertEquals(statusCode, actualStatusCode);
	}
	
	
	@Then("User validates if following elements exist in response")
	public void user_validates_if_following_elements_exist_in_response(List<String> elementList) {
	    System.out.println("************************");
	    System.out.println(elementList);
	    for (String element : elementList) {
			assertTrue(getResponse.asString().contains(element));
			 System.out.println(element +" was validated");
   	

		}
	}
	
	
	@Then("User validates if {string} exist in response")
	public void user_validates_if_exist_in_response(String element) {
		assertTrue(getResponse.asString().contains(element));
		 System.out.println(element +" was validated");
	}

}
