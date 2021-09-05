package api_automation.stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import api_automation.RequestBuilder.TokenRequestBuilder;
import api_automation.utils.FunctionLibrary;
import com.jayway.jsonpath.JsonPath;


import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetExpenseAPI extends FunctionLibrary {
	private String token;
	private Response getResponse;
	

	@Given("User generate token with {string} account")
	public void user_generate_token_with_account(String account) {
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
		   
		  Response response =given()
				              .body(requestData)
				              .contentType(ContentType.JSON)
				              .relaxedHTTPSValidation()
					         .when()
					           .post("https://34.235.0.4/api/tokenauth/authenticate");
           response.prettyPrint();
           
           // retrieve token from response
             token=JsonPath.read(response.asString(), "$.result.accessToken");
           
		
			
	   
	}

	@When("User submits GET request to GetExpense API")
	public void user_submits_GET_request_to_GetExpense_API() {
		 getResponse=given()
				  				.header("Authorization", "Bearer "+token)
				  			.when().
				  			     get("http://34.235.0.4/api/expenses");
		getResponse.prettyPrint();
		
		    
	}

	@When("User validates status code is {int}")
	public void user_validates_status_code_is(int statusCode) {
		// retrieve status code
		int statCode=getResponse.getStatusCode();
		System.out.println(statCode);
		assertEquals(statCode, statusCode);
		// validate status code
		getResponse.then().statusCode(statusCode);
	}

	
	@Then("User validates if expenses are present")
	public void user_validates_if_expenses_are_present() {
		List<String>expenseList=JsonPath.read(getResponse.asString(),"$.result[*]");
		System.out.println("Expense count: "+expenseList.size());
		assertTrue(expenseList.size()>0);
	    
	}

}
