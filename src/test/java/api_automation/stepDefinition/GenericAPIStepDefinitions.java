package api_automation.stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import api_automation.RequestBuilder.ExpenseRequestBuilder;
import api_automation.RequestBuilder.TokenRequestBuilder;
import api_automation.utils.FunctionLibrary;
import com.jayway.jsonpath.JsonPath;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GenericAPIStepDefinitions extends FunctionLibrary {
	private String flag;
	private String token;
	private static Response getResponse;
	private String expenseRequest;
	private String id;
	Scenario scenario;
	
	@Before
	public void keepScenario(Scenario scenario) {
		this.scenario=scenario;
	}
	
	
	@Given("I get token for {string} account when flag is {string}")
	public void i_get_token_for_account_when_flag_is(String account, String flag) {
	    this.flag=flag;
	    if(flag.equalsIgnoreCase("true")) {
	    	String userName="";
			String password="";
			// read username and password from properties file
			switch (account) {
				case "company":
					userName = property.getProperty("CompanyUserID");
					password = property.getProperty("CompanyPassword");

					break;
				case "employee":
					userName = property.getProperty("EmployeeUserID");
					password = property.getProperty("EmployeePassword");

					break;
				case "self-employed":
					userName = property.getProperty("SelfEmployedUserID");
					password = property.getProperty("SelfEmployedPassword");

					break;
			}
			// Write username and password to report
			scenario.write("Username:::: "+userName);
			scenario.write("Password:::: "+password);
			// create token request body;
			TokenRequestBuilder payload=new TokenRequestBuilder();
				payload.setUsernameOrEmailAddress(userName);
				payload.setPassword(password);
			// convert payload obj to String	
			   String requestData=convertObjectToString(payload);
			    //Attache request data to report
			   scenario.embed(requestData.getBytes(), "application/json");
			   //scenario.write("Token Request Data::::: "+requestData);
			 //submit request to token API  
			   Response response = submitTokenAPIRequest(requestData);
	           response.prettyPrint();
//	           scenario.write("Token Response:::: "+response.asPrettyString());
	           scenario.embed(response.asPrettyString().getBytes(), "application/json");
	           // retrieve token from response
	            token=JsonPath.read(response.asString(), "$.result.accessToken");
	           scenario.write("TOKEN:::::"+token); 
	    	
	    }
	    
	}

	@When("I submit GET request to {string}")
	public void i_submit_GET_request_to(String url) {
		if(flag.equalsIgnoreCase("true")) {
			 if(url.equalsIgnoreCase("expensesURL_ByID")) {
			    getResponse=given()
							 .header("Authorization", "Bearer "+token)
						  .when().
						 	  get(property.getProperty("expensesURL")+"/"+id);
			    scenario.embed(getResponse.asPrettyString().getBytes(), "application/json");
			 }else {

				 getResponse=submitGETRequest(token,url);
				getResponse.prettyPrint();
				scenario.embed(getResponse.asPrettyString().getBytes(), "application/json");
		 }
		}
	}

	@When("I validate if the status code is {int}")
	public void i_validate_if_the_status_code_is(int statusCode) {
		if(flag.equalsIgnoreCase("true")) {
			int actualStatusCode=getResponse.getStatusCode();
			scenario.write("Actual Status code:::"+actualStatusCode+"  Expected Status code::: "+statusCode);
		    assertEquals(statusCode, actualStatusCode);
		    
		}
	}

	@Then("I validate if element {string} exist in response")
	public void i_validate_if_element_exist_in_response(String element) {
		assertTrue(getResponse.asString().contains(element));		
		scenario.write("Element:::: "+element+ "  Value:::: "+JsonPath.read(getResponse.asString(), "$.result."+element));
		
	}
	
	@Then("User creates request body with type {string}  and otherExpenseNameId {int} for expense API")
	public void user_creates_request_body_with_type_and_otherExpenseNameId_for_expense_API(String expenseType, int otherExpenseNameId) {
		if(flag.equalsIgnoreCase("true")) { 
			
			ExpenseRequestBuilder request=new ExpenseRequestBuilder();
				request.setName("Vocation expense");
				request.setAmount(3000);
				request.setExpenseDateTime("01/28/2021 00:00:00");
				request.setExpenseType(expenseType);				
				if(expenseType.equalsIgnoreCase("Other")) {
					request.setOtherExpenseNameId(otherExpenseNameId);
				}else if(expenseType.equalsIgnoreCase("Gift")) {
					request.setGiftRecipient("ABC123");
				}
				
				

				
			 expenseRequest=convertObjectToString(request);
			 scenario.embed(expenseRequest.getBytes(), "application/json");
		} 
		
	}

	@Then("User submits POST request to {string} api")
	public void user_submits_POST_request_to_api(String url) {
		if(flag.equalsIgnoreCase("true")) {	
				getResponse =given()
				 				.header("Authorization", "Bearer "+token)
					 			.body(expenseRequest)
					 			.contentType(ContentType.JSON)
				 		    .when()
				 		    	.post(property.getProperty(url));
				getResponse.prettyPrint();
				scenario.embed(getResponse.asPrettyString().getBytes(), "application/json");
		}
	    
	}
	
	
	@Then("I validate if value in {string} equals to {string}")
	public void i_validate_if_value_in_equals_to(String jsonPath, String expectedValue) {
	   
		String actulaValue=JsonPath.read(getResponse.asString(), jsonPath).toString();
		scenario.write("Expected value:::"+expectedValue+"    Actual value:::"+actulaValue);
		assertEquals(expectedValue, actulaValue);
		
	}
	
	
	@Then("I retrieves {string} from response")
	public void i_retrieves_from_response(String element) {
	     id=JsonPath.read(getResponse.asString(), "$.result."+element).toString();
	     scenario.write("Response ID::: "+id);
	}
	
	
	@Then("I submits DELETE expense request")
	public void i_submits_DELETE_expense_request() {
				getResponse=given()
	    					.header("Authorization","Bearer "+token)
	    				 .when()
	    				    .post(property.getProperty("deleteExpenseURL")+id);
				
				scenario.embed(getResponse.asPrettyString().getBytes(), "application/json");
    						    		   
	}
	
}
